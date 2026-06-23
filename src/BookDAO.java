import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class BookDAO {

    public void addBook(Book book) {

        String sql = "INSERT INTO books(title,author,category,quantity) VALUES(?,?,?,?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setInt(4, book.getQuantity());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Book Added Successfully");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewBooks() {

        try {

            Connection con = DBConnection.getConnection();

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM books");

            System.out.println("\n===== BOOK LIST =====");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("book_id")
                                + " | "
                                + rs.getString("title")
                                + " | "
                                + rs.getString("author")
                                + " | "
                                + rs.getString("category")
                                + " | Qty: "
                                + rs.getInt("quantity"));
            }

            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    public void searchBook(String keyword) {

    String sql =
            "SELECT * FROM books WHERE title LIKE ?";

    try {

        Connection con =
                DBConnection.getConnection();

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setString(1, "%" + keyword + "%");

        ResultSet rs =
                ps.executeQuery();

        System.out.println("\n===== SEARCH RESULT =====");

        boolean found = false;

        while(rs.next()) {

            found = true;

            System.out.println(
                    rs.getInt("book_id")
                    + " | "
                    + rs.getString("title")
                    + " | "
                    + rs.getString("author")
                    + " | "
                    + rs.getString("category")
                    + " | Qty: "
                    + rs.getInt("quantity")
            );
        }

        if(!found) {
            System.out.println("No Book Found");
        }

        con.close();

    }
    catch(Exception e) {

        e.printStackTrace();
    }
}
}