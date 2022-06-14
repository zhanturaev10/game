import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class gamePanel extends JPanel {
    private javax.swing.Timer timer;
    private Ball ball;
    private Paddle paddle;
    private Player player;
    private boolean gameStarted = false;
    private ArrayList<Brick> bricks;
    private static final int NUM_BRICK_ROWS = 9;
    private static final int NUM_BRICK_COLUMNS = 10;
    private int SCORE = 0;
    private String playerName;


    public gamePanel() {
        ball = new Ball(Color.red,this);
        paddle = new Paddle(Color.BLUE, this);
        timer = new javax.swing.Timer(10, new TimeListener());
        bricks = new ArrayList<>();
        player = new Player();
        createBricks();
        playerName = JOptionPane.showInputDialog(null, "Enter Your Name:","Breakout game", JOptionPane.QUESTION_MESSAGE);
        if (playerName == null) {
            System.exit(0);
        }
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_LEFT){
                    paddle.moveLeft();
                }
                if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
                    paddle.moveRight();
                }
                repaint();
            }
        });
        setFocusable(true);
        addMouseMotionListener(new MouseMotionAdapter() {
            boolean firstTime = true;
            int oldX;
            public void mouseMoved(MouseEvent e) {
                if (firstTime) {
                    oldX = e.getX();
                    firstTime = false;
                }
                paddle.move(e.getX() - oldX);
                oldX = e.getX();
                repaint();
            }
        });
        }
    private Color getRandomColor() {
        Color color = new Color((int) (Math.random() * 256),
                (int) (Math.random() * 256), (int) (Math.random() * 256));
        if (getBackground().equals(color)) {
            return Color.RED;
        }
        return color;
    }
    private void createBricks() {
        for (int row = 0; row < NUM_BRICK_ROWS; row++) {
            for (int col = 0; col < NUM_BRICK_COLUMNS; col++) {
                bricks.add(new Brick(row, col, Color.red));
            }
        }
    }
    public void start() {
        gameStarted = true;
        if (timer != null) {
            timer.stop();
        }
        if (!player.isAlive()) {
            player = new Player();

            createBricks();
        }
        timer = new javax.swing.Timer(BallSpeed.NORMAL.speed(),
                new TimeListener());
        timer.start();
        repaint();
    }

    public void pause() {
        if (timer == null) {
            return;
        }
        timer.stop();
    }

    public void changeBallColor(BallColor color) {
        ball.changeColor(color.color());
        repaint();
    }
    public void showMessage(String s, Graphics2D g2) {
        Font myFont = new Font("SansSerif",Font.BOLD+Font.ITALIC, 20);
        g2.setFont(myFont);
        g2.setColor(Color.RED);
        Rectangle2D textBox = myFont.getStringBounds(s,
                g2.getFontRenderContext());
        g2.drawString(s, (int) (getWidth()/ 2-textBox.getWidth() / 2),
                (int) (getHeight() / 2 - textBox.getHeight()));
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (bricks.size()==0) {
            gameStarted = false;
            timer.stop();
            showMessage("YOU WIN! Score: " + SCORE, g2);
        } else if (!player.isAlive()) {
            timer.stop();
            gameStarted = false;
            showMessage("GAME OVER! Score: " + SCORE, g2);
        } else {
            ball.draw(g2);
            paddle.draw(g2);
            for (Brick brick : bricks) {
                brick.draw(g2);
            }
        }
        if (gameStarted) {
            player.draw(g2);
            g.drawString("Score: " + SCORE, 210, 470);
            g.drawString("Player: " + playerName, 350, 470);
        }
    }

    public void changeBallSpeed(int speed) {
        timer.setDelay(speed);
    }

    class TimeListener implements ActionListener {
        private void bounceBall(Ball ball, Brick brick) {
            if (ball.below(brick)) {
                ball.goDown();
            }
            if (ball.above(brick)) {
                ball.goUp();

            }
            if (ball.leftOf(brick)) {
                ball.goLeft();
            }
            if (ball.rightOf(brick)) {
                ball.goRight();

            }
        }
        private void bounceBall(Ball ball, ArrayList<Brick> bricks) {
            if (bricks.size() == 0) {
                return;
            }
            if (bricks.size() == 1) {
                bounceBall(ball, bricks.get(0));
                return;
            }

            Brick combinedBrick = bricks.get(0).add(bricks.get(1));
            bounceBall(ball, combinedBrick);
        }

        public void actionPerformed(ActionEvent e) {
            Ball newBall = ball.getVirtualBall();
            ArrayList<Brick> bricksToBeDeleted = new ArrayList<Brick>();
            for (Brick brick : bricks) {
                if (brick.intersects(newBall)) {
                    SCORE+=1;
                    bricksToBeDeleted.add(brick);
                }
            }
            bounceBall(ball, bricksToBeDeleted);
            for (Brick brick : bricksToBeDeleted) {
                bricks.remove(brick);
            }
            if (newBall.intersects(paddle)) {
                ball.goUp();
                if (newBall.getX() + newBall.getWidth() / 2 <
                        paddle.getX() + paddle.getWidth() / 2) {
                    ball.goLeft();
                } else {
                    ball.goRight();
                }
            } else if (ball.getY() >
                    paddle.getY() - paddle.getHeight()) {
                player.killPlayer();
                ball.goUp();
            }
            ball.move();
            repaint();
        }
    }

}



