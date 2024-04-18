package Backend;

import java.util.Random;
public class diceRNG implements I_diceRNG{

    //counter for roll since 1 
    private int rollsSinceOne;
    // The chance for rolling a 1 and 2-6 numbers
    private int chancefor1;

    public diceRNG()
    {
        rollsSinceOne = 0;
        chancefor1 = 20;
    }

    
    public int diceRoll() 
    {
        // Create a Random object
        Random rand = new Random();

        // Variables to store previous roll and current roll
        int currentRoll, chance;

        int numRolls = 10; // You can adjust the number of rolls as needed
        // The number of roll defines the sure chance for rolling 1 

    
        // The chance for rolling a 1 and 2-6 numbers

        // generating a chance for 1 and 2-6
        chance = rand.nextInt(100);
        if(chance <= chancefor1)
        {
            //rolling 1
            currentRoll = 1;
        }
        else
        {
            // generating roll for 2-6
            currentRoll = rand.nextInt(5) + 2;
        }

        // Check if current roll is 1
        if (currentRoll == 1) {
            System.out.println("Roll " + rollsSinceOne + ": You rolled a 1");
            rollsSinceOne = 0; // Reset the count of rolls since last 1
            chancefor1 = 20; // Reset the chance for 1 
        } 
        else 
        {
            System.out.println("Roll " + rollsSinceOne + ": You rolled a " + currentRoll + "chance for 1:" + chancefor1);
            rollsSinceOne++; // Increment the count of rolls since last 1
            // Check if the probability of rolling a 1 should increase
            if(rollsSinceOne > 0)
            {
                chancefor1 += 5;
            }
        }



        // Check if a 1 hasn't been rolled yet within the first 10 rolls
        if (rollsSinceOne >= numRolls) {
            System.out.println("Insured roll: Rolling a 1 to ensure it appears at least once within 10 rolls");
            // Reset count of rolls since last 1
            rollsSinceOne = 0;
            // Force a roll to 1 is obtained
            if (currentRoll != 1) {
                currentRoll = 1;
                System.out.println("Insured roll: You rolled a " + currentRoll);
            }
        }
        return currentRoll;
    }
    
    // Resets the counter for chance adder
    public void rngReset()
    {
        chancefor1 = 20;
        rollsSinceOne = 0;
    }
}