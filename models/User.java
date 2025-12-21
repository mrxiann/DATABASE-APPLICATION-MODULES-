package models;

public class User {
    private int id;
    private String fullName;
    private String email;
    private String password;
    private String role;
    private String status;
    private String purokAddress;
    private int age;
    
    public User(int id, String fullName, String email, String role, String status, String purokAddress, int age) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.status = status;
        this.purokAddress = purokAddress;
        this.age = age;
    }
    
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getPurokAddress() { return purokAddress; }
    public void setPurokAddress(String purokAddress) { this.purokAddress = purokAddress; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}