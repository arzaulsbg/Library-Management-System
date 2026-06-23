import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ViewTransactionsFrame extends JFrame {

    JTable table;

    public ViewTransactionsFrame() {

        setTitle("Transactions");

        setSize(1000,500);

        DefaultTableModel model =
                new DefaultTableModel();

        model.setColumnIdentifiers(
                new String[]{
                        "ID",
                        "Book",
                        "Member",
                        "Issue Date",
                        "Due Date",
                        "Return Date",
                        "Fine"
                });

        table =
                new JTable(model);

        add(new JScrollPane(table));

        loadTransactions(model);

        setVisible(true);
    }

    private void loadTransactions(
            DefaultTableModel model) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    """
                    SELECT
                    t.transaction_id,
                    b.title,
                    m.name,
                    t.issue_date,
                    t.due_date,
                    t.return_date,
                    t.fine

                    FROM transactions t

                    JOIN books b
                    ON t.book_id=b.book_id

                    JOIN members m
                    ON t.member_id=m.member_id
                    """;

            Statement stmt =
                    con.createStatement();

            ResultSet rs =
                    stmt.executeQuery(sql);

            while(rs.next()) {

                model.addRow(
                        new Object[]{

                                rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getDate(4),
                                rs.getDate(5),
                                rs.getDate(6),
                                rs.getDouble(7)
                        });
            }

            con.close();
        }
        catch(Exception e) {

            e.printStackTrace();
        }
    }
}