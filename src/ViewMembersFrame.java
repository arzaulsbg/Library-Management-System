import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ViewMembersFrame extends JFrame {

    JTable table;

    public ViewMembersFrame() {

        setTitle("View Members");

        setSize(700,400);

        String[] columns = {
                "ID",
                "Name",
                "Email"
        };

        DefaultTableModel model =
                new DefaultTableModel();

        model.setColumnIdentifiers(
                columns);

        table =
                new JTable(model);

        JScrollPane pane =
                new JScrollPane(table);

        add(pane);

        loadMembers(model);

        setVisible(true);
    }

    private void loadMembers(
            DefaultTableModel model) {

        try {

            Connection con =
                    DBConnection.getConnection();

            Statement stmt =
                    con.createStatement();

            ResultSet rs =
                    stmt.executeQuery(
                    "SELECT * FROM members");

            while(rs.next()) {

                model.addRow(
                        new Object[]{

                                rs.getInt(
                                "member_id"),

                                rs.getString(
                                "name"),

                                rs.getString(
                                "email")
                        });
            }

            con.close();
        }
        catch(Exception e) {

            e.printStackTrace();
        }
    }
}