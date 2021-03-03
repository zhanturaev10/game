import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class gamePanel extends JPanel {
    private javax.swing.Timer timer;
    private Ball ball;
    private Paddle paddle;
    public gamePanel() {
        ball = new Ball(Color.ORANGE, this);
        paddle = new Paddle(Color.BLUE, this);
        timer = new javax.swing.Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ball.move();
                repaint();
            }
        });
        timer.start();
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (KeyEvent.VK_LEFT == e.getKeyCode()) {
                    paddle.moveLeft();
                }
                if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
                    paddle.moveRight();
                }
                repaint();
            }
        });
        setFocusable(true);
        }
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        ball.draw(g2);
        paddle.draw(g2);
    }
}
