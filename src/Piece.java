import javax.swing.*;
import java.awt.*;

public class Piece {
    private JButton button;
    private int color;
    private int i, j;

    public Piece(int color, int i, int j) {
        button = new JButton(new ImageIcon(color+".png"));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setBackground(Color.WHITE);
        this.i = i;
        this.j = j;
        this.color=color;
    }

    public JButton getButton() {
        return button;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color=color;
        button.setIcon(new ImageIcon(color+".png"));
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
