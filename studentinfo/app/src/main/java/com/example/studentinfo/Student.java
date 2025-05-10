package com.example.studentinfo;

public class Student {
    private long id;
    private String fullName;
    private String studentId;
    private String phone;
    private String email;
    private String avatar;
    private String gender;
    private String year;
    //MOI NGUOI CO CAC THONG TIN NHU SAU: ID, TEN, MSSV, SDT, EMAIL, AVATAR, GIOI TINH, SINH VIEN NAM THU BAO NHIEU
    public Student(long id, String fullName, String studentId, String phone, String email, String avatar, String gender, String year) {
        this.id = id;
        this.fullName = fullName;
        this.studentId = studentId;
        this.phone = phone;
        this.email = email;
        this.avatar = avatar;
        this.gender = gender;
        this.year = year;
    }

    public long getId() { return id; }
    public String getFullName() { return fullName; }
    public String getStudentId() { return studentId; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getAvatar() { return avatar; }
    public String getGender() { return gender; }
    public String getYear() { return year; }
}