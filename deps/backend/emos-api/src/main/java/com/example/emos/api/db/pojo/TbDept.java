package com.example.emos.api.db.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * tb_dept
 *
 * @author
 */
@Data
public class TbDept implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 部门名称
     */
    private String deptName;

    private String tel;

    private String email;

    private String desc;

    private static final long serialVersionUID = 1L;
}