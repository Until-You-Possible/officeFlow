package com.example.flow.db.dao;

import generator.TbAction;
import generator.TbActionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbActionDao {
    long countByExample(TbActionExample example);

    int deleteByExample(TbActionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbAction record);

    int insertSelective(TbAction record);

    List<TbAction> selectByExample(TbActionExample example);

    TbAction selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbAction record, @Param("example") TbActionExample example);

    int updateByExample(@Param("record") TbAction record, @Param("example") TbActionExample example);

    int updateByPrimaryKeySelective(TbAction record);

    int updateByPrimaryKey(TbAction record);
}