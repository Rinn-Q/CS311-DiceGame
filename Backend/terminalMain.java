package Backend;

import java.util.*;

public class terminalMain{
    public static void main(String[] args) {
        menu Game = new menu();
        try (Scanner sc = new Scanner(System.in)) {
            for(;;)
            {
                System.out.println("\t1.Play game\n\t2.instructions\n\t3.quit");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        for(;;)
                        {
                            Game.game.printState();
                            Game.game.printChoice();
                            Game.game.play(sc.nextInt());
                            if(Game.game.isGameOver())
                            {
                                break;
                            }
                        }
                        break;
                    case 2:
                        System.out.println(Game.getInstruction());
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            }
        }
        catch(Exception e){
            System.out.println("Catched excepiton:" + e.toString());
        }
    }
}
