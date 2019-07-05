package br.com.finalcraft.unesp.cd.client.javafx.controller;

import br.com.finalcraft.unesp.cd.client.tcp.ClientSideTCP;
import br.com.finalcraft.unesp.cd.main.TrueMain;
import br.com.finalcraft.unesp.cd.common.consoleview.ConsoleView;
import br.com.finalcraft.unesp.cd.main.javafx.view.MyFXMLs;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class ClientController {

    public static ClientController instance;

    @FXML
    void initialize() {
        instance = this;
        ConsoleView.initialize();
        ClientSideTCP.initialize();
        onFirstProgram();
    }

    @FXML
    private BorderPane borderPane;

    @FXML
    void onClose() {
        TrueMain.shutDown();
    }

    @FXML
    void onFirstProgram() {
        borderPane.setCenter(MyFXMLs.client_pane1);
    }

    @FXML
    void onSecondProgram() {
        borderPane.setCenter(MyFXMLs.client_pane2);
    }

    @FXML
    void onThirdProgram() {
        borderPane.setCenter(MyFXMLs.client_pane3);
    }
}
