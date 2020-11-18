package edu.eci.arep.mypaas;

import com.google.gson.Gson;
import edu.eci.arep.mypaas.model.Application;
import edu.eci.arep.mypaas.utils.UpdateFile;

import java.io.IOException;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        PortQueue ports = new PortQueue();
        port(getPort());
        get("/hello", (req, res) -> "Hello Heroku");
        post("/newApp",(req,res)->{
            Gson gson = new Gson();
            Application newApp = gson.fromJson(req.body(),Application.class);
            System.out.println(newApp);
            runConfigurations(newApp,ports);
            return newApp;
        });

    }

    private static void runConfigurations(Application app, PortQueue ports){
        String port = ports.getPorts().poll();
        try {
            app.deployApp(port);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set(i.e on localhost)
    }

}
