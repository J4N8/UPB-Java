import javax.swing.*;

public class ImportForm {
    private int user_id;
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

    }
}