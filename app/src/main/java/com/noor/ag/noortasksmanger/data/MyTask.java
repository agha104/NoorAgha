package com.noor.ag.noortasksmanger.data;

import java.util.Date;

/**
 * Created by user on 9/8/2016.
 */
public class MyTask
{
    /**
     * rakam almhama
     */
    private String id;
    /**
     * 3nwan
     */
    private String title;
    private int priority;
    private Date when;
    private String adress;
    private String phone;



    public MyTask(String id, String title, int priority, Date when, String adress, String phone) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.when = when;
        this.adress = adress;
        this.phone = phone;
    }

    public MyTask() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "MyTask{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", priority=" + priority +
                ", when=" + when +
                ", adress='" + adress + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
