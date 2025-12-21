package models;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String purok;
    private int age;
    private String status;
    private String qrCodeId;
    
    public User(int userId, String firstName, String lastName, String email, 
                String role, String purok, int age, String status, String qrCodeId) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.purok = purok;
        this.age = age;
        this.status = status;
        this.qrCodeId = qrCodeId;
    }
    
    // Getters
    public int getUserId() { return userId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getFullName() { return firstName + " " + lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public String getPurok() { return purok; }
    public int getAge() { return age; }
    public String getStatus() { return status; }
    public String getQrCodeId() { return qrCodeId; }
}