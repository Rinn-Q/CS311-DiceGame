package Backend;

import java.io.Serializable;
import java.net.Socket;

public class menu implements menu_Interface, Serializable, Cloneable
{
    public GameFunctions game;
    public menu()
    {
        Player player1 = new Player("player 1");
        Player player2 = new Player("player 2");
        game = new GameFunctions(player1, player2);
    }
    public menu(Socket p1,Socket p2 ,String ip, int port)
    {        
        Player player1 = new Player("player 1", p1);
        Player player2 = new Player("player 2", p2);
        game = new GameFunctions(player1, player2);
    }
    public Object clone() throws CloneNotSupportedException 
    { 
        return super.clone(); 
    } 
    /*the instruction*/
    public String getInstruction()
    {
        return 
        "1. 2 тоглогч хэн нь түрүүлж эхлэхээ хоорондоо тохирно. " + "\n" +
        "2. Таныг оноогоо татах эсвэл алдах хүртэл одоогийн оноонд шооны буусан оноо нэмэгдэнэ. " + "\n" +
        "3. Ээлжээр шоог хаях бөгөөд таны хаясан шоо 1 нүдээр буух хүртэл эсвэл цуглуулсан оноог татаж авах хүртэл хаяж болно." + "\n" + 
        "4. Түрүүлж 20 оноонд хүрсэн тоглогч ялалт байгуулна.";
    }
}
