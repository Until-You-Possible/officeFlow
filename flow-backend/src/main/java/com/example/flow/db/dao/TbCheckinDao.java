package com.example.flow.db.dao;

import generator.TbCheckin;
import generator.TbCheckinExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCheckinDao {
    long countByExample(TbCheckinExample example);

    int deleteByExample(TbCheckinExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbCheckin record);

    int insertSelective(TbCheckin record);

    List<TbCheckin> selectByExample(TbCheckinExample example);

    TbCheckin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbCheckin record, @Param("example") TbCheckinExample example);

    int updateByExample(@Param("record") TbCheckin record, @Param("example") TbCheckinExample example);

    int updateByPrimaryKeySelective(TbCheckin record);

    int updateByPrimaryKey(TbCheckin record);
}