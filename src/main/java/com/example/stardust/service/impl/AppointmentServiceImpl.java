package com.example.stardust.service.impl;

import com.example.stardust.entity.Appointment;
import com.example.stardust.mapper.AppointmentMapper;
import com.example.stardust.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/20 10:56
 */
@Service
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Override
    public List<Appointment> getAllApp() {
        return appointmentMapper.getAllApp();
    }

    @Override
    public Appointment selectById(long id) {
        return appointmentMapper.selectById(id);
    }

    @Override
    public void insertAddApp(Appointment appointment) {
        appointmentMapper.insertAddApp(appointment);
    }

    @Override
    public void updateAppoint(Appointment appointment) {
        appointmentMapper.updateAppoint(appointment);
    }

    @Override
    public void deleteAppoint(Long id) {
        appointmentMapper.deleteAppoint(id);
    }
}
