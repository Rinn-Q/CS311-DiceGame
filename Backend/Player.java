package Backend;
import java.io.Serializable;
import java.net.*;

public class Player implements Player_Interface, Serializable
{
    private String name;
    private Socket playerID;
    //private int rank;
    public Player(String name , Socket playerID)
    {
        this.name = name;
        this.playerID = playerID;
//        this.rank = rank;
    }
    public Player(String name)
    {
        this.name = name;
//        this.rank = rank;
    }

    public String getPname()
    {
        return name;
    }

     public Socket getPID ()
    {
        return playerID;
    }
/*
    public int  etPRank()
    {
        return rank;
    }*/
}
