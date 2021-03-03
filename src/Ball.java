import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball extends gameShape {
    private static final int SIZE = 15;
    private static final int START_X = 200;
    private static final int START_Y = 400;

    private int dx = 3;
    private int dy = -3;
    private gamePanel panel;

    public Ball(Color color, gamePanel panel) {
        super(new Ellipse2D.Double(Ball.START_X, Ball.START_Y, Ball.SIZE, Ball.SIZE), color, true);
        this.panel = panel;
    }
    private Ball(Color color, Ellipse2D.Double ellipse){
        super(ellipse, color, true);
    }

    public Ball getVirtualBall(){
        return new Ball(super.getColor(), new Ellipse2D.Double(getX()+dx, getY()+dy, SIZE,SIZE));
    }
    public void move(){
        if (getX() + dx < 0){
            dx = 3;
        }
        if (getX() + getWidth() +dx > panel.getWidth()){
            dx = -3;
        }
        if (getY() + dy < 0){
            dy = 3;
        }
        if (getY() + getHeight()+ dy > panel.getHeight()){
            dy = -3;
        }
        super.move(dx, dy);
    }
    public void goUp(){
        dy = -1;
    }
    public void goDown(){
        dy = 1;
    }
    public  void goLeft(){
        dx = -1;
    }
    public void goRight(){
        dx = 1;
    }
}
