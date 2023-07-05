package com.example.stardust.service;

import com.example.stardust.entity.Appointment;

import java.util.List;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/20 10:50
 */
public interface IAppointmentService {
    List<Appointment> getAllApp();

    Appointment selectById(long id);

    void insertAddApp(Appointment appointment);

    void updateAppoint(Appointment appointment);

    void deleteAppoint(Long id);
}
