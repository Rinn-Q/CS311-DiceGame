package Backend;
    
public class menu 
{
    public GameFunctions game;
    public menu()
    {
        Player player1 = new Player("player 1");
        Player player2 = new Player("player 2");
        game = new GameFunctions(player1, player2);
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
