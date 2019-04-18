package com.submit.pojo;

public class timetable {
    private Long no;

    private String teachclassno;

    private String weeks;

    private String classroom;

    private Short day;

    private Short time;

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getTeachclassno() {
        return teachclassno;
    }

    public void setTeachclassno(String teachclassno) {
        this.teachclassno = teachclassno == null ? null : teachclassno.trim();
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks == null ? null : weeks.trim();
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom == null ? null : classroom.trim();
    }

    public Short getDay() {
        return day;
    }

    public void setDay(Short day) {
        this.day = day;
    }

    public Short getTime() {
        return time;
    }

    public void setTime(Short time) {
        this.time = time;
    }
}