@Override
public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (backgroundImage != null) {
        g.drawImage(backgroundImage, 0, 0, width, height, this);
    }

    // Draw border walls
    g.setColor(new Color(101, 67, 33)); // Dark brown
    g.fillRect(0, 0, width, boxSize); // Top
    g.fillRect(0, height - boxSize, width, boxSize); // Bottom
    g.fillRect(0, 0, boxSize, height); // Left
    g.fillRect(width - boxSize, 0, boxSize, height); // Right

    // Background behind snake
    g.setColor(Color.BLACK);
    g.fillRect(boxSize, boxSize, width - 2 * boxSize, height - 2 * boxSize);

    // Yellow food
    g.setColor(Color.YELLOW);
    Point foodPos = food.getPosition();
    g.fillOval(foodPos.x * boxSize, foodPos.y * boxSize, boxSize, boxSize);

    // Snake body
    for (int i = 0; i < snake.getBody().size(); i++) {
        Point p = snake.getBody().get(i);
        if (i == 0) {
            g.setColor(Color.RED); // Head
        } else {
            g.setColor(new Color(150, 0, 0)); // Body
        }
        g.fillRoundRect(p.x * boxSize, p.y * boxSize, boxSize, boxSize, 10, 10);
    }

    // Eyes on head
    if (!snake.getBody().isEmpty()) {
        Point head = snake.getBody().get(0);
        int x = head.x * boxSize;
        int y = head.y * boxSize;
        g.setColor(Color.white);
        g.fillOval(x + 4, y + 4, 5, 5);
        g.fillOval(x + boxSize - 9, y + 4, 5, 5);
    }

    // Score
    g.setColor(Color.WHITE);
    g.setFont(new Font("Times New Roman", Font.BOLD, 20));
    g.drawString("Score: " + (snake.getBody().size() - 1), 10, 20);

    // Game Over message
    if (gameOver) {
        g.setColor(Color.white);
        g.setFont(new Font("Times New Roman", Font.BOLD, 30));
        g.drawString("Game Over!Press R to Restart", 110, height / 2 + 50);
    }
}
