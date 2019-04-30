package application;

import java.util.ArrayList;
import java.util.Random;

public class Topic {
    private String name;
    private ArrayList<Question> questions;

    public Topic(String topic) {
        name = topic;
        questions = new ArrayList<Question>();
    }

    public String getName() {
      return name;
    }
    public void addQuestion(Question question) {
        questions.add(question);
    }
    private ArrayList<Question> getQuestions(int numQuestions) {
        if(numQuestions<questions.size()) {
            ArrayList<Question> duplicate = new ArrayList<Question>();
            ArrayList<Question> returnList = new ArrayList<Question>();
            //Create the duplicate
            for (int i=0; i < questions.size(); i ++) {
                duplicate.add(questions.get(i));
            }
            int remainingQuestions = numQuestions;
            Random rand = new Random();
            //Get some questions
            for (int i=0; i <numQuestions; i++) {
                int index = rand.nextInt(remainingQuestions);
                returnList.add(duplicate.get(index));
                duplicate.remove(index);
                remainingQuestions--;
                
            }
            return returnList;
        }
        //They want all of the topic questions
        else {
            return questions;
        }
    }


}
