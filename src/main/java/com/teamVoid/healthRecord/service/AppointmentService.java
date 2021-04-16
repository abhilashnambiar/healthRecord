package com.teamVoid.healthRecord.service;

import com.teamVoid.healthRecord.model.Appointment;
import com.teamVoid.healthRecord.model.Profile;
import com.teamVoid.healthRecord.model.User;
import com.teamVoid.healthRecord.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public String addAppointment(Appointment appointment) {
        try {
            appointmentRepository.save(appointment);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("invalid case or doctor or hospital");
        }
        return "appointment added with id:" + appointment.getId() + " in case:" + appointment.getPatientCase() + " by doctor:" + appointment.getDoctor() + " in hospital:" + appointment.getHospital();
    }

    public String updateReport(Long id, MultipartFile report) throws IOException {
        Appointment appointment = appointmentRepository.getAppointmentById(id);
        if (appointment == null) throw new IllegalArgumentException("appointment:" + id + " not found");
        appointment.setReport(report.getBytes());
        appointmentRepository.save(appointment);
        return "report updated for appointment:" + id;
    }

    public String deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
        return "appointment:" + id + " deleted";
    }
}
