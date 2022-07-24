package ua.intellias.intellistart;
import java.math.BigDecimal;
import java.util.*;

public class Main {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();
    private static Map<Integer,ArrayList<Integer>> multiMapUser = new HashMap<Integer, ArrayList<Integer>>();
    private static ArrayList<Integer> multiMapUserProducts = new ArrayList<Integer>();
    private static Map<Integer,ArrayList<Integer>> multiMapProduct = new HashMap<Integer, ArrayList<Integer>>();
    private static ArrayList<Integer> multiMapBoughtProduct = new ArrayList<Integer>();

    public static void main(String[] args) {

       createUsers();

       createProducts();

       mainMenu();
}
    private static void createUsers(){
        //Create users
        User user0 = new User(0, "Bob", "Miller", new BigDecimal("1260.50"));
        User user1 = new User(1, "David", "Gonzales", new BigDecimal("2350.25"));
        User user2 = new User(2, "Bruno", "Lewis", new BigDecimal("3100.00"));

        // Add users to Collection ArrayList
        users.add(user0);
        users.add(user1);
        users.add(user2);
    }

    private static void createProducts(){
        //Create products
        Product product0 = new Product(0, "Pork", new BigDecimal("249.65"));
        Product product1 = new Product(1, "Veal", new BigDecimal("316.99"));
        Product product2 = new Product(2, "Chicken", new BigDecimal("56.75"));

        // Add products to Collection ArrayList
        products.add(product0);
        products.add(product1);
        products.add(product2);
    }

    private static void mainMenu(){
        System.out.println("Menu:");
        System.out.println("1: Display list of all users\n" +
                "2: Display list of all products\n" +
                "3: The user buys the product\n" +
                "4: Display list of user products\n" +
                "5: Display list of users that bought product\n" +
                "Press 0 to exit");

        System.out.print("\nEnter menu number: ");
        Scanner in = new Scanner(System.in);
        int numCase;
        while (!in.hasNextInt() || (numCase = in.nextInt()) > 5 || numCase < 0) {
            System.out.println("This is not a menu number!");
            System.out.print("Enter menu number: ");
            in.next();
        }

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
                userProducts();
                break;
            case 5:
                boughtProduct();
                break;
            case 0:
                break;
            default:
                System.out.println("Oooops, something wrong!");
        }
        in.close();
    }

    private static void returnToMenu(){

        System.out.println("\nPress 1 return to menu: ");
        System.out.println("Press 0 to exit ");
        Scanner in = new Scanner(System.in);
        int menuReturn;
        while (!in.hasNextInt() || ((menuReturn = in.nextInt()) != 0 && menuReturn != 1)) {
            System.out.println("Oooops, something wrong!");
            System.out.println("\nPress 1 return to menu: ");
            System.out.println("Press 0 to exit ");
            in.next();
        }
        if (menuReturn == 1){
            mainMenu();
        }
    }
    private static void userList(){
        System.out.println("\nDisplay list of all users:");
        for (int i = 0; i < users.size(); i++) {
            System.out.println("id = " + users.get(i).getId() + ", first name = " + users.get(i).getFirstName()
                    + ", last name = " + users.get(i).getLastName() + ", amountOfMoney = "
                    + users.get(i).getAmountOfMoney() + " UAH");
        }
        returnToMenu();
    }

    private static void productList(){
        System.out.println("\nDisplay list of all products:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println("id = " + products.get(i).getId() + ", name = " + products.get(i).getName()
                    + ", price = " + products.get(i).getPrice() + " UAH");
        }
        returnToMenu();
    }

    private static void buyProduct(){
        System.out.println("The user buys the product:");
        Scanner in = new Scanner(System.in);
        System.out.print("\nWho want to buy product? Enter id: ");
        int userId;
        while (!in.hasNextInt() || ((userId = in.nextInt()) > users.size() - 1 || userId < 0)) {
            System.out.println("Error, please try again!");
            System.out.print("Who want to buy product? Enter id: ");
            in.nextLine();
        }
        System.out.print("\nWhich product want to buy? Enter id: ");
        int productId;
        while (!in.hasNextInt() || ((productId = in.nextInt()) > products.size() - 1 || productId < 0)) {
            System.out.println("Error, please try again!");
            System.out.print("Which product want to buy? Enter id: ");
            in.nextLine();
        }
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < products.size(); j++) {
                if (userId == users.get(i).getId() && productId == products.get(j).getId()){
                    if(users.get(i).getAmountOfMoney().compareTo(products.get(j).getPrice()) == 1){
                        System.out.println("\nSuccess!!! " + users.get(i).getFirstName() + " "
                                + users.get(i).getLastName() + " bought " + products.get(j).getName());
                        //Decrease of user's money
                        users.get(i).setAmountOfMoney(users.get(i).getAmountOfMoney().subtract(products.get(j).getPrice()));

                        System.out.println(users.get(i).getFirstName() + " " + users.get(i).getLastName()
                                + " has left " + users.get(i).getAmountOfMoney() + " UAH");

                        //Add user products to Collection
                        multiMapUserProducts.add(products.get(j).getId());
                        multiMapUser.put(users.get(i).getId(), multiMapUserProducts);

                        //Add bought product to Collection
                        multiMapBoughtProduct.add(users.get(i).getId());
                        multiMapProduct.put(products.get(j).getId(), multiMapBoughtProduct);
                    }else {
                        System.out.println("\n" + users.get(i).getFirstName() + " " + users.get(i).getLastName()
                                + " has no money to buy " + products.get(j).getName());
                    }
                }
            }
        }
        returnToMenu();
    }

    private static void userProducts(){
        System.out.println("Display list of user products:");
        Scanner in = new Scanner(System.in);
        int userProductsId;

        while (!in.hasNextInt() || (userProductsId = in.nextInt()) > multiMapUser.size() - 1 || userProductsId < 0){
            System.out.println("Error, please try again!");
            in.nextLine();
        }
        for (int i = 0; i < multiMapUser.size(); i++) {
            if (multiMapUser.containsKey(userProductsId)){
                System.out.println(multiMapUser);
            }else
                System.out.println("This user has not purchased anything!");
        }
        returnToMenu();
    }

    private static void boughtProduct(){
        System.out.println("Display list of users that bought product:");
        Scanner in = new Scanner(System.in);
        int boughtProductId;

        while (!in.hasNextInt() || (boughtProductId = in.nextInt()) > multiMapProduct.size() - 1 || boughtProductId < 0){
            System.out.println("Error, please try again!");
            in.nextLine();
        }
        for (int i = 0; i < multiMapProduct.size(); i++) {
            if (multiMapProduct.containsKey(boughtProductId)){
                System.out.println(multiMapProduct);
            }else
                System.out.println("No one bought this product!");
        }
        returnToMenu();
    }
}