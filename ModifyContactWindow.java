import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyContactWindow extends JFrame {

    private Container container;
    private JPanel panelWest, panelEast;
    private JLabel labelName, labelLastname, labelPhoneNumber, labelMail;
    private JTextField textName, textLastname, textPhoneNumber, textMail;
    private JButton buttonAccept, buttonCancel;
    private DataBase db;

    public ModifyContactWindow() {
        super("Modify Contact");

        String contactName = JOptionPane.showInputDialog(null,"Waht's the name of the contact you'd like to modify?");

        container = this.getContentPane();
        container.setLayout(new GridLayout(1, 2));
        panelWest = new JPanel(new GridLayout(5,1));
        panelEast = new JPanel(new GridLayout(5,1));
        labelName = new JLabel("Name: ");
        labelLastname = new JLabel("Surname: ");
        labelPhoneNumber = new JLabel("Phone: ");
        labelMail = new JLabel("Mail: ");
        buttonAccept = new JButton("Accept");
        buttonCancel = new JButton("Cancel");
        textName = new JTextField();
        textLastname = new JTextField();
        textMail = new JTextField();
        textPhoneNumber = new JTextField();


        container.add(panelWest);
        panelWest.add(labelName);
        panelWest.add(labelLastname);
        panelWest.add(labelPhoneNumber);
        panelWest.add(labelMail);
        panelWest.add(buttonAccept);

        container.add(panelEast);
        panelEast.add(textName);
        panelEast.add(textLastname);
        panelEast.add(textPhoneNumber);
        panelEast.add(textMail);
        panelEast.add(buttonCancel);


        labelName.setHorizontalAlignment(0);
        labelLastname.setHorizontalAlignment(0);
        labelPhoneNumber.setHorizontalAlignment(0);
        labelMail.setHorizontalAlignment(0);
        panelEast.setBackground(Color.lightGray);


        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonAccept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(500, 500);

    }
}
