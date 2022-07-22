package ua.intellias.intellistart;
import java.util.Scanner;
public class Main {
    public static void showMenu(){
        Scanner in = new Scanner(System.in);
        System.out.println("Menu:");
        System.out.println("1: Display list of all users\n" +
                "2: Display list of all products\n" +
                "3: The user buys the product\n" +
                "4: Display list of user products\n" +
                "5: Display list of users that bought product");
        System.out.print("\nEnter menu number: ");
        int numCase = in.nextInt();
        in.close();
        switch (numCase) {
            case 1:
                System.out.println("TDisplay list of all users!");

                break;
            case 2:
                System.out.println("Display list of all products!");
                break;
            case 3:
                System.out.println("The user buys the product!");
                break;
            case 4:
                System.out.println("Display list of user products!");
                break;
            case 5:
                System.out.println("Display list of users that bought product!");
                break;
            default:
                System.out.println("Oooops, something wrong !");

        }

    }
    public static void main(String[] args) {
    showMenu();
}

}