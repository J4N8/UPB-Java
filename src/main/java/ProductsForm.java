import javax.swing.*;
import java.util.ArrayList;

public class ProductsForm {
    private JPanel panel1;
    private JTabbedPane ProductsTabbedPane;
    private JPanel ProductsPanel;
    private JPanel CartPanel;
    private JList Productlist;

    public ProductsForm(){
        JFrame jframe = new JFrame("Products");
        jframe.setContentPane(panel1);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(1050, 400);
        jframe.setVisible(true);

        ArrayList<Product> products =  database.selectAllProducts();

        DefaultListModel<Product> demoList = new DefaultListModel<>();

        for (Product product: products
        ) {
            demoList.addElement(product);
        }
        //teamsList = new JList(demoList);
        Productlist.setModel(demoList);

        System.out.println("teams demoList: " + demoList);
    }
}
