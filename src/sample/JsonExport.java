//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           JsonExport
// Files:           (Question,Main,JsonParser,Quiz,Topic)
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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;
import org.json.simple.parser.JSONParser;

/**
 * Class that saves the current questions in the quiz to a JSON file
 * @author  Parker Schroeder, Dylan Clark, Zachary Chanak, Jacob Mayl
 */
public class JsonExport {
    /**
     * Default constructor fo the JsonExport class
     */
    public JsonExport() {

    }

    /**
     * Method that gets all of the current quiz information and creates a JSON string
     * that can be added to a file
     * @param quiz the current quiz
     * @return String prettyJson, the string that contains all of the questions in JSON format
     */
    public static String exportToJson(Quiz quiz) {
        ArrayList<Question> questionList = quiz.getTotalQuestionList();
        String isCorrect;
        JSONObject finalQuestionObject = new JSONObject(); //The final object that contains all of the question details
        JSONArray questionArray = new JSONArray(); //The JSON array of all of the questions
        JSONObject questionDetails = new JSONObject(); //Contains all of the fields of the question
        for (int i = 0; i < questionList.size(); i++) {
            //Get all of the fields from the current quiz/questionList
            String metadata = questionList.get(i).metadata;
            String questionText = questionList.get(i).questionText;
            String topic = questionList.get(i).topic;
            String image = questionList.get(i).imageName;
            String correctAnswer = questionList.get(i).correctAnswer;
            ArrayList<String> answerList = questionList.get(i).allAnswers;
            questionDetails.put("image", image);
            questionDetails.put("topic", topic);
            questionDetails.put("questionText", questionText);
            questionDetails.put("meta-data", metadata);
            JSONArray answerChoicesArray = new JSONArray();
            //Now add the choices to the questionDetails object
            for (int j = 0; j < answerList.size(); j++) {

                //Check if the answer is correct and assign the correct json tag
                if (answerList.get(j).equals(correctAnswer)) {
                    isCorrect = "T";
                } else {
                    isCorrect = "F";
                }
                //Add to the answer choice object
                JSONObject answerChoiceObject = new JSONObject();
                answerChoiceObject.put("isCorrect", isCorrect);
                answerChoiceObject.put("choice", answerList.get(j));
                //Make sure it isn't a duplicated
                if (!answerChoicesArray.contains(answerChoiceObject)) {
                    answerChoicesArray.add(answerChoiceObject);
                }


                //Add the array of answers to the details object
                questionDetails.put("choiceArray", answerChoicesArray);
                //Add the details to the array
                questionArray.add(questionDetails);
                //Add the question to the final Object
                finalQuestionObject.put("questionArray", questionArray);
            }

        }
        //Use gson to make the json file more readable
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(finalQuestionObject);
        return prettyJson;

    }
}

