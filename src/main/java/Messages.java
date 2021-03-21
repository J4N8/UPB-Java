import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Contains often-used error messages
 */
public class Messages {

    /**
     * Displays a record deletion confirmation dialog box
     *
     * @param panel a JPanel (used to make a modal dialog)
     * @return true if the user has clicked OK
     */
    static boolean confirmDeleteRecord(JPanel panel) {
        int status = JOptionPane.showConfirmDialog(panel,
                "The current record will be deleted!\n" +
                        "This cannot be undone! Continue?", "Confirm deletion",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        return status == JOptionPane.YES_OPTION;
    }

    /**
     * Displays a "Database connection failed" error message
     *
     * @param database     the database path
     * @param errorMessage the error message (ex. getMessage() from an exception)
     */
    static void databaseConnectionFailed(String database, String errorMessage) {
        JOptionPane.showMessageDialog(null,
                "Could not connect to the database " + database + ":\n" +
                        errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays a database creation error dialog box
     *
     * @param database     database file name
     * @param errorMessage output of getMessage() on an exception
     * @param panel        a JPanel (used to make a modal dialog)
     */
    static void databaseCreationError(String database, String errorMessage, JPanel panel) {
        JOptionPane.showMessageDialog(panel,
                "Could not create " + database + ":\n" +
                        errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays a data reading error dialog box
     *
     * @param database     the database path
     * @param errorMessage the error message (ex. getMessage() from an exception)
     */
    static void databaseReadingError(String database, String errorMessage) {
        JOptionPane.showMessageDialog(null,
                "Error while reading data from the database " + database + ":\n" +
                        errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays a data saving error dialog box
     *
     * @param database     database path (or other string to be shown as
     *                     the database name
     * @param errorMessage detailed error message, preferably output
     *                     of getMessage() method on an exception
     * @param panel        a JPanel (used to make a modal dialog)
     */
    static void databaseSavingError(String database, String errorMessage, JPanel panel) {
        JOptionPane.showMessageDialog(panel,
                "Error while writing data to the database " + database + ":\n" +
                        errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays a "Fill in the required fields" error message
     *
     * @param panel a JPanel (used to make a modal dialog)
     */
    static void fillInFields(JPanel panel) {
        JOptionPane.showMessageDialog(panel,
                "Please fill in at least the name field.",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays a "Can't delete the only record" error message
     *
     * @param panel a JPanel (used to make a modal dialog)
     */
    static void cantDeleteOnlyRecord(JPanel panel) {
        JOptionPane.showMessageDialog(panel,
                "Can't delete the only record!",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    static void registerUserSuccessful(JPanel panel) {
        JOptionPane.showMessageDialog(panel,
                "You registered successfully. Go to login tab to log in!",
                "Registration complete", JOptionPane.INFORMATION_MESSAGE);
    }

    static void registerUserFailed(JPanel panel) {
        JOptionPane.showMessageDialog(panel,
                "Registration failed. Please try again!",
                "Registration failed", JOptionPane.ERROR_MESSAGE);
    }

    static void loginUserSuccessful(JPanel panel) {
        JOptionPane.showMessageDialog(panel,
                "You logged in successfully!",
                "Login successful", JOptionPane.INFORMATION_MESSAGE);
    }

    static void loginUserFailed(JPanel panel) {
        JOptionPane.showMessageDialog(panel,
                "Login failed. Please try again!",
                "Login failed", JOptionPane.ERROR_MESSAGE);
    }

    static void addedToShoppingCart(JPanel panel) {
        JOptionPane.showMessageDialog(panel,
                "Product successfully added to shopping cart!",
                "Added to shopping cart", JOptionPane.INFORMATION_MESSAGE);
    }

}
