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
    }

    private void loadImage() {
        try {
            backgroundImage = ImageIO.read(new File("backround.jpg"));
        } catch (IOException e) {
            System.out.println("Background not loaded.");
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

    @Override public void actionPerformed(ActionEvent e) {}
    @Override public void keyPressed(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
