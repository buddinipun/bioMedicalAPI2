package com.dpl.biomedical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dpl.biomedical.entity.Practice;



@Repository
public interface PracticeRepository extends JpaRepository<Practice, Long> {

}
