package UI;

public class ConsoleDebug {

    public static void debug(String message){
        print("<DEBUG> " + message);
    }

    public static void alert(String message){
        print("<ALERT> " + message);
    }

    public static void info(String message){
        print("<INFO> " + message);
    }

    public static void print(String print){
        System.out.println(print);
    }

}
