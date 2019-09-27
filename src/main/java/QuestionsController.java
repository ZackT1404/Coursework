import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QuestionsController {

    public static void insertQuestions(int id, String question) {

        try {

            PreparedStatement ps = Main.db.prepareStatement(
                    "INSERT INTO Questions (QuestionID,Question) VALUES (?, ?)");

            ps.setInt(1, id);
            ps.setString(2, question);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }


    public static void listQuestions() {

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT QuestionId, Question FROM Questions");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int id = results.getInt(1);
                String Question = results.getString(2);
                System.out.print("Question: " + id + ",  ");
                System.out.println("Name: " + Question + "  ");
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }
}
