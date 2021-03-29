import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.*;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.File;
import java.io.IOException;


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
    private JButton AddImageButton;


    public AddingProductForm() {
        JFrame jframe = new JFrame("UPB-Java");
        jframe.setContentPane(Panel1);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        AddImageButton.addActionListener(e -> {
            //Shranjevanje slike
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG, GIF, and PNG Images", "jpg", "gif", "png");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(main);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                System.out.println("You chose to open this file: "
                        + file.getName());
                BufferedImage image;
                try {
                    image = ImageIO.read(file);
                    ImageIO.write(image, "jpg",new File("C:\Users\maksb\IdeaProjects\UPB-Java\src\img\" + file.getName()));
                            fileName = file.getName();
                    fileIfDelete = true;
                } catch (IOException ex) {
                    Logger.getLogger(Mainpage.class.getName()).log(Level.SEVERE, null, ex);

                    //izpis errorja ce ne zberes slike
                    JOptionPane.showMessageDialog(main,
                            "Niste izbrali slike!",
                            "Error!",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }






}
