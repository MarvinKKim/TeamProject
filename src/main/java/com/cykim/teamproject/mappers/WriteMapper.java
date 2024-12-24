package com.cykim.teamproject.mappers;

import com.cykim.teamproject.entities.WriteEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WriteMapper {

    int insertAdminWrite(WriteEntity adminPage);
}
