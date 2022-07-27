package com.example.emos.api.service.impl;

import com.example.emos.api.db.dao.TbRoleDao;
import com.example.emos.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private TbRoleDao roleDao;

    @Override
    public ArrayList<HashMap> searchAllRole() {
        ArrayList<HashMap> list = roleDao.searchAllRole();
        return list;
    }

    @Override
    public HashMap searchById(int id) {
        HashMap map = roleDao.searchById(id);
        return map;
    }
}
