package com.example.stardust.mapper;

import com.example.stardust.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author AlHeae
 * @Description
 * @date 2023/3/10 21:19
 */
public interface AddressMapper {

    Integer insert(Address address);

    Integer countByUid(Integer uid);


    List<Address> findByUid(Integer uid);


    Integer updateNonDefaultByUid(Integer uid);


    Integer updateDefaultByAid(
            @Param("aid") Integer aid,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);


    Address findByAid(Integer aid);


    Integer deleteByAid(Integer aid);


    Address findLastModified(Integer uid);

}