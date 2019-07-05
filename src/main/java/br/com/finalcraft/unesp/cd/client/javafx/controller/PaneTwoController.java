package br.com.finalcraft.unesp.cd.client.javafx.controller;

import br.com.finalcraft.unesp.cd.client.tcp.ClientSideTCP;
import br.com.finalcraft.unesp.cd.common.clientserver.messages.tcp.TCPMessage;
import br.com.finalcraft.unesp.cd.common.clientserver.messages.tcp.TCPMessageDirection;
import br.com.finalcraft.unesp.cd.common.clientserver.sliddingpuzzle.Direction;
import br.com.finalcraft.unesp.cd.common.clientserver.sliddingpuzzle.Matriz;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.List;

public class PaneTwoController {

    public static PaneTwoController instance;
    public static Matriz matriz;

    @FXML
    void initialize() {
        instance = this;
        matriz = new Matriz();
    }

    @FXML
    private JFXButton slot7;

    @FXML
    private JFXButton slot8;

    @FXML
    private JFXButton slot9;

    @FXML
    private JFXButton slot4;

    @FXML
    private JFXButton slot5;

    @FXML
    private JFXButton slot6;

    @FXML
    private JFXButton slot1;

    @FXML
    private JFXButton slot2;

    @FXML
    private JFXButton slot3;

    @FXML
    private JFXButton aleatorio;

    @FXML
    private Label movesLabel;

    public static void updateMoves(){
        instance.movesLabel.setText(moves + "");
    }

    private static int moves = 0;
    public static void resetMoves(){
        moves = 0;
        updateMoves();
    }

    public static void addMoves(){
        moves++;
        updateMoves();
    }

    public static void updateMatriz(){
        instance.slot1.setText("" + (matriz.matriz[0][0] != 99 ? matriz.matriz[0][0] : " "));
        instance.slot2.setText("" + (matriz.matriz[0][1] != 99 ? matriz.matriz[0][1] : " "));
        instance.slot3.setText("" + (matriz.matriz[0][2] != 99 ? matriz.matriz[0][2] : " "));
        instance.slot4.setText("" + (matriz.matriz[1][0] != 99 ? matriz.matriz[1][0] : " "));
        instance.slot5.setText("" + (matriz.matriz[1][1] != 99 ? matriz.matriz[1][1] : " "));
        instance.slot6.setText("" + (matriz.matriz[1][2] != 99 ? matriz.matriz[1][2] : " "));
        instance.slot7.setText("" + (matriz.matriz[2][0] != 99 ? matriz.matriz[2][0] : " "));
        instance.slot8.setText("" + (matriz.matriz[2][1] != 99 ? matriz.matriz[2][1] : " "));
        instance.slot9.setText("" + (matriz.matriz[2][2] != 99 ? matriz.matriz[2][2] : " "));
    }

    public static void disableAllButtons(){
        instance.aleatorio.setDisable(true);
        resetMoves();
    }

    public static void enableAllButtons(){
        instance.aleatorio.setDisable(false);
    }

    @FXML
    void onEmbaralhar(ActionEvent event) {

        disableAllButtons();
        Thread th = new Thread(){
            @Override
            public void run() {
                ClientSideTCP.sendToServer(new TCPMessage.SliddingPuzzle(null, TCPMessageDirection.CLIENT_TO_SERVER));
                TCPMessage.SliddingPuzzle tcpMessage = (TCPMessage.SliddingPuzzle) ClientSideTCP.readFromServer();
                List<Direction> directionList = tcpMessage.getDirectionList();

                matriz = new Matriz();
                for (Direction direction : directionList){
                    matriz.getMovimentador().realizaMov(direction);
                }

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        disableAllButtons();
                        enableAllButtons();
                        updateMatriz();
                    }
                });
            }
        };
        th.setDaemon(true);
        th.start();
    }

    @FXML
    void onMatrizClick(ActionEvent event) {
        JFXButton clickedButton = (JFXButton) event.getSource();

        int slot = Integer.parseInt(clickedButton.getId().charAt(4) + "");
        int number = 0;

        if (slot == 1) number = matriz.matriz[0][0];
        else if (slot == 2) number = matriz.matriz[0][1];
        else if (slot == 3) number = matriz.matriz[0][2];
        else if (slot == 4) number = matriz.matriz[1][0];
        else if (slot == 5) number = matriz.matriz[1][1];
        else if (slot == 6) number = matriz.matriz[1][2];
        else if (slot == 7) number = matriz.matriz[2][0];
        else if (slot == 8) number = matriz.matriz[2][1];
        else if (slot == 9) number = matriz.matriz[2][2];

        if (number == 99) return;

        Direction direction = null;
        matriz.opAnterior = Direction.NULO;
        for (Direction direcao : matriz.getMovimentador().montaListaMov()){
            switch(direcao){
                case CIMA:
                    if (number == matriz.matriz[matriz.posX-1][matriz.posY]) direction = Direction.CIMA;
                    break;
                case BAIXO:
                    if (number == matriz.matriz[matriz.posX+1][matriz.posY]) direction = Direction.BAIXO;                    //System.out.println("Foi para Baixo");
                    break;
                case DIREITA:
                    if (number == matriz.matriz[matriz.posX][matriz.posY+1]) direction = Direction.DIREITA;
                    break;
                case ESQUERDA:
                    if (number == matriz.matriz[matriz.posX][matriz.posY-1]) direction = Direction.ESQUERDA;
                    break;
            }
        }
        if (direction != null){
            matriz.getMovimentador().realizaMov(direction);
            addMoves();
            updateMatriz();
        }
    }
}
