import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JButton removeFromShoppingCartButton;
    private JPanel ButtonsPanel;
    private JButton checkoutButton;
    private int user_id;
    private JButton addProductButton;
    private JList PurchaseHistoryList;
    private JButton refreshPurchaseHistoryButton;
    private JPanel PurchaseHistoryPanel;
    private JButton importButton;

    public ProductsForm(int user_id) {
        JFrame jframe = new JFrame("Products");
        jframe.setContentPane(panel1);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(1050, 400);
        jframe.setVisible(true);
        this.user_id = user_id;

        setActionListeners();

        updateProductsList();

        //Fill shopping cart list on form load
        updateShoppingCart();

        //Load purchase history on form load
        ArrayList<ShoppingCart> shoppingCart = database.selectUserPurchasedItems(user_id);
        DefaultListModel<ShoppingCart> purchaseHistoryDefaultListModel = new DefaultListModel<>();

        for (ShoppingCart sc : shoppingCart) {
            purchaseHistoryDefaultListModel.addElement(sc);
        }
        PurchaseHistoryList.setModel(purchaseHistoryDefaultListModel);
    }

    public void updateProductsList(){
        ArrayList<Product> products = database.selectAllProducts();
        DefaultListModel<Product> demoList = new DefaultListModel<>();

        for (Product product : products
        ) {
            demoList.addElement(product);
        }
        Productlist.setModel(demoList);
    }

    private void setActionListeners() {
        //Add to shopping cart button on click
        addToShoppingCartButton.addActionListener(e -> {
            Product selectedProduct = (Product) Productlist.getSelectedValue();
            database.addToShoppingCart(selectedProduct.id, user_id, selectedProduct.price);
            Messages.addedToShoppingCart(panel1);
            updateShoppingCart();
        });

        //Shopping cart list refresh button
        refreshShoppingCartButton.addActionListener(e -> {
            updateShoppingCart();
        });

        //Remove selected item from shopping cart
        removeFromShoppingCartButton.addActionListener(e -> {
            try {
                ShoppingCart sc = (ShoppingCart) ShoppingCartList.getSelectedValue();
                database.removeShoppingCartItem(sc, user_id);
                updateShoppingCart();
            } catch (Exception exception) {
                //no item selected so do nothing
            }
        });

        //Marks product as purchased
        checkoutButton.addActionListener(e -> {
            database.BuyShoppingCartItem(user_id);
            updateShoppingCart();
        });

        //Opens adding product form
        addProductButton.addActionListener(e -> {
            new AddingProductForm(this);
        });

        //Refreshes purchase history list
        refreshPurchaseHistoryButton.addActionListener(e -> {
            ArrayList<ShoppingCart> shoppingCart = database.selectUserPurchasedItems(user_id);
            DefaultListModel<ShoppingCart> purchaseHistoryDefaultListModel = new DefaultListModel<>();

            for (ShoppingCart sc : shoppingCart) {
                purchaseHistoryDefaultListModel.addElement(sc);
            }
            PurchaseHistoryList.setModel(purchaseHistoryDefaultListModel);
        });

        Productlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList Productlist = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    Product selectedProduct = (Product) Productlist.getSelectedValue();
                    int id = selectedProduct.id;
                    new ProductInfo(id);
                }
            }
        });

        importButton.addActionListener(e -> {
            new ImportForm(user_id);
        });
    }

    //Fills in shopping cart list
    private void updateShoppingCart() {
        ArrayList<ShoppingCart> shoppingCart = database.selectUserShoppingCart(user_id);

        DefaultListModel<ShoppingCart> shoppingCartList = new DefaultListModel<>();

        for (ShoppingCart sc : shoppingCart) {
            shoppingCartList.addElement(sc);
        }
        ShoppingCartList.setModel(shoppingCartList);
    }
}
