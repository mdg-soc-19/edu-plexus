package com.example.mdg_soc_eduplexus;

import android.provider.ContactsContract;
import android.widget.EditText;

import java.sql.Time;
import java.util.Date;

public class Events {
private String EventName;
private String date;
private String Duration;
private String time;
private String Email;
    public Events() {
    }

    public Events(String eventName, String date, String duration, String time, String Email) {
        EventName = eventName;
        this.date = date;
        Duration = duration;
        this.time = time;
        this.Email = Email;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String  getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
