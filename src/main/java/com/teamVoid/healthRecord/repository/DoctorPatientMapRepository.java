package com.teamVoid.healthRecord.repository;

import com.teamVoid.healthRecord.model.DoctorPatientMap;
import com.teamVoid.healthRecord.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorPatientMapRepository extends JpaRepository<DoctorPatientMap, Long> {
    List<DoctorPatientMap> getPatientIdsByDoctorId(String doctorId);
}
