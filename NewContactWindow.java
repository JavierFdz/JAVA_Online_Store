import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

public class NewContactWindow extends JFrame {

    private Container container;
    private JPanel panelWest, panelEast;
    private JTextField textName, textSurname, textPhone, textMail;
    private JLabel labelName, labelSurname, labelPhone, labelMail;
    private JButton buttonNewContact, buttonCancel;
    private DataBase dataBase;
    private Line2D line;

    public NewContactWindow() {
        super("New Contact Form");
        container = this.getContentPane();
        container.setLayout(new GridLayout(1, 2));
        panelWest = new JPanel(new GridLayout(5, 1));
        panelEast = new JPanel(new GridLayout(5, 1));
        textName = new JTextField("");
        textSurname = new JTextField("");
        textPhone = new JTextField("");
        textMail = new JTextField("");
        buttonNewContact = new JButton("Create Contact");
        buttonCancel = new JButton("Cancel");
        labelName = new JLabel("Name: ");
        labelSurname = new JLabel("Surname: ");
        labelPhone = new JLabel("Phone Number: ");
        labelMail = new JLabel("Email: ");
        dataBase = new DataBase();


        container.add(panelWest);
        panelWest.add(labelName);
        panelWest.add(labelSurname);
        panelWest.add(labelPhone);
        panelWest.add(labelMail);
        panelWest.add(buttonNewContact);
        container.add(panelEast);
        panelEast.add(textName);
        panelEast.add(textSurname);
        panelEast.add(textPhone);
        panelEast.add(textMail);
        panelEast.add(buttonCancel);
        labelName.setHorizontalAlignment(0);
        labelPhone.setHorizontalAlignment(0);
        labelSurname.setHorizontalAlignment(0);
        labelMail.setHorizontalAlignment(0);

        //panelEast.setBackground(Color.lightGray);


        if (dataBase.getConnection() == null) {
            System.out.println("Conn error.");
        } else {
            System.out.println("Conn OK");
        }


        buttonNewContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //1st, check if the contact already exists in the DB before inserting it
                if (dataBase.checkIfContactExists(textName.getText(), textSurname.getText(), textPhone.getText(), textMail.getText()) == "") {
                    dataBase.newcontact(textName.getText(), textSurname.getText(), textPhone.getText(), textMail.getText());
                    dispose();
                } else {
                    dispose();
                }


            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

            }
        });


        this.setVisible(true);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
