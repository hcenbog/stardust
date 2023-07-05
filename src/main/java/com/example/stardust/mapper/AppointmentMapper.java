package com.example.stardust.mapper;

import com.example.stardust.entity.Appointment;

import java.util.List;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/20 9:52
 */
public interface AppointmentMapper {
    List<Appointment> getAllApp();

    Appointment selectById(long id);

    Integer insertAddApp(Appointment appointment);

    void updateAppoint(Appointment appointment);

    /**
     * @param id
     * @return void
     * @Description 删除预约提货信息
     * @create 2023/3/20 17:14
     **/
    void deleteAppoint(Long id);

}
