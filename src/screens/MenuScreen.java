package screens;

import javax.swing.*;

//import project.staticClasses.*;

import other.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

public class MenuScreen extends Screen {
    // initialising User object
    static User u;
    // initialising frame variables and components
    static JFrame frame = new JFrame("Menu");
    JPanel panel = new JPanel();
    SpringLayout layout = new SpringLayout();
    JButton btnQuiz = new JButton("Quiz", null);
    JButton btnStats = new JButton("Statistics", null);
    JLabel lblUsername = new JLabel("Username: ");
    JLabel lblMenu = new JLabel("Select an option");

    //final strings for switching actionperformed
    final String QUIZSTRING = "QUIZSTRING";
    final String STATSSTRING = "STATSSTRING";

    // constructor
    public MenuScreen(User u) {

        super(u);
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
        panel.setBackground(Color.CYAN);
        lblUsername.setText(lblUsername.getText() + "" + u.getUsername());
        lblUsername.setFont(new Font("", Font.PLAIN, 16));
        lblMenu.setFont(new Font("", Font.BOLD, 20));
        panel.setSize(700, 500);
        panel.setLayout(layout);
        panel.add(btnQuiz);
        panel.add(btnStats);
        panel.add(lblUsername);
        panel.add(lblMenu);
    }

    @Override
    void layoutConstraints() {
        // lblUsername CONSTRAINTS
        layout.putConstraint(SpringLayout.WEST, lblUsername, 5, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, lblUsername, 50, SpringLayout.NORTH, panel);
        // lblMenu CONSTRAINTS
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblMenu, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.NORTH, lblMenu, 5, SpringLayout.NORTH, panel);
        // btnStats CONSTRAINTS
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnStats, 50, SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, btnStats, 0, SpringLayout.VERTICAL_CENTER, panel);
        // btnQuiz CONSTRAINTS
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnQuiz, -50, SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, btnQuiz, 0, SpringLayout.VERTICAL_CENTER, panel);

    }
    @Override
    void setupActionListener() {
        btnQuiz.addActionListener(this);
        btnQuiz.setActionCommand(QUIZSTRING);
        btnStats.addActionListener(this);
        btnStats.setActionCommand(STATSSTRING);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case QUIZSTRING:
                new QuizScreen(u);
                frame.dispose();
                break;
            case STATSSTRING:
                new StatsScreen(u);
                frame.dispose();
                break;
        }
    }

}
