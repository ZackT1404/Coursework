package Controllers;
import Server.Main;
//import org.glassfish.jersey.media.multipart.FormDataParam;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

//@Path ("Answer/")

public class AnswerController {

    public static void Answer() {

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT answerid, Answer, correct FROM Answers");

            ResultSet results = ps.executeQuery();

            while (results.next()) {

                int answerid = results.getInt(1);

                String Answer = results.getString(2);

                Boolean correct = results.getBoolean(3);

            }

        } catch (Exception exception) {

            System.out.println("Database Error:" + exception.getMessage());

        }

    }


    /*@GET

    @Path("get/{questionid}")

    @Produces(MediaType.APPLICATION_JSON)

    public String getThing(@PathParam("questionid") String questionid) {

        try {

            if (questionid == null) {

                throw new Exception("questionid is missing in the HTTP request's URL.");

            }

            System.out.println("Question/get/" + QuestionID);


            JSONArray list = new JSONArray();

            PreparedStatement ps = Main.db.prepareStatement("SELECT answerid, Answer, Correct, questionid FROM Answers WHERE questionid = ?");

            ps.setString(1, questionid);

            ResultSet results = ps.executeQuery();


            while (results.next()) {

                JSONObject item = new JSONObject();

                item.put("answerid", answerid);

                item.put("Answer", results.getString(1));

                item.put("Correct", results.getBoolean(2));

                item.put("questionid", results.getInt(3));

                list.add(item);

            }

            return list.toString();

        } catch (Exception exception) {

            System.out.println("Database error: " + exception.getMessage());

            return "{\"error\": \"Unable to get item, please see server console for more info.\"}";

        }

    }*/
}
