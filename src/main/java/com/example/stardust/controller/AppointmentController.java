package com.example.stardust.controller;

import com.example.stardust.entity.Appointment;
import com.example.stardust.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/20 11:00
 */
@RestController
@ResponseBody
@RequestMapping("/appointments")
public class AppointmentController extends BaseController {
    @Autowired
    private IAppointmentService appointmentService;

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllApp();
    }

    @GetMapping("/{id}")
    public Appointment selectById(@PathVariable Long id) {
        return appointmentService.selectById(id);
    }

    @PostMapping
    public void insertAddApp(@RequestBody Appointment appointment) {
        appointmentService.insertAddApp(appointment);
    }

    @PutMapping("/{id}")
    public void updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        appointment.setId(id);
        appointmentService.updateAppoint(appointment);
    }

    @DeleteMapping("/{id}")
    public void deleteAppoint(@PathVariable Long id) {
        appointmentService.deleteAppoint(id);
    }
}
