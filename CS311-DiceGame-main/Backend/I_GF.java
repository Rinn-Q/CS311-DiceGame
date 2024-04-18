package Backend;

public interface I_GF {
    void printState();
    void printChoice();
    void play(int choice);
    void switchActive();
    int getActiveplayer();
    boolean isGameOver();
    void updateScore();
    int rolldice();
    void resetScore();
    int[] getScore();
}
