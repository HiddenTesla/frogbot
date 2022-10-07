package com.efrog.frogbot.model.database.mapper;

import com.efrog.frogbot.model.pojo.WishEntry;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface WishMapper {

    @Options(keyProperty = "wishId", keyColumn = "wish_id", useGeneratedKeys = true)
    @Insert(" INSERT INTO `wish` " +
            " (wish_id, user_id, wish_time, outcome ) " +
            " VALUES " +
            " (#{wishId}, #{userId}, #{time}, #{outcome})"
    )
    void insert(WishEntry wishEntry);
}
