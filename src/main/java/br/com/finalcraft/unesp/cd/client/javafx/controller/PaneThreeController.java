package br.com.finalcraft.unesp.cd.client.javafx.controller;

import br.com.finalcraft.unesp.cd.client.tcp.ClientSideTCP;
import br.com.finalcraft.unesp.cd.common.clientserver.messages.tcp.TCPMessage;
import br.com.finalcraft.unesp.cd.common.clientserver.messages.tcp.TCPMessageDirection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import java.math.BigInteger;

public class PaneThreeController {

    public static PaneThreeController instance;

    @FXML
    void initialize() {
        instance = this;
    }

    @FXML
    private GridPane pane;

    @FXML
    private JFXTextField textField;

    @FXML
    private JFXButton resultViwer;

    @FXML
    void onCalculate() {

        long amount;
        try {
            amount = Long.valueOf(textField.getText().trim());
        }catch (Exception ignored){
            return;
        }
        textField.setText("");

        ClientSideTCP.sendToServer(new TCPMessage.Fibonacci(BigInteger.valueOf(amount), TCPMessageDirection.CLIENT_TO_SERVER));
        TCPMessage.Fibonacci tcpMessage = (TCPMessage.Fibonacci) ClientSideTCP.readFromServer();

        if (tcpMessage == null){
            resultViwer.setText("!Error: Connection T.T");
        }else {
            resultViwer.setText(String.valueOf(tcpMessage.getAmount()));
        }
    }

}
