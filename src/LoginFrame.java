import javax.swing.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;

    public LoginFrame() {

        setTitle("Library Login");

        setSize(400, 300);

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel userLabel = new JLabel("Username");

        userLabel.setBounds(
                50,
                50,
                100,
                30);

        add(userLabel);

        usernameField = new JTextField();

        usernameField.setBounds(
                150,
                50,
                150,
                30);

        add(usernameField);

        JLabel passLabel = new JLabel("Password");

        passLabel.setBounds(
                50,
                100,
                100,
                30);

        add(passLabel);

        passwordField = new JPasswordField();

        passwordField.setBounds(
                150,
                100,
                150,
                30);

        add(passwordField);

        loginButton = new JButton("Login");

        loginButton.setBounds(
                130,
                170,
                100,
                35);

        add(loginButton);

        loginButton.addActionListener(
                new ActionListener() {

                    public void actionPerformed(
                            ActionEvent e) {

                        String username = usernameField.getText();

                        String password = String.valueOf(
                                passwordField.getPassword());

                        UserDAO dao = new UserDAO();

                        boolean valid = dao.login(
                                username,
                                password);

                        if (valid) {

                            dispose();

                            new DashboardFrame();
                        } else {

                            JOptionPane.showMessageDialog(
                                    null,
                                    "Invalid Credentials");
                        }
                    }
                });

        setVisible(true);
    }
}