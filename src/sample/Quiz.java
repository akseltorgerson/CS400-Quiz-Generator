//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Quiz
// Files:           (Question,JsonExport,Main,JsonParser,Quiz,Topic)
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
import java.util.Random;

/**
 * Quiz class that contains many questions of various topics
 * with the ability to add questions and determine how many questions there are.
 *
 * @author Parker Schroeder, Dylan Clark, Zachary Chanak, Jacob Mayl
 */
public class Quiz {

    int questionNum; //Contains the number of question in the quiz
    ArrayList<Question> totalQuestionList; //Final list of all of the questions
    ArrayList<Question> questionList; //List of questions of a certain topic
    ArrayList<String> topicNames; //List of the names of the topics
    ArrayList<Topic> topicList; //List of the Topic objects
    private int correct = 0; //Number of questions correct in the quiz
    private int incorrect = 0; //Number of questions incorrect in the quiz

    /**
     * Default no argument constructor for the Quiz class
     */
    Quiz() {
        //Initialize the Quiz fields
        questionNum = 0;
        totalQuestionList = new ArrayList<Question>();
        questionList = new ArrayList<Question>();
        topicNames = new ArrayList<String>();
        topicList = new ArrayList<Topic>();
    }

    /**
     * Method that adds a question to the total question list
     * used for exporting
     * @param question the question to be added
     */
    public void addToTotalQuestionList(Question question) {
        totalQuestionList.add(question);
    }

    /**
     * Method that adds a question of a specific topic
     * to the questionList
     * @param question the question to be added
     */
    public void addQuestion(Question question) {
        //Check if the question is null
        if (question == null)
            return;
    //Add to the topic list
        if (!topicNames.contains(question.topic)) {
            topicNames.add(question.topic);
            topicList.add(new Topic(question.topic));
            topicList.get(topicList.size() - 1).addQuestion(question);
            questionNum++;
        } else {
            for (Topic topic : topicList) {
                if (topic.getName().equals(question.topic)) {
                    topic.addQuestion(question);
                    questionNum++;
                    break;
                }
            }
        }
    }

    /**
     * Method that adds all of the questions to the questionList
     * @param topicName the topic of all the questions
     * @param numQuestions the number of questions
     */
    public void getQuestions(String topicName, int numQuestions) {
        ArrayList<Question> temp = new ArrayList<Question>();
        if (topicNames.contains(topicName)) {
            for (Topic topic : topicList) {
                if (topicName.equals(topic.getName())) {
                    temp = topic.getQuestions(numQuestions);
                    questionList.addAll(temp);
                }
            }
        } else {

        }
    }

    /**
     * Method that randomizes the question list so that questions appear in a random order
     * in the quiz
     */
    public void randomizeQuestionList() {
        ArrayList<Question> duplicate = new ArrayList<Question>();
        for (int i = 0; i < questionList.size(); i++) {
            duplicate.add(questionList.get(i));
        }
        int remainingQuestions = questionList.size();

        for (int i = 0; i < duplicate.size(); i++) {
            questionList.remove(0);
        }
        Random rand = new Random();
        // Get some questions
        int length = duplicate.size();
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(remainingQuestions);
            questionList.add(duplicate.get(index));
            duplicate.remove(index);
            remainingQuestions--;
        }
    }

    /**
     *  Public Getter method for getCorrect
     * @return number of correct answers
     */
    public int getCorrect() {
        return correct;
    }

    /**
     *  Public Getter method for getIncorrect
     * @return number of incorrect answers
     */
    public int getIncorrect() {
        return incorrect;
    }
    /**
     *  Calculates the percentage of correct answers
     * @return percentage of correct answers
     */
    public double getPercentage() {
        return ((double) correct / (((double) incorrect + (double) correct)) * 100);
    }

    /**
     * Public setter method that sets the number of correct answers
     * @param correct correct answers
     */
    public void setCorrect(int correct) {
        this.correct = correct;
    }

    /**
     * Public setter method that sets the number of incorrect answers
     * @param incorrect incorrect answers
     */
    public void setIncorrect(int incorrect) {
        this.incorrect = incorrect;
    }

    /**
     * Public getter method for getTotalQuestionList
     * @return the total list of questions
     */
    public ArrayList<Question> getTotalQuestionList() {
        return totalQuestionList;
    }
}
