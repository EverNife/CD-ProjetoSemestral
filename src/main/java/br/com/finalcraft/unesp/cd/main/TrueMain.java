package br.com.finalcraft.unesp.cd.main;

import br.com.finalcraft.unesp.cd.main.javafx.view.MyFXMLs;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class TrueMain extends Application {

    public static Parent the_current_root_parent;
    public static Stage primaryStage;

    public static void shutDown(){
        primaryStage.close();
        System.exit(0);
    }

    @Override
    public void start(Stage thePrimaryStage) throws Exception{
        primaryStage = thePrimaryStage;
        TrueMain.primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        showScene();
    }

    public static void changeModule(ProgramModule newModule){
        prepareModule(newModule);
        showScene();
    }

    private static double x;
    private static double y;

    private static void showScene(){

        if (the_current_root_parent == null){
            System.out.println("root is null...");
            return;
        }
        Scene scene = new Scene(the_current_root_parent);
        scene.setFill(Color.TRANSPARENT);

        the_current_root_parent.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        the_current_root_parent.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });

        primaryStage.setTitle(ProgramModule.getTitle());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static boolean regularJar = false;
    public static String classPath;


    public static void main(String[] args) throws IOException {
        //Verifica se o programa está sendo executado diretamente pelo arquivo jar
        //ou se está sendo executado por uma IDE
        classPath = System.getProperty("java.class.path");
        System.out.println("ClassPath: " + classPath);
        regularJar = classPath.split(";").length == 1;

        //Se estiver vazio, logo, executar programa principal! (truemain.fxml)
        String argOne = args.length > 0 ? args[0].toLowerCase() : "default";

        switch (argOne){
            case "server":
                prepareModule(ProgramModule.SERVER);
                break;
            case "client":
                prepareModule(ProgramModule.CLIENT);
                break;
            case "slave":
                prepareModule(ProgramModule.SLAVE);
                break;
            default:
                prepareModule(ProgramModule.MAIN);
                break;
        }
        launch(args);

    }

    private static void prepareModule(ProgramModule module){
        System.out.println("Preparing Module: " + module);
        MyFXMLs.loadModule(module);
        switch (module){
            case MAIN:
                the_current_root_parent = MyFXMLs.main;
                break;
            case SERVER:
                the_current_root_parent = MyFXMLs.server;
                break;
            case CLIENT:
                the_current_root_parent = MyFXMLs.client;
                break;
            case SLAVE:
                the_current_root_parent = MyFXMLs.slave;
                break;
        }
    }
}