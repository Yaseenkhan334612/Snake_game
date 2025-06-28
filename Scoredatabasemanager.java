import java.sql.*;

public class ScoredatabaseManager {
    private Connection conn;

    public ScoreDatabaseManager() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/snake_game", "root", "f24cs1817");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTs highscore (id INT PRIMARY KEY AUTO_INCREMENT, score INT)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveScore(int score) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO highscore(score) VALUES(?)");
            ps.setInt(1, score);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getHighScore() {
        int max = 0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(score) AS max_score FROM highscore");
            if (rs.next()) {
                max = rs.getInt("max_score");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return max;
    }
}
