import javax.swing.*;

public class IssueBookFrame extends JFrame {

    JTextField bookIdField;
    JTextField memberIdField;

    JButton issueButton;

    public IssueBookFrame() {

        setTitle("Issue Book");

        setSize(400,300);

        setLayout(null);

        JLabel bookLabel =
                new JLabel("Book ID");

        bookLabel.setBounds(
                50,50,100,30);

        add(bookLabel);

        bookIdField =
                new JTextField();

        bookIdField.setBounds(
                150,50,150,30);

        add(bookIdField);

        JLabel memberLabel =
                new JLabel("Member ID");

        memberLabel.setBounds(
                50,100,100,30);

        add(memberLabel);

        memberIdField =
                new JTextField();

        memberIdField.setBounds(
                150,100,150,30);

        add(memberIdField);

        issueButton =
                new JButton("Issue Book");

        issueButton.setBounds(
                120,180,120,35);

        add(issueButton);

        issueButton.addActionListener(e -> {

            try {

                int bookId =
                        Integer.parseInt(
                                bookIdField.getText());

                int memberId =
                        Integer.parseInt(
                                memberIdField.getText());

                TransactionDAO dao =
                        new TransactionDAO();

                dao.issueBook(
                        bookId,
                        memberId);

                JOptionPane.showMessageDialog(
                        null,
                        "Book Issued Successfully");

            }
            catch(Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        null,
                        "Invalid Input");
            }
        });

        setVisible(true);
    }
}