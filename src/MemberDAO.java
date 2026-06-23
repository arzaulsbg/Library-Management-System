import java.sql.*;

public class MemberDAO {

    public void addMember(Member member) {

        String sql =
        "INSERT INTO members(name,email) VALUES(?,?)";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1,
                    member.getName());

            ps.setString(2,
                    member.getEmail());

            ps.executeUpdate();

            System.out.println(
                    "Member Added Successfully");

            con.close();

        }
        catch(Exception e) {

            e.printStackTrace();
        }
    }

    public void viewMembers() {

        try {

            Connection con =
                    DBConnection.getConnection();

            Statement stmt =
                    con.createStatement();

            ResultSet rs =
                    stmt.executeQuery(
                    "SELECT * FROM members");

            System.out.println(
                    "\n===== MEMBERS =====");

            while(rs.next()) {

                System.out.println(

                        rs.getInt("member_id")
                        + " | "

                        + rs.getString("name")
                        + " | "

                        + rs.getString("email")
                );
            }

            con.close();

        }
        catch(Exception e) {

            e.printStackTrace();
        }
    }
}