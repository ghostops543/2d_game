package Main;
import  javax.swing.*;
public class GUI {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("GUI");

        PanelSettings PanelSettings = new PanelSettings();
        window.add(PanelSettings);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        PanelSettings.startGameThread();

    }
}
