import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ViewBooksFrame extends JFrame {

    JTable table;
    JScrollPane scrollPane;

    public ViewBooksFrame() {

        setTitle("View Books");

        setSize(800,500);

        setLayout(null);

        String[] columns = {
                "ID",
                "Title",
                "Author",
                "Category",
                "Quantity"
        };

        DefaultTableModel model =
                new DefaultTableModel();

        model.setColumnIdentifiers(columns);

        table =
                new JTable(model);

        scrollPane =
                new JScrollPane(table);

        scrollPane.setBounds(
                20,
                20,
                740,
                400);

        add(scrollPane);

        loadBooks(model);

        setVisible(true);
    }

    private void loadBooks(
            DefaultTableModel model) {

        try {

            Connection con =
                    DBConnection.getConnection();

            Statement stmt =
                    con.createStatement();

            ResultSet rs =
                    stmt.executeQuery(
                            "SELECT * FROM books");

            while(rs.next()) {

                model.addRow(
                        new Object[]{

                                rs.getInt(
                                        "book_id"),

                                rs.getString(
                                        "title"),

                                rs.getString(
                                        "author"),

                                rs.getString(
                                        "category"),

                                rs.getInt(
                                        "quantity")
                        });
            }

            con.close();
        }
        catch(Exception e) {

            e.printStackTrace();
        }
    }
}