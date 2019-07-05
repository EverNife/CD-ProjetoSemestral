package br.com.finalcraft.unesp.cd.client.javafx.controller;

import br.com.finalcraft.unesp.cd.client.tcp.ClientSideTCP;
import br.com.finalcraft.unesp.cd.common.clientserver.messages.tcp.TCPMessage;
import br.com.finalcraft.unesp.cd.common.clientserver.messages.tcp.TCPMessageDirection;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PaneOneController {

    public static PaneOneController instance;

    @FXML
    void initialize() {
        instance = this;
        viwer.setText("");
    }

    @FXML
    private JFXButton viwer;

    @FXML
    void onCalculate() {

        String theExpression = viwer.getText();

        ClientSideTCP.sendToServer(new TCPMessage.Calculator(theExpression, TCPMessageDirection.CLIENT_TO_SERVER));
        TCPMessage.Calculator tcpMessage = (TCPMessage.Calculator) ClientSideTCP.readFromServer();

        if (tcpMessage == null){
            viwer.setText("!Error: Connection T.T");
        }else {
            viwer.setText(tcpMessage.getTheExpression());
        }
    }

    private void clearWrongnus(){
        if (viwer.getText().startsWith("!")){
            viwer.setText("");
        }
    }

    @FXML
    void onErase() {
        clearWrongnus();
        String textView = viwer.getText();
        if (textView.length() > 0){
            viwer.setText(textView.substring(0,textView.length() - 1));
        }
    }

    @FXML
    void onPressedButton(ActionEvent event) {
        clearWrongnus();
        if (event.getTarget() instanceof JFXButton){
            JFXButton pressedButton = (JFXButton) event.getTarget();
            String pressedButtonText = pressedButton.getText();
            viwer.setText(viwer.getText() + pressedButtonText);
        }
    }
}
