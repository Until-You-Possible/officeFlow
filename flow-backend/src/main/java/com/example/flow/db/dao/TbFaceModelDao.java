package com.example.flow.db.dao;

import generator.TbFaceModel;
import generator.TbFaceModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbFaceModelDao {
    long countByExample(TbFaceModelExample example);

    int deleteByExample(TbFaceModelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbFaceModel record);

    int insertSelective(TbFaceModel record);

    List<TbFaceModel> selectByExampleWithBLOBs(TbFaceModelExample example);

    List<TbFaceModel> selectByExample(TbFaceModelExample example);

    TbFaceModel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbFaceModel record, @Param("example") TbFaceModelExample example);

    int updateByExampleWithBLOBs(@Param("record") TbFaceModel record, @Param("example") TbFaceModelExample example);

    int updateByExample(@Param("record") TbFaceModel record, @Param("example") TbFaceModelExample example);

    int updateByPrimaryKeySelective(TbFaceModel record);

    int updateByPrimaryKeyWithBLOBs(TbFaceModel record);

    int updateByPrimaryKey(TbFaceModel record);
}