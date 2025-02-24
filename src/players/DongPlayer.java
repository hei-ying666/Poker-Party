package players;

import game.HandRanks;
import game.Player;
import java.util.Random;

public class DongPlayer extends Player {

    private final Random random = new Random();


    public DongPlayer(String name) {
        super(name);
    }

    @Override
    protected void takePlayerTurn() {
        if (shouldFold()) {
            fold();
        } else if (shouldCheck()) {
            check();
        } else if (shouldCall()) {
            call();
        } else if (shouldRaise()) {
            // raise(getGameState().getTableMinBet() * 2); // Raise aggressively
            //int raiseAmount = (int) (getGameState().getTableMinBet() * (1.5 + Math.random())); // More flexible raise
            //raise(raiseAmount);
            //int raiseAmount = (int) (getGameState().getTableMinBet() * (2 + random.nextDouble() * 2)); // Dynamic raise
            int raiseAmount = (int) (getGameState().getTableMinBet() * (2 + random.nextDouble() * 2));
            raise(raiseAmount);
        } else if (shouldAllIn()) {
            allIn();
        }
    }

    @Override
    protected boolean shouldFold() {
        return evaluatePlayerHand().getValue() == HandRanks.HIGH_CARD.getValue() && getGameState().getTableBet() > getBank() * 0.1;
    }

    @Override
    protected boolean shouldCheck() {
        return evaluatePlayerHand().getValue() <= HandRanks.PAIR.getValue() && !getGameState().isActiveBet();
    }

    @Override
    protected boolean shouldCall() {
        return evaluatePlayerHand().getValue() >= HandRanks.PAIR.getValue() || getGameState().getTableBet() < getBank() * 0.15;
    }

    @Override
    protected boolean shouldRaise() {
        //return evaluatePlayerHand().getValue() >= HandRanks.TWO_PAIR.getValue() || getGameState().getTableBet() < getBank() * 0.07;
        //return evaluatePlayerHand().getValue() >= HandRanks.TWO_PAIR.getValue() && Math.random() > 0.4;
        return (evaluatePlayerHand().getValue() >= HandRanks.TWO_PAIR.getValue() && random.nextDouble() > 0.3) || getGameState().getTableBet() < getBank() * 0.05;

    }

    @Override
    protected boolean shouldAllIn() {
        //return evaluatePlayerHand().getValue() >= HandRanks.FULL_HOUSE.getValue() || getBank() < getGameState().getTablePot() * 0.4;
        return evaluatePlayerHand().getValue() >= HandRanks.FULL_HOUSE.getValue() || (getBank() < getGameState().getTablePot() * 0.3 && random.nextDouble() > 0.5);

    }
}

