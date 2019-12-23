import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class QuestionsController {

    public static void insertQuestions(Integer questionid, String question, String correctanswer, String wrongaswerone, String wronganswertwo, String wronganswerthree, Integer quizid) {

        try {

            PreparedStatement ps = Main.db.prepareStatement(
                    "INSERT INTO questions (questionid,question,correctanswer,wronganswerone,wronganswertwo,wronganswerthree,quizid) VALUES (?, ?)");

            ps.setInt(1, questionid);
            ps.setString(2, question);
            ps.setString(3, correctanswer);
            ps.setString(4, wrongaswerone);
            ps.setString(5, wronganswertwo);
            ps.setString(6, wronganswerthree);
            ps.setInt(7, quizid);


            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }


    public static int listQuestions() {

        Scanner QuestionsController = new Scanner(System.in);

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT questionid, question, correctanswer, wronganswerone, wronganswertwo, wronganswerthree, quizid FROM questions");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int questionid = results.getInt(1);
                String question = results.getString(2);
                String correctanswer = results.getString(3);
                String wronganswerone = results.getString(4);
                String wronganswertwo = results.getString(5);
                String wronganswerthree = results.getString(6);
                int randomNumber = (int) (Math.random() * 4);
                System.out.println(randomNumber);
                int score = 0;
                System.out.println("Score = " + score);
                System.out.print("Question Number " + questionid + ", ");
                System.out.println("" + question + " ");
                if (randomNumber == 0) {
                    System.out.println("Answer 1: " + correctanswer + ", ");
                    System.out.println("Answer 2: " + wronganswerone + ", ");
                    System.out.println("Answer 3: " + wronganswertwo + ", ");
                    System.out.println("Answer 4: " + wronganswerthree + ", ");
                    System.out.println("Hit 1 for answer 1, Hit 2 for answer 2, Hit 3 for answer 3 and Hit 4 for answer 4!");
                    int UserAnswer = QuestionsController.nextInt();
                    if (UserAnswer == 1) {
                        System.out.println("Correct!");
                        score = score + 1;
                    } else {
                        System.out.println("Sorry that's incorrect!");
                    }

                } else if (randomNumber == 1) {
                    System.out.println("Answer 1: " + wronganswerone + ", ");
                    System.out.println("Answer 2: " + correctanswer + ", ");
                    System.out.println("Answer 3: " + wronganswertwo + ", ");
                    System.out.println("Answer 4: " + wronganswerthree + ", ");
                    System.out.println("Hit 1 for answer 1, Hit 2 for answer 2, Hit 3 for answer 3 and Hit 4 for answer 4!");
                    int UserAnswer = QuestionsController.nextInt();
                    if (UserAnswer == 2) {
                        System.out.println("Correct!");
                        score = score + 1;
                    } else {
                        System.out.println("Sorry that's incorrect!");
                    }
                } else if (randomNumber == 2) {
                    System.out.println("Answer 1: " + wronganswerone + ", ");
                    System.out.println("Answer 2: " + wronganswertwo + ", ");
                    System.out.println("Answer 3: " + correctanswer + ", ");
                    System.out.println("Answer 4: " + wronganswerthree + ", ");
                    System.out.println("Hit 1 for answer 1, Hit 2 for answer 2, Hit 3 for answer 3 and Hit 4 for answer 4!");
                    int UserAnswer = QuestionsController.nextInt();
                    if (UserAnswer == 1) {
                        System.out.println("Correct!");
                        score = score + 1;
                    } else {
                        System.out.println("Sorry that's incorrect!");
                    }
                } else {
                    System.out.println("Answer 4: " + wronganswerone + ", ");
                    System.out.println("Answer 2: " + wronganswertwo + ", ");
                    System.out.println("Answer 3: " + wronganswerthree + ", ");
                    System.out.println("Answer 4: " + correctanswer + ", ");
                    System.out.println("Hit 1 for answer 1, Hit 2 for answer 2, Hit 3 for answer 3 and Hit 4 for answer 4!");
                    int UserAnswer = QuestionsController.nextInt();
                    if (UserAnswer == 4) {
                        System.out.println("Correct!");
                        score = score + 1;
                    } else {
                        System.out.println("Sorry that's incorrect!");
                    }
                }

            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    return  0;
    }
}
