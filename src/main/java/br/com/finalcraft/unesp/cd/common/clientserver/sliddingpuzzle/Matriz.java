/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finalcraft.unesp.cd.common.clientserver.sliddingpuzzle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Matriz implements Serializable {

    public int[][] matriz = {{1,2,3},{4,5,6},{7,8,99}};
    public int posX = 2;
    public int posY = 2;
    public Direction opAnterior = Direction.NULO;
    public int opAnteriorValor = 0;

    private final Movimentador movimentador;

    public Movimentador getMovimentador() {
        return movimentador;
    }

    public Matriz() {
        movimentador = new Movimentador(this);
    }

    public Matriz(int[][] matriz) {
        this.matriz = matriz;
        movimentador = new Movimentador(this);
    }
}
