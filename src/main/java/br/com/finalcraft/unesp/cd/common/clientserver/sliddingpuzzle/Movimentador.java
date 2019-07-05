/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finalcraft.unesp.cd.common.clientserver.sliddingpuzzle;

import java.io.Serializable;
import java.util.ArrayList;

public class Movimentador implements Serializable {

    public Matriz matriz;
    public Movimentador(Matriz matriz) {
        this.matriz = matriz;
    }

    public ArrayList<Direction> montaListaMov(){
        ArrayList<Direction> movPossiveis = new ArrayList<Direction>();

        if((matriz.posX > 0) && (!matriz.opAnterior.equals(Direction.BAIXO))){
            movPossiveis.add(Direction.CIMA);
        }
        if((matriz.posX < 2) && (!matriz.opAnterior.equals(Direction.CIMA))){
            movPossiveis.add(Direction.BAIXO);
        }
        if((matriz.posY < 2) && (!matriz.opAnterior.equals(Direction.ESQUERDA))){
            movPossiveis.add(Direction.DIREITA);
        }
        if((matriz.posY > 0) && (!matriz.opAnterior.equals(Direction.DIREITA))){
            movPossiveis.add(Direction.ESQUERDA);
        }

        return movPossiveis;
    }

    public void realizaMov(Direction direcao){
        switch(direcao){
            case CIMA:
                cima();
                break;

            case BAIXO:
                baixo();
                break;

            case DIREITA:
                direita();
                break;

            case ESQUERDA:
                esquerda();
                break;
        }
    }

    private void cima(){
        matriz.opAnteriorValor = matriz.matriz[matriz.posX-1][matriz.posY];
        matriz.opAnterior = Direction.CIMA;
        matriz.matriz[matriz.posX][matriz.posY] = matriz.matriz[matriz.posX-1][matriz.posY];
        matriz.matriz[matriz.posX-1][matriz.posY] = 99;
        matriz.posX = matriz.posX - 1;
    }

    private void baixo(){
        matriz.opAnteriorValor = matriz.matriz[matriz.posX+1][matriz.posY];
        matriz.opAnterior = Direction.BAIXO;
        matriz.matriz[matriz.posX][matriz.posY] = matriz.matriz[matriz.posX+1][matriz.posY];
        matriz.matriz[matriz.posX+1][matriz.posY] = 99;
        matriz.posX = matriz.posX + 1;
    }

    private void direita(){
        matriz.opAnteriorValor = matriz.matriz[matriz.posX][matriz.posY+1];
        matriz.opAnterior = Direction.DIREITA;
        matriz.matriz[matriz.posX][matriz.posY] = matriz.matriz[matriz.posX][matriz.posY+1];
        matriz.matriz[matriz.posX][matriz.posY+1] = 99;
        matriz.posY = matriz.posY + 1;
    }

    private void esquerda(){
        matriz.opAnteriorValor = matriz.matriz[matriz.posX][matriz.posY-1];
        matriz.opAnterior = Direction.ESQUERDA;
        matriz.matriz[matriz.posX][matriz.posY] = matriz.matriz[matriz.posX][matriz.posY-1];
        matriz.matriz[matriz.posX][matriz.posY-1] = 99;
        matriz.posY = matriz.posY - 1;
    }

}
