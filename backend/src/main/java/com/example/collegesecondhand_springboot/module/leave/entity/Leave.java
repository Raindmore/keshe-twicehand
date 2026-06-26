package com.example.collegesecondhand_springboot.module.leave.entity;

import lombok.Data;

@Data
public class Leave {
    private Integer Lid;
    private Integer Gid;
    private Integer Sid;
    private String Lmessage;
    private String Ltime;

    //以下非数据库字段
    private String Susername;//来自student表
    private String Savatar;
}
