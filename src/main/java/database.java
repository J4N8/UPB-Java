import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class database {

    private static Connection connect() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:postgresql://ec2-54-74-35-87.eu-west-1.compute.amazonaws.com:5432/d6i76trh3o2h8i", "nrsatjxkiinfpe", "5241c9c1347855efed23a3c192f8a2f9f9c898c02b5dd94a355670782a5a752c");
        } catch (SQLException e) {
            Messages.databaseConnectionFailed("jdbc:postgresql://ec2-54-74-35-87.eu-west-1.compute.amazonaws.com:5432/d6i76trh3o2h8i", e.getMessage());
        }
        return con;
    }

    //change from void
    public static ArrayList<String> selectAllCountries() {
        String cmd = "SELECT * FROM countries";
        ArrayList<String> countries = new ArrayList<>();

        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            // loop through the records
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
            System.out.println("Error selecting from database!");
        }

        return countries;
    }

    public static ArrayList<Product> selectAllProducts() {
        String cmd = "SELECT * FROM products";
        ArrayList<Product> products = new ArrayList<>();

        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet set = st.executeQuery(cmd)) {

            // loop through the records
            while (set.next()) {
                Product p = new Product(set.getInt("id"));
                p.name = set.getString("name");
                p.price = set.getFloat("price");
                p.description = set.getString("description");
                products.add(p);
            }

        } catch (SQLException e) {
            //Messages.databaseReadingError(database, e.getMessage());
            System.out.println("Error selecting from database!");
        }

        return products;
    }
}
