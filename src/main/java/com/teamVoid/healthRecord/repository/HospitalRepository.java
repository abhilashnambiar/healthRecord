package com.teamVoid.healthRecord.repository;

import com.teamVoid.healthRecord.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    Hospital getHospitalByName(String name);

    Hospital getHospitalById(Long id);
}
