import javax.swing.*;
import java.util.ArrayList;

public class ProductsForm {
    private JPanel panel1;
    private JTabbedPane ProductsTabbedPane;
    private JPanel ProductsPanel;
    private JPanel CartPanel;
    private JList Productlist;

    public ProductsForm() {
        JFrame jframe = new JFrame("Products");
        jframe.setContentPane(panel1);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(1050, 400);
        jframe.setVisible(true);
        loadProducts();


    }

        private void loadProducts()
        {
            DefaultListModel model = new DefaultListModel();
            model.addAll(database.selectAllProducts());
            ArrayList<Product> products = database.selectAllProducts();
            Productlist.setModel(model);
        }
    }

