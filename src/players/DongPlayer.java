package players;

import game.HandRanks;
import game.Player;

public class DongPlayer extends Player {

    /**
     * Constructs a new Player with the specified name.
     * Initializes the player's hand, bank balance, and various status flags.
     *
     * @param name The name of the player.
     */
    public DongPlayer(String name) {
        super(name);
    }

    @Override
    protected void takePlayerTurn() {
        System.out.println("I have a" + evaluatePlayerHand());

        if (evaluatePlayerHand().getValue() > HandRanks.HIGH_CARD.getValue()) {
            System.out.println("I have a hand greater thana high card");
        } else {
            System.out.println("I have a high card");
        }
    }
    @Override
    protected boolean shouldFold() {
        return false;
    }

    @Override
    protected boolean shouldCheck() {
        return !getGameState().isActiveBet();

    }

    @Override
    protected boolean shouldCall() {
        return false;
    }

    @Override
    protected boolean shouldRaise() {

        return false;
    }

    @Override
    protected boolean shouldAllIn() {
        return false;
    }
}
