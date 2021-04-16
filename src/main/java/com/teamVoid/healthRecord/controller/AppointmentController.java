package com.teamVoid.healthRecord.controller;

import com.teamVoid.healthRecord.model.Appointment;
import com.teamVoid.healthRecord.service.AppointmentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping(path = "add/appointment")
    @PreAuthorize("hasAnyAuthority('DOCTOR')")
    public String addAppointment(@RequestBody Appointment appointment) {
        return appointmentService.addAppointment(appointment);
    }

    @PutMapping(path = "update/{id}/report")
    @PreAuthorize("hasAnyAuthority('DOCTOR')")
    public String updateReport(@PathVariable Long id, @RequestParam("report") MultipartFile file) throws IOException {
        return appointmentService.updateReport(id, file);
    }

    @DeleteMapping(path = "delete/appointment/{id}")
    @PreAuthorize("hasAnyAuthority('DOCTOR')")
    public String deleteAppointment(@PathVariable Long id) {
        return appointmentService.deleteAppointment(id);
    }
}