import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;



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
    private JPanel Panel1;
    private JButton AddImageButton;
    public static String fileName;
    public boolean fileIfDelete = true;
    ProductsForm main_form;


    public AddingProductForm(ProductsForm main_form) {
        JFrame jframe = new JFrame("UPB-Java");
        jframe.setContentPane(Panel1);
        jframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jframe.pack();
        jframe.setSize(250, 300);
        jframe.setVisible(true);
        this.main_form = main_form;

        setActionListeners();

        jframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit();
                jframe.dispose();
            }
        });







        //fill categories combobox with items from database
        loadCategories();
    }

    public void onExit() {
        if (fileIfDelete == true)
        {
            File file = new File("%USERPROFILE%" + fileName);
            System.out.print(file);
            //file.delete();
        }
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
            String image = "src\\img\\" + fileName;
            System.out.print(image);
            if (database.AddNewProduct(ProductNameTextField.getText(), ProductPriceTextField.getText(), DescriptionTextField.getText(), image ,CategoryComboBox.getSelectedItem().toString().split(";")[1].trim()) == true ) {
                Messages.AddNewProductSuccessful(Panel1);
                main_form.updateProductsList();
            } else {
                Messages.registerUserFailed(Panel1);
            }
            fileIfDelete = false;
        });
        AddImageButton.addActionListener(e -> {
            //Shranjevanje slike
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG, jpeg, and PNG Images", "jpg", "png", "jpeg");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(Panel1);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                System.out.println("You chose to open this file: "
                        + file.getName());
                BufferedImage image;
                try {
                    image = ImageIO.read(file);
                    ImageIO.write(image, "jpg",new File("src\\img\\" + file.getName()));
                    ImageIO.write(image, "jpeg",new File("src\\img\\" + file.getName()));
                    ImageIO.write(image, "png",new File("src\\img\\" + file.getName()));
                    fileName = file.getName();
                    fileIfDelete = true;
                } catch (IOException ex) {
                    Logger.getLogger(AddingProductForm.class.getName()).log(Level.SEVERE, null, ex);

                    //izpis errorja ce ne zberes slike
                    JOptionPane.showMessageDialog(Panel1,
                            "Niste izbrali slike!",
                            "Error!",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}