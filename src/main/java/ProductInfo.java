import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.WindowEvent;
import javax.swing.JTextArea;
import java.awt.event.WindowAdapter;

public class ProductInfo {
    private JPanel panel1;
    private JLabel Imagelabel;
    private JLabel Productname;
    private JLabel Productprice;
    private JTextArea textArea1;
    String name;
    String description;
    String image;
    private int idd;
    double price;

    public ProductInfo(int id) {
        JFrame jframe = new JFrame("Products");
        jframe.setContentPane(panel1);
        jframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jframe.pack();
        jframe.setSize(900, 300);
        jframe.setVisible(true);
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);


        idd = id;
        Polnjenje();
        jframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                jframe.dispose();
            }
        });
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
        textArea1.setText(ProductInfo.description );

        ImageIcon slika = new ImageIcon(new ImageIcon(ProductInfo.image).getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
        Imagelabel.setIcon(slika);
        Imagelabel.setText("");

    }
}


