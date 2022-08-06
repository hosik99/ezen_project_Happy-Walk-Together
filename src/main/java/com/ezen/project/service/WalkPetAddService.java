package com.ezen.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ezen.project.Repository.MemberRepository;
import com.ezen.project.Repository.PetRepository;
import com.ezen.project.model.Family;
import com.ezen.project.model.Member;
import com.ezen.project.model.Pet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WalkPetAddService {
	@Autowired
	private PetRepository petRepository;
	
	
	@Autowired
	private MemberRepository memberRepository;
	
	
	public Pet PetAddForm(Long FamilyId,Pet pet) { 
		Pet fa = petRepository.save(pet);
		petRepository.petId_update_value(FamilyId, pet.getPetId());
		return fa;
	}
	public List<Pet> getList() {	
		List<Pet> resEntity = petRepository.findAll();
		return resEntity;
	}
	public Family findByDelEmailId(String delEmail) {
		return memberRepository.findByMemberEmail(delEmail).get(0).getFamily();
	}
	//검색
	public List<Pet> findByPetId(Long petId) {
		List<Pet> op =petRepository.findByPetId(petId);
		return op;	
	}
	
	//수정
	public boolean update(Pet pet) {
		List<Pet> list = petRepository.findAll();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPetId().equals(pet.getPetId())) {
				System.out.println("입장");
				int savedUser = petRepository.update_object(pet);
				log.trace("seavice update : {}",savedUser);
				return true;
			}else{}		
		}
		return false;
	}
	//삭제
	public boolean delete(Long petId) {

		return petRepository.delete_object(petId)>0;
		
	}
}
