import java.sql.*;
import java.util.ArrayList;

public class database {

    //Used to establish connection to database.
    private static Connection connect() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:postgresql://ec2-54-74-35-87.eu-west-1.compute.amazonaws.com:5432/d6i76trh3o2h8i", "nrsatjxkiinfpe", "5241c9c1347855efed23a3c192f8a2f9f9c898c02b5dd94a355670782a5a752c");
        } catch (SQLException e) {
            Messages.databaseConnectionFailed("jdbc:postgresql://ec2-54-74-35-87.eu-west-1.compute.amazonaws.com:5432/d6i76trh3o2h8i", e.getMessage());
        }
        return con;
    }

    //Selects all countries from the databse and returns them in an array of strings (separated by "|")
    public static ArrayList<String> selectAllCountries() {
        String cmd = "SELECT * FROM countries";
        ArrayList<String> countries = new ArrayList<>();

        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            while (set.next()) {
                //Country c = new Country(set.getInt("id"));
                String temp = "";
                temp = temp + set.getString("id");
                temp = temp + ", ";
                temp = temp + set.getString("name");
                countries.add(temp);
            }

        } catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println("selectAllCountries - Error getting data from database!");
        }

        return countries;
    }

    //Selects all products from database and returns them in an array of Product class objects
    public static ArrayList<Product> selectAllProducts() {
        String cmd = "SELECT * FROM products";
        ArrayList<Product> products = new ArrayList<>();

        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            while (set.next()) {
                Product p = new Product(set.getInt("id"));
                p.name = set.getString("name");
                p.price = set.getDouble("price");
                p.description = set.getString("description");
                products.add(p);
            }

        } catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println("selectAllProducts - error getting data from database!");
        }

        return products;
    }

    //Gets user's id from the database if email and password are correct.
    public static int loginUser(String email, String password) {
        String cmd = "SELECT id FROM users WHERE email = '" + email + "' AND password = '" + password + "';";
        int id = 0;
        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            while (set.next()) {
                id = set.getInt("id");
            }

        } catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println("loginUser - error getting data from database!");
        }
        return id;
    }

    //Registers new user. Returns false if that email already exists and true if it worked.
    public static boolean registerNewUser(String email, String password, String name, String surname, String city) {
        String cmd = "SELECT registerNewUser('" + email + "', '" + password + "', '" + name + "', '" + surname + "', '" + city.split(",")[1].trim() + "');";
        boolean success = false;
        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            while (set.next()) {
                success = set.getBoolean(1);
            }

        } catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println("registerNewUser - error getting data from database!");
            System.out.println(e.getMessage());
        }
        return success;
    }

    //Selects all cities from database and returns them in an array of strings
    public static ArrayList<String> selectAllCities() {
        String cmd = "SELECT * FROM cities";
        ArrayList<String> cities = new ArrayList<>();

        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            while (set.next()) {
                String c = set.getString("name");
                c = c + ", " + set.getString("zip_code");
                cities.add(c);
            }

        } catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println("selectAllCities - error getting data from database!");
        }

        return cities;
    }

    //Adds an item to shopping cart
    public static void addToShoppingCart(int _product_id, int _user_id, double _current_price) {
        String cmd = "INSERT INTO \"shoppingCarts\" (product_id, user_id, current_price) VALUES ('" + _product_id + "', '" + _user_id + "', '" + _current_price + "');";
        try {Connection con = connect();
             Statement st = con.createStatement();
             st.executeUpdate(cmd);

        } catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println("addToShoppingCart - error getting data from database!");
            System.out.println(e.getMessage());
        }
    }

    //Returns user's shopping cart
    public static ArrayList<ShoppingCart> selectUserShoppingCart(int user_id) {
        String cmd = "SELECT sc.id, sc.date, sc.current_price, p.id, p.name, p.price, p.description FROM users u INNER JOIN \"shoppingCarts\" sc ON u.id = sc.user_id INNER JOIN products p ON sc.product_id = p.id WHERE u.id = '" + user_id + "' AND sc.bought = FALSE;";
        ArrayList<ShoppingCart> shoppingCart = new ArrayList<ShoppingCart>();

        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            while (set.next()) {
                //get all properties needed
                int sc_id = set.getInt(1);
                Date sc_date = set.getDate(2);
                double sc_current_price = set.getDouble(3);
                int p_id = set.getInt(4);
                String p_name = set.getString(5);
                double p_price = set.getDouble(6);
                String p_description = set.getString(7);

                //create Products object
                Product p = new Product(p_id, p_name, p_price, p_description);
                ShoppingCart sc = new ShoppingCart(sc_id, sc_date, sc_current_price, p);
                shoppingCart.add(sc);
            }

        } catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println("selectUserShoppingCart - error getting data from database!");
            System.out.println(e.getMessage());
        }

        return shoppingCart;
    }

    public static void removeShoppingCartItem(ShoppingCart shoppingCart, int user_id) {
        String cmd = "DELETE FROM \"shoppingCarts\" WHERE id = '" + shoppingCart.id + "' AND product_id = '" + shoppingCart.product.id + "' AND user_id = '" + user_id + "' AND date = '" + shoppingCart.date + "' AND current_price = '" + shoppingCart.current_price + "' LIMIT 1;";
        try {Connection con = connect();
            Statement st = con.createStatement();
            st.executeUpdate(cmd);

        } catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println("removeShoppingCartItem - error getting data from database!");
            System.out.println(e.getMessage());
        }
    }

    public static void BuyShoppingCartItem(int user_id) {
        String cmd = "UPDATE \"shoppingCarts\" SET bought = TRUE WHERE user_id = '" + user_id + "' AND bought = 'FALSE';";
        try {Connection con = connect();
            Statement st = con.createStatement();
            st.executeUpdate(cmd);

        } catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println("BuyShoppingCartItem - error getting data from database!");
            System.out.println(e.getMessage());
        }
    }
  
    public static ArrayList<String> selectAllCategories() {
        String cmd = "SELECT * FROM categories";
        ArrayList<String> category = new ArrayList<>();

        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            while (set.next()) {
                String name = set.getString("name");
                int id = set.getInt("id");

                category.add(id + " ; " + name);
            }

        } catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println("selectAllCategories - error getting data from database!");
        }

        return category;
    }

    //Add new product to database
    public static boolean AddNewProduct(String name, String price, String description, String category) {
        String cmd = "SELECT AddNewProduct('" + name + "', '" + price + "', '" + description + "', '" + category.split(",")[0].trim() + "');";
        boolean success = false;
        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            while (set.next()) {
                success = set.getBoolean(1);
            }

        } catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println("AddNewProduct - error getting data from database!");
            System.out.println(e.getMessage());
        }
        return success;
    }

    //Get all purchased items
    public static ArrayList<ShoppingCart> selectUserPurchasedItems(int user_id) {
        String cmd = "SELECT sc.id, sc.date, sc.current_price, p.id, p.name, p.price, p.description FROM users u INNER JOIN \"shoppingCarts\" sc ON u.id = sc.user_id INNER JOIN products p ON sc.product_id = p.id WHERE u.id = '" + user_id + "' AND sc.bought = TRUE;";
        ArrayList<ShoppingCart> shoppingCart = new ArrayList<ShoppingCart>();

        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            while (set.next()) {
                //get all properties needed
                int sc_id = set.getInt(1);
                Date sc_date = set.getDate(2);
                double sc_current_price = set.getDouble(3);
                int p_id = set.getInt(4);
                String p_name = set.getString(5);
                double p_price = set.getDouble(6);
                String p_description = set.getString(7);

                //create Products object
                Product p = new Product(p_id, p_name, p_price, p_description);
                ShoppingCart sc = new ShoppingCart(sc_id, sc_date, sc_current_price, p);
                shoppingCart.add(sc);
            }

        } catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println("selectUserPurchasedItems - error getting data from database!");
            System.out.println(e.getMessage());
        }

        return shoppingCart;
    }
}