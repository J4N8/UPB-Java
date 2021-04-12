import javax.swing.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


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
        JFrame jframe = new JFrame("Login");
        jframe.setContentPane(panel1);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(1050, 400);
        jframe.setVisible(true);

        setActionListeners();

        //fill city combobox with items from database
        loadCities();
    }
    public static String doHashing (String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.update(password.getBytes());

            byte[] resultByteArray = messageDigest.digest();

            StringBuilder sb = new StringBuilder();

            for (byte b : resultByteArray) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }

    private void setActionListeners(){
        //register button on click
        registerButton.addActionListener(e ->{
            if (database.registerNewUser(registerEmailTextField.getText().trim(), doHashing(registerPasswordField.getText()), registerNameTextField.getText(), registerSurnameTextField.getText(), registerCityComboBox.getSelectedItem().toString()) == true){
                Messages.registerUserSuccessful(panel1);
            }
            else{
                Messages.registerUserFailed(panel1);
            }
        });

        //login button on click
        loginButton.addActionListener(e -> {
            user_id = database.loginUser(loginEmailTextField.getText(), doHashing(loginPasswordTextField.getText()));
            if (user_id != 0){
                Messages.loginUserSuccessful(panel1);
                JFrame.getFrames()[0].dispose();
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