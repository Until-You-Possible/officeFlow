package com.example.flow.db.dao;

import generator.TbAmectType;
import generator.TbAmectTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbAmectTypeDao {
    long countByExample(TbAmectTypeExample example);

    int deleteByExample(TbAmectTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbAmectType record);

    int insertSelective(TbAmectType record);

    List<TbAmectType> selectByExample(TbAmectTypeExample example);

    TbAmectType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbAmectType record, @Param("example") TbAmectTypeExample example);

    int updateByExample(@Param("record") TbAmectType record, @Param("example") TbAmectTypeExample example);

    int updateByPrimaryKeySelective(TbAmectType record);

    int updateByPrimaryKey(TbAmectType record);
}