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

    int user_id;

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
        //register button on click
        registerButton.addActionListener(e ->{
            if (database.registerNewUser(registerEmailTextField.getText().trim(), registerPasswordField.getText(), registerNameTextField.getText(), registerSurnameTextField.getText(), registerCityComboBox.getSelectedItem().toString()) == true){
                Messages.registerUserSuccessful(panel1);
            }
            else{
                Messages.registerUserFailed(panel1);
            }
        });

        //login button on click
        loginButton.addActionListener(e -> {
            user_id = database.loginUser(loginEmailTextField.getText(), loginPasswordTextField.getText());
            if (user_id != 0){
                Messages.loginUserSuccessful(panel1);
                new ProductsForm(user_id);
            }
            else{
                Messages.loginUserFailed(panel1);
            }
        });

    }

    //loads city combobox items from database
    private void loadCities(){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addAll(database.selectAllCities());
        registerCityComboBox.setModel(model);
    }




}
