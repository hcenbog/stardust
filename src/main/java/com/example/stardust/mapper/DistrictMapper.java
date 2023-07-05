package com.example.stardust.mapper;

/**
 * @author AlHeae
 * @Description
 * @date 2023/3/11 23:28
 */

import com.example.stardust.entity.District;

import java.util.List;

public interface DistrictMapper {

    List<District> findByParent(String parent);

    String findNameByCode(String code);
}
