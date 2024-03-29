import java.awt.*;
import java.awt.geom.*;

public class Ball extends gameShape {
    private static final int SIZE = 15;
    private static final int START_X = 200;
    private static final int START_Y = 400;

    private int dx = 4;
    private int dy = -4;
    private gamePanel panel;

    public Ball(Color color, gamePanel panel) {
        super(new Ellipse2D.Double(START_X, START_Y, SIZE, SIZE), color, true);
        this.panel = panel;
    }
    private Ball(Color color, Ellipse2D.Double ellipse){
        super(ellipse,color,true);
    }
    public Ball getVirtualBall(){
        return new Ball( super.getColor(),
                new Ellipse2D.Double(getX()+dx,getY()+dy,SIZE,SIZE));
    }

    public void move() {
        if (getX() + dx < 0) {
            dx = 4;
        }
        if (getX() + getWidth() + dx > panel.getWidth()) {
            dx = -4;
        }
        if (getY() + dy < 0) {
            dy = 4;
        }
        if (getY()+getHeight()+dy > panel.getHeight()){
            dy = -4;
        }
        super.move(dx, dy);
    }
    public void goUp(){
        dy = -4;
    }

    public void goDown(){
        dy = 4;
    }

    public void goLeft(){
        dx = -4;
    }

    public void goRight(){
        dx = 4;
    }

}


