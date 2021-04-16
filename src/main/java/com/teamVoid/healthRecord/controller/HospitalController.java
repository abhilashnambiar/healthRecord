package com.teamVoid.healthRecord.controller;

import com.teamVoid.healthRecord.model.Hospital;
import com.teamVoid.healthRecord.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HospitalController {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping(path = "get/hospitals")
    public List<Hospital> getHospitals() {
        return hospitalService.getHospitals();
    }

    @PostMapping(path = "add/hospital")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String addHospital(@RequestBody Hospital hospital) {
        return hospitalService.addHospital(hospital);
    }

    @PutMapping(path = "update/hospital/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String updateHospital(@PathVariable Long id, @RequestBody Hospital hospital) {
        return hospitalService.updateHospital(id, hospital);
    }

    @DeleteMapping(path = "delete/hospital/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String deleteHospital(@PathVariable Long id) {
        return hospitalService.deleteHospital(id);
    }
}
