import javax.swing.*;
import java.util.ArrayList;

public class ProductsForm {
    private JPanel panel1;
    private JTabbedPane ProductsTabbedPane;
    private JPanel ProductsPanel;
    private JPanel CartPanel;
    private JList Productlist;
    private JButton addToShoppingCartButton;
    private JList ShoppingCartList;
    private JButton refreshShoppingCartButton;
    private int user_id;

    public ProductsForm(int user_id){
        JFrame jframe = new JFrame("Products");
        jframe.setContentPane(panel1);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(1050, 400);
        jframe.setVisible(true);
        this.user_id = user_id;

        setActionListeners();

        ArrayList<Product> products =  database.selectAllProducts();

        DefaultListModel<Product> demoList = new DefaultListModel<>();

        for (Product product: products
        ) {
            demoList.addElement(product);
        }
        Productlist.setModel(demoList);
    }

    private void setActionListeners(){
        //Add to shopping cart button on click
        addToShoppingCartButton.addActionListener(e ->{
            Product selectedProduct = (Product) Productlist.getSelectedValue();
            database.addToShoppingCart(selectedProduct.id, user_id, selectedProduct.price);
            Messages.addedToShoppingCart(panel1);
        }
    );}
}
