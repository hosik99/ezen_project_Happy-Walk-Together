package com.ezen.project.Repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.project.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
	@Transactional
	@Modifying
	@Query("UPDATE Pet P SET P.family=(SELECT F.familyId FROM Family F WHERE familyId = ?1) WHERE P.petId = ?2")
	int petId_update_value(Long familyId,Long petId);	
	
	@Transactional
	@Modifying
	@Query("UPDATE Pet p SET"
			+ " p.petName=:#{#pet.petName},"
			+ " p.breed=:#{#pet.breed},"
			+ " p.petSize=:#{#pet.petSize}"
			+ " WHERE p.petId=:#{#pet.petId}")
	int update_object(Pet pet);	
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Pet where petId = :petId")
	int delete_object(@Param("petId")Long petId);	
	
	List<Pet> findByPetId(Long petId);
}
