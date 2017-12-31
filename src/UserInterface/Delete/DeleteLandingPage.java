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

    public DeleteLandingPage(){
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

    private void selectedString(ItemSelectable is) {
        Object selected[] = is.getSelectedObjects();
        String selectedValue = ((String)selected[0]);

        if(selectedValue == (this.selectStrings[0])){
            clearScreen();
        }

        else if(selectedValue == (this.selectStrings[1])){
            createDeleteAccounts();
        }

        else if(selectedValue == (this.selectStrings[2])){
            createDeleteProfiles();
        }

        else if(selectedValue == (this.selectStrings[3])){
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
