package Controllers;
import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

@Path ("Quiz/")

public class QuizController {


    public static void Quiz() {

        try {


            PreparedStatement ps = Main.db.prepareStatement("SELECT quizid, quiztitle, quizdescription FROM Quiz");

            ResultSet results = ps.executeQuery();

            while (results.next()) {
                int quizid = results.getInt(1);
                String quiztitle = results.getString(2);
                String quizdescription = results.getString(3);
            }
        } catch (Exception exception) {

            System.out.println("Database Error:" + exception.getMessage());

        }

    }

    @GET
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
    }
}

