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

@Path ("Users/")

public class UsersController {


    public static boolean validtoken(String token) {

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT username FROM Users WHERE token = ?");

            ps.setString(1, token);

            ResultSet logoutResults = ps.executeQuery();

            return logoutResults.next();

        } catch (Exception exception) {

            System.out.println("Database error" + exception.getMessage());

            return false;

        }

    }


    @POST

    @Path("incrementscore")

    @Consumes(MediaType.MULTIPART_FORM_DATA)

    @Produces(MediaType.APPLICATION_JSON)

    public String incrementscore(@FormDataParam("username") String username, @FormDataParam("incrementscore") Integer incrementscore, @CookieParam("token") String token) {

        try {

            if (username == null || incrementscore == null) {

                throw new Exception("One or more form data parameters are missing in the HTTP request.");

            }

            System.out.println("Users/incrementscore username=" + username);


            PreparedStatement ps1 = Main.db.prepareStatement("UPDATE Users SET score = score + ? WHERE username = ?");

            ps1.setInt(1, incrementscore);

            ps1.setString(2, username);

            ps1.execute();


            PreparedStatement ps2 = Main.db.prepareStatement("SELECT score FROM Users WHERE username = ?");

            ps2.setString(1, username);

            ResultSet results = ps2.executeQuery();

            Integer score = results.getInt(1);


            return "{\"newscore\": \"" + score + "\"}";


        } catch (Exception exception) {

            System.out.println("Database error: " + exception.getMessage());

            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";

        }

    }


    @POST

    @Path("new")

    @Consumes(MediaType.MULTIPART_FORM_DATA)

    @Produces(MediaType.APPLICATION_JSON)

    public String insertThing(@FormDataParam("username") String username, @FormDataParam("password") String password){

        try {

            if (password == null || username == null) {

                throw new Exception("One or more form data parameters are missing in the HTTP request.");

            }

            System.out.println("thing/new username=" + username);


            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Users (username, password) VALUES (?, ?)");

            ps.setString(1, username);

            ps.setString(2, password);

            ps.execute();

            return "{\"status\": \"OK\"}";


        } catch (Exception exception) {

            System.out.println("Database error: " + exception.getMessage());

            return "{\"error\": \"Unable to create new item, please see server console for more info.\"}";

        }

    }

    @POST

    @Path("logout")

    @Consumes(MediaType.MULTIPART_FORM_DATA)

    @Produces(MediaType.APPLICATION_JSON)

    public String logoutUser(@CookieParam("token") String token) {


        try {


            System.out.println("user/logout");


            PreparedStatement ps1 = Main.db.prepareStatement("SELECT UserID, token FROM Users WHERE token = ?");

            ps1.setString(1, token);

            ResultSet logoutResults = ps1.executeQuery();

            if (logoutResults.next()) {

                int userid = logoutResults.getInt(1);

                PreparedStatement ps2 = Main.db.prepareStatement("UPDATE Users SET token = NULL WHERE userid = ?");

                ps2.setInt(1, userid);

                ps2.executeUpdate();


                return "{\"status\": \"OK\"}";

            } else {


                return "{\"error\": \"Invalid token!\"}";


            }


        } catch (Exception exception){

            System.out.println("Database error during /user/logout: " + exception.getMessage());

            return "{\"error\": \"Server side error!\"}";

        }


    }


    public static boolean validToken(String token) {

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT userid, username, password, token FROM Users WHERE token = ?");

            ps.setString(1, token);

            ResultSet logoutResults = ps.executeQuery();

            return logoutResults.next();

        } catch (Exception exception) {

            System.out.println("Database error during /user/logout: " + exception.getMessage());

            return false;

        }

    }


    @POST

    @Path("login")

    @Consumes(MediaType.MULTIPART_FORM_DATA)

    @Produces(MediaType.APPLICATION_JSON)

    public String loginUser(@FormDataParam("username") String username, @FormDataParam("password") String password) {


        try {


            if (username == null || password == null) {

                throw new Exception("Something is missing in the HTTP request.");

            }

            PreparedStatement ps1 = Main.db.prepareStatement("SELECT password FROM Users WHERE username = ?");

            ps1.setString(1, username);

            ResultSet loginResults = ps1.executeQuery();

            if (loginResults.next()) {


                String correctPassword = loginResults.getString(1);


                if (password.equals(correctPassword)) {


                    String token = UUID.randomUUID().toString();


                    PreparedStatement ps2 = Main.db.prepareStatement("UPDATE Users SET token = ? WHERE username = ?");

                    ps2.setString(1, token);

                    ps2.setString(2, username);

                    ps2.executeUpdate();


                    JSONObject response = new JSONObject();

                    response.put("username", username);

                    response.put("token", token);

                    return response.toString();

                } else {

                    return "{\"error\": \"Incorrect password!\"}";

                }

            } else {

                return "{\"error\": \"Unknown user!\"}";

            }

        }catch (Exception exception){

            System.out.println("Database error during /user/login: " + exception.getMessage());

            return "{\"error\": \"Server side error!\"}";

        }

    }

}
