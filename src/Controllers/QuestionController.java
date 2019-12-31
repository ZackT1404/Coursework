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

@Path ("Question/")

public class QuestionController {

    public static void ListQuestion() {

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT questionid, question, quizid FROM Questions");

            ResultSet results = ps.executeQuery();

            while (results.next()) {

                int questionid = results.getInt(1);

                String question = results.getString(2);

                int quizid = results.getInt(3);

            }


        } catch (Exception exception) {

            System.out.println("Database Error:" + exception.getMessage());

        }

    }


    @GET

    @Path("get/{quizid}")

    @Produces(MediaType.APPLICATION_JSON)

    public String getThing(@PathParam("quizid") String quizid) {

        try {

            if (quizid == null) {

                throw new Exception("QuizID is missing in the HTTP request's URL.");

            }

            System.out.println("Question/get/" + quizid);

            JSONArray list = new JSONArray();

            PreparedStatement ps = Main.db.prepareStatement("SELECT questionid, question, quizid WHERE quizid = ?");

            ps.setString(1, quizid);

            ResultSet results = ps.executeQuery();


            while (results.next()) {

                JSONObject item = new JSONObject();

                item.put("questionid", results.getInt(1));
                item.put("question", results.getString(2));
                item.put("quizid", results.getInt(3));
                list.add(item);

            }

            return list.toString();

        } catch (Exception exception) {

            System.out.println("Database error: " + exception.getMessage());

            return "{\"error\": \"Unable to get item, please see server console for more info.\"}";

        }

    }
}
