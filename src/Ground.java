import java.util.Random;

public class Ground {
    private static final Random random=new Random();
    private int n;
    private int k;
    private Piece[][] pieces;

    public Ground(int n,int k) {
        this.n = n;
        pieces = new Piece[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pieces[i][j] = new Piece(random.nextInt(k)+1,i,j);
            }
        }
    }

    public void setPiece(Piece piece) {
        pieces[piece.getI()][piece.getJ()] = piece;
    }

    public Piece getPiece(int i, int j) {
        return pieces[i][j];
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public int getN() {
        return n;
    }
}
