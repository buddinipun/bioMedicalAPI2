package com.dpl.biomedical.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dpl.biomedical.entity.Practice;



@Repository
public interface PracticeRepository extends JpaRepository<Practice, Long> {

	@Query(value = "SELECT pt FROM Practice pt WHERE sequence= :sequenceNumber ")
	public List<Practice> getPracticeBySequenceNumber(String sequenceNumber);

}
