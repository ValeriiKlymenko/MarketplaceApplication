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

        //Create users
        User user0 = new User(0, "Bob", "Miller", new BigDecimal("260.50"));
        User user1 = new User(1, "David", "Gonzales", new BigDecimal("350.25"));
        User user2 = new User(2, "Bruno", "Lewis", new BigDecimal("100.00"));

        // Add users to Collection ArrayList
        users.add(user0);
        users.add(user1);
        users.add(user2);

        //Create products
        Product product0 = new Product(0, "Pork", new BigDecimal("249.65"));
        Product product1 = new Product(1, "Veal", new BigDecimal("316.99"));
        Product product2 = new Product(2, "Chicken", new BigDecimal("56.75"));

        // Add products to Collection ArrayList
        products.add(product0);
        products.add(product1);
        products.add(product2);

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
                userProducts();
                break;
            case 5:
                boughtProduct();
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
                    + ", last name = " + users.get(i).getLastName() + ", amountOfMoney = "
                    + users.get(i).getAmountOfMoney() + " UAH");
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
        while (!in.hasNextInt() || (userId = in.nextInt()) > users.size() || userId < 0) {
            System.out.println("Error, please try again!");
            in.nextLine();
        }
        System.out.print("\nWhich product want to buy? Enter id: ");
        int productId = 0;
        while (!in.hasNextInt() || (productId = in.nextInt()) > products.size() || productId < 0) {
            System.out.println("Error, please try again!");
            in.nextLine();
        }
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < products.size(); j++) {
                if (userId == users.get(i).getId() && productId == products.get(j).getId()){
                    if(users.get(i).getAmountOfMoney().compareTo(products.get(j).getPrice()) == 1){
                        System.out.println("Success!!! " + users.get(i).getFirstName() + " "
                                + users.get(i).getLastName() + " bought " + products.get(j).getName());
                        //Decrease of user's money
                        users.get(i).setAmountOfMoney(users.get(i).getAmountOfMoney().subtract(products.get(j).getPrice()));

                        //Add user products to Collection
                        multiMapUserProducts.add(products.get(j).getId());
                        multiMapUser.put(users.get(i).getId(), multiMapUserProducts);

                        //Add bought product to Collection
                        multiMapBoughtProduct.add(users.get(i).getId());
                        multiMapProduct.put(products.get(j).getId(), multiMapBoughtProduct);
                    }else {
                        System.out.println(users.get(i).getFirstName() + " " + users.get(i).getLastName()
                                + " has no money to buy " + products.get(j).getName());
                    }
                }
            }
        }
    }

    private static void userProducts(){
        System.out.println("Display list of user products:");
        Scanner in = new Scanner(System.in);
        int userProductsId;

        while (!in.hasNextInt() || (userProductsId = in.nextInt()) > multiMapUser.size() || userProductsId < 0){
            System.out.println("Error, please try again!");
            in.nextLine();
        }
        for (int i = 0; i < multiMapUser.size(); i++) {
            if (multiMapUser.containsKey(userProductsId)){
                System.out.println(multiMapUser);
            }else
                System.out.println("This user has not purchased anything!");
        }
    }

    private static void boughtProduct(){
        System.out.println("Display list of users that bought product:");
        Scanner in = new Scanner(System.in);
        int boughtProductId;

        while (!in.hasNextInt() || (boughtProductId = in.nextInt()) > multiMapProduct.size() || boughtProductId < 0){
            System.out.println("Error, please try again!");
            in.nextLine();
        }
        for (int i = 0; i < multiMapProduct.size(); i++) {
            if (multiMapProduct.containsKey(boughtProductId)){
                System.out.println(multiMapProduct);
            }else
                System.out.println("No one bought this product!");
        }
    }
}