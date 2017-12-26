package UserInterface;

import javax.swing.*;
import java.awt.*;

public class EditLandingPage {
    private JPanel content;

    public EditLandingPage() {
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        String[] petStrings = {"-Selecteer-                                                                     ","Accounts", "Profielen", "Bekeken programma's"};
        JComboBox petList = new JComboBox(petStrings);
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(petList, constraints);

        JPanel createEditAccounts = createEditAccounts();
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(createEditAccounts, constraints);

        JButton submit = new JButton("Opslaan");
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.content.add(submit, constraints);

    }

    private JPanel createEditAccounts(){
        EditAccounts editaccounts = new EditAccounts();
        return editaccounts.getEditAccounts();
    }

    public JPanel getEditLandingPage() {
        return this.content;
    }

}
