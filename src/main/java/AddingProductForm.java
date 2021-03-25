import javax.swing.*;
import javax.swing.*;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AddingProductForm {
    private JTextField ProductNameTextField;
    private JTextField ProductPriceTextField;
    private JButton AddItemButton;
    private JTextField DescriptionTextField;
    private JComboBox CategoryComboBox;
    private JLabel NameLabel;
    private JLabel PriceLabel;
    private JLabel DescLabel;
    private JLabel CategoryLabel;
    private JPanel Panel;
    private JPanel Panel1;



    public AddingProductForm() {
        JFrame jframe = new JFrame("UPB-Java");
        jframe.setContentPane(Panel1);
        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jframe.pack();
        jframe.setSize(250, 300);
        jframe.setVisible(true);

        setActionListeners();





        //fill categories combobox with items from database
        loadCategories();
    }

    private void loadCategories(){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addAll(database.selectAllCategories());
        ArrayList<String> category = database.selectAllCategories();
        CategoryComboBox.setModel(model);
    }

    private void setActionListeners() {
        //register button on click
        AddItemButton.addActionListener(e -> {
            if (database.AddNewProduct(ProductNameTextField.getText(), ProductPriceTextField.getText(), DescriptionTextField.getText(), CategoryComboBox.getSelectedItem().toString().split(";")[1].trim()) == true) {
                Messages.AddNewProductSuccessful(Panel1);
            } else {
                Messages.registerUserFailed(Panel1);
            }
        });
    }





}
