package com.example.emos.api.db.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface TbDeptDao {
    public ArrayList<HashMap> searchAllDept();
    public HashMap searchById(int id);

}