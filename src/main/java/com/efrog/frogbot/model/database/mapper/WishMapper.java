package com.efrog.frogbot.model.database.mapper;

import com.efrog.frogbot.model.pojo.WishEntry;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WishMapper {

    @Options(keyProperty = "wishId", keyColumn = "wish_id", useGeneratedKeys = true)
    @Insert(" INSERT INTO `wish` " +
            " (wish_id, user_id, wish_time, outcome ) " +
            " VALUES " +
            " (#{wishId}, #{userId}, #{time}, #{outcome})"
    )
    void insert(WishEntry wishEntry);

    @Select(
            "SELECT * FROM `wish` WHERE user_id = #{userId} " +
                " ORDER BY `wish_id` DESC LIMIT #{limit}"
    )
    @Results(id = "common", value = {
            @Result(property = "wishId", column = "wish_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "time",   column = "wish_time"),
    })
    List<WishEntry> findByUserId(long userId, int limit);
}
