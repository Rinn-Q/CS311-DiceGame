package Backend;
public class Player {
    private String name;
    //private int playerID;
    //private int rank;
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
