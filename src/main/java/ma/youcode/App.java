package ma.youcode;


import ma.youcode.controllers.GameController;
import ma.youcode.services.GameService;
import ma.youcode.utils.Printer;

public class App
{
    public static void main( String[] args )
    {
        lunch();
    }

    public static void lunch(){
        Printer.into();
        GameController controller = new GameController(new GameService());
        controller.start();
    }
}
