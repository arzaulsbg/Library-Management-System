import javax.swing.*;

public class AddMemberFrame extends JFrame {

    JTextField nameField;
    JTextField emailField;

    JButton saveButton;

    public AddMemberFrame() {

        setTitle("Add Member");

        setSize(400,300);

        setLayout(null);

        JLabel nameLabel =
                new JLabel("Name");

        nameLabel.setBounds(
                50,50,100,30);

        add(nameLabel);

        nameField =
                new JTextField();

        nameField.setBounds(
                150,50,150,30);

        add(nameField);

        JLabel emailLabel =
                new JLabel("Email");

        emailLabel.setBounds(
                50,100,100,30);

        add(emailLabel);

        emailField =
                new JTextField();

        emailField.setBounds(
                150,100,150,30);

        add(emailField);

        saveButton =
                new JButton("Save");

        saveButton.setBounds(
                130,180,100,35);

        add(saveButton);

        saveButton.addActionListener(e -> {

            MemberDAO dao =
                    new MemberDAO();

            Member member =
                    new Member(
                            nameField.getText(),
                            emailField.getText());

            dao.addMember(member);

            JOptionPane.showMessageDialog(
                    null,
                    "Member Added Successfully");
        });

        setVisible(true);
    }
}