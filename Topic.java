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
