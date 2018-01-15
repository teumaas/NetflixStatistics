package UserInterface.Delete;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteLandingPage {
    private GridBagConstraints constraints = new GridBagConstraints();
    private JPanel content;
    private JPanel subscreen;
    private String[] selectStrings = {"-Selecteer-                                                                     ","Accounts", "Profielen", "Bekeken programma's"};

    //Constructor
    public DeleteLandingPage(){
        //Maakt de content en layout aan.
        this.content = new JPanel();
        this.subscreen = new JPanel();

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        //Maakt de content aan en voegt deze toe.
        JLabel title = new JLabel("Verwijderen");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        this.constraints.gridx = 0;
        this.constraints.gridy = 1;
        this.content.add(title, this.constraints);

        JComboBox selectList = new JComboBox(this.selectStrings);
        this.constraints.gridx = 0;
        this.constraints.gridy = 2;
        this.content.add(selectList, this.constraints);

        this.constraints.gridx = 0;
        this.constraints.gridy = 3;

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ItemSelectable is = (ItemSelectable)actionEvent.getSource();
                selectedString(is);
            }
        };
        selectList.addActionListener(actionListener);
    }

    private void selectedString(ItemSelectable is) {
        Object selected[] = is.getSelectedObjects();
        String selectedValue = ((String)selected[0]);

        if(selectedValue.equals(this.selectStrings[0])){
            clearScreen();
        }

        else if(selectedValue.equals(this.selectStrings[1])){
            createDeleteAccounts();
        }

        else if(selectedValue.equals(this.selectStrings[2])){
            createDeleteProfiles();
        }

        else if(selectedValue.equals(this.selectStrings[3])){
            createDeleteWatched();
        }
    }

    private void clearScreen(){
        this.subscreen.removeAll();
        this.content.validate();
    }

    private void createDeleteAccounts(){
        this.subscreen.removeAll();

        DeleteAccounts deleteaccounts = new DeleteAccounts();
        JPanel returnValueAccounts = deleteaccounts.getDeleteAccounts();

        this.subscreen.add(returnValueAccounts);
        this.content.add(this.subscreen, this.constraints);
        this.content.validate();
    }

    private void createDeleteProfiles(){
        this.subscreen.removeAll();

        DeleteProfiles deleteprofiles = new DeleteProfiles();
        JPanel returnValueProfiles = deleteprofiles.getDeleteprofiles();

        this.subscreen.add(returnValueProfiles);
        this.content.add(this.subscreen, this.constraints);
        this.content.validate();
    }

    private void createDeleteWatched(){
        this.subscreen.removeAll();

        DeleteWatched deletewatched = new DeleteWatched();
        JPanel returnValueWatched = deletewatched.getDeleteWatched();

        this.subscreen.add(returnValueWatched);
        this.content.add(this.subscreen, this.constraints);
        this.content.validate();
    }

    public JPanel getDeleteLandingPage(){
        return this.content;
    }
}
