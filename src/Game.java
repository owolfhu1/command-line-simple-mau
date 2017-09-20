import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Orion Wolf_Hubbard on 9/19/2017.
 */
public class Game {

    ArrayList<Card> computer = new ArrayList<>();
    ArrayList<Card> human = new ArrayList<>();
    Deck deck = new Deck();
    Scanner input = new Scanner(System.in);

    public Game(){

        //deal out 5 cards to both human and computer
        for (int x = 0; x < 5; x++) {
            computer.add(deck.draw());
            human.add(deck.draw());
        }

    }

    public void humanTurn() {

        //check if the computer won
        if (computer.isEmpty()){
            endGame(false);
            return;
        }

        //if you have no legal moves, draw card and play it if its legal then end turn
        if (deck.legalMoves(human).isEmpty()) {
            human.add(deck.draw());
            System.out.println("You draw a card.");
            displayGameState();
            if (deck.legalMoves(human).isEmpty()) {
                System.out.println("You still can't play a card, your turn ends.");
            } else {
                System.out.println("after drawing, you can now play a card, you play it and your turn ends.");
                deck.discardCard(human.remove((int)deck.legalMoves(human).get(0)));
            }
        }

        //if you have one move, make it and end turn
        else if (deck.legalMoves(human).size() == 1) {
            System.out.println("You can only make one move so I'm going to make it for you.");
            deck.discardCard(human.remove((int)deck.legalMoves(human).get(0)));
        }

        //if you can make more then one move
        else if (deck.legalMoves(human).size() > 1) {
            int cardToPlay = -1;
            ArrayList<Integer> legalMoves = deck.legalMoves(human);

            //while you haven't picked a legal move
            while (!legalMoves.contains(cardToPlay)) {
                System.out.print(cardToPlay == -1 ?
                        "Please pick the card you wish to play: " : "that was not a legal move, try again: ");

                //while you haven't picked an integer
                while (!input.hasNextInt()) {
                    System.out.print("you must enter an integer. try again: ");
                    input.next();
                }

                //save user input
                cardToPlay = input.nextInt();
            }
            System.out.println("You play the card and your turn ends.");
            deck.discardCard(human.remove(cardToPlay));
        }

        System.out.println("After your turn:");
        displayGameState();

        computerTurn();

    }

    private void computerTurn() {

        //check if the human won
        if (computer.isEmpty()){
            endGame(true);
            return;
        }

        //if the computer can't make a move
        if (deck.legalMoves(computer).isEmpty()) {
            computer.add(deck.draw());
            System.out.println("Computer can't play any cards, computer draws a card.");
            if (deck.legalMoves(computer).isEmpty()) {
                System.out.println("Computer still can't play a card, computer turn ends.");
            } else {
                System.out.println("after drawing, the computer can now play a card, computer plays it and it's turn ends.");
                deck.discardCard(computer.remove((int)deck.legalMoves(computer).get(0)));
            }
        }

        //if the computer can play a card, they play the first card in their hand.
        else {
            System.out.println("The computer plays a card and ends their turn.");
            deck.discardCard(computer.remove((int)deck.legalMoves(computer).get(0)));
        }

        System.out.println("After the computer's turn:");
        displayGameState();

        humanTurn();

    }

    public void displayGameState() {
        String humanHand = "";
        String computerHand = "";
        int count = 0;
        for (Card c : human)
            humanHand += String.format("(%s)[%s of %s], ", count++, c.getValue(), c.getSuit());
        for (Card c : computer)
            computerHand += "[card], ";
        System.out.printf("Computer Hand: %s\nDeck: %s cards\nDiscard: %s cards, Top card: [%s of %s]\nYour Hand: %s\n\n",
                computerHand, deck.deckSize(), deck.discardSize(), deck.topDiscard().getValue(), deck.topDiscard().getSuit(), humanHand);
    }

    public void endGame(boolean winner) {
        System.out.println("YOU " + (winner ? "WIN":"LOSE"));
    }

}
