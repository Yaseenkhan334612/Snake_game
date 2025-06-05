import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    public static final int boxSize = 23;
    public static final int width = 600;
    public static final int height = 600;

    private Snake snake;
    private Food food;
    private boolean running = true;
    private boolean gameOver = false;
    private Timer timer;
    private SoundPlayer sound;
    private Image backgroundImage;
    public static ArrayList<Point> wallBlocks;

    public GamePanel() {
        this.setPreferredSize(new Dimension(width, height));
        this.setFocusable(true);
        this.addKeyListener(this);
        loadImage();
        snake = new Snake();

        wallBlocks = new ArrayList<>(); 

        int columns = width / boxSize;
        int rows = height / boxSize;

       //  Top wall 
       for (int y = 0; y <= 1; y++) {
       for (int x = 0; x < columns; x++) {
        wallBlocks.add(new Point(x, y));
          }
        } 
 
       //  Bottom wall
       for (int y = rows - 2; y < rows; y++) {
       for (int x = 0; x < columns; x++) {
       wallBlocks.add(new Point(x, y));
        }
      } 

     //  Left wall
     for (int x = 0; x <= 1; x++) {
     for (int y = 0; y < rows; y++) {
        wallBlocks.add(new Point(x, y));
         } 
     }

     //Right wall
     for (int x = columns - 2; x < columns; x++) {
     for (int y = 0; y < rows; y++) {
        wallBlocks.add(new Point(x, y));
         }
       }

        food = new Food(snake.getBody()); 
        sound = new SoundPlayer();
        sound.playGameStartSound(); 
        timer = new Timer(130, this);
        timer.start();
   
    }
   
    private void loadImage() {
    try {
        backgroundImage = ImageIO.read(new File("backround.jpg"));
    } catch (IOException e) {
        System.out.println("Background not loaded.");
    }
 }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            snake.move();

          if (snake.getBody().get(0).equals(food.getPosition())) {
              snake.grow();
              food.generateNewPosition(snake.getBody()); // Fixed
              sound.playEatSound();
            }

            Point head = snake.getBody().get(0);
            // Check for wall collision
            if (wallBlocks.contains(head)) {
            running = false;
            gameOver = true;
            sound.stopSound();
            }


            for (int i = 1; i < snake.getBody().size(); i++) {
                if (head.equals(snake.getBody().get(i))) {
                    running = false;
                    gameOver = true;
                    sound.stopSound();
                    break;
                }
            }
            repaint();
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, width, height, this);
        }
        g.fillRect(0, 0, width, height);
    }

        @Override
    public void keyPressed(KeyEvent e) {
        if (!running && gameOver && e.getKeyCode() == KeyEvent.VK_R) {
            snake = new Snake();
            food = new Food(snake.getBody()); //Fixed to pass snake body
            running = true;
            gameOver = false;
            sound.playGameStartSound(); //  Play music again

        }

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (!snake.getDirection().equals("DOWN")) snake.setDirection("UP");
                break;
            case KeyEvent.VK_DOWN:
                if (!snake.getDirection().equals("UP")) snake.setDirection("DOWN");
                break;
            case KeyEvent.VK_LEFT:
                if (!snake.getDirection().equals("RIGHT")) snake.setDirection("LEFT");
                break;
            case KeyEvent.VK_RIGHT:
                if (!snake.getDirection().equals("LEFT")) snake.setDirection("RIGHT");
                break;
        }
    }

    @Override 
    public void keyReleased(KeyEvent e) {}
    @Override 
    public void keyTyped(KeyEvent e) {}
}
