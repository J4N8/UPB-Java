import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProductsForm {
    private JPanel panel1;
    private JTabbedPane ProductsTabbedPane;
    private JPanel ProductsPanel;
    private JPanel CartPanel;
    private JList Productlist;
    private JButton AddProductButton;


    public ProductsForm(){
        JFrame jframe = new JFrame("Products");
        jframe.setContentPane(panel1);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(1050, 400);
        jframe.setVisible(true);

        ArrayList<Product> products =  database.selectAllProducts();

        DefaultListModel<Product> demoList = new DefaultListModel<>();

        setActionListeners();



        for (Product product: products
        ) {
            demoList.addElement(product);
        }
        //teamsList = new JList(demoList);
        Productlist.setModel(demoList);

        System.out.println("teams demoList: " + demoList);


    }


    private void setActionListeners() {

        AddProductButton.addActionListener(e -> {
            new AddingProductForm();


        });
    }

}
