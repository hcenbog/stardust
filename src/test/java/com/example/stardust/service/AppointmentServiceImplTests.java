package com.example.stardust.service;

import com.example.stardust.entity.Appointment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/20 19:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppointmentServiceImplTests {
    @Autowired
    private IAppointmentService appointmentService;

    @Test
    public void addAppointment() {
        Appointment appointment = new Appointment();
        appointment.setName("John");
        appointment.setPhone("1234567890");
        appointment.setAddress("123 Main St");
        appointment.setPickupTime(new Date());
        appointment.setStatus("未提货");
        appointmentService.insertAddApp(appointment);
    }
}
