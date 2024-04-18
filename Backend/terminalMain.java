package Backend;

import java.util.*;

public class terminalMain {
    public static void main(String[] args) {
        menu game = new menu();
        for(;;)
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("\t1.Play game\n\t2.instructions\n\t3.quit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    for(;;)
                    {
                        if(game.game.isGameOver())
                        {
                            break;
                        }
                        game.game.printState();
                        game.game.printChoice();
                        game.game.play(sc.nextInt());
                    }
                    break;
                case 2:
                    System.out.println(game.getInstruction());
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    break;
            }
            sc.close();
        }
    }
}
