package com.ezen.project.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.project.Repository.PictureBoardImageRepository;
import com.ezen.project.Repository.PictureBoardRepository;
import com.ezen.project.dto.PageRequestDTO;
import com.ezen.project.dto.PageResultDTO;
import com.ezen.project.dto.PictureBoardDTO;
import com.ezen.project.dto.PictureBoardImageDTO;
import com.ezen.project.model.PictureBoard;
import com.ezen.project.model.PictureBoardImage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PictureBoardService {
	@Autowired
	PictureBoardRepository pictureBoardRepository;
	
	@Autowired
	PictureBoardImageRepository pictureBoardImageRepository;
	
	public PictureBoardDTO entitiesToDTO(PictureBoard pictureBoard, List<PictureBoardImage> pictureBoardImages, Double avg, Long reviewCnt){
        PictureBoardDTO pictureBoardDTO = PictureBoardDTO.builder()
                .bno(pictureBoard.getBno())
                .title(pictureBoard.getTitle())
                .regDate(pictureBoard.getRegDate())
                .modDate(pictureBoard.getModDate())
                .build();

        List<PictureBoardImageDTO> pictureBoardImageDTOList = pictureBoardImages.stream().map(pictureBoardImage -> {
            return PictureBoardImageDTO.builder().imgName(pictureBoardImage.getImgName())
                    .path(pictureBoardImage.getPath())
                    .uuid(pictureBoardImage.getUuid())
                    .build();
        }).collect(Collectors.toList());

        pictureBoardDTO.setImageDTOList(pictureBoardImageDTOList);
        pictureBoardDTO.setAvg(avg);
        pictureBoardDTO.setReviewCnt(reviewCnt.intValue());

        return pictureBoardDTO;

    }
	
    public PageResultDTO<PictureBoardDTO, Object[]> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("bno").descending());

        Page<Object[]> result = pictureBoardRepository.getListPage(pageable);

        log.info("==============================================");
        result.getContent().forEach(arr -> {
            log.info(Arrays.toString(arr));
        });


        Function<Object[], PictureBoardDTO> fn = (arr -> entitiesToDTO(
                (PictureBoard)pictureBoardRepository.findById((Long) arr[0]).get() ,
                (List<PictureBoardImage>)(Arrays.asList((PictureBoardImage)pictureBoardImageRepository.findById((Long)arr[1]).get())),
                (Double) arr[2],
                (Long)arr[3])
        );

        return new PageResultDTO<>(result, fn);
    }
    
    public PictureBoardDTO getPictureBoard(Long bno) {
    	List<Object[]> result = pictureBoardRepository.getPictureBoardWithAll(bno);
    	
    	PictureBoard pictureBoard = (PictureBoard)(pictureBoardRepository.findById((Long)result.get(0)[0]).get()) ; // PictureBoard 엔티티는 가장 앞에 존재 - 모든 Row가 동일한 값
    	
    	List<PictureBoardImage> pictureBoardImageList = new ArrayList<>();

        result.forEach(arr -> {
            PictureBoardImage  pictureBoardImage = (PictureBoardImage)(pictureBoardImageRepository.findById((Long)arr[1]).get());
            pictureBoardImageList.add(pictureBoardImage);
        });

        Double avg = (Double) result.get(0)[2];
        Long reviewCnt = (Long) result.get(0)[3];

        return entitiesToDTO(pictureBoard, pictureBoardImageList, avg, reviewCnt);
    	
    }
	
	public Map<String, Object> dtoToEntity(PictureBoardDTO pictureBoardDTO) {
		Map<String, Object> entityMap = new HashMap<>();
		
		PictureBoard pictureBoard = PictureBoard.builder()
									.bno(pictureBoardDTO.getBno())
									.title(pictureBoardDTO.getTitle())
									.build();
		
		entityMap.put("pictureBoard", pictureBoard);
		
		List<PictureBoardImageDTO> imageDTOList = pictureBoardDTO.getImageDTOList();
		
		//PictureBoardImageDTO 처리
		if(imageDTOList != null && imageDTOList.size() > 0) {
			List<PictureBoardImage> pictureBoardImageList = imageDTOList.stream().map(pictureBoardImageDTO ->{
					PictureBoardImage pictureBoardImage = PictureBoardImage.builder()
															.path(pictureBoardImageDTO.getPath())
															.imgName(pictureBoardImageDTO.getImgName())
															.uuid(pictureBoardImageDTO.getUuid())
															.board(pictureBoard)
															.build();
					return pictureBoardImage;
			}).collect(Collectors.toList());
			
			entityMap.put("imgList", pictureBoardImageList);
		}
		return entityMap;
	}
	
	@Transactional
	public Long register(PictureBoardDTO pictureBoardDTO) {
		
		Map<String, Object> entityMap = dtoToEntity(pictureBoardDTO);
		PictureBoard pictureBoard = (PictureBoard) entityMap.get("pictureBoard");
		List<PictureBoardImage> pictureBoardImageList = (List<PictureBoardImage>) entityMap.get("imgList");
		
		pictureBoardRepository.save(pictureBoard);
		
		pictureBoardImageList.forEach(pictureBoardImage -> {
			pictureBoardImageRepository.save(pictureBoardImage);
		});
		
		return pictureBoard.getBno();
	}
}
