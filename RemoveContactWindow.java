import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveContactWindow extends JFrame {

    private Container container;
    private JPanel panelWest, panelEast;
    private JLabel labelName, labelLastname;
    private JTextField textName, textSurname;
    private JButton buttonRemove, buttonCancel;
    private DataBase dataBase;

    public RemoveContactWindow() {
        super("Remove Contact");
        container = this.getContentPane();
        container.setLayout(new GridLayout(1, 2));
        panelWest = new JPanel(new GridLayout(3, 1));
        panelEast = new JPanel(new GridLayout(3, 2));
        labelName = new JLabel("Name: ");
        labelLastname = new JLabel("Surname: ");
        buttonRemove = new JButton("Remove Contact");
        buttonCancel = new JButton("Cancel");
        textName = new JTextField();
        textSurname = new JTextField();
        dataBase = new DataBase();

        container.add(panelEast);
        panelEast.add(labelName);
        panelEast.add(labelLastname);
        panelEast.add(buttonRemove);

        container.add(panelWest);
        panelWest.add(textName);
        panelWest.add(textSurname);
        panelWest.add(buttonCancel);


        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataBase.removeContact(textName.getText(), textSurname.getText());
                dispose();
            }
        });


        this.setVisible(true);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
