package UserInterface.New;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewLandingPage {

    private GridBagConstraints constraints = new GridBagConstraints();
    private JPanel content;
    private JPanel subscreen;
    private String[] selectStrings = {"-Selecteer-                                                                     ","Accounts", "Profielen", "Bekeken programma's"};

    //Constructor
    public NewLandingPage(){

        //Maakt de content en layout aan.
        this.content = new JPanel();
        this.subscreen = new JPanel();

        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        //Maakt de content aan en voegt deze toe.
        JLabel title = new JLabel("Toevoegen");
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
            createNewAccounts();
        }

        else if(selectedValue.equals(this.selectStrings[2])){
            createNewProfiles();
        }

        else if(selectedValue.equals(this.selectStrings[3])){
            createNewWatched();
        }
    }

    //Ververst het scherm.
    private void clearScreen(){
        this.subscreen.removeAll();
        this.content.validate();
    }

    //Genereerd het subscreen van Accounts
    private void createNewAccounts(){
        this.subscreen.removeAll();

        NewAccounts newaccounts = new NewAccounts();
        JPanel returnValueAccounts = newaccounts.getNewAccounts();

        this.subscreen.add(returnValueAccounts);
        this.content.add(this.subscreen, this.constraints);
        this.content.validate();
    }

    //Genereerd het subscreen van Profielen
    private void createNewProfiles(){
        this.subscreen.removeAll();

        NewProfiles newprofiles = new NewProfiles();
        JPanel returnValueProfiles = newprofiles.getNewProfiles();

        this.subscreen.add(returnValueProfiles);
        this.content.add(this.subscreen, this.constraints);
        this.content.validate();
    }
    //Genereerd het subscreen van Bekeken
    private void createNewWatched(){
        this.subscreen.removeAll();

        NewWatched newwatched = new NewWatched();
        JPanel returnValueWatched = newwatched.getNewWatched();

        this.subscreen.add(returnValueWatched);
        this.content.add(this.subscreen, this.constraints);
        this.content.validate();
    }

    public JPanel getNewLandingPage(){
        return this.content;
    }
}
