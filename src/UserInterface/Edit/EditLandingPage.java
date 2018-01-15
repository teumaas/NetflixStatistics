package UserInterface.Edit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Constructor
public class EditLandingPage {
    private GridBagConstraints constraints = new GridBagConstraints();
    private JPanel content;
    private JPanel subscreen;
    private String[] selectStrings = {"-Selecteer-                                                                     ","Accounts", "Profielen", "Bekeken programma's"};

    public EditLandingPage() {
        //Maakt de content en layout aan.
        this.content = new JPanel();
        this.subscreen = new JPanel();

        GridBagLayout layout = new GridBagLayout();
        //Maakt de content aan en voegt deze toe.
        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        JLabel title = new JLabel("Wijzigen");
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

    // Select de value van de geselecteerde dropdown.
    private void selectedString(ItemSelectable is) {
        Object selected[] = is.getSelectedObjects();
        String selectedValue = ((String)selected[0]);

        if(selectedValue.equals(this.selectStrings[0])){
            clearScreen();
        }

        else if(selectedValue.equals(this.selectStrings[1])){
            createEditAccounts();
        }

        else if(selectedValue.equals(this.selectStrings[2])){
            createEditProfiles();
        }

        else if(selectedValue.equals(this.selectStrings[3])){
            createEditWatched();
        }
    }
    //Ververst het scherm.
    private void clearScreen(){
        this.subscreen.removeAll();
        this.content.validate();
    }
    //Genereerd het subscreen van Accounts
    private void createEditAccounts(){
        this.subscreen.removeAll();

        EditAccounts editaccounts = new EditAccounts();
        JPanel returnValueAccounts = editaccounts.getEditAccounts();

        this.subscreen.add(returnValueAccounts);
        this.content.add(this.subscreen, this.constraints);
        this.content.validate();
    }
    //Genereerd het subscreen van Profielen
    private void createEditProfiles(){
        this.subscreen.removeAll();

        EditProfiles editprofiles = new EditProfiles();
        JPanel returnValueProfiles = editprofiles.getEditProfiles();

        this.subscreen.add(returnValueProfiles);
        this.content.add(this.subscreen, this.constraints);
        this.content.validate();
    }
    //Genereerd het subscreen van Bekeken
    private void createEditWatched(){
        this.subscreen.removeAll();

        EditWatched editwatched = new EditWatched();
        JPanel returnValueWatched = editwatched.getEditWatched();

        this.subscreen.add(returnValueWatched);
        this.content.add(this.subscreen, this.constraints);
        this.content.validate();
    }

    public JPanel getEditLandingPage() {
        return this.content;
    }
}
