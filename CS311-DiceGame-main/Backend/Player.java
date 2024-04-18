package Backend;
public class Player implements I_PLayer{
    private String name;
    public Player(String name/* , int playerID, int rank*/)
    {
        this.name = name;
/*      this.playerID = playerID;
        this.rank = rank;*/
    }

    public String getPname()
    {
        return name;
    }

/*     public int getPID ()
    {
        return playerID;
    }

    public int  etPRank()
    {
        return rank;
    }*/
}
