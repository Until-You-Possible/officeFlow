package com.example.flow.db.dao;

import generator.TbLeave;
import generator.TbLeaveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbLeaveDao {
    long countByExample(TbLeaveExample example);

    int deleteByExample(TbLeaveExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbLeave record);

    int insertSelective(TbLeave record);

    List<TbLeave> selectByExample(TbLeaveExample example);

    TbLeave selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbLeave record, @Param("example") TbLeaveExample example);

    int updateByExample(@Param("record") TbLeave record, @Param("example") TbLeaveExample example);

    int updateByPrimaryKeySelective(TbLeave record);

    int updateByPrimaryKey(TbLeave record);
}