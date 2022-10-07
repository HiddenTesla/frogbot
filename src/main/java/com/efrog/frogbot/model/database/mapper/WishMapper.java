package com.efrog.frogbot.model.database.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WishMapper {

    @Select("SELECT 0")
    int frog();
}
