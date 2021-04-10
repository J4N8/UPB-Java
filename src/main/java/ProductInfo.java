import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.util.ArrayList;

public class ProductInfo {
    private JPanel panel1;
    private JLabel Imagelabel;
    private JLabel Productname;
    private JLabel Productprice;
    private JLabel ProductDesc;
    String name;
    String description;
    String image;
    private int idd;
    double price;

    public ProductInfo(int id) {
        JFrame jframe = new JFrame("Products");
        jframe.setContentPane(panel1);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(1050, 400);
        jframe.setVisible(true);

        idd = id;
        Polnjenje();
    }

    ProductInfo(String name,double price ,String desc, String image){
        this.name = name;
        this.price = price;
        this.description = desc;
        this.image = image;
    }

    public ProductInfo() {

    }

    private void Polnjenje()
    {
        ProductInfo ProductInfo =  database.SelectProductInfo(idd);

        Productname.setText( ProductInfo.name );
        Productprice.setText("Price: " + ProductInfo.price + "€" );
        ProductDesc.setText(ProductInfo.description );

        ImageIcon slika = new ImageIcon(new ImageIcon(ProductInfo.image).getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
        Imagelabel.setIcon(slika);
        Imagelabel.setText("");

    }
}


