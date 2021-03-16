import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                temp = temp + "|";
                temp = temp + set.getString("name");
                countries.add(temp);
            }

        } catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println("Error getting data from database!");
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
                p.price = set.getFloat("price");
                p.description = set.getString("description");
                products.add(p);
            }

        } catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println("Error getting data from database!");
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
            System.out.println("Error getting data from database!");
        }
        return id;
    }

    //Registers new user. Returns false if that email already exists and true if it worked.
    public static boolean registerNewUser(String email, String password, String name, String surname, String city) {
        String cmd = "SELECT registerNewUser('" + email + "', '" + password + "', '" + name + "', '" + surname + "', '" + city + "';";
        boolean added = false;
        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            while (set.next()) {
                added = set.getBoolean(0);
            }

        } catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println("Error getting data from database!");
        }
        return added;
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
                c = c + " | " + set.getString("zip_code");
                cities.add(c);
            }

        } catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println("Error getting data from database!");
        }

        return cities;
    }
}
