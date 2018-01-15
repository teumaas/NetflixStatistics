package UserInterface.New;

import Utillities.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

class NewProfiles {
    private JPanel content;
    private Map<Integer, String> selectAccountList;
    private JComboBox selectAccount;
    private JComboBox dateOfBirthDayValue;
    private JTextArea nameValue;
    private ArrayList<String> profileInfo;
    private int AccountID;
    private String ProfileID;

    NewProfiles(){
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        profileInfo = new ArrayList<String>();
        selectAccountList = DatabaseHandler.getAccountName();
        selectAccount = new JComboBox(selectAccountList.values().toArray());
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(selectAccount, constraints);

        JLabel name = new JLabel("Naam: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(name, constraints);

        JTextArea nameValue = new JTextArea("            ");
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.content.add(nameValue, constraints);

        JLabel dateOfBirth = new JLabel("Geboortedatum: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.content.add(dateOfBirth, constraints);

        JTextArea dateOfBirthValue = new JTextArea("            ");
        constraints.gridx = 1;
        constraints.gridy = 2;
        this.content.add(dateOfBirthValue, constraints);

        JButton submit = new JButton("Opslaan");
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.content.add(submit, constraints);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (nameValue.getText().matches("[a-zA-Z- .]{1,64}") && dateOfBirthValue.getText().matches("((((19){1}(0){1}(4|8){1})|((19){1}(1|3|5|7|9){1}(2|6){1})|((19){1}(2|4|6|8){1}(0|4|8){1}))|(((20){1}(0|2|4|6|8){1}(0|4|8))|((20){1}(1|3|5|7|9){1}(2|6){1}))((-){1})(((((0){1}(1|3|5|7|8){1})|((1){1}(0|2){1}))((-){1})((0){1}[1-9]{1}|[1-2]{1}[0-9]{1}|(3){1}[0-1]{1}))|((((0){1}(4|6|9){1})|((1){1}(1){1}))((-){1})((0){1}[1-9]{1}|[1-2]{1}[0-9]{1}|(3){1}(0){1}))|((((0){1}(2){1})|((1){1}(1){1}))((-){1})((0){1}[1-9]{1}|[1-2]{1}[0-9]{1}))))|((((19){1}(0|2|4|6|8){1}(1|2|3|5|6|7|9){1})|((19){1}(1|3|5|7|9){1}(0|1|3|4|5|7|8|9){1})|((20){1}(0){1}(1|2|3|5|6|7|9){1})|(20{1}(1){1}(0|1|3|4|5|7){1}))((-){1})(((((0){1}(1|3|5|7|8){1})|((1){1}(0|2){1}))((-){1})((0){1}[1-9]{1}|[1-2]{1}[0-9]{1}|(3){1}[0-1]{1}))|((((0){1}(4|6|9){1})|((1){1}(1){1}))((-){1})((0){1}[1-9]{1}|[1-2]{1}[0-9]{1}|(3){1}(0){1}))|((((0){1}(2){1})|((1){1}(1){1}))((-){1})((0){1}[1-9]{1}|[1-2]{1}[0-8]{1}))))"))
                {
                    profileInfo.add(getCurrentAccountID());
                    profileInfo.add(nameValue.getText());
                    profileInfo.add(dateOfBirthValue.getText());

                    try {
                        DatabaseHandler.setProfileInfo(profileInfo);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }

                    profileInfo.clear();
                    nameValue.setText("            ");
                    dateOfBirthValue.setText("            ");
                    JOptionPane.showMessageDialog(content,
                            "De gegevens zijn toegevoegd aan de database.",
                            "Succes!",
                            JOptionPane.QUESTION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(content,
                            "Controleer de ingevoerde gegevens...",
                            "Validatiefout!",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    public String getCurrentAccountID() {
        String AccountID = null;
        for (Map.Entry<Integer, String> entry : selectAccountList.entrySet()) {
            if (entry.getValue().equals(selectAccount.getSelectedItem())) {
                AccountID = entry.getKey().toString();
            }
        }
        return AccountID;
    }

    JPanel getNewProfiles(){
        return this.content;
    }
}
