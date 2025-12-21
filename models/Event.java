package models;

import java.util.Date;

public class Event {
    private int eventId;
    private String title;
    private String description;
    private Date eventDate;
    private String eventTime;
    private String location;
    private String type;
    private int slotsTotal;
    private int slotsFilled;
    private String status;
    
    public Event(int eventId, String title, String description, Date eventDate, 
                 String eventTime, String location, String type, 
                 int slotsTotal, int slotsFilled, String status) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.location = location;
        this.type = type;
        this.slotsTotal = slotsTotal;
        this.slotsFilled = slotsFilled;
        this.status = status;
    }
    
    // Getters
    public int getEventId() { return eventId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Date getEventDate() { return eventDate; }
    public String getEventTime() { return eventTime; }
    public String getLocation() { return location; }
    public String getType() { return type; }
    public int getSlotsTotal() { return slotsTotal; }
    public int getSlotsFilled() { return slotsFilled; }
    public int getSlotsAvailable() { return slotsTotal - slotsFilled; }
    public String getStatus() { return status; }
    public String getSlotsDisplay() { return slotsFilled + " / " + slotsTotal; }
}