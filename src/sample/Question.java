
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Question
// Files:           (Quiz,JsonExport,Main,JsonParser,Quiz,Topic)
// Course:          (CS 400, 2019)
//
// Author:          (See README)
// Email:           (See README)
// Lecturer's Name: (See README)
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
 * Question class that serves as the class that stores all of the fields of the question
 * these questions are added to the Topic class and the Quiz class.
 * @author Parker Schroeder, Dylan Clark, Zachary Chanak, Jacob Mayl
 */
public class Question {
    // Field for the QuestionNode
    String metadata; //metadata field not really used
    String questionText; //Contains the text for the question
    String topic; //Contains the topic for the question
    String imageName; //Name of the image if used
    String correctAnswer; //The correct answer as a string
    ArrayList<String> allAnswers; //All of the available answers

    /**
     * Constructor for the Question class that creates a new question
     * with all of the required fields
     * @param metadata not really used
     * @param questionText the test of the question
     * @param topic the topic of the question
     * @param imageName the name of the image
     * @param correctAnswer the string of the correct answer
     * @param allAnswers the ArrayList of all the answers
     */
    public Question(String metadata, String questionText, String topic, String imageName,
        String correctAnswer, ArrayList<String> allAnswers) {
        this.metadata = metadata;
        this.questionText = questionText;
        this.topic = topic;
        this.imageName = imageName;
        this.correctAnswer = correctAnswer;
        this.allAnswers = allAnswers;
    }

    //Getter methods used for testing
    /**
     * Getter method for metadata
     * @return current metadata
     */
    public String getMetadata() {
        return this.metadata;
    }
    /**
     * Getter method for questionText
     * @return current questionText
     */
    public String questionText() {
        return this.questionText;
    }
    /**
     * Getter method for topic
     * @return current topic
     */
    public String getTopic() {
        return this.topic;
    }
    /**
     * Getter method for imageName
     * @return current image name
     */
    public String imageName() {
        return this.imageName;
    }
    /**
     * Getter method for correctAnswer
     * @return current correct answer
     */
    public String getCorrectAnswer() {
        return this.correctAnswer;
    }
    /**
     * Getter method for allAnswers
     * @return current list of all answers
     */
    public ArrayList<String> getallAnswers() {
        return this.allAnswers;
    }
}

