package br.com.finalcraft.unesp.cd.main.javafx.view;

import br.com.finalcraft.unesp.cd.main.ProgramModule;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

public class MyFXMLs {

    public static Parent main;

    public static Parent server;

    public static Parent client;
    public static Parent client_pane1;
    public static Parent client_pane2;
    public static Parent client_pane3;

    public static Parent slave;

    public static Parent loadModule(ProgramModule module){
        System.out.println("Loading module [" + module + "] frome files...");
        switch (module){
            case MAIN:
                return (main = loadFXML("/assets/truemain.fxml"));
            case SERVER:
                return (server = loadFXML("/assets/server.fxml"));
            case CLIENT:
                client_pane1 = loadFXML("/assets/client/panes/pane1.fxml");
                client_pane2 = loadFXML("/assets/client/panes/pane2.fxml");
                client_pane3 = loadFXML("/assets/client/panes/pane3.fxml");
                return (client = loadFXML("/assets/client.fxml"));
            case SLAVE:
                return (slave = loadFXML("/assets/slave.fxml"));
        }
        return null;
    }

    public static URL getConsoleCSS(){
        return MyFXMLs.class.getResource("/assets/main/console-style.css");
    }

    private static Parent loadFXML(String fxmlFileAssetPath){
        try {
            return FXMLLoader.load(MyFXMLs.class.getResource(fxmlFileAssetPath));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return null;
    }

}
