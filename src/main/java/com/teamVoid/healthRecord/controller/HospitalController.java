package com.teamVoid.healthRecord.controller;

import com.teamVoid.healthRecord.model.Hospital;
import com.teamVoid.healthRecord.service.HospitalService;
import com.teamVoid.healthRecord.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HospitalController {

    private final HospitalService hospitalService;
    private final MappingService mappingService;

    @Autowired
    public HospitalController(HospitalService hospitalService, MappingService mappingService) {
        this.hospitalService = hospitalService;
        this.mappingService = mappingService;
    }

    @GetMapping(path = "get/hospitals")
    public List<Hospital> getHospitals() {
        return hospitalService.getHospitals();
    }

    @PostMapping(path = "add/hospital")
    @PreAuthorize("hasAnyAuthority('ROOT_ADMIN')")
    public String addHospital(@RequestBody Hospital hospital) {
        return hospitalService.addHospital(hospital);
    }

    @PutMapping(path = "update/hospital/{id}")
    @PreAuthorize("hasAnyAuthority('HOSP_ADMIN')")
    public String updateHospital(@PathVariable String id, @RequestBody Hospital hospital) {
        String hospAdmin = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if (mappingService.checkAdminHospital(hospAdmin, id))
            return hospitalService.updateHospital(id, hospital);
        else throw new IllegalArgumentException("hospital admin not mapped with hospital");
    }

//    @DeleteMapping(path = "delete/hospital/{id}")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
//    public String deleteHospital(@PathVariable Long id) {
//        return hospitalService.deleteHospital(id);
//    }
}
