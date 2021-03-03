import javax.swing.*;

public class gameFrame extends JFrame {
    private static final int HEIGHT = 600;
    private static final int WIDTH = 480;
    private gamePanel panel = new gamePanel();

    public gameFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(0, 0);
        setSize(WIDTH, HEIGHT);
        add(panel);
        setResizable(false);
    }
}