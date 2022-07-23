package ua.intellias.intellistart;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Main {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();
    private static HashMap<Integer, Integer> userProducts = new HashMap<>();

    public static void main(String[] args) {

        //Create users
        User user1 = new User(1, "Bob", "Miller", 5000);
        User user2 = new User(2, "David", "Gonzales", 2000);
        User user3 = new User(3, "Bruno", "Lewis", 4000);

        // Add users to Collection ArrayList
        users.add(user1);
        users.add(user2);
        users.add(user3);

        //Create products
        Product product1 = new Product(1, "Pork", 2150);
        Product product2 = new Product(2, "Veal", 3250);
        Product product3 = new Product(3, "Chicken", 850);

        // Add products to Collection ArrayList
        products.add(product1);
        products.add(product2);
        products.add(product3);

        //Main menu
        System.out.println("Menu:");
        System.out.println("1: Display list of all users\n" +
                "2: Display list of all products\n" +
                "3: The user buys the product\n" +
                "4: Display list of user products\n" +
                "5: Display list of users that bought product");

        System.out.print("\nEnter menu number: ");
        Scanner in = new Scanner(System.in);
        int numCase = in.nextInt();

        switch (numCase) {
            case 1:
                userList();
                break;
            case 2:
                productList();
                break;
            case 3:
                buyProduct();
                break;
            case 4:
                System.out.println("Display list of user products:");
                break;
            case 5:
                System.out.println("Display list of users that bought product:");
                break;
            default:
                System.out.println("Oooops, something wrong!");
        }
        in.close();
}

    private static void userList(){
        System.out.println("\nDisplay list of all users:");
        for (int i = 0; i < users.size(); i++) {
            System.out.println("id = " + users.get(i).getId() + ", first name = " + users.get(i).getFirstName()
                    + ", last name = " + users.get(i).getLastName() + ", amountOfMoney = " + users.get(i).getAmountOfMoney() + " UAH");
        }
    }

    private static void productList(){
        System.out.println("\nDisplay list of all products:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println("id = " + products.get(i).getId() + ", name = " + products.get(i).getName()
                    + ", price = " + products.get(i).getPrice() + " UAH");
        }
    }

    private static void buyProduct(){
        System.out.println("The user buys the product:");
        Scanner in = new Scanner(System.in);
        System.out.print("\nWho want to buy product? Enter id: ");
        int userId = 0;
        while (!in.hasNextInt() || (userId = in.nextInt()) > 3 || userId < 1) {
            System.out.println("Error, please try again!");
            in.nextLine();
        }
        System.out.print("\nWhich product want to buy? Enter id: ");
        int productId = 0;
        while (!in.hasNextInt() || (productId = in.nextInt()) > 3 || productId < 1) {
            System.out.println("Error, please try again!");
            in.nextLine();
        }
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < products.size(); j++) {

                if (userId == users.get(i).getId() && productId == products.get(j).getId()){
                    if(users.get(i).getAmountOfMoney() >= products.get(j).getPrice()){
                        System.out.println("Success!!! " + users.get(i).getFirstName() + " " + users.get(i).getLastName() + " bought " + products.get(j).getName());
                        //Decrease of user's money
                        users.get(i).setAmountOfMoney(users.get(i).getAmountOfMoney() - products.get(j).getPrice());
                        //Add user products to Collection HashMap
                        userProducts.put(users.get(i).getId(), products.get(j).getId());
                    }else {
                        System.out.println(users.get(i).getFirstName() + " " + users.get(i).getLastName() + " has no money!");
                    }
                }
            }
        }
    }

}