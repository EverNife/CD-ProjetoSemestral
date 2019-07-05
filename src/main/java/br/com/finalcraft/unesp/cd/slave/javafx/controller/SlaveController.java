package br.com.finalcraft.unesp.cd.slave.javafx.controller;

import br.com.finalcraft.unesp.cd.main.TrueMain;
import br.com.finalcraft.unesp.cd.common.consoleview.ConsoleView;
import br.com.finalcraft.unesp.cd.slave.rmi.SlaveSideRMI;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class SlaveController {

    public static SlaveController instance;

    @FXML
    private BorderPane consoleBorderPane;

    @FXML
    void initialize() {
        instance = this;
        SlaveSideRMI.initialize();
        gifImage.setVisible(false);

        consoleBorderPane.setCenter(ConsoleView.createConsole());
    }

    @FXML
    void onClose() {
        TrueMain.shutDown();
    }

    @FXML
    private ImageView gifImage;

    public static void setGifOn(){
        instance.gifImage.setVisible(true);
    }

    public static void setGifOff(){
        instance.gifImage.setVisible(false);
    }

}
