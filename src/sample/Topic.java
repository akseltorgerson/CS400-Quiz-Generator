
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Topic
// Files:           (Quiz,JsonExport,Main,JsonParser,Question,Topic)
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

public class Topic implements  TopicInterface{
    private String name; //the name of the topic
    private ArrayList<Question> questions; //the questions of this topic

    /**
     * Sets the name field to the name of the topic and creates a new Arraylist of questions
     * @param topic name of topic
     */
    public Topic(String topic) {
        name = topic;
        questions = new ArrayList<Question>();
    }

    public String getName() {
        return name;
    }

    /**
     * Adds a question to this current topic
     * @param question the question
     */
    public void addQuestion(Question question) {
        questions.add(question);
    }

    /**
     * Gets a number of questions to add to the quiz, if the passed in number
     * is less than the number of questions in the topic, then they are chosen randomly
     * @param numQuestions the number of questions
     * @return list of chosen questions
     */
    @Override
    public ArrayList<Question> getQuestions(int numQuestions) {
        ArrayList<Question> duplicate = new ArrayList<Question>(); //a duplicate list so that we can ensure no duplicates
        //are added
        // Create the duplicate
        for (int i = 0; i < questions.size(); i++) {
            duplicate.add(questions.get(i));
        }

        if (numQuestions < questions.size()) {
            ArrayList<Question> returnList = new ArrayList<Question>();

            int remainingQuestions = questions.size();
            Random rand = new Random();
            // Randomly get a question that hasn't been gotten yet
            for (int i = 0; i < numQuestions; i++) {
                int index = rand.nextInt(remainingQuestions);
                returnList.add(duplicate.get(index));
                duplicate.remove(index);
                remainingQuestions--;
            }
            return returnList;
        }
        // They want all of the topic questions
        else {
            return duplicate;
        }
    }
}
