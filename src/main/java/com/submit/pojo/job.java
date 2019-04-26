package com.submit.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class job {
    private Integer id;

    private Integer teachclassid;

    private Integer no;

    private String title;

    private String duedate;

    private Integer type;

    private String note;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeachclassid() {
        return teachclassid;
    }

    public void setTeachclassid(Integer teachclassid) {
        this.teachclassid = teachclassid;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate == null ? null : duedate.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}