//package com.ezen.project.Repository.search;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
//
//import com.ezen.project.model.MainBoard;
//import com.ezen.project.model.QMainBoard;
//import com.ezen.project.model.QMainBoardReply;
//import com.ezen.project.model.QMember;
//import com.querydsl.core.BooleanBuilder;
//import com.querydsl.core.Tuple;
//import com.querydsl.core.types.Order;
//import com.querydsl.core.types.OrderSpecifier;
//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.core.types.dsl.PathBuilder;
//import com.querydsl.jpa.JPQLQuery;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//public class SearchMainBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchMainBoardRepository {
//
//	public SearchMainBoardRepositoryImpl() {
//		super(MainBoard.class);
//	}
//	
//	@Override
//	public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
//
//        log.info("searchPage.............................");
//
//        QMainBoard mainBoard = QMainBoard.mainBoard;
//        QMainBoardReply mainBoardReply = QMainBoardReply.mainBoardReply;
//        QMember member = QMember.member;
//
//        JPQLQuery<MainBoard> jpqlQuery = from(mainBoard);
//        jpqlQuery.leftJoin(member).on(mainBoard.mainBoardAuthor.eq(member));
//        jpqlQuery.leftJoin(mainBoardReply).on(mainBoardReply.mainboard.eq(mainBoard));
//
//        //SELECT b, w, count(r) FROM Board b
//        //LEFT JOIN b.writer w LEFT JOIN Reply r ON r.board = b
//        JPQLQuery<Tuple> tuple = jpqlQuery.select(mainBoard, member, mainBoardReply.count());
//
//        BooleanBuilder booleanBuilder = new BooleanBuilder();
//        BooleanExpression expression = mainBoard.mainBoardNum.gt(0L);
//
//        booleanBuilder.and(expression);
//
//        if(type != null){
//            String[] typeArr = type.split("");
//            //검색 조건을 작성하기
//            BooleanBuilder conditionBuilder = new BooleanBuilder();
//
//            for (String t:typeArr) {
//                switch (t){
//                    case "t":
//                        conditionBuilder.or(mainBoard.mainBoardTitle.contains(keyword));
//                        break;
//                    case "w":
//                        conditionBuilder.or(member.memberEmail.contains(keyword));
//                        break;
//                    case "c":
//                        conditionBuilder.or(mainBoard.mainBoardContents.contains(keyword));
//                        break;
//                }
//            }
//            booleanBuilder.and(conditionBuilder);
//        }
//
//        tuple.where(booleanBuilder);
//
//        //order by
//        Sort sort = pageable.getSort();
//
//        //tuple.orderBy(board.bno.desc());
//
//        sort.stream().forEach(order -> {
//            Order direction = order.isAscending()? Order.ASC: Order.DESC;
//            String prop = order.getProperty();
//
//            PathBuilder orderByExpression = new PathBuilder(MainBoard.class, "mainBoard");
//            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
//
//        });
//        tuple.groupBy(mainBoard);
//
//        //page 처리
//        tuple.offset(pageable.getOffset());
//        tuple.limit(pageable.getPageSize());
//
//        List<Tuple> result = tuple.fetch();
//
//        log.info("result={}", result);
//
//        long count = tuple.fetchCount();
//
//        log.info("COUNT: " +count);
//
//        return new PageImpl<Object[]>(
//                result.stream().map(t -> t.toArray()).collect(Collectors.toList()),
//                pageable,
//                count);
//    }
//
//}
