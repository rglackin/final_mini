package screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;
import staticClasses.*;
import staticClasses.Quiz.quizType;
import other.*;


public class QuizScreen extends Screen {
    //User u = new User();
    Quiz quiz = new Quiz();
    qaBank bank = new qaBank();
    int[] qNums;
    int qPointer = 0;
    int marks = 0;

    Answer ans0;
    Answer ans1;
    Answer ans2;
    Answer ans3;

    final boolean INCREASING_DIFFICULTY = true;
    final boolean RANDOM = false;
    final String STARTQUIZ = "startQuiz";
    final String ANS0 = "ANS0";
    final String ANS1 = "ANS1";
    final String ANS2 = "ANS2";
    final String ANS3 = "ANS3";

    long startTime = 0;
    // initialising frame and components
    static JFrame frame = new JFrame("Quiz");
    JPanel formatPanel = new JPanel();
    JPanel quizPanel = new JPanel();
    SpringLayout layout = new SpringLayout();
    // SpringLayout quizLayout = new SpringLayout();

    JLabel lblSelectFormat = new JLabel("Select a Quiz Format");
    ButtonGroup radiGroup = new ButtonGroup();
    JRadioButton radRandom = new JRadioButton("Random selection");
    JRadioButton radIncDiff = new JRadioButton("Increasing Difficulty");
    JRadioButton radTimed = new JRadioButton("Timed");
    JButton btnStart = new JButton("Start Quiz");
    JLabel lblQuestion = new JLabel("question");
    JButton btnAnsZero = new JButton("a0");
    JButton btnAnsOne = new JButton("a1");
    JButton btnAnsTwo = new JButton("a2");
    JButton btnAnsThree = new JButton("a3");
    JLabel lblMarks = new JLabel("Marks: " + marks);
    //JLabel lblAStatus = new JLabel();
    ButtonGroup ansGroup = new ButtonGroup();

    public QuizScreen(User u) {
        super(u);
        quiz.setU(u);
        // adds components to frame
        initFrame();
        // Put constraint on components
        layoutConstraints();
        //sets up buttons
        setupActionListener();

        frame.add(formatPanel);
        frame.add(quizPanel);
        frame.pack();
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case STARTQUIZ:
                if (radIncDiff.isSelected()) {
                    quiz.setType(quizType.INC_DIFF);
                    startQuiz(INCREASING_DIFFICULTY);
                } else if (radRandom.isSelected()) {
                    quiz.setType(quizType.RAND);;
                    startQuiz(RANDOM);
                }else if(radTimed.isSelected()){
                    quiz.setType(quizType.TIMED);
                    startQuiz(INCREASING_DIFFICULTY);
                }
                 else {
                    JOptionPane.showMessageDialog(null, "Select a format");
                }
                break;
            case ANS0:
                nextQuestion(ans0);
                break;
            case ANS1:
                nextQuestion(ans1);
                break;
            case ANS2:
                nextQuestion(ans2);
                break;
            case ANS3:
                nextQuestion(ans3);
                break;
        }
    }
    @Override
    void initFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set background color
        formatPanel.setBackground(Color.CYAN);
        quizPanel.setBackground(Color.CYAN);
        radIncDiff.setBackground(Color.cyan);
        radRandom.setBackground(Color.cyan);
        radTimed.setBackground(Color.cyan);
        // set title font
        lblSelectFormat.setFont(new Font("", Font.BOLD, 20));
        lblQuestion.setFont(new Font("", Font.BOLD, 14));
        lblMarks.setFont(new Font("", Font.BOLD, 20));
        //lblAStatus.setFont(new Font("", Font.BOLD, 20));
        // set format panel size/layout
        formatPanel.setSize(1200, 800);
        formatPanel.setLayout(layout);
        // setup radio button group
        radiGroup.add(radIncDiff);
        radiGroup.add(radRandom);
        radiGroup.add(radTimed);

        ansGroup.add(btnAnsZero);
        ansGroup.add(btnAnsOne);
        ansGroup.add(btnAnsTwo);
        ansGroup.add(btnAnsThree);
        // add components to format panel
        formatPanel.add(lblSelectFormat);
        formatPanel.add(radIncDiff);
        formatPanel.add(radRandom);
        formatPanel.add(btnStart);
        formatPanel.add(radTimed);

        quizPanel.setVisible(false);
        quizPanel.add(lblQuestion);
        //quizPanel.add(lblAStatus);
        quizPanel.add(btnAnsZero);
        quizPanel.add(btnAnsOne);
        quizPanel.add(btnAnsTwo);
        quizPanel.add(btnAnsThree);
        quizPanel.add(lblMarks);
        // set quiz panel size/layout
        quizPanel.setSize(1200, 800);
        quizPanel.setLayout(layout);
        
    }
    @Override
    void layoutConstraints() {
        // Spring layout constraints
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblSelectFormat, 0, SpringLayout.HORIZONTAL_CENTER,
                formatPanel);
        layout.putConstraint(SpringLayout.NORTH, lblSelectFormat, 5, SpringLayout.NORTH, formatPanel);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, radIncDiff, -100, SpringLayout.HORIZONTAL_CENTER,
                formatPanel);
        layout.putConstraint(SpringLayout.NORTH, radIncDiff, 40, SpringLayout.NORTH, formatPanel);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, radTimed, 0, SpringLayout.HORIZONTAL_CENTER,
                formatPanel);
        layout.putConstraint(SpringLayout.NORTH, radTimed, 40, SpringLayout.NORTH, formatPanel);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, radRandom, 100, SpringLayout.HORIZONTAL_CENTER,
                formatPanel);
        layout.putConstraint(SpringLayout.NORTH, radRandom, 40, SpringLayout.NORTH, formatPanel);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnStart, 0, SpringLayout.HORIZONTAL_CENTER, formatPanel);
        layout.putConstraint(SpringLayout.NORTH, btnStart, 80, SpringLayout.NORTH, formatPanel);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblQuestion, 0, SpringLayout.HORIZONTAL_CENTER, quizPanel);
        layout.putConstraint(SpringLayout.NORTH, lblQuestion, 50, SpringLayout.NORTH, quizPanel);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnAnsZero, -200, SpringLayout.HORIZONTAL_CENTER,
                quizPanel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, btnAnsZero, -50, SpringLayout.VERTICAL_CENTER, quizPanel);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnAnsOne, 200, SpringLayout.HORIZONTAL_CENTER, quizPanel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, btnAnsOne, -50, SpringLayout.VERTICAL_CENTER, quizPanel);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnAnsTwo, -200, SpringLayout.HORIZONTAL_CENTER,
                quizPanel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, btnAnsTwo, 50, SpringLayout.VERTICAL_CENTER, quizPanel);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnAnsThree, 200, SpringLayout.HORIZONTAL_CENTER,
                quizPanel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, btnAnsThree, 50, SpringLayout.VERTICAL_CENTER, quizPanel);

        layout.putConstraint(SpringLayout.WEST, lblMarks, 10, SpringLayout.WEST, quizPanel);
        layout.putConstraint(SpringLayout.SOUTH, lblMarks, -10, SpringLayout.SOUTH, quizPanel);

        
    }
    @Override
    void setupActionListener() {
        btnStart.addActionListener(this);
        btnStart.setActionCommand(STARTQUIZ);

        btnAnsZero.addActionListener(this);
        btnAnsZero.setActionCommand(ANS0);

        btnAnsOne.addActionListener(this);
        btnAnsOne.setActionCommand(ANS1);

        btnAnsTwo.addActionListener(this);
        btnAnsTwo.setActionCommand(ANS2);

        btnAnsThree.addActionListener(this);
        btnAnsThree.setActionCommand(ANS3);

        
    }
    public void startQuiz(boolean incOrRand) {
        formatPanel.setVisible(false);
        quizPanel.setVisible(true);
        if(quiz.getType()==quizType.TIMED){
            startTime = startTimer();
        }
        qNums = bank.incOrRand(incOrRand);
        
        nextQuestion(null);
    }

    public void nextQuestion(Answer answerChoice) {
        int questionID = 0;
        try {
            Question currentQ = bank.returnQuestionAtPointer(qNums[qPointer]);
            questionID = currentQ.getQuestionID();
            if (answerChoice != null) {
                
                if (answerChoice.isCorrect()) {
                    marks++;
                    lblMarks.setText("Marks: " + marks);
                   
                }
                qPointer++;
            }
            
            lblQuestion.setText("<html><pre>" + currentQ.getqString() + "</pre></html>");
        } catch (ArrayIndexOutOfBoundsException e) {
            //end quiz
            endQuiz();
            return;
        }
        try {
            btnAnsTwo.setVisible(true);
            btnAnsThree.setVisible(true);
            ans0 = bank.returnAnswerAtPointer(questionID, 0); 
            btnAnsZero.setText(ans0.getAnsString());
            ans1 = bank.returnAnswerAtPointer(questionID, 1); 
            btnAnsOne.setText(ans1.getAnsString());
            ans2 = bank.returnAnswerAtPointer(questionID, 2); 
            btnAnsTwo.setText(ans2.getAnsString());
            ans3 = bank.returnAnswerAtPointer(questionID, 3); 
            btnAnsThree.setText(ans3.getAnsString());
        } catch (ArrayIndexOutOfBoundsException e) {
            btnAnsTwo.setVisible(false);
            btnAnsThree.setVisible(false);
        }

    }
    


    public void endQuiz(){
        quiz.setMark(marks);
        if(quiz.getType()==quizType.TIMED){
            endTimer(startTime);
        }
        new QuizOver(u,quiz);
        frame.dispose();
    }
    public long startTimer() { //method to begin timer
		long startTime = System.currentTimeMillis();
        return startTime;
	}
	public  void endTimer(long startTime) { //method to end timer and return elapsed time
        long secondsDivisor = 1000;
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        quiz.setTime(elapsedTime / secondsDivisor); 

	}
}