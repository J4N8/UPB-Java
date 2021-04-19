import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImportForm {
    private int user_id;
    private File import_file;
    private JPanel panel1;
    private JTextField separatorTextField;
    private JButton fileSelectButton;
    private JButton importButton;

    public ImportForm(int user_id){
        JFrame jframe = new JFrame("Import");
        jframe.setContentPane(panel1);
        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jframe.pack();
        jframe.setSize(1050, 400);
        jframe.setVisible(true);
        this.user_id = user_id;

        setActionListeners();
    }

    private void setActionListeners(){
        fileSelectButton.addActionListener(e -> {
            //Shranjevanje slike
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "txt files", "txt");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(panel1);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                System.out.println("You chose to open this file: " + file.getName());
                try {
                    file.canRead();
                    import_file = file;
                } catch (Exception ex){
                    Logger.getLogger(AddingProductForm.class.getName()).log(Level.WARNING, null, ex);

                    //Displays error message if you don't select any file or if the file can't be read
                    JOptionPane.showMessageDialog(panel1,
                            "You didn't select a file!",
                            "Error!",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}