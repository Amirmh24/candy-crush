public class Player {
    private final String id;
    private final String name;
    private int score;
    private int health;

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
        this.score = 0;
        this.health = 3;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getHealthString() {
        String string = "<html>. Health: <font color='red'>";
        for (int i = 0; i < health; i++) {
            string = string + "❤";
        }
        string = string + "</font></html>";
        return string;
    }

    public String getScoreString() {
        String string = "<html> Score: <font color='gray'> " + score + "</font></html>";
        return string;
    }
}