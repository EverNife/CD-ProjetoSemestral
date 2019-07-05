package br.com.finalcraft.unesp.cd.server.javafx.controller;

import br.com.finalcraft.unesp.cd.main.TrueMain;
import br.com.finalcraft.unesp.cd.common.consoleview.ConsoleView;
import br.com.finalcraft.unesp.cd.server.handlers.rmi.ServerSideRMI;
import br.com.finalcraft.unesp.cd.server.handlers.tcp.ServerSideTCP;
import br.com.finalcraft.unesp.cd.server.handlers.tcp.ServerSocketThread;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.BorderPane;

public class ServerController {

    public static ServerController instance;

    @FXML
    void initialize() {
        instance = this;
        consoleBorderPane.setCenter(ConsoleView.createConsole());

        ServerSideTCP.initialize();
        ServerSideRMI.initialize();

    }

    @FXML
    private BorderPane consoleBorderPane;

    @FXML
    private TableColumn<ServerSocketThread, String> mainColumn;

    @FXML
    void onClose() {
        TrueMain.shutDown();
    }

}
