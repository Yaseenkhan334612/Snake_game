import java.awt.*;
import java.util.Random;
import java.util.ArrayList;

public class Food {
    private Point position;

    public Food(ArrayList<Point> snakeBody) {
        generateNewPosition(snakeBody);
    }

    public void generateNewPosition(ArrayList<Point> snakeBody) {
        Random rand = new Random();
        Point newPos;
        do {
            int x = rand.nextInt(GamePanel.width / GamePanel.boxSize);
            int y = rand.nextInt(GamePanel.height / GamePanel.boxSize);
            newPos = new Point(x, y);
        } while (snakeBody.contains(newPos) || GamePanel.wallBlocks.contains(newPos));
        position = newPos;
    }

    public Point getPosition() { return position; }
}
