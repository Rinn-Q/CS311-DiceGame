package Backend;

public class GameFunctions {

    public int goalscore, activescore[];
    public int activePlayer;
    private int score[];
    private Player player[];
    private diceRNG rand = new diceRNG();  //Random number generator for random moves of players
    

    // Constructor for the game object with two players and their scores initialized to  0
    public GameFunctions(Player p1, Player p2) 
    {
        this.goalscore = 40;
        this.score = new int[2];
        this.activePlayer = 0;
        this.activescore = new int[2];
        this.player = new Player[] {p1, p2};
        this.activescore[0] = this.activescore[1] = 0;
        this.score[0] = this.score[1] = 0;
    }


    /* Prints representation of the current state of the game */
    public void printState() 
    {
        System.out.println( "\nGoal Score: " + this.goalscore);
        System.out.println( "\nPlayers:\n\t" + this.player[0].getPname() + "\nScore: " + this.score[0] + "-" + this.score[1] + "\n\t" + this.player[1].getPname());
        System.out.println( this.player[activePlayer].getPname() + "'s turn\n");
    }


    public void printChoice()
    {
        System.out.println("current collected score for " + player[activePlayer].getPname() + " is:" + activescore[activePlayer]);
        System.out.println("1. Roll dice");
        System.out.println("2. Hold and switch to next player");
    }


    public void play(int choice)
    {
        if(choice == 1)
        {
            System.out.println("you rolled: " + this.rolldice());  
        }
        else if(choice == 2)
        {
            this.updateScore();
            this.switchActive();
        }
        else
        {
            System.out.println("Invalid choice");
        }
    }

    /*changes the active player*/
    public void switchActive()
    {
        rand.rngReset();
        if(activePlayer == 1)
        {
            activePlayer = 0;
        }
        else
        {
            activePlayer = 1;
        }
    }


    /*returns wich player is active*/
    public int getActiveplayer()
    {
        return activePlayer;
    }

    /*checks win condition */
    public boolean isGameOver()
    {
        if(activePlayer == 0)
        {
            if(score[0] >= goalscore)
            {
                System.out.println("Player 1 wins!");
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            if(score[1] >= goalscore)
            {
                System.out.println("Player 2 wins!");
                return true;
            }
            else
            {
                return false;
            }
        }
    }


    /* Updates the score based on who scored - either player1 or player2 */
    public void updateScore()
    {
        score[activePlayer] += activescore[activePlayer];
        activescore[0] = 0;
        activescore[1] = 0;
    }


    /*dice roll for active player */
    public int rolldice()
    {
        int dice = rand.diceRoll();
        if(dice == 1)
        {
            activescore[activePlayer] = 0;
            this.switchActive();
            return 0;
        }
        else
        {
            activescore[activePlayer] += dice;
            return dice;
        }
    }


    /* Resets the score back to 0-0 */
    public void resetScore()
    {
        activePlayer = 0;
        this.score[0]=this.score[1]=0;
    }

    public int[] getActivescore()
    {
        return this.activescore;
    }


    /* Returns an array containing the current score */
    public int[] getScore()
    {
        return this.score;
    }
}