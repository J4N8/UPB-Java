import javax.swing.*;

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
    }

    private void setActionListeners(){
        registerButton.addActionListener(e ->
                database.registerNewUser(registerEmailTextField.getText().trim(), registerPasswordField.getPassword().toString(), registerNameTextField.getText(), registerSurnameTextField.getText(), registerCityComboBox.getSelectedItem().toString()));
    }
}
