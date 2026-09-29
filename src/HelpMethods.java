import java.util.Scanner;

public class HelpMethods {
    private static Scanner sc= new Scanner(System.in);
    public static int InputInteger() {
        while (true) {
            try {
                String s =sc.nextLine();
                int i =Integer.parseInt(s);
                return i;

            } catch (NumberFormatException e){
                System.out.println("Write only numbers");
            }
        }

    }
    public static String InputString(){
            return sc.nextLine();
    }
}
