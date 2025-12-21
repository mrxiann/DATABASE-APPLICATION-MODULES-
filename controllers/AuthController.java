package controllers;

public class AuthController {
    // This will handle authentication logic when we add MySQL
    
    public static boolean authenticate(String email, String password, String role) {
        // Demo authentication - will connect to database later
        if (email.equals("admin@sk.com") && password.equals("admin123") && role.equals("SK Official")) {
            return true;
        } else if (email.equals("youth@sk.com") && password.equals("youth123") && role.equals("Youth User")) {
            return true;
        }
        return false;
    }
}