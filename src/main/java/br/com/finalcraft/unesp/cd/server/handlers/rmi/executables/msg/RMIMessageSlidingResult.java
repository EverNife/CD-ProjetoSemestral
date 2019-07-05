package br.com.finalcraft.unesp.cd.server.handlers.rmi.executables.msg;

import br.com.finalcraft.unesp.cd.common.clientserver.sliddingpuzzle.Direction;
import br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi.RMIMessage;
import br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi.RMIMessageDirection;

import java.util.List;

public class RMIMessageSlidingResult extends RMIMessage {

    public final List<Direction> solvingDirections;

    public RMIMessageSlidingResult(List<Direction> solvingDirections) {
        this.solvingDirections = solvingDirections;
        this.direction = RMIMessageDirection.SLAVE_TO_SERVER;
    }

    public List<Direction> getSolvingDirections() {
        return solvingDirections;
    }

    @Override
    public String toString() {
        return "[" + direction + " - TheDirectionsSize: \"" + solvingDirections.size() + "\"]";
    }
}