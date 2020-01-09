package com.example.mdg_soc_eduplexus;

public class Events {
    private String EventsID;
    private String EventName;
    private String subject;

    public Events(){

    }

    public Events(String eventsID, String eventName, String subject) {
        EventsID = eventsID;
        EventName = eventName;
        this.subject = subject;
    }

    public String getEventsID() {
        return EventsID;
    }

    public String getEventName() {
        return EventName;
    }

    public String getSubject() {
        return subject;
    }
}
