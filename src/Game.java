import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    private static final Random random=new Random();
    private final Player player;
    private final Ground ground;
    private final JFrame frame;
    private final JPanel board;
    private final JPanel status;
    private final JLabel health;
    private final JLabel score;
    private final int n, k;

    public Game(Player player, int n, int k) {
        this.n = n;
        this.k = k;
        this.player = player;
        this.ground = new Ground(n, k);
        this.frame = new JFrame();
        this.board = new JPanel();
        this.status = new JPanel();
        this.health = new JLabel(player.getHealthString());
        health.setFont(new Font(health.getFont().getName(), Font.PLAIN, 20));
        this.score = new JLabel(player.getScoreString());
        score.setFont(new Font(health.getFont().getName(), Font.PLAIN, 20));

        status.setLayout(new GridLayout(1, 2));
        status.add(health);
        status.add(score);
        status.setMaximumSize(new Dimension(n * 50, 100));
        status.setBackground(Color.WHITE);
        board.setLayout(new GridLayout(n, n));
        final Piece[] clicked = {null};
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                board.add(ground.getPiece(i, j).getButton());
                int I = i, J = j;
                ground.getPiece(i, j).getButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (ground.getPiece(I, J).getColor() != 0) {
                            if (clicked[0] == null) {
                                clicked[0] = ground.getPiece(I, J);
                            } else {
                                if ((clicked[0].getI() - I) * (clicked[0].getI() - I) + (clicked[0].getJ() - J) * (clicked[0].getJ() - J) == 1) {
                                    int color = clicked[0].getColor();
                                    clicked[0].setColor(ground.getPiece(I, J).getColor());
                                    ground.getPiece(I, J).setColor(color);
                                    int c = Math.max(check(I, J), check(clicked[0].getI(), clicked[0].getJ()));
                                    update(c);
                                }
                                clicked[0] = null;
                                board.repaint();

                            }
                        }
                    }
                });
            }
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(status);
        frame.add(board);
        frame.setVisible(true);
        frame.setSize(n * 50, n * 50 + 70);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public int check(int i, int j) {
        if (ground.getPiece(i, j).getColor() == 0)
            return 0;
        ArrayList<Piece> matchedPieces = new ArrayList<>();
        matchedPieces.add(ground.getPiece(i, j));
        int s = 1;
//        down
        while (i + s < n && ground.getPiece(i + s, j).getColor() == ground.getPiece(i, j).getColor()) {
            matchedPieces.add(ground.getPiece(i + s, j));
            s++;
        }
//        up
        s = 1;
        while (i - s >= 0 && ground.getPiece(i - s, j).getColor() == ground.getPiece(i, j).getColor()) {
            matchedPieces.add(ground.getPiece(i - s, j));
            s++;
        }
//        right
        s = 1;
        while (j + s < n && ground.getPiece(i, j + s).getColor() == ground.getPiece(i, j).getColor()) {
            matchedPieces.add(ground.getPiece(i, j + s));
            s++;
        }
//        left
        s = 1;
        while (j - s >= 0 && ground.getPiece(i, j - s).getColor() == ground.getPiece(i, j).getColor()) {
            matchedPieces.add(ground.getPiece(i, j - s));
            s++;
        }

        if (matchedPieces.size() >= 3)
            for (Piece piece : matchedPieces)
                piece.setColor(0);

        return matchedPieces.size();
    }

    public void update(int c) {
        if (c < 3)
            player.setHealth(player.getHealth() - 1);
        else if (c == 3)
            player.setScore(player.getScore() + 50);
        else if (c == 4)
            player.setScore(player.getScore() + 100);
        else {
            player.setScore(player.getScore() + 150);
            player.setHealth(player.getHealth() + 1);
        }
        health.setText(player.getHealthString());
        score.setText(player.getScoreString());

        if (player.getHealth() == 0) {
            System.exit(0);
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int color = ground.getPiece(i, j).getColor();
                int h = 0;
                while (i + h + 1 < n && ground.getPiece(i + h + 1, j).getColor() == 0)
                    h++;
                if (h > 0) {
                    ground.getPiece(i + h, j).setColor(color);
                    ground.getPiece(i, j).setColor(0);
                }
            }
        }
        creat();
    }
    public void creat(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (ground.getPiece(i, j).getColor()==0){
                    ground.getPiece(i,j).setColor(random.nextInt(k)+1);
                }
            }
        }
    }
}
