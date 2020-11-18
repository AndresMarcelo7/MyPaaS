package edu.eci.arep.mypaas.utils;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class UpdateFile {

    public static final String FILE_PATH = "docker-compose.yml";

    public static void addServices(String gitRepo, String mainClass, String appName, String port) {
        System.out.println("GitRepo: " + gitRepo);
        System.out.println("MainClass: " + mainClass);
        String userRepo = gitRepo.replaceAll("https://github.com/", "");
        StringBuilder info = new StringBuilder("\n  "+appName.toLowerCase()+":\n");
        info.append(
                "    build:\n" +
                "      context: ./Apps/"+appName + "\n" +
                "      args:"+ "\n" +
                "        - GIT_REPO=" + gitRepo + "\n" +
                "        - GIT_REPO_LAST_PATH=/" + userRepo.split("/")[1] + "\n" +
                "        - MAIN_CLASS=" + mainClass + "\n" +
                "      dockerfile: Dockerfile\n" +
                "    ports:\n" +
                "      - \"" + port+ ":6000\"\n" +
                "      ");

        System.out.println(info.toString());
        add(info.toString());

    }

    public static void add(String info) {
        try {
            Files.write(Paths.get(FILE_PATH), info.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("We can't find anything");
        }
    }
}
