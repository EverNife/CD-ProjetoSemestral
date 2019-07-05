package br.com.finalcraft.unesp.cd.server.handlers.rmi.executables;

import br.com.finalcraft.unesp.cd.common.clientserver.messages.tcp.TCPMessage;
import br.com.finalcraft.unesp.cd.common.clientserver.sliddingpuzzle.Direction;
import br.com.finalcraft.unesp.cd.common.clientserver.sliddingpuzzle.Matriz;
import br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi.RMIMessage;
import br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi.RMIMessageDirection;
import br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi.RMIMessageExecutable;
import br.com.finalcraft.unesp.cd.server.handlers.rmi.executables.msg.RMIMessageSlidingResult;

import java.util.*;

@SuppressWarnings("Duplicates")
public class SliddingPuzzleSolver extends RMIMessage implements RMIMessageExecutable {

    private Matriz matriz;
    private final Random random = new Random();

    public SliddingPuzzleSolver(TCPMessage.SliddingPuzzle tcpMessage) {
        this.matriz = new Matriz();
        this.direction = RMIMessageDirection.SERVER_TO_SLAVE;
    }

    public List<Direction> embaralhar() {
        int qtd = 200;
        matriz.opAnterior = Direction.NULO;
        List<Direction> historyMoves = new ArrayList<Direction>();

        while(qtd > 0){
            ArrayList<Direction> movPossiveis = new ArrayList<Direction>();

            if((matriz.posX > 0) && (matriz.opAnterior != Direction.BAIXO)){
                movPossiveis.add(Direction.CIMA);
            }
            if((matriz.posX < 2) && (matriz.opAnterior != Direction.CIMA)){
                movPossiveis.add(Direction.BAIXO);
            }
            if((matriz.posY < 2) && (matriz.opAnterior != Direction.ESQUERDA)){
                movPossiveis.add(Direction.DIREITA);
            }
            if((matriz.posY > 0) && (matriz.opAnterior != Direction.DIREITA)){
                movPossiveis.add(Direction.ESQUERDA);
            }

            Direction randomDirection = movPossiveis.get(random.nextInt(movPossiveis.size()));

            matriz.getMovimentador().realizaMov(randomDirection);
            historyMoves.add(randomDirection);
            qtd--;
        }
        return historyMoves;
    }

    @Override
    public RMIMessage execute() {
        return new RMIMessageSlidingResult(embaralhar());
    }

    @Override
    public String toString() {
        return "[\"" + direction + "\"]";
    }

}
