import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;

public class ProductInfo {
    private JPanel panel1;
    private JLabel Imagelabel;
    private JLabel Productname;
    private JLabel Productprice;
    private JLabel ProductDesc;

    public ProductInfo() {
        JFrame jframe = new JFrame("Products");
        jframe.setContentPane(panel1);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(1050, 400);
        jframe.setVisible(true);
        Productname = new JLabel();
        Productname.setFont(new Font("Serif", Font.PLAIN, 30));
        Polnjenje();
    }
    private void Polnjenje()
    {

        ImageIcon slika = new ImageIcon("src\\img\\brah.png" );
        Imagelabel.setIcon(slika);
        Imagelabel.setText("");

    }
}


