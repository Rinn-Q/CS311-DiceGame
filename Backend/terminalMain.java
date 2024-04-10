package Backend;

import java.util.*;

public class terminalMain {
    public static void main(String[] args) {
        menu game = new menu();
        Scanner sc = new Scanner(System.in);
        for(;;)
        {
            System.out.println("\t1.Play game\n\t2.instructions\n\t3.quit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    for(;;)
                    {
                        game.game.printState();
                        game.game.printChoice();
                        game.game.play(sc.nextInt());
                        if(game.game.isGameOver())
                        {
                            break;
                        }
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
        }
    }
}
