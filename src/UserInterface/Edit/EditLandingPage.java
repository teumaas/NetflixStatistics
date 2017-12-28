package UserInterface.Edit;

import javax.swing.*;
import java.awt.*;

public class EditLandingPage {
    private GridBagConstraints constraints = new GridBagConstraints();
    private JPanel content;
    protected JPanel subscreen;

    public EditLandingPage() {
        this.content = new JPanel();
        this.subscreen = new JPanel();

        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        String[] selectStrings = {"-Selecteer-                                                                     ","Accounts", "Profielen", "Bekeken programma's"};
        JComboBox selectList = new JComboBox(selectStrings);
        this.constraints.gridx = 0;
        this.constraints.gridy = 1;

        LandingPageListener selecter = new LandingPageListener();
        selectList.addActionListener(selecter);


        this.content.add(selectList, constraints);

        this.constraints.gridx = 0;
        this.constraints.gridy = 2;
    }

    protected void createEditAccounts(){
        this.subscreen.removeAll();

        EditAccounts editaccounts = new EditAccounts();
        JPanel returnValueAccounts = editaccounts.getEditAccounts();

        this.subscreen.add(returnValueAccounts);
        this.content.add(this.subscreen, this.constraints);
    }

    protected void createEditProfiles(){
        this.subscreen.removeAll();

        EditProfiles editprofiles = new EditProfiles();
        JPanel returnValueProfiles = editprofiles.getEditProfiles();

        this.subscreen.add(returnValueProfiles);
        this.content.add(this.subscreen, this.constraints);

    }

    protected void createEditWatched(){
        this.subscreen.removeAll();

        EditWatched editwatched = new EditWatched();
        JPanel returnValueWatched = editwatched.getEditWatched();

        this.subscreen.add(returnValueWatched);
        this.content.add(this.subscreen, this.constraints);
    }

    public JPanel getEditLandingPage() {
        return this.content;
    }
}
