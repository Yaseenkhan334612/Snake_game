import java.awt.*;
import java.util.ArrayList;

public class Snake {
    private ArrayList<Point> body;
    private String direction;
    private boolean growing = false;

   public Snake() {
    body = new ArrayList<>();
    body.add(new Point(5, 5)); 
    direction = "RIGHT";
}

    public void move() {
        Point head = body.get(0);
        Point newHead = new Point(head);

        switch (direction) {
            case "UP": newHead.y--; break;
            case "DOWN": newHead.y++; break;
            case "LEFT": newHead.x--; break;
            case "RIGHT": newHead.x++; break;
        }

        body.add(0, newHead);

        if (growing) {
            growing = false; 
        } else {
            body.remove(body.size() - 1);
    }

    public void grow() {
        growing = true; 
    }

    public ArrayList<Point> getBody() {
        return body;
    }

    public void setDirection(String dir) {
        direction = dir;
    }

    public String getDirection() {
        return direction;
    }
    
}
}