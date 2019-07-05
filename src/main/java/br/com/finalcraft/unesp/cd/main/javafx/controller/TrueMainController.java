package br.com.finalcraft.unesp.cd.main.javafx.controller;

import br.com.finalcraft.unesp.cd.main.ProgramModule;
import br.com.finalcraft.unesp.cd.main.TrueMain;
import br.com.finalcraft.unesp.cd.common.consoleview.ConsoleView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class TrueMainController {

    public static TrueMainController instance;

    @FXML
    void initialize() {
        instance = this;
        if (!TrueMain.regularJar){
            remoteAssist.setSelected(false);
            remoteAssist.setDisable(true);
        }
        consoleBorderPane.setCenter(ConsoleView.createConsole());
    }

    @FXML
    private JFXButton btnClient;

    @FXML
    private JFXButton btnServer;

    @FXML
    private JFXButton btnSlave;

    @FXML
    private JFXToggleButton remoteAssist;

    @FXML
    private BorderPane consoleBorderPane;


    private static void runtimeExecute(String command){
        try {
            Runtime runtime = Runtime.getRuntime();
            System.out.println("Executing command");
            System.out.println(command);
            runtime.exec(command);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static boolean isRemoteAssistPossible(){
        return TrueMain.regularJar && instance.remoteAssist.isSelected();
    }

    @FXML
    void onClientStart(ActionEvent event) {
        if (isRemoteAssistPossible()){
            btnClient.setDisable(true);
            runtimeExecute("java -jar \"" + TrueMain.classPath + "\" Client");
        }else {
            TrueMain.changeModule(ProgramModule.CLIENT);
        }
    }

    @FXML
    void onServerStart(ActionEvent event) {
        if (isRemoteAssistPossible()){
            btnServer.setDisable(true);
            runtimeExecute("java -jar \"" + TrueMain.classPath + "\" Server");
        }else {
            TrueMain.changeModule(ProgramModule.SERVER);
        }
    }

    @FXML
    void onSlaveStart(ActionEvent event) {
        if (isRemoteAssistPossible()){
            btnSlave.setDisable(true);
            runtimeExecute("java -jar \"" + TrueMain.classPath + "\" Slave");
        }else {
            TrueMain.changeModule(ProgramModule.SLAVE);
        }
    }

    @FXML
    void onRemoteAssistClick(ActionEvent event) {
        btnClient.setDisable(false);
        btnServer.setDisable(false);
        btnSlave.setDisable(false);

    }

    @FXML
    void onClose() {
        TrueMain.shutDown();
    }
}
