package screens;

import javax.swing.*;

import staticClasses.*;

import other.*;

import java.awt.Color;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsScreen extends Screen {
    User u;
    final String RandString = "RAND";
    final String TimedString = "TIMED";
    final String IncDiffString = "INC_DIFF";
    final String BACK = "BACK";
    static JFrame frame = new JFrame("Statistics");
    JPanel panel = new JPanel();
    SpringLayout layout = new SpringLayout();

    JLabel lblTitle = new JLabel("Select a format");
    // JTable tblStats =new JTable();
    JButton btnIncDiff = new JButton("Increasing Difficulty");
    JButton btnRand = new JButton("Random");
    JButton btnTim = new JButton("Timed");
    JButton btnBack = new JButton("Back");

    public StatsScreen(User u) {
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
    @Override
    void layoutConstraints() {
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblTitle, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.NORTH, lblTitle, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnIncDiff, 200, SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.NORTH, btnIncDiff, 30, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnRand, 200, SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.NORTH, btnRand, 60, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnTim, 200, SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.NORTH, btnTim, 90, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, btnBack, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.SOUTH, btnBack, -10, SpringLayout.SOUTH, panel);

        
    }
    @Override
    void initFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setBackground(Color.CYAN);
        
        panel.setSize(700, 500);
        panel.setLayout(layout);
        // panel.add(tblStats);
        panel.add(lblTitle);
        panel.add(btnRand);
        panel.add(btnTim);
        panel.add(btnIncDiff);
        panel.add(btnBack);
        
    }
    @Override
    void setupActionListener() {
         btnIncDiff.addActionListener(this);
        btnIncDiff.setActionCommand(IncDiffString);
        btnRand.addActionListener(this);
        btnRand.setActionCommand(RandString);
        btnTim.addActionListener(this);
        btnTim.setActionCommand(TimedString);
        btnBack.addActionListener(this);
        btnBack.setActionCommand(BACK);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case IncDiffString:
                setupDataTable(IncDiffString, false);
                break;
            case RandString:
                setupDataTable(RandString, false);
                break;
            case TimedString:
                setupDataTable(TimedString, true);
                break;
            case BACK:
                new MenuScreen(u);
                frame.dispose();
                break;
            default:
                break;
        }
    }

    public void setupDataTable(String quizType, boolean timed) {

        
        String[][] data = queryDB(quizType);

        String[] columnNames = { "User ID/Name", "Mean", "Median", "Standard Deviation" };
        JTable tblStats = new JTable(data, columnNames);
        panel.add(tblStats);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, tblStats, 50, SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, tblStats, 50, SpringLayout.VERTICAL_CENTER, panel);
        tblStats.repaint();
        tblStats.setVisible(true);
        tblStats.setFillsViewportHeight(true);
        JScrollPane sp = new JScrollPane(tblStats);
        panel.add(sp);

    }

    public String[][] queryDB(String quizType) {
        String query = String.format("Select * From quiz_result WHERE quiz_type = '%s'", quizType);
        Map<Integer, List<Integer>> resultsByUserID = DAL.SelectQuery(query, rset -> {
            Map<Integer, List<Integer>> results = new HashMap<>();
            while (rset.next()) {

                int userID = rset.getInt("user_id");
                int result = rset.getInt("score");
                List<Integer> userResults = results.get(userID);
                if (userResults == null) {
                    userResults = new ArrayList<>();
                    results.put(userID, userResults);
                }
                userResults.add(result);
            }
            return results;
        });
        String[][] data = formatTableData(resultsByUserID);
        return data;
    }

    public String[][] formatTableData(Map<Integer, List<Integer>> resultsByUserID) {
        String[][] data = new String[resultsByUserID.entrySet().size()][];
        int counter = 0;
        for (Map.Entry<Integer, List<Integer>> entry : resultsByUserID.entrySet()) {
            int userID = entry.getKey();
            List<Integer> userResults = entry.getValue();
            System.out.println(userID);
            System.out.println(userResults);
            int[] results = new int[userResults.size()];
            for (int i = 0; i < results.length; i++) {
                results[i] = userResults.get(i);
            }
            double mean = userStatistics.mean(results);
            double median = userStatistics.median(results);
            double standardDeviation = userStatistics.standardDeviation(results);
            String[] dataEntry = { "" + userID, "" + mean, "" + median, "" + standardDeviation };
            data[counter] = dataEntry;
            counter++;
        }
        return data;
    }
}
