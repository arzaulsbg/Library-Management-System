import javax.swing.*;

public class DashboardFrame extends JFrame {

        JButton addBookButton;
        JButton viewBookButton;
        JButton addMemberButton;
        JButton viewMemberButton;
        JButton issueBookButton;
        JButton returnBookButton;
        JButton transactionButton;

        public DashboardFrame() {

                setTitle("Library Dashboard");

                setSize(600, 600);

                setLayout(null);

                setDefaultCloseOperation(
                                JFrame.EXIT_ON_CLOSE);

                // Add Book Button

                addBookButton = new JButton("Add Book");

                addBookButton.setBounds(
                                200,
                                50,
                                180,
                                40);

                add(addBookButton);

                // View Books Button

                viewBookButton = new JButton("View Books");

                viewBookButton.setBounds(
                                200,
                                120,
                                180,
                                40);

                add(viewBookButton);

                // Add Member Button

                addMemberButton = new JButton("Add Member");

                addMemberButton.setBounds(
                                200,
                                190,
                                180,
                                40);

                add(addMemberButton);

                // View Members Button

                viewMemberButton = new JButton("View Members");

                viewMemberButton.setBounds(
                                200,
                                260,
                                180,
                                40);

                add(viewMemberButton);
                issueBookButton = new JButton("Issue Book");

                issueBookButton.setBounds(
                                200,
                                330,
                                180,
                                40);

                add(issueBookButton);
                returnBookButton = new JButton("Return Book");

                returnBookButton.setBounds(
                                200,
                                400,
                                180,
                                40);

                add(returnBookButton);
                transactionButton =
        new JButton("Transactions");

transactionButton.setBounds(
        200,
        470,
        180,
        40);

add(transactionButton);

transactionButton.addActionListener(
        e -> new ViewTransactionsFrame());

                // Event Listeners

                addBookButton.addActionListener(
                                e -> new AddBookFrame());

                viewBookButton.addActionListener(
                                e -> new ViewBooksFrame());

                addMemberButton.addActionListener(
                                e -> new AddMemberFrame());

                viewMemberButton.addActionListener(
                                e -> new ViewMembersFrame());
                issueBookButton.addActionListener(
                                e -> new IssueBookFrame());
                returnBookButton.addActionListener(
                                e -> new ReturnBookFrame());

                setVisible(true);
        }
}