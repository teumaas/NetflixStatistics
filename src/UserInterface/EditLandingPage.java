package UserInterface;

import javax.swing.*;
import java.awt.*;

public class EditLandingPage {
    private GridBagConstraints constraints = new GridBagConstraints();
    private JPanel content;

    public EditLandingPage() {
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        String[] selectStrings = {"-Selecteer-                                                                     ","Accounts", "Profielen", "Bekeken programma's"};
        JComboBox selectList = new JComboBox(selectStrings);
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(selectList, constraints);

        createEditProfiles();
        createEditWatched();
        createEditAccounts();
    }

    private void createEditAccounts(){
        EditAccounts editaccounts = new EditAccounts();
        JPanel returnValue = editaccounts.getEditAccounts();

        constraints.gridx = 0;
        constraints.gridy = 1;

        this.content.add(returnValue, this.constraints);
    }

    private void createEditProfiles(){
        EditProfiles editprofiles = new EditProfiles();
        JPanel returnValue = editprofiles.getEditProfiles();

        constraints.gridx = 0;
        constraints.gridy = 1;

        this.content.add(returnValue, this.constraints);
    }

    private void createEditWatched(){
        EditWatched editwatched = new EditWatched();
        JPanel returnValue = editwatched.getEditWatched();

        constraints.gridx = 0;
        constraints.gridy = 1;

        this.content.add(returnValue, this.constraints);
    }


    public JPanel getEditLandingPage() {
        return this.content;
    }

}
