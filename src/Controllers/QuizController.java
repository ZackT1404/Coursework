package Controllers;
import Server.Main;
/*import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;*/
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

//@Path ("Quiz/")

public class    QuizController {


    public static void QuizList() {
        System.out.println("Quiz/list");
        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT quizid, quiztitle, quizdescription FROM Quiz");

            ResultSet results = ps.executeQuery();

            while (results.next()) {
                int quizid = results.getInt(1);
                String quiztitle = results.getString(2);
                String quizdescription = results.getString(3);
                System.out.println(quizid);
                System.out.println(quiztitle);
                System.out.println(quizdescription);
            }
        } catch (Exception exception) {

            System.out.println("Database Error:" + exception.getMessage());

        }
    }

        public static void QuizInsert(int quizid, String quiztitle, String quizdescription) {
            System.out.println("Quiz/Insert");
            try {

                PreparedStatement ps = Main.db.prepareStatement("INSERT INTO quiz (quizid, quiztitle, quizdescription) VALUES(?,?,?)");
                ps.setInt(1, quizid);
                ps.setString(2, quiztitle);
                ps.setString(3, quizdescription);
                ps.execute();

                System.out.println("Inserted successfully");


            } catch (Exception exception) {

                System.out.println("Database Error:" + exception.getMessage());

            }

    }

        public static void QuizUpdate(int quizid, String quiztitle, String quizdescrption) {
        try {

            PreparedStatement ps = Main.db.prepareStatement("UPDATE Quiz SET quizid=?, quiztitle=?, quizdescription=? WHERE quizid=?");
            ps.setInt(1, quizid);
            ps.setString(2, quiztitle);
            ps.setString(3, quizdescrption);
        }
        }

    // Phase 2 code
    /*@GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)

    public String listQuizzes() {

        System.out.println("Quiz/list");

        JSONArray list = new JSONArray();

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT quizid, quiztitle, quizdescription FROM Quiz");

            ResultSet results = ps.executeQuery();

            while (results.next()) {
               JSONObject item = new JSONObject();
                item.put("quizid", results.getInt(1));
                item.put("quiztitle", results.getString(2));
                item.put("quizdescription", results.getString(3));
            }
            list.add(item);

            return list.toString();
        } catch (Exception exception) {

            System.out.println("Database error: " + exception.getMessage());

            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";
        }
    }*/
}

