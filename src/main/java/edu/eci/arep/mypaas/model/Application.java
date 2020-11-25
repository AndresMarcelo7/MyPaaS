package edu.eci.arep.mypaas.model;

import edu.eci.arep.mypaas.utils.CopyFiles;
import edu.eci.arep.mypaas.utils.UpdateFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Application {
    private String AppName;
    private String gitRepo;
    private String mainClass;
    private String url;
    private String port;
    private boolean flag = false;
    private String serverUrl;

    public Application(String appName, String gitRepo, String mainClass,String port,String serverUrl) {
        AppName = appName;
        this.gitRepo = gitRepo;
        this.mainClass = mainClass;
        this.port = port;
        this.serverUrl = serverUrl;
    }

    public void deployApp(String port) throws IOException, InterruptedException {
        setPort(port);
        setUrl(getServerUrl()+":"+getPort()+"/");
        UpdateFile.addServices(getGitRepo(),getMainClass(),getAppName(),getPort());
        createDirectory();
        execDockerCompose();
    }

    private void execDockerCompose() throws IOException {
            boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

            /*if (isWindows) {
                System.out.println("no uses windows crack");
                Process process = Runtime.getRuntime().exec("docker-compose up -d");
                printResults(process);

            } else {
                Runtime.getRuntime().exec("sudo docker-compose up -d");
            }*/
            Process process =Runtime.getRuntime().exec("sudo docker-compose up -d");
            printResults(process);

    }

    private void createDirectory() throws IOException, InterruptedException {
        Path path = Paths.get("Apps/"+this.AppName);
        try {
            Files.createDirectories(path);
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        CopyFiles.copy("src/main/resources/Dockerfile","Apps/"+this.AppName+"/Dockerfile");

    }

    public static void printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }



    public String getAppName() {
        return AppName;
    }

    public void setAppName(String appName) {
        AppName = appName;
    }

    public String getGitRepo() {
        return gitRepo;
    }

    public void setGitRepo(String gitRepo) {
        this.gitRepo = gitRepo;
    }

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    @Override
    public String toString() {
        return "Application{" +
                "AppName='" + AppName + '\'' +
                ", gitRepo='" + gitRepo + '\'' +
                ", mainClass='" + mainClass + '\'' +
                ", url='" + url + '\'' +
                ", port='" + port + '\'' +
                ", flag=" + flag +
                ", serverUrl='" + serverUrl + '\'' +
                '}';
    }
}
