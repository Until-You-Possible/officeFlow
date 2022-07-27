package com.example.emos.api.db.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface TbMeetingDao {
    public boolean searchMeetingMembersInSameDept(String uuid);
    public HashMap searchMeetingById(HashMap param);
}