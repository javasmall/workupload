package com.submit.pojo;

public class teachclass {
    private Integer id;

    private String teachclassno;

    private String coursename;

    private String coursesemester;

    private Byte credit;

    private String evalmethod;

    private String teacherno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeachclassno() {
        return teachclassno;
    }

    public void setTeachclassno(String teachclassno) {
        this.teachclassno = teachclassno == null ? null : teachclassno.trim();
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename == null ? null : coursename.trim();
    }

    public String getCoursesemester() {
        return coursesemester;
    }

    public void setCoursesemester(String coursesemester) {
        this.coursesemester = coursesemester == null ? null : coursesemester.trim();
    }

    public Byte getCredit() {
        return credit;
    }

    public void setCredit(Byte credit) {
        this.credit = credit;
    }

    public String getEvalmethod() {
        return evalmethod;
    }

    public void setEvalmethod(String evalmethod) {
        this.evalmethod = evalmethod == null ? null : evalmethod.trim();
    }

    public String getTeacherno() {
        return teacherno;
    }

    public void setTeacherno(String teacherno) {
        this.teacherno = teacherno == null ? null : teacherno.trim();
    }
}