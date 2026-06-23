import java.sql.*;

public class TransactionDAO {

        public void issueBook(int bookId,
                        int memberId) {

                try {

                        Connection con = DBConnection.getConnection();

                        // Check Book Availability

                        String checkSql = "SELECT quantity FROM books WHERE book_id=?";

                        PreparedStatement checkPs = con.prepareStatement(checkSql);

                        checkPs.setInt(1, bookId);

                        ResultSet rs = checkPs.executeQuery();

                        if (rs.next()) {

                                int quantity = rs.getInt("quantity");

                                if (quantity <= 0) {

                                        System.out.println(
                                                        "Book Not Available");

                                        con.close();

                                        return;
                                }
                        } else {

                                System.out.println(
                                                "Book ID Not Found");

                                con.close();

                                return;
                        }

                        // Insert Transaction

                        String issueSql = """
                                        INSERT INTO transactions
                                        (
                                            book_id,
                                            member_id,
                                            issue_date,
                                            due_date
                                        )
                                        VALUES
                                        (
                                            ?,
                                            ?,
                                            CURDATE(),
                                            DATE_ADD(CURDATE(),INTERVAL 7 DAY)
                                        )
                                        """;

                        PreparedStatement issuePs = con.prepareStatement(issueSql);

                        issuePs.setInt(1, bookId);
                        issuePs.setInt(2, memberId);

                        issuePs.executeUpdate();

                        // Reduce Quantity

                        String updateSql = """
                                        UPDATE books
                                        SET quantity = quantity - 1
                                        WHERE book_id=?
                                        """;

                        PreparedStatement updatePs = con.prepareStatement(updateSql);

                        updatePs.setInt(1, bookId);

                        updatePs.executeUpdate();

                        System.out.println(
                                        "Book Issued Successfully");

                        con.close();

                } catch (Exception e) {

                        e.printStackTrace();
                }
        }

        public void returnBook(int transactionId) {

                try {

                        Connection con = DBConnection.getConnection();

                        // Get Transaction Details

                        String selectSql = """
                                        SELECT book_id,
                                               due_date
                                        FROM transactions
                                        WHERE transaction_id=?
                                        """;

                        PreparedStatement selectPs = con.prepareStatement(selectSql);

                        selectPs.setInt(1, transactionId);

                        ResultSet rs = selectPs.executeQuery();

                        if (!rs.next()) {

                                System.out.println(
                                                "Transaction Not Found");

                                con.close();

                                return;
                        }

                        int bookId = rs.getInt("book_id");

                        Date dueDate = rs.getDate("due_date");

                        Date today = new Date(
                                        System.currentTimeMillis());

                        // Fine Calculation

                        long diff = today.getTime()
                                        - dueDate.getTime();

                        long lateDays = diff / (1000 * 60 * 60 * 24);

                        double fine = 0;

                        if (lateDays > 0) {

                                fine = lateDays * 5;
                        }

                        // Update Transaction

                        String updateTransaction = """
                                        UPDATE transactions
                                        SET return_date=?,
                                            fine=?
                                        WHERE transaction_id=?
                                        """;

                        PreparedStatement updatePs = con.prepareStatement(
                                        updateTransaction);

                        updatePs.setDate(1, today);
                        updatePs.setDouble(2, fine);
                        updatePs.setInt(3, transactionId);

                        updatePs.executeUpdate();

                        // Increase Quantity

                        String updateBook = """
                                        UPDATE books
                                        SET quantity=quantity+1
                                        WHERE book_id=?
                                        """;

                        PreparedStatement bookPs = con.prepareStatement(updateBook);

                        bookPs.setInt(1, bookId);

                        bookPs.executeUpdate();

                        System.out.println(
                                        "Book Returned Successfully");

                        System.out.println(
                                        "Fine = ₹" + fine);

                        con.close();

                } catch (Exception e) {

                        e.printStackTrace();
                }
        }

        public void viewTransactions() {

                try {

                        Connection con = DBConnection.getConnection();

                        String sql = """
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

                        Statement stmt = con.createStatement();

                        ResultSet rs = stmt.executeQuery(sql);

                        System.out.println(
                                        "\n=========== TRANSACTIONS ===========");

                        while (rs.next()) {

                                System.out.println(

                                                rs.getInt("transaction_id")

                                                                + " | "

                                                                + rs.getString("title")

                                                                + " | "

                                                                + rs.getString("name")

                                                                + " | Issue: "

                                                                + rs.getDate("issue_date")

                                                                + " | Due: "

                                                                + rs.getDate("due_date")

                                                                + " | Return: "

                                                                + rs.getDate("return_date")

                                                                + " | Fine: ₹"

                                                                + rs.getDouble("fine"));
                        }

                        con.close();
                } catch (Exception e) {

                        e.printStackTrace();
                }
        }
}