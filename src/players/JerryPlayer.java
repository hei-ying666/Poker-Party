package players;

import game.HandRanks;
import game.Player;

public class JerryPlayer extends Player {

    public JerryPlayer(String name) {
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
            raise(getGameState().getTableMinBet() *2); // More aggressive raises
        } else if (shouldAllIn()) {
            allIn();
        }
    }

    @Override
    protected boolean shouldFold() {
        HandRanks handRank = evaluatePlayerHand();
        return handRank.getValue() < HandRanks.PAIR.getValue() && getGameState().getTableBet() > getBank() * 0.2; // Fold more strategically
    }

    @Override
    protected boolean shouldCheck() {
        return !getGameState().isActiveBet() && evaluatePlayerHand().getValue() <= HandRanks.PAIR.getValue();
    }

    @Override
    protected boolean shouldCall() {
        return evaluatePlayerHand().getValue() >= HandRanks.PAIR.getValue() || (getGameState().getTableBet() < getBank() * 0.12 && Math.random() > 0.25); // Call more selectively
    }

    @Override
    protected boolean shouldRaise() {
        return evaluatePlayerHand().getValue() >= HandRanks.TWO_PAIR.getValue() || (Math.random() > 0.4 && getGameState().getTableBet() < getBank() * 0.14); // Smarter bluffing
    }

    @Override
    protected boolean shouldAllIn() {
        return evaluatePlayerHand().getValue() >= HandRanks.FULL_HOUSE.getValue() || getBank() < getGameState().getTablePot() * 0.18; // Go all-in more carefully
    }
}