import java.awt.*;
import java.awt.geom.RectangularShape;

public class gameShape {
    private RectangularShape shape;
    private Color color;
    private boolean fill;
    private boolean destroyed;

  public gameShape(RectangularShape shape, Color color, boolean fill){
            this.shape = shape;
            this.color = color;
            this.fill = fill;
        }
        public Color getColor () {
            return color;
        }
        public void changeColor(Color color){
      this.color = color;
        }
    protected Rectangle getBounds(){
        return shape.getBounds();
    }
        public void draw (Graphics2D g2){
            g2.setPaint(color);
            g2.draw(shape);
            if (fill) {
                g2.fill(shape);
            }
        }

        public double getX () {
            return shape.getX();
        }

        public double getY () {
            return shape.getY();
        }

        public double getHeight () {
            return shape.getHeight();
        }

        public double getWidth () {
            return shape.getWidth();
        }

    public boolean intersects(gameShape other) {
        return shape.intersects(other.shape.getBounds());
    }

        public void move ( int dx, int dy){
            shape.setFrame(getX() + dx, getY() + dy, getWidth(), getHeight());
        }

        public boolean below (gameShape other){
            if (getY() >= other.getY() + other.getHeight() && destroyed==false){
                return true;
            }
            return false;
        }

        public boolean above (gameShape other){
            if (getY() + getHeight() <= other.getY() && destroyed==false){
                return true;
            }
            return false;
        }

        public boolean leftOf (gameShape other){
      if (getX() + getWidth() <= other.getX() && destroyed==false){
          return true;
      }
      return false;
        }

        public boolean rightOf (gameShape other){
      if (getX() >= other.getX() + other.getWidth() && destroyed==false){
          return true;
      }
      return false;
        }

        public boolean isDestroyed(){
      return destroyed;
        }
        public void setDestroyed(boolean destroyed){
      this.destroyed = destroyed;
        }

}