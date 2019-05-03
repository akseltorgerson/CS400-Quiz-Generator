//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           JsonParser
// Files:           (Quiz,JsonExport,Main,Question,Quiz,Topic)
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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * JsonParser class that parses a json file to read questions and add
 * them to the quiz.
 *
 * @author Parker Schroeder, Dylan Clark, Zachary Chanak, Jacob Mayl
 */
public class JsonParser {
    ArrayList<Question> QuestionList; //Stores the questions added

    /**
     * No argument constructor for the JsonParser
     */
    JsonParser() {
        QuestionList = new ArrayList<Question>();
    }

    /**
     * Method that performs the JSON parsing, creating new questions as they appear
     * in the JSON file and adding them to the quiz
     *
     * @param jsonFileName the name of the JSON file to be parsed
     * @param quiz         the current quiz
     * @throws FileNotFoundException
     * @throws IOException
     * @throws org.json.simple.parser.ParseException
     */
    public void getQuestions(String jsonFileName, Quiz quiz)
        throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {
        String correctAnswer = null;
        String answer = null;
        Object obj =
            new JSONParser().parse(new FileReader(jsonFileName)); //Create the jsonParser
        JSONObject jo = (JSONObject) obj; //Create the jsonObject
        JSONArray jsonQuestions = (JSONArray) jo.get("questionArray");
        // Loop through the questions
        for (int i = 0; i < jsonQuestions.size(); i++) {
            ArrayList<String> choices =
                new ArrayList<String>(); //Arraylist that stores the answer choices
            JSONObject jsonQuestion = (JSONObject) jsonQuestions.get(i);
            //Get the different question fields from the jsonFile
            String metadata = (String) jsonQuestion.get("meta-data");
            String tempquestionText = (String) jsonQuestion.get("questionText");
            String questionText = tempquestionText.replaceAll("\\$", "");
            String topic = (String) jsonQuestion.get("topic");
            String imageName = (String) jsonQuestion.get("image");
            JSONArray jsonChoices = (JSONArray) jsonQuestion.get("choiceArray");
            // Loop through the choices
            for (int j = 0; j < jsonChoices.size(); j++) {
                JSONObject jsonChoice = (JSONObject) jsonChoices.get(j);
                String isCorrect = (String) jsonChoice.get("isCorrect");
                //Check if it's the correct answer or not
                if (isCorrect.equals("T")) {
                    String tempAnswer = (String) jsonChoice.get("choice");
                    answer = tempAnswer.replaceAll("\\$", "");
                    String tempCorrect = (String) jsonChoice.get("choice");
                    correctAnswer = tempCorrect.replaceAll("\\$", "");

                } else {
                    String tempAnswer = (String) jsonChoice.get("choice");
                    answer = tempAnswer.replaceAll("\\$", "");
                }
                choices.add(answer);
            }
            //Create the new question
            Question question =
                new Question(metadata, questionText, topic, imageName, correctAnswer,
                    choices);
            QuestionList.add(question); // For testing purposes
            quiz.addQuestion(question);
            quiz.addToTotalQuestionList(question);
        }
    }

    /**
     * Public getter for the questionList used for testing
     *
     * @return the current questionList
     */
    public ArrayList<Question> getQuestionList() {
        return this.QuestionList;
    }

}
