//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Question
// Files:           (Quiz,JsonExport,Main,JsonParser,Quiz,Topic)
// Course:          (CS 400, 2019)
//
// Author:         	Aksel Torgerson, Dylan Clark, Zach Chanak, Jacob Mayl
//									Parker Schroeder
// Email:           (see README)
// Lecturer's Name: (see README)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (See README)
// Partner Email:   (See README)
// Partner Lecturer's Name: (See README)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//    N/A  Write-up states that pair programming is allowed for this assignment.
//    N/A  We have both read and understand the course Pair Programming Policy.
//    N/A  We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here.  Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         N/A
// Previous Course: N/A
// Online Sources:  N/A
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
package sample;
import java.util.ArrayList;

/**
 * The main class serves as the class that starts the program, and contains all
 * scenes, as well as all the transitions between them.
 * @author Dylan Clark, Aksel Torgerson, Zach Chanak, Jacob May,l Parker Schroeder
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

public class Main extends Application {
    //initialize vars
    static Quiz quiz;
    private int questionNumber = 1;

    /**
     * this method contains all the scenes that the user can access,
     * with correct transitions to the next scene, depending on the
     * button that is pushed.
     * @param primaryStage is the stage all the scenes take place in.
     *
     */
    @Override public void start(Stage primaryStage) throws Exception {

        // ---------------------------------------------------------- MAIN SCREEN ----------------------
        Label titleLabel = new Label("Quiz Generator"); // all the nodes that go on the main screen scene
        titleLabel.setFont(Font.font("Denmark", 30));		//contains:
        Button startButton = new Button("Start Quiz");	//start quiz button
        startButton.setFont(Font.font("Denmark", 15));
        Button newQuestionButton = new Button("Add Question"); //add question button
        newQuestionButton.setFont(Font.font("Denmark", 15));
        Button importButton = new Button("Import Questions"); //import question button
        importButton.setFont(Font.font("Denmark", 15));
        Button exportButton = new Button("Export Questions"); //export question button
        exportButton.setFont(Font.font("Denmark", 15));
        Button quitButton = new Button("Quit"); //quit button
        quitButton.setFont(Font.font("Denmark", 15));

        // --------------------------------------------------------- ADD QUESTION -----------------------
        Label addQuestionTitle = new Label("Add Question"); // all of the nodes that go on the add question scene. Contains:
        addQuestionTitle.setFont(Font.font("Denmark", 30));
        Label questionPrompt = new Label("Enter Question: ");  //adds title
        questionPrompt.setFont(Font.font("Denmark", 15));
        Label topicPrompt = new Label("Enter Topic: "); //adds label
        topicPrompt.setFont(Font.font("Denmark", 15));
        Label answer1 = new Label("Enter Correct Answer: "); //adds label
        answer1.setFont(Font.font("Denmark", 15));
        answer1.setTextFill(Paint.valueOf("Green"));
        Label answer2 = new Label("Enter Alternative Answer: "); //adds label
        answer2.setFont(Font.font("Denmark", 15));
        answer2.setTextFill(Paint.valueOf("Red"));
        Label answer3 = new Label("Enter Alternative Answer: "); //adds label
        answer3.setFont(Font.font("Denmark", 15));
        answer3.setTextFill(Paint.valueOf("Red"));
        Label answer4 = new Label("Enter Alternative Answer: "); //adds label
        answer4.setFont(Font.font("Denmark", 15));
        answer4.setTextFill(Paint.valueOf("Red"));
        Label answer5 = new Label("Enter Alternative Answer: "); //adds label
        answer5.setFont(Font.font("Denmark", 15));
        answer5.setTextFill(Paint.valueOf("Red"));
        Label questionImage = new Label("Image path: "); //adds label
        questionImage.setFont(Font.font("Denmark", 15));
        Label totalNumberOfQuestions = new Label("Number of questions loaded: " + 0); //adds label
        totalNumberOfQuestions.setAlignment(Pos.CENTER_LEFT);
        totalNumberOfQuestions.setFont(Font.font("Denmark", 15));
        Label totalNumberOfTopics = new Label("Number of different topics: " + 0); //adds label
        totalNumberOfTopics.setAlignment(Pos.CENTER_LEFT);
        totalNumberOfTopics.setFont(Font.font("Denmark", 15));

        Button back = new Button("Back"); //back button to return to main
        Button addQuestion = new Button("Add Question"); //add Question button
        //add text fields to allow user to input question and topic
        TextField newQuestion = new TextField();
        TextField newTopic = new TextField();
        //add text fields to allow user to input answers and an image
        TextField a1 = new TextField();
        TextField a2 = new TextField();
        TextField a3 = new TextField();
        TextField a4 = new TextField();
        TextField a5 = new TextField();
        TextField imagePath = new TextField();

        HBox hb2 = new HBox(10); // were using a vbox of hboxes for the components that are close to each other
        hb2.setAlignment(Pos.CENTER);
        hb2.getChildren().addAll(questionPrompt, newQuestion);
        HBox hb3 = new HBox(10);
        hb3.getChildren().addAll(topicPrompt, newTopic);
        hb3.setAlignment(Pos.CENTER);
        HBox hb4 = new HBox(10);
        hb4.getChildren().addAll(answer1, a1);
        hb4.setAlignment(Pos.CENTER);
        HBox hb5 = new HBox(10);
        hb5.getChildren().addAll(answer2, a2);
        hb5.setAlignment(Pos.CENTER);
        HBox hb6 = new HBox(10);
        hb6.getChildren().addAll(answer3, a3);
        hb6.setAlignment(Pos.CENTER);
        HBox hb7 = new HBox(10);
        hb7.getChildren().addAll(answer4, a4);
        hb7.setAlignment(Pos.CENTER);
        HBox hb8 = new HBox(10);
        hb8.getChildren().addAll(answer5, a5);
        hb8.setAlignment(Pos.CENTER);
        HBox hb9 = new HBox(10);
        hb9.getChildren().addAll(questionImage, imagePath);
        hb9.setAlignment(Pos.CENTER);
        HBox hb10 = new HBox(10);
        hb10.getChildren().addAll(back, addQuestion);
        hb10.setAlignment(Pos.CENTER);
        HBox hb11 = new HBox(10);
        hb11.getChildren().addAll(totalNumberOfQuestions, totalNumberOfTopics);
        hb11.setAlignment(Pos.BASELINE_CENTER);

        // ------------------------------------------------------------------------ QUIT SCREEN -----------------
        Label goodByeMessage = new Label("Thanks for playing :)"); // all of the nodes on the quit screen page
        goodByeMessage.setFont(Font.font("Denmark", 30));
        goodByeMessage.setAlignment(Pos.TOP_CENTER);
        Button quit = new Button("Quit");
        quit.setFont(Font.font("Denmark", 15));
        quit.setAlignment(Pos.CENTER);
        Button saveAndQuit = new Button("Save");
        saveAndQuit.setFont(Font.font("Denmark", 15));
        saveAndQuit.setAlignment(Pos.CENTER);
        Button backToMain = new Button("Back");
        backToMain.setFont(Font.font("Denmark", 15));
        backToMain.setAlignment(Pos.CENTER);

        // ------------------------------------------------------------------------ START QUIZ SCREEN --------------
        Label startQuiz = new Label("Start Quiz"); // all of the nodes on the start quiz screen
        startQuiz.setFont(Font.font("Denmark", 30));
        startQuiz.setAlignment(Pos.TOP_CENTER);
        Label topicLabel = new Label("Select Topic: ");
        topicLabel.setFont(Font.font("Denmark", 15));
        Label desiredNumQuestions = new Label("Number of questions: ");
        desiredNumQuestions.setFont(Font.font("Denmark", 15));
        Button backToMain2 = new Button("Back");
        backToMain2.setFont(Font.font("Denmark", 15));
        Button addTopicQuestions = new Button("Add Questions");
        addTopicQuestions.setFont(Font.font("Denmark", 15));
        Button startQuizBtn = new Button("Start");
        startQuizBtn.setFont(Font.font("Denmark", 15));
        TextField numQuestions = new TextField();
        numQuestions.setFont(Font.font("Denmark", 15));


        ObservableList<String> options =
            FXCollections.observableArrayList("Math", "Science", "Smash Ultimate");
        Comparator<String> stringComparator = Comparator.comparing(String::toString);

        Label currentLoadedQuestions = new Label("Number of questions: 0");
        currentLoadedQuestions.setFont(Font.font("Denmark", 15));

        ComboBox<String> topicBox = new ComboBox<String>(options);
        topicBox.setPromptText("Choose topic");
        HBox hbsq1 = new HBox(10);
        HBox hbsq2 = new HBox(10);
        HBox hbsq3 = new HBox(10);
        HBox hbsq4 = new HBox(10);
        hbsq1.getChildren().addAll(topicLabel, topicBox);
        hbsq1.setAlignment(Pos.CENTER);
        hbsq2.getChildren().addAll(desiredNumQuestions, numQuestions);
        hbsq2.setAlignment(Pos.CENTER);
        hbsq3.getChildren().addAll(backToMain2,addTopicQuestions, startQuizBtn);
        hbsq3.setAlignment(Pos.CENTER);
        hbsq4.getChildren().addAll(currentLoadedQuestions);
        hbsq4.setAlignment(Pos.CENTER);

        // ------------------------------------------------------------------------- Question Screen
        // -----------------

        Label questionNum = new Label("Question Number: " + questionNumber);
        questionNum.setFont(Font.font("Denmark", 30));
        Label currentTopic = new Label("Topic: " + "Topic here");
        currentTopic.setFont(Font.font("Denmark", 15));
        Label questionLabel = new Label("Question: " + "What is the BEST CS class?");
        questionLabel.setFont(Font.font("Denmark", 15));

        Label answerList = new Label("Answers: ");
        answerList.setFont(Font.font("Denmark", 15));

        ObservableList<String> answers = FXCollections
            .observableArrayList("Answer 1", "Answer 2", "Answer 3", "Answer 4", "Answer 5");

        ComboBox<String> answersBox = new ComboBox<String>(answers);

        Button nextQuestion = new Button("Next");
        nextQuestion.setFont(Font.font("Denmark", 20));
        Button submitAnswer = new Button("Submit");
        submitAnswer.setFont(Font.font("Denmark", 20));

        Label answerResult = new Label("");

        Image image =
            new Image("https://static.thisisinsider.com/image/5c59e77ceb3ce80d46564023.jpg");
        ImageView viewImage = new ImageView(image);
        viewImage.setFitHeight(200);
        viewImage.setFitWidth(200);
        HBox answerSelect = new HBox(10);
        answerSelect.getChildren().addAll(answerList, answersBox);
        answerSelect.setAlignment(Pos.CENTER);


        // --------------------------------------------------------------- IMPORT SCREEN ----------
        Label importQuestions = new Label("Import Questions");
        importQuestions.setFont(Font.font("Denmark", 30));
        Label enterFileName = new Label("Enter filename: ");
        enterFileName.setFont(Font.font("Denmark", 15));
        TextField fileNameHere = new TextField();
        Button backToMain3 = new Button("Back");
        backToMain3.setFont(Font.font("Denmark", 15));
        Button submitImport = new Button("Submit");
        submitImport.setFont(Font.font("Denmark", 15));
        HBox importBox1 = new HBox(10);
        importBox1.getChildren().addAll(enterFileName, fileNameHere);
        importBox1.setAlignment(Pos.CENTER);
        HBox importBox2 = new HBox(10);
        importBox2.getChildren().addAll(backToMain3, submitImport);
        importBox2.setAlignment(Pos.CENTER);

        // -------------------------------------------------------------- FINAL SCREEN ------------
        Label quizOver = new Label("Quiz Complete!");
        quizOver.setFont(Font.font("Denmark", 30));
        Label finalCorrect = new Label("Correct: ");
        finalCorrect.setFont(Font.font("Denmark", 15));
        Label finalWrong = new Label("Total Answered: ");
        finalWrong.setFont(Font.font("Denmark", 15));
        Label percentage = new Label("Percentage: " + 0 + "%");
        percentage.setFont(Font.font("Denmark", 15));
        Button endQuiz = new Button("Try again");
        endQuiz.setFont(Font.font("Denmark", 15));
        Button quitQuiz = new Button("Quit");
        quitQuiz.setFont(Font.font("Denmark", 15));

        HBox finalBox1 = new HBox(10);
        finalBox1.getChildren().addAll(endQuiz, quitQuiz);
        finalBox1.setAlignment(Pos.CENTER);

        //------------------------------------------------------------------------- EXPORT SCREEN -------------------

        Label exportQuestions = new Label("Export Questions");
        exportQuestions.setFont(Font.font("Denmark", 30));
        Label exportFileName = new Label("Enter filename: ");
        exportFileName.setFont(Font.font("Denmark", 15));
        TextField exportFileNameField = new TextField();
        exportFileNameField.setPromptText("Name file");
        Button backToMain4 = new Button("Back");
        backToMain4.setFont(Font.font("Denmark", 15));
        Button submitExport = new Button("Submit");
        submitExport.setFont(Font.font("Denmark", 15));
        Label jsonLabel = new Label(".json");
        jsonLabel.setFont(Font.font("Denmark", 15));

        HBox eBox1 = new HBox(10);
        eBox1.getChildren().addAll(exportFileName, exportFileNameField, jsonLabel);
        eBox1.setAlignment(Pos.CENTER);
        HBox eBox2 = new HBox(10);
        eBox2.getChildren().addAll(backToMain4, submitExport);
        eBox2.setAlignment(Pos.CENTER);

        // ------------------------------------------------------------------------- SCENES -----------------------
        // main screen is a vbox
        VBox mainVbox = new VBox();
        mainVbox.getChildren()
            .addAll(titleLabel, startButton, newQuestionButton, importButton, exportButton,
                quitButton);
        mainVbox.setAlignment(Pos.TOP_CENTER);
        mainVbox.setSpacing(10);

        // add question is a vbox holding hboxes
        VBox addQuestionVbox = new VBox();
        addQuestionVbox.getChildren()
            .addAll(addQuestionTitle, hb2, hb3, hb4, hb5, hb6, hb7, hb8, hb9, hb10, hb11);
        addQuestionVbox.setAlignment(Pos.TOP_CENTER);
        addQuestionVbox.setSpacing(10);

        // quit screen is a vbox
        VBox quitScreen = new VBox(10, goodByeMessage, backToMain, saveAndQuit, quit);
        quitScreen.setAlignment(Pos.TOP_CENTER);

        // start game screen is a vbox
        VBox startScreen = new VBox(10, startQuiz, hbsq2, hbsq1, hbsq3, hbsq4);
        startScreen.setAlignment(Pos.TOP_CENTER);

        // current question is a vbox of hbox
        VBox currQuestion =
            new VBox(10, questionNum, viewImage, questionLabel, currentTopic, answerSelect,
                submitAnswer, answerResult);
        currQuestion.setAlignment(Pos.TOP_CENTER);

        // import questions is a vbox of hbox
        VBox importScreen = new VBox(10, importQuestions, importBox1, importBox2);
        importScreen.setAlignment(Pos.TOP_CENTER);

        // export questions is a vbox of hbox
        VBox exportScreen = new VBox(10, exportQuestions, eBox1, eBox2);
        exportScreen.setAlignment(Pos.TOP_CENTER);

        // final screen is a vbox
        VBox finalScreen = new VBox(10, quizOver, finalCorrect, finalWrong, percentage, finalBox1);
        finalScreen.setAlignment(Pos.TOP_CENTER);

        // ----------------------------------------------------------------------------- CREATING SCENES
        // Creating a scene object
        Scene mainPage = new Scene(mainVbox, 300, 260);
        Scene addQuestionPage = new Scene(addQuestionVbox, 450, 400);
        Scene quitPage = new Scene(quitScreen, 300, 175);
        Scene startPage = new Scene(startScreen, 375, 200);
        Scene quizPage = new Scene(currQuestion, 450, 445);
        Scene importPage = new Scene(importScreen, 300, 130);
        Scene endPage = new Scene(finalScreen, 300, 185);
        Scene exportPage = new Scene(exportScreen, 330, 130);

        // ----------------------------------------------------------------------------- STAGE STUFF
        primaryStage.setTitle("Quiz Generator"); //sets title
        primaryStage.setScene(mainPage); //sets scene to mainPage
        primaryStage.show(); //show scene

        // ------------------------------------------------------------------------------ BUTTON ACTIONS
        // main screen actions. Each button directs the stage to another scene
        importButton.setOnAction(event -> primaryStage.setScene(importPage));
        backToMain3.setOnAction(event -> primaryStage.setScene(mainPage));
        exportButton.setOnAction(event -> primaryStage.setScene(exportPage));
        backToMain4.setOnAction(event -> primaryStage.setScene(mainPage));
        saveAndQuit.setOnAction(event -> primaryStage.setScene(exportPage));
        submitExport.setOnAction(event -> {
            try {
                String result = JsonExport.exportToJson(quiz);
                FileWriter file = new FileWriter(exportFileNameField.getText().concat(".json"));
                file.write(result);
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            primaryStage.setScene(mainPage);
        });

        submitImport.setOnAction(event -> {
            try {
                JsonParser jsonParser = new JsonParser();
                String filename = fileNameHere.getText();
                jsonParser.getQuestions(filename, quiz);
                primaryStage.setScene(mainPage);
            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (FileNotFoundException e1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Sorry your file could not be found");
                alert.setContentText("I can't believe you've done this");
                alert.showAndWait();

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        newQuestionButton.setOnAction(event -> {
            totalNumberOfQuestions.setText("Number of questions loaded: " + quiz.questionNum);
            totalNumberOfTopics.setText("Number of different topics: " + quiz.topicList.size());
            primaryStage.setScene(addQuestionPage);
        });

        // start quiz actions
        startButton.setOnAction(event -> {
            ObservableList<String> tempOptions = FXCollections.observableArrayList(quiz.topicNames);
            FXCollections.sort(tempOptions, stringComparator);
            topicBox.setItems(tempOptions);
            currentLoadedQuestions.setText("Number of questions: 0");
            topicBox.setPromptText("Choose topic");
            numQuestions.setPromptText("Number of questions");
            primaryStage.setScene(startPage);
            questionNumber = 1;
        });

        startQuizBtn.setOnAction(event -> {
            if(quiz.questionNum != 0 && quiz.questionList.size() !=0) {
                quiz.randomizeQuestionList();
                transitionToNextQuestion(currentTopic, questionLabel, answersBox, viewImage, answerResult, questionNum);
                primaryStage.setScene(quizPage);
            } else {
                topicBox.setPromptText("Choose a valid response");
                numQuestions.setPromptText("Enter a valid response");
            }
        });

        addTopicQuestions.setOnAction(event -> {
            boolean success = true;
            int number = 0;

            try {
                number = Integer.parseInt(numQuestions.getText());
                if (number < 1) {
                    numQuestions.setText("");
                    numQuestions.setPromptText("Enter a number > 1");
                    success = false;
                }
            } catch (NumberFormatException e) {
                numQuestions.setText("");
                numQuestions.setPromptText("Enter a valid response");
                success = false;
            }

            String topic = topicBox.getValue();
            if (topic == null || topic.equals("")) {
                topicBox.setPromptText("Choose a valid response");
                success = false;
            }

            if (success) {
                quiz.getQuestions(topic, number);

            }

            currentLoadedQuestions.setText("Number of questions: " + quiz.questionList.size());
        });

        nextQuestion.setOnAction(event -> {
            questionNumber++;
            questionNum.setText("Question Number: " + questionNumber);
            if (quiz.questionList.get(0).correctAnswer.equals(answersBox.getValue().toString())) {
                quiz.questionList.remove(0);
                quiz.setCorrect(quiz.getCorrect() + 1);
            } else {
                quiz.questionList.remove(0);
                quiz.setIncorrect(quiz.getIncorrect() + 1);
            }
            if (quiz.questionList.size() == 0) {
                primaryStage.setScene(endPage);
                transitionToEndScreen(finalCorrect, finalWrong, percentage);
            } else {
                transitionToNextQuestion(currentTopic, questionLabel, answersBox, viewImage, answerResult, questionNum);
            }

            currQuestion.getChildren().removeAll(nextQuestion);
            currQuestion.getChildren().addAll(submitAnswer);
        });

        submitAnswer.setOnAction(event -> {

            if (quiz.questionList.get(0).correctAnswer.equals(answersBox.getValue().toString())) {
                answerResult.setTextFill(Paint.valueOf("Green"));
                answerResult.setText("CORRECT");
            } else {
                answerResult.setTextFill(Paint.valueOf("Red"));
                answerResult.setText("INCORRECT");
            }
            currQuestion.getChildren().removeAll(submitAnswer);
            currQuestion.getChildren().addAll(nextQuestion);
        });

        // add question actions
        back.setOnAction(event -> primaryStage.setScene(mainPage));
        backToMain.setOnAction(event -> primaryStage.setScene(mainPage));
        quitButton.setOnAction(event -> primaryStage.setScene(quitPage));
        quit.setOnAction(event -> primaryStage.close());
        backToMain2.setOnAction(event -> primaryStage.setScene(mainPage));
        endQuiz.setOnAction(event -> {
            quiz.setCorrect(0);
            quiz.setIncorrect(0);

            primaryStage.setScene(mainPage);
        });
        quitQuiz.setOnAction(event -> primaryStage.setScene(quitPage));


        addQuestion.setOnAction(event -> {
            boolean success = true;
            //check if the fields have been correctly inputted
            success = (checkTextField(newQuestion)) ? success : false;
            success = (checkTextField(newTopic)) ? success : false;
            success = (checkTextField(a1)) ? success : false;
            success = (checkTextField(a2)) ? success : false;
            success = (checkTextField(a3)) ? success : false;
            success = (checkTextField(a4)) ? success : false;
            success = (checkTextField(a5)) ? success : false;
            if (imagePath.getText() != null || imagePath.getText().equals("")) {
                File test = new File("./src/" + imagePath.getText());
                Boolean exist = test.exists();
                if (!exist) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Incorrect image path");
                    alert.setContentText("I can't believe you've done this");
                    alert.showAndWait();
                    success = false;
                }
            }

            if (success) {
                ArrayList<String> allAnswers = new ArrayList<String>();
                allAnswers.add(a1.getText());
                allAnswers.add(a2.getText());
                allAnswers.add(a3.getText());
                allAnswers.add(a4.getText());
                allAnswers.add(a5.getText());
                Collections.shuffle(allAnswers);

                //creates the question with given inputs
                Question question =
                    new Question("", newQuestion.getText(), newTopic.getText(), imagePath.getText(),
                        a1.getText(), allAnswers);
                quiz.addQuestion(question);
                quiz.addToTotalQuestionList(question);
            }

            totalNumberOfQuestions.setText("Number of questions loaded: " + quiz.questionNum);
            totalNumberOfTopics.setText("Number of different topics: " + quiz.topicNames.size());
        });
    }
    /**
     *
     * @param textField is the users input to the textField
     * @return boolean true if response is valid, false otherwise
     */
    private boolean checkTextField(TextField textField) {
        if (textField.getText() == null || textField.getText().equals("")) {
            textField.setPromptText("Enter a valid response");
            return false;
        }
        return true;
    }
    /**
     * this method is called when the user clicks the button to go to the next slide.
     * Reveals if the user was right or not and then transitions to the next question
     *
     * @param topicLabel the label of the topic
     * @param questionLabel the label of the question
     * @param answersBox the box containing the answers
     * @param viewImage the place where the question's image is located
     * @param answerResult the label that says if the user got the right anwer
     * @param questionNum the label of which question the user is on
     */
    private void transitionToNextQuestion(Label topicLabel, Label questionLabel,
        ComboBox<String> answersBox, ImageView viewImage, Label answerResult, Label questionNum) {
        Question question = quiz.questionList.get(0);
        topicLabel.setText("Current topic: " + question.topic);
        questionLabel.setText("Question: " + question.questionText);
        answersBox.setPromptText("Choose an answer");
        ObservableList<String> answers = FXCollections.observableArrayList(question.allAnswers);
        answersBox.setItems(answers);
        answersBox.setValue("");
        answerResult.setText("");
        questionNum.setText("Question Number: " + questionNumber);
        try {
            Image image = new Image(question.imageName);
            viewImage.setImage(image);
        } catch (IllegalArgumentException e) {
            viewImage.setImage(null);
        }
    }
    /**
     * @param finalCorrect the label of how many questions the user got right
     * @param finalWrong the label of how many questions the user got wrong
     * @param percentage the percentage the of correct answers
     */
    private void transitionToEndScreen(Label finalCorrect, Label finalWrong, Label percentage) {
        finalCorrect.setText("Correct: " + quiz.getCorrect());
        finalWrong.setText("Total Number: " + (quiz.getIncorrect() + quiz.getCorrect()));
        percentage.setText("Percentage: " + Math.round(quiz.getPercentage()) + "%");
    }

    public static void main(String[] args) {
        quiz = new Quiz(); //creates a new quiz
        launch(args); //launches prog
    }
}
