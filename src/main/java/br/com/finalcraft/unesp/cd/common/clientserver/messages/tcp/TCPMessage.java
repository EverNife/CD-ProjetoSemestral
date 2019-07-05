package br.com.finalcraft.unesp.cd.common.clientserver.messages.tcp;

import br.com.finalcraft.unesp.cd.common.clientserver.sliddingpuzzle.Direction;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public abstract class TCPMessage implements Serializable {

    TCPMessageDirection direction;
    public TCPMessageDirection getDirection() {
        return direction;
    }

    public static class Calculator extends TCPMessage{
        String theExpression;

        public String getTheExpression() {
            return theExpression;
        }

        public Calculator(String theExpression, TCPMessageDirection direction) {
            this.theExpression = theExpression;
            this.direction = direction;
        }

        @Override
        public String toString() {
            return "[" + direction + " - TheExpression: \"" + theExpression + "\"]";
        }
    }

    public static class Fibonacci extends TCPMessage{
        BigInteger amount;

        public BigInteger getAmount() {
            return amount;
        }

        public Fibonacci(BigInteger amount, TCPMessageDirection direction) {
            this.amount = amount;
            this.direction = direction;
        }

        @Override
        public String toString() {
            return "[" + direction + " - Amount: \"" + amount.toString() + "\"]";
        }
    }

    public static class SliddingPuzzle extends TCPMessage{
        private List<Direction> directionList = new ArrayList<Direction>();

        public SliddingPuzzle(List<Direction> directionList, TCPMessageDirection direction) {
            this.directionList = directionList;
            this.direction = direction;
        }

        public List<Direction> getDirectionList() {
            return directionList;
        }

        @Override
        public String toString() {
            if (directionList == null) {
                return "[" + direction + " - Requesting Direction List]";
            }
            return "[" + direction + " - DirectionListSize: " + directionList.size() + "]";
        }
    }

}
