import java.awt.*;
import java.awt.geom.RectangularShape;

public class gameShape {
    private RectangularShape shape;
    private Color color;
    private boolean fill;

    public gameShape(RectangularShape shape, Color color, boolean fill) {
        this.shape = shape;
        this.color = color;
        this.fill = fill;
    }
    public Color getColor(){
        return color;
    }
    protected Rectangle getBounds(){
        return shape.getBounds();
    }
    public void changeColor(Color color){
        this.color = color;
    }

    public void draw(Graphics2D g2){
        g2.setPaint(color);
        g2.draw(shape);
        if (fill){
            g2.fill(shape);
        }
    }

    public double getX() {
        return shape.getX();
    }

    public double getY() {
        return shape.getY();
    }

    public double getHeight() {
        return shape.getHeight();
    }

    public double getWidth() {
        return shape.getWidth();
    }

    public boolean intersects(gameShape other){
        return shape.intersects(other.shape.getBounds());
    }


    public void move(int dx, int dy) {
        shape.setFrame(getX() + dx, getY() + dy, getWidth(), getHeight());
    }
}