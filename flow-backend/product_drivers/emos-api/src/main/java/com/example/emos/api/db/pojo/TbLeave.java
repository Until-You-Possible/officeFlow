package com.example.emos.api.db.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class TbLeave {
    private Integer id;
    private Integer userId;
    private String reason;
    private String start;
    private String end;
    private String days;
    private Byte type;
    private Byte status;
    private String instanceId;
    private Date createTime;
}
