import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QuestionServices service = new QuestionServices();
            service.initializeQuestions();
            QuizApp quizApp = new QuizApp(service);
            quizApp.setVisible(true);
        });
    }
}

class QuizApp extends JFrame {
    private QuestionServices questionServices;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup group;
    private JButton nextButton;

    public QuizApp(QuestionServices service) {
        this.questionServices = service;
        setTitle("Java Basics Quiz");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        questionLabel = new JLabel();
        options = new JRadioButton[4];
        group = new ButtonGroup();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(questionLabel);

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            group.add(options[i]);
            panel.add(options[i]);
        }

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questionServices.questions.length) {
                    displayQuestion();
                } else {
                    showScore();
                }
            }
        });
        panel.add(nextButton);

        add(panel);
        displayQuestion();
    }

    private void displayQuestion() {
        Questions q = questionServices.questions[currentQuestionIndex];
        questionLabel.setText(q.getQuestions());
        options[0].setText(q.getOPT1());
        options[1].setText(q.getOPT2());
        options[2].setText(q.getOPT3());
        options[3].setText(q.getOPT4());
        group.clearSelection();
    }

    private void checkAnswer() {
        Questions q = questionServices.questions[currentQuestionIndex];
        String correctAnswer = q.getANS();
        for (JRadioButton option : options) {
            if (option.isSelected() && option.getText().equals(correctAnswer)) {
                score++;
                break;
            }
        }
    }

    private void showScore() {
        JOptionPane.showMessageDialog(this, "Quiz Over! Your score is: " + score);
        System.exit(0);
    }
}

class QuestionServices {
    Questions[] questions = new Questions[5];

    public void initializeQuestions() {
        questions[0] = new Questions();
        questions[0].setId(1);
        questions[0].setQuestions("Which of the following is a valid keyword in Java?");
        questions[0].setOPT1("interface");
        questions[0].setOPT2("string");
        questions[0].setOPT3("Float");
        questions[0].setOPT4("unsigned");
        questions[0].setANS("interface");

        questions[1] = new Questions();
        questions[1].setId(2);
        questions[1].setQuestions("Which method is used to start a thread in Java?");
        questions[1].setOPT1("init()");
        questions[1].setOPT2("start()");
        questions[1].setOPT3("run()");
        questions[1].setOPT4("resume()");
        questions[1].setANS("start()");

        questions[2] = new Questions();
        questions[2].setId(3);
        questions[2].setQuestions("Which of the following is not a primitive data type in Java?");
        questions[2].setOPT1("int");
        questions[2].setOPT2("float");
        questions[2].setOPT3("boolean");
        questions[2].setOPT4("String");
        questions[2].setANS("String");

        questions[3] = new Questions();
        questions[3].setId(4);
        questions[3].setQuestions("What is the size of an int in Java?");
        questions[3].setOPT1("4 bytes");
        questions[3].setOPT2("8 bytes");
        questions[3].setOPT3("2 bytes");
        questions[3].setOPT4("16 bytes");
        questions[3].setANS("4 bytes");

        questions[4] = new Questions();
        questions[4].setId(5);
        questions[4].setQuestions("Which of the following is used to handle exceptions in Java?");
        questions[4].setOPT1("try-catch");
        questions[4].setOPT2("final");
        questions[4].setOPT3("throw");
        questions[4].setOPT4("None of the above");
        questions[4].setANS("try-catch");
    }

    public void DCisplayQuestions() {
        System.out.println(questions[0].getQuestions());
    }
}

class Questions {
    private int id;
    private String Questions;
    private String OPT1;
    private String OPT2;
    private String OPT3;
    private String OPT4;
    private String ANS;

    // Getters And Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestions() {
        return Questions;
    }

    public void setQuestions(String questions) {
        this.Questions = questions;
    }

    public String getOPT1() {
        return OPT1;
    }

    public void setOPT1(String oPT1) {
        this.OPT1 = oPT1;
    }

    public String getOPT2() {
        return OPT2;
    }

    public void setOPT2(String oPT2) {
        this.OPT2 = oPT2;
    }

    public String getOPT3() {
        return OPT3;
    }

    public void setOPT3(String oPT3) {
        this.OPT3 = oPT3;
    }

    public String getOPT4() {
        return OPT4;
    }

    public void setOPT4(String oPT4) {
        this.OPT4 = oPT4;
    }

    public String getANS() {
        return ANS;
    }

    public void setANS(String ANS) {
        this.ANS = ANS;
    }
}
