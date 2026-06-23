import javax.swing.*;

public class AddBookFrame extends JFrame {

    JTextField titleField;
    JTextField authorField;
    JTextField categoryField;
    JTextField quantityField;

    JButton saveButton;

    public AddBookFrame() {

        setTitle("Add Book");

        setSize(500,400);

        setLayout(null);

        JLabel titleLabel =
                new JLabel("Title");

        titleLabel.setBounds(
                50,
                50,
                100,
                30);

        add(titleLabel);

        titleField =
                new JTextField();

        titleField.setBounds(
                150,
                50,
                200,
                30);

        add(titleField);

        JLabel authorLabel =
                new JLabel("Author");

        authorLabel.setBounds(
                50,
                100,
                100,
                30);

        add(authorLabel);

        authorField =
                new JTextField();

        authorField.setBounds(
                150,
                100,
                200,
                30);

        add(authorField);

        JLabel categoryLabel =
                new JLabel("Category");

        categoryLabel.setBounds(
                50,
                150,
                100,
                30);

        add(categoryLabel);

        categoryField =
                new JTextField();

        categoryField.setBounds(
                150,
                150,
                200,
                30);

        add(categoryField);

        JLabel qtyLabel =
                new JLabel("Quantity");

        qtyLabel.setBounds(
                50,
                200,
                100,
                30);

        add(qtyLabel);

        quantityField =
                new JTextField();

        quantityField.setBounds(
                150,
                200,
                200,
                30);

        add(quantityField);

        saveButton =
                new JButton(
                        "Save");

        saveButton.setBounds(
                170,
                280,
                100,
                35);

        add(saveButton);

        saveButton.addActionListener(e -> {

            try {

                BookDAO dao =
                        new BookDAO();

                Book book =
                        new Book(

                                titleField.getText(),

                                authorField.getText(),

                                categoryField.getText(),

                                Integer.parseInt(
                                quantityField.getText())
                        );

                dao.addBook(book);

                JOptionPane.showMessageDialog(
                        null,
                        "Book Added");

            }
            catch(Exception ex) {

                ex.printStackTrace();
            }
        });

        setVisible(true);
    }
}