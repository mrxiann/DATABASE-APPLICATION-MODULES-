package models;

import java.util.Date;

public class Opportunity {
    private int opportunityId;
    private String title;
    private String description;
    private String type;
    private String location;
    private Date deadline;
    private int slotsAvailable;
    private String status;
    
    public Opportunity(int opportunityId, String title, String description, String type,
                       String location, Date deadline, int slotsAvailable, String status) {
        this.opportunityId = opportunityId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.location = location;
        this.deadline = deadline;
        this.slotsAvailable = slotsAvailable;
        this.status = status;
    }
    
    // Getters
    public int getOpportunityId() { return opportunityId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getType() { return type; }
    public String getLocation() { return location; }
    public Date getDeadline() { return deadline; }
    public int getSlotsAvailable() { return slotsAvailable; }
    public String getStatus() { return status; }
}