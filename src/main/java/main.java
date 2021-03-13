import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class main {

    public static void main(String[] args) {
        Connection conn = database.connect();
        database.selectAllCountries();
    }
}
