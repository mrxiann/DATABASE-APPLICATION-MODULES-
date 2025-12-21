import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // SwingUtils for thread safety
        SwingUtilities.invokeLater(() -> {
            // login scren
            new LoginFrame();
        });
    }
}