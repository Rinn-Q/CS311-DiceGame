package Backend;

public interface GameFunctions_Interface {
    void printState();
    void printChoice();
    void play(int choice);
    void switchActive();
    int getActiveplayer();
    boolean isGameOver();
    void updateScore();
    int rolldice();
    void resetScore();
    int[] getActivescore();
    int[] getScore();
}
