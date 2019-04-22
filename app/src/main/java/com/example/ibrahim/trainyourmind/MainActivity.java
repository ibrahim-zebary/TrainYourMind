package com.example.ibrahim.trainyourmind;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // FIRST LAYOUT
    LinearLayout firstLayout;
    EditText yourName;
    String name;
    Button submitButton;

    // SECOND LAYOUT
    LinearLayout secondLayout;
    Button playButton;

    // THIRD LAYOUT - First Question
    ProgressBar progressBar;
    LinearLayout firstQuestion;
    EditText questionOneEditText;
    Button firstQuestionNextButton;

    // Second Question
    LinearLayout secondQuestion;
    RadioGroup secondQuestionRadioGroup;
    RadioButton secondQuestionOptionOne;
    RadioButton secondQuestionOptionTwo;
    RadioButton secondQuestionOptionThree;
    RadioButton secondQuestionOptionFour;
    RadioButton secondQuestionOptionFive;
    RadioButton secondQuestionOptionSix;
    Button secondQuestionNextButton;

    // Third Question
    LinearLayout thirdQuestion;
    CheckBox thirdQuestionOptionOne;
    CheckBox thirdQuestionOptionTwo;
    CheckBox thirdQuestionOptionThree;
    CheckBox thirdQuestionOptionFour;
    CheckBox thirdQuestionOptionFive;
    Button thirdQuestionNextButton;

    // Fourth Question
    LinearLayout fourthQuestion;
    RadioGroup fourthQuestionRadioGroup;
    RadioButton fourthQuestionOptionOne;
    RadioButton fourthQuestionOptionTwo;
    RadioButton fourthQuestionOptionThree;
    RadioButton fourthQuestionOptionFour;
    Button fourthQuestionNextButton;

    // Fifth Question
    LinearLayout fifthQuestion;
    RadioGroup fifthQuestionRadioGroup;
    RadioButton fifthQuestionOptionOne;
    RadioButton fifthQuestionOptionTwo;
    RadioButton fifthQuestionOptionThree;
    RadioButton fifthQuestionOptionFour;
    Button fifthQuestionSubmitButton;

    // Result Layout
    LinearLayout resultLayout;
    TextView percentage;
    LinearLayout tryAgainAndShowAnswerButtons;
    Button tryAgainButton;
    Button showAnswersButton;

    /*
     * Question one = 12 points
     * Question two = 24 points
     * Question three = 28 points
     * Question four = 16 points
     * Question five = 20 points
     */
    long score;
    int scoreIncrementer;

    // Wrong answers
    boolean questionOneAnswer;
    boolean questionTwoAnswer;
    boolean questionThreeAnswer;
    boolean questionFourAnswer;
    boolean questionFiveAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // First layout views
        firstLayout = findViewById(R.id.first_layout);
        yourName = findViewById(R.id.your_name_edit_text);
        submitButton = findViewById(R.id.submit_your_name_button);

        // Second layout views
        secondLayout = findViewById(R.id.second_layout);
        playButton = findViewById(R.id.play_button);

        // First question views and view groups
        progressBar = findViewById(R.id.progress_bar);
        firstQuestion = findViewById(R.id.first_question);
        questionOneEditText = findViewById(R.id.question_one_edit_text);
        firstQuestionNextButton = findViewById(R.id.first_question_next_button);

        // Second question views and view groups
        secondQuestion = findViewById(R.id.second_question);
        secondQuestionRadioGroup = findViewById(R.id.radio_group_second_question);
        secondQuestionOptionOne = findViewById(R.id.second_question_option_one);
        secondQuestionOptionTwo = findViewById(R.id.second_question_option_two);
        secondQuestionOptionThree = findViewById(R.id.second_question_option_three);
        secondQuestionOptionFive = findViewById(R.id.second_question_option_five);
        secondQuestionOptionSix = findViewById(R.id.second_question_option_six);
        secondQuestionOptionFour = findViewById(R.id.second_question_option_four);
        secondQuestionNextButton = findViewById(R.id.second_question_next_button);

        // Third question views and view groups
        thirdQuestion = findViewById(R.id.third_question);
        thirdQuestionOptionOne = findViewById(R.id.third_question_option_one);
        thirdQuestionOptionTwo = findViewById(R.id.third_question_option_two);
        thirdQuestionOptionThree = findViewById(R.id.third_question_option_three);
        thirdQuestionOptionFour = findViewById(R.id.third_question_option_four);
        thirdQuestionOptionFive = findViewById(R.id.third_question_option_five);
        thirdQuestionNextButton = findViewById(R.id.third_question_next_button);

        // Fourth question views and view groups
        fourthQuestion = findViewById(R.id.fourth_question);
        fourthQuestionRadioGroup = findViewById(R.id.radio_group_fourth_question);
        fourthQuestionOptionOne = findViewById(R.id.fourth_question_option_one);
        fourthQuestionOptionTwo = findViewById(R.id.fourth_question_option_two);
        fourthQuestionOptionThree = findViewById(R.id.fourth_question_option_three);
        fourthQuestionOptionFour = findViewById(R.id.fourth_question_option_four);
        fourthQuestionNextButton = findViewById(R.id.fourth_question_next_button);

        // Fifth question views and view groups
        fifthQuestion = findViewById(R.id.fifth_question);
        fifthQuestionRadioGroup = findViewById(R.id.radio_group_fifth_question);
        fifthQuestionOptionOne = findViewById(R.id.fifth_question_option_one);
        fifthQuestionOptionTwo = findViewById(R.id.fifth_question_option_two);
        fifthQuestionOptionThree = findViewById(R.id.fifth_question_option_three);
        fifthQuestionOptionFour = findViewById(R.id.fifth_question_option_four);
        fifthQuestionSubmitButton = findViewById(R.id.fifth_question_submit_button);

        // Result Layout view and view groups
        resultLayout = findViewById(R.id.result_layout);
        percentage = findViewById(R.id.percentage_of_user_brain);
        tryAgainAndShowAnswerButtons = findViewById(R.id.try_again_and_correct_answer_layout);
        tryAgainButton = findViewById(R.id.try_again);
        showAnswersButton = findViewById(R.id.show_answers);

        // To show first layout
        showFirstLayout();

        // To submit your name and prepare to start quiz
        submitButton.setOnClickListener(submitYourName);

        // To start quiz and go throw all question
        playButton.setOnClickListener(startQuiz);
        firstQuestionNextButton.setOnClickListener(startQuiz);
        secondQuestionNextButton.setOnClickListener(startQuiz);
        thirdQuestionNextButton.setOnClickListener(startQuiz);
        fourthQuestionNextButton.setOnClickListener(startQuiz);
        fifthQuestionSubmitButton.setOnClickListener(startQuiz);

        // To show user's answer and correct answers
        showAnswersButton.setOnClickListener(showScore);

        // To restart the quiz
        tryAgainButton.setOnClickListener(tryAgain);
    }

    // works for submit name button
    View.OnClickListener submitYourName = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String name = String.valueOf(yourName.getText());

            // to check if user entered his/her name or not
            if (name.equals(""))
                Toast.makeText(MainActivity.this, "Enter your name", Toast.LENGTH_SHORT).show();
            else {
                setName(name);
                showSecondLayout();
            }
        }
    };

    // this onClick will work for start quiz till end of quiz
    View.OnClickListener startQuiz = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            // start the quiz by clicking play button
            if(playButton.getId() == view.getId()) {
                showFirstQuestionLayout();
            }

            // if next button of first question is clicked, then do follows
            else if(firstQuestionNextButton.getId() == view.getId()) {

                String answer = String.valueOf(questionOneEditText.getText());
                // the user must check one of radio buttons, then show next question
                if (!answer.equalsIgnoreCase("")) {

                    // if user checked the right answer, then increase the score
                    if (answer.equalsIgnoreCase("teapot")) {
                        score = score + 12;
                        questionOneAnswer = true;
                    }
                    showSecondQuestionLayout();
                }

                // show a toast to say what should user do
                else {
                    Toast.makeText(MainActivity.this,"Write your answer",Toast.LENGTH_SHORT).show();
                }
            }

            // if next button of second question is clicked, then do follows
            else if(secondQuestionNextButton.getId() == view.getId()) {

                // the user must check one of radio buttons, then show next question
                if (secondQuestionOptionOne.isChecked() ||
                        secondQuestionOptionTwo.isChecked() ||
                        secondQuestionOptionThree.isChecked() ||
                        secondQuestionOptionFour.isChecked() ||
                        secondQuestionOptionFive.isChecked() ||
                        secondQuestionOptionSix.isChecked()) {

                    // if user checked the right answer, then increase the score
                    if (secondQuestionOptionTwo.isChecked()) {
                        score = score + 24;
                        questionTwoAnswer = true;
                    }
                    showThirdQuestionLayout();
                }

                // show a toast to say what should user do
                else {
                    Toast.makeText(MainActivity.this,"Check one of the answers",Toast.LENGTH_SHORT).show();
                }
            }

            // if next button of third question is clicked, then do follows
            else if(thirdQuestionNextButton.getId() == view.getId()) {

                // the user at least must check one of the check box's, then show next question
                if (thirdQuestionOptionOne.isChecked() ||
                        thirdQuestionOptionTwo.isChecked() ||
                        thirdQuestionOptionThree.isChecked() ||
                        thirdQuestionOptionFour.isChecked() ||
                        thirdQuestionOptionFive.isChecked()) {

                    // if user checked the right answer, then increase the score
                    if (thirdQuestionOptionOne.isChecked() &&
                            thirdQuestionOptionTwo.isChecked() &&
                            thirdQuestionOptionThree.isChecked() &&
                            thirdQuestionOptionFour.isChecked() &&
                            thirdQuestionOptionFive.isChecked()) {
                        score = score + 28;
                        questionThreeAnswer = true;
                    }
                    showFourthQuestionLayout();
                }

                // show a toast to say what should user do
                else {
                    Toast.makeText(MainActivity.this,"Check one of the answers",Toast.LENGTH_SHORT).show();
                }
            }

            // if next button of fourth question is clicked, then do follows
            else if(fourthQuestionNextButton.getId() == view.getId()) {

                // the user must check one of radio buttons, then show next question
                if (fourthQuestionOptionOne.isChecked() ||
                        fourthQuestionOptionTwo.isChecked() ||
                        fourthQuestionOptionThree.isChecked() ||
                        fourthQuestionOptionFour.isChecked()) {

                    // if user checked the right answer, then increase the score
                    if (fourthQuestionOptionTwo.isChecked()) {
                        score = score + 16;
                        questionFourAnswer = true;
                    }
                    showFifthQuestionLayout();
                }

                // show a toast to say what should user do
                else {
                    Toast.makeText(MainActivity.this,"Check one of the answers",Toast.LENGTH_SHORT).show();
                }
            }

            // if next button of fifth question is clicked, then do follows
            else if(fifthQuestionSubmitButton.getId() == view.getId()) {
                // the user must check one of radio buttons, then show next question
                if (fifthQuestionOptionOne.isChecked() ||
                        fifthQuestionOptionTwo.isChecked() ||
                        fifthQuestionOptionThree.isChecked() ||
                        fifthQuestionOptionFour.isChecked()) {

                    // if user checked the right answer, then increase the score
                    if (fifthQuestionOptionTwo.isChecked()) {
                        score = score + 20;
                        questionFiveAnswer = true;
                    }
                    showResultLayout();

                    new CountDownTimer(10500, 100) {

                        @Override
                        public void onTick(long l) {

                            // if scoreIncrementer is less than score then stop increment and do else statement
                            if (scoreIncrementer <= score) {

                                percentage.setText(scoreIncrementer + "%");
                                scoreIncrementer++;
                            }

                            else {
                                // to show linear layout of try again and correct answer buttons
                                tryAgainAndShowAnswerButtons.animate().alpha(1).setDuration(2000).start();

                                // to cancel the timer
                                cancel();
                            }
                        }

                        @Override
                        public void onFinish() {

                        }
                    }.start();
                }

                // show a toast to say what should user do
                else {
                    Toast.makeText(MainActivity.this,"Check one of the answers",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    View.OnClickListener showScore = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Toast.makeText(MainActivity.this, "Score: " + score + "/100",Toast.LENGTH_SHORT).show();
        }
    };

    View.OnClickListener tryAgain = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            progressBar.setProgress(0);
            score = 0;
            scoreIncrementer = 0;
            progressBar.setVisibility(View.GONE);
            yourName.setText(null);
            tryAgainAndShowAnswerButtons.setAlpha(0);

            questionOneAnswer = false;
            questionTwoAnswer = false;
            questionThreeAnswer = false;
            questionFourAnswer = false;
            questionFiveAnswer = false;

            questionOneEditText.setText("");
            secondQuestionRadioGroup.clearCheck();
            thirdQuestionOptionOne.setChecked(false);
            thirdQuestionOptionTwo.setChecked(false);
            thirdQuestionOptionThree.setChecked(false);
            thirdQuestionOptionFour.setChecked(false);
            thirdQuestionOptionFive.setChecked(false);
            fourthQuestionRadioGroup.clearCheck();
            fifthQuestionRadioGroup.clearCheck();

            showFirstLayout();
        }
    };

    // to show first layout
    void showFirstLayout() {
        firstLayout.setVisibility(View.VISIBLE);
    }

    // to show second layout and hide first layout
    void showSecondLayout() {
        firstLayout.setVisibility(View.GONE);
        secondLayout.setVisibility(View.VISIBLE);
    }

    // to show first question and hide second layout
    void showFirstQuestionLayout() {
        secondLayout.setVisibility(View.GONE);
        firstQuestion.setVisibility(View.VISIBLE);
        showProgressBar();
        incrementProgressBar();
    }

    // to show second question and hide first layout
    void showSecondQuestionLayout() {
        firstQuestion.setVisibility(View.GONE);
        secondQuestion.setVisibility(View.VISIBLE);
        incrementProgressBar();
    }

    // to show third question and hide second question
    void showThirdQuestionLayout() {
        secondQuestion.setVisibility(View.GONE);
        thirdQuestion.setVisibility(View.VISIBLE);
        incrementProgressBar();
    }

    // to show fourth question and hide third question
    void showFourthQuestionLayout() {
        thirdQuestion.setVisibility(View.GONE);
        fourthQuestion.setVisibility(View.VISIBLE);
        incrementProgressBar();
    }

    // to show fifth question and hide third question
    void showFifthQuestionLayout() {
        fourthQuestion.setVisibility(View.GONE);
        fifthQuestion.setVisibility(View.VISIBLE);
        incrementProgressBar();
    }

    // To show result layout
    void showResultLayout() {
        progressBar.setVisibility(View.GONE);
        fifthQuestion.setVisibility(View.GONE);
        resultLayout.setVisibility(View.VISIBLE);
    }



    // to show progress bar
    void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    // Progress bar incrementer
    void incrementProgressBar() {
        progressBar.setProgress(progressBar.getProgress() + 20);
    }

    // Getter and Setter method to set and return the name of user
    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}
