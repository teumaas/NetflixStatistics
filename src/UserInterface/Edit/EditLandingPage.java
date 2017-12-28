package UserInterface.Edit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditLandingPage {
    private GridBagConstraints constraints = new GridBagConstraints();
    private JPanel content;
    protected JPanel subscreen;
    private String[] selectStrings = {"-Selecteer-                                                                     ","Accounts", "Profielen", "Bekeken programma's"};

    public EditLandingPage() {
        this.content = new JPanel();
        this.subscreen = new JPanel();

        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        JComboBox selectList = new JComboBox(this.selectStrings);
        this.constraints.gridx = 0;
        this.constraints.gridy = 1;

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ItemSelectable is = (ItemSelectable)actionEvent.getSource();
                selectedString(is);
            }
        };
        selectList.addActionListener(actionListener);

        this.content.add(selectList, constraints);

        this.constraints.gridx = 0;
        this.constraints.gridy = 2;
    }

    private String selectedString(ItemSelectable is) {
        Object selected[] = is.getSelectedObjects();
        String selectedValue = ((String)selected[0]);

        if(selectedValue == (this.selectStrings[0])){
            clearScreen();
        }

        else if(selectedValue == (this.selectStrings[1])){
            createEditAccounts();
        }

        else if(selectedValue == (this.selectStrings[2])){
            createEditProfiles();
        }

        else if(selectedValue == (this.selectStrings[3])){
            createEditWatched();
        }

        return selectedValue;
    }

    private void clearScreen(){
        this.subscreen.removeAll();

        this.content.validate();
    }

    private void createEditAccounts(){
        this.subscreen.removeAll();

        EditAccounts editaccounts = new EditAccounts();
        JPanel returnValueAccounts = editaccounts.getEditAccounts();

        this.subscreen.add(returnValueAccounts);
        this.content.add(this.subscreen, this.constraints);

        this.content.validate();
    }

    private void createEditProfiles(){
        this.subscreen.removeAll();

        EditProfiles editprofiles = new EditProfiles();
        JPanel returnValueProfiles = editprofiles.getEditProfiles();

        this.subscreen.add(returnValueProfiles);
        this.content.add(this.subscreen, this.constraints);

        this.content.validate();
    }

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
