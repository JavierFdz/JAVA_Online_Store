import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    private Container container;
    private JPanel panelNorth, panelSouth;
    private JButton buttonNewContact, buttonRemoveContact, buttonSearchContact, buttonShowAllContacts, buttonModifyContact;
    private JTextField tx_tittle_app;
    private NewContactWindow newContactWindow;
    private RemoveContactWindow removeContactWindow;
    private JTextArea textAreaNorth;
    private DataBase db;

    public MainWindow() {
        super("MainWindow");
        container = this.getContentPane();
        container.setLayout(new GridLayout(2, 1));
        panelNorth = new JPanel();
        panelSouth = new JPanel(new GridLayout(2, 4));
        buttonNewContact = new JButton("New Contact");
        buttonRemoveContact = new JButton("Remove Contact");
        buttonSearchContact = new JButton("Search Contact");
        buttonShowAllContacts = new JButton("Show all contacts");
        buttonModifyContact = new JButton("Modify contact");
        textAreaNorth = new JTextArea();
        db = new DataBase();


        container.add(panelNorth);
        container.add(panelSouth);

        panelNorth.setBackground(Color.lightGray);
        panelNorth.add(textAreaNorth);
        panelSouth.add(buttonNewContact);
        panelSouth.add(buttonRemoveContact);
        panelSouth.add(buttonShowAllContacts);
        panelSouth.add(buttonModifyContact);


        buttonNewContact.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newContactWindow = new NewContactWindow();

            }
        });

        buttonRemoveContact.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeContactWindow = new RemoveContactWindow();
            }
        });

        buttonShowAllContacts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textAreaNorth.setText(db.showAllContacts());
            }
        });


        this.setVisible(true);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }
}
