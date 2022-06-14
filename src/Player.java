import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;

    public class Player {
        private static int INITIAL_NUM_LIVES = 3;
        private static int IMAGE_Y_POSITION = 450;
        private static int IMAGE_H_GAP = 5;
        private int numLives;

        public Player() {
            this.numLives = INITIAL_NUM_LIVES;
        }
        public void killPlayer() {
            numLives--;
        }
        public boolean isAlive() {
            return (numLives > 0);
        }
        public void draw(Graphics2D g2) {
            try {
                Image image = ImageIO.read(new File("src/player.gif"));
                for (int x = 0; x < numLives; x++) {
                    g2.drawImage(image, x * (image.getWidth(null) + IMAGE_H_GAP), IMAGE_Y_POSITION, null);
                }
            } catch (Exception newException) {
            }
        }
    }

