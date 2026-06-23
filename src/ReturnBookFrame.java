import javax.swing.*;

public class ReturnBookFrame extends JFrame {

    JTextField transactionIdField;

    JButton returnButton;

    public ReturnBookFrame() {

        setTitle("Return Book");

        setSize(400,250);

        setLayout(null);

        JLabel transactionLabel =
                new JLabel("Transaction ID");

        transactionLabel.setBounds(
                40,50,120,30);

        add(transactionLabel);

        transactionIdField =
                new JTextField();

        transactionIdField.setBounds(
                170,50,150,30);

        add(transactionIdField);

        returnButton =
                new JButton("Return Book");

        returnButton.setBounds(
                120,130,130,35);

        add(returnButton);

        returnButton.addActionListener(e -> {

            try {

                int transactionId =
                        Integer.parseInt(
                                transactionIdField.getText());

                TransactionDAO dao =
                        new TransactionDAO();

                dao.returnBook(transactionId);

                JOptionPane.showMessageDialog(
                        null,
                        "Book Returned Successfully");

            }
            catch(Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        null,
                        "Invalid Transaction ID");
            }
        });

        setVisible(true);
    }
}