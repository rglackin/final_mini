package screens;

import javax.swing.*;

import staticClasses.*;
import other.*;

import java.awt.Color;
import java.awt.event.*;

public class LoginScreen extends Screen {
    private String usernameInput, passwordInput;
    static JFrame frame = new JFrame("Login");
    JPanel panel = new JPanel();
    SpringLayout layout = new SpringLayout();

    // final strings for switching action listeners
    final String LOGINSTRING = "LOGIN";
    final String REGSTRING = "REGISTER";

    // initialising components
    JLabel lblTitle = new JLabel("Welcome to the ISE Quiz ");
    JLabel lblUser = new JLabel("Username: ");
    JTextField txtUser = new JTextField("", 15);
    JLabel lblPass = new JLabel("Password: ");
    JTextField txtPass = new JTextField("", 15);
    JButton btnLogin = new JButton("Login", null);
    JButton btnRegister = new JButton("Register New User", null);

    public LoginScreen() {
        super();

        // adds components to frame
        initFrame();
        // Put constraint on components
        layoutConstraints();
        //sets up buttons
        setupActionListener();
        frame.add(panel);
        frame.pack();
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void initFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.CYAN);
        panel.setBackground(Color.CYAN);
        panel.setBounds(0, 0, 700, 500);
        panel.setLayout(layout);
        panel.add(lblTitle);
        panel.add(lblUser);
        panel.add(txtUser);
        panel.add(lblPass);
        panel.add(txtPass);
        panel.add(btnLogin);
        panel.add(btnRegister);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
    }

    public void layoutConstraints() {
        // USERNAME CONSTRAINTS
        usernameConstraints();
        // PASSWORD CONSTRAINTS
        passwordConstraints();
        // LOGIN BUTTON CONSTRAINTS
        loginBtnConstraints();
        // REGISTER BUTTON CONSTRAINTS
        registerBtnConstraints();

    }

    public void usernameConstraints() {
        layout.putConstraint(SpringLayout.WEST, lblUser, 70, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, lblUser, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, txtUser, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, txtUser, 100, SpringLayout.NORTH, panel);
    }

    public void passwordConstraints() {
        layout.putConstraint(SpringLayout.WEST, lblPass, 70, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, lblPass, 150, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, txtPass, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, txtPass, 150, SpringLayout.NORTH, panel);
    }

    public void loginBtnConstraints() {
        layout.putConstraint(SpringLayout.WEST, btnLogin, 70, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, btnLogin, 200, SpringLayout.NORTH, panel);
    }

    public void registerBtnConstraints() {
        layout.putConstraint(SpringLayout.WEST, btnRegister, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, btnRegister, 200, SpringLayout.NORTH, panel);
    }

    public void setupActionListener() {
        btnLogin.addActionListener(this);
        btnLogin.setActionCommand(LOGINSTRING);
        btnRegister.addActionListener(this);
        btnRegister.setActionCommand(REGSTRING);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        usernameInput = txtUser.getText();
        passwordInput = txtPass.getText();
        String[] info = { usernameInput, passwordInput };
        if (!usernameInput.contains(" ") && !passwordInput.contains(" ") && !usernameInput.isBlank()
                && !passwordInput.isBlank()) {
            switch (e.getActionCommand()) {
                case LOGINSTRING:
                    Login(info);
                    break;
                case REGSTRING:
                    Register(info);
                    break;
            }

        } else {
            JOptionPane.showMessageDialog(null, "Login Unsuccessful, please enter a username and password");
        }
    }

    public void Login(String[] info) {

        if (u.validLogIn(info)) {
            OpenMenu();
        } else {
            JOptionPane.showMessageDialog(null, "Login Unsuccessful, Invalid username or password");
        }
    }

    public void Register(String[] info) {
        if (passRegex.validPassword(info[1])) {
            if (u.setUserInfo(info)) {
                OpenMenu();
            } else {
                JOptionPane.showMessageDialog(null, "Registration Unsuccessful, username is taken");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Registration Unsuccessful, password invalid");
        }

    }

    public void OpenMenu() {
        new MenuScreen(u);
        frame.dispose();
    }
}
