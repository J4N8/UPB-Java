import javax.swing.*;
import java.util.ArrayList;

public class Login {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField loginEmailTextField;
    private JPasswordField loginPasswordTextField;
    private JButton loginButton;
    private JButton registerButton;
    private JTextField registerEmailTextField;
    private JPasswordField registerPasswordField;
    private JTextField registerNameTextField;
    private JTextField registerSurnameTextField;
    private JComboBox registerCityComboBox;

    public Login() {
        JFrame jframe = new JFrame("UPB-Java");
        jframe.setContentPane(panel1);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(1050, 400);
        jframe.setVisible(true);

        setActionListeners();

        //fill city combobox with items from database
        loadCities();
    }

    private void setActionListeners(){
        registerButton.addActionListener(e ->
                database.registerNewUser(registerEmailTextField.getText().trim(), registerPasswordField.getPassword().toString(), registerNameTextField.getText(), registerSurnameTextField.getText(), registerCityComboBox.getSelectedItem().toString()));
    }

    //loads city combobox items from database
    private void loadCities(){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addAll(database.selectAllCities());
        ArrayList<String> cities = database.selectAllCities();
        registerCityComboBox.setModel(model);
    }
}
