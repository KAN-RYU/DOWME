package com.puresushi.cse364project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserDetail {
    private @Id Long userId;
    private char gender;
    private int age;
    private int occupation;
    private int zipCode;

    UserDetail() {
    }

    public UserDetail(Long userId, char gender, int age, int occupation, int zipCode) {
        this.gender = gender;
        this.age = age;
        this.occupation = occupation;
        this.zipCode = zipCode;
    }

    public Long getuserId() {
        return this.userId;
    }

    public void setuserId(Long userId) {
        this.userId = userId;
    }

    public char getGender() {
        return this.gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }    

    public int getOccupation() {
        return this.occupation;
    }

    public void setOccupation(int occupation) {
        this.occupation = occupation;
    }    
    
    public int getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }    
  
}
