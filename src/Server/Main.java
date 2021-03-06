/* import org.sqlite.SQLiteConfig;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static Connection db = null;
    public static void main(String[] args) {
        openDatabase("Quiz.db");
        QuizController.listQuiz();
        QuizController.insertQuiz(1,"A", "B");
        QuestionsController.listQuestions();
        QuestionsController.insertQuestions(1, "When was the original Xbox released?", "1", "2","3", "4", 1);
        closeDatabase();

    }


    private static void openDatabase(String dbFile)
    {

        try  {

            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            db = DriverManager.getConnection("jdbc:sqlite:resources/" + dbFile, config.toProperties());
            System.out.println("Database connection successfully established.");

        } catch (Exception exception) {

            System.out.println("Database connection error: " + exception.getMessage());
        }

    }
    private static void closeDatabase()
    {
        try {

            db.close();
            System.out.println("Disconnected from database.");

        } catch (Exception exception) {

            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }

}
*/

//New server code shown below- will not run.

package Server;
/*import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;*/
import Controllers.AnswerController;
import Controllers.QuizController;
import org.sqlite.SQLiteConfig;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
public class Main {

    public static Connection db = null;

/*  public static void main(String[] args) {
     openDatabase("CourseWork.db");
     code to get data from here
   closeDatabase();
     }*/

   /* public static void main(String[] args) {

        openDatabase("Quiz.db");

        ResourceConfig config = new ResourceConfig();
        config.packages("Controllers");
        config.register(MultiPartFeature.class);
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        Server server = new Server(8081);
        ServletContextHandler context = new ServletContextHandler(server, "/");
        context.addServlet(servlet, "/*");

        try {
            server.start();
            System.out.println("Server successfully started.");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
        openDatabase("Quiz.db");
        //QuizController.QuizList();
        AnswerController.Answer();
        closeDatabase();
    }

    private static void openDatabase(String dbFile){
        try{
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            db = DriverManager.getConnection("jdbc:sqlite:resources/" + dbFile, config.toProperties());
            System.out.println("\nDatabase connection established successfully.");
        } catch (Exception exception) {
            System.out.println("\nConnection Error:\n" + exception.getMessage());
        }
    }


    private static void closeDatabase(){
        try{
            db.close();
            System.out.println("\nDatabase connection established successfully.");

        } catch (Exception exception) {
            System.out.println("\nConnection Error:\n" + exception.getMessage());
        }
    }

}
