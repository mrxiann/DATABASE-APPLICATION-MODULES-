package models;

import java.util.Date;

public class Event {
    private int id;
    private String title;
    private Date date;
    private String location;
    private String type;
    private String status;
    private int slotsAvailable;
    private int slotsTotal;
    
    public Event(int id, String title, Date date, String location, String type, 
                 String status, int slotsAvailable, int slotsTotal) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.location = location;
        this.type = type;
        this.status = status;
        this.slotsAvailable = slotsAvailable;
        this.slotsTotal = slotsTotal;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public int getSlotsAvailable() { return slotsAvailable; }
    public void setSlotsAvailable(int slotsAvailable) { this.slotsAvailable = slotsAvailable; }
    
    public int getSlotsTotal() { return slotsTotal; }
    public void setSlotsTotal(int slotsTotal) { this.slotsTotal = slotsTotal; }
}