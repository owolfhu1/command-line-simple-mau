
/**
 * Created by Orion Wolf_Hubbard on 9/19/2017.
 */
public class MauArcade {

    public static void main(String... args) {

        //make a new game
        Game game = new Game();

        //show the gameState at start
        game.displayGameState();

        //start playing with human (could also start with computer or make it random)
        game.humanTurn();

    }

}
