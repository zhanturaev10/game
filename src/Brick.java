import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Brick extends gameShape{
    private static final int HEIGHT = 10;
    private static final int WIDTH = 52;
    private static final int BRICK1 = 2;
    private static final int BRICK2 = 2;

    public Brick(int row, int col, Color color) {
        super(new Rectangle2D.Double(BRICK1 + row * (BRICK1 + Brick.WIDTH), BRICK2 + col * (BRICK2 + Brick.HEIGHT), WIDTH, HEIGHT), color, true);
        setDestroyed(false);
    }
    private Brick(Rectangle2D rectangle, Color color){
        super(rectangle, color, true);
    }
    public Brick add(Brick other){
        Rectangle2D rectangle1 = super.getBounds();
        Rectangle2D rectangle2 = other.getBounds();
        rectangle1.add(rectangle2);
        return new Brick(rectangle1, super.getColor());
    }

}
