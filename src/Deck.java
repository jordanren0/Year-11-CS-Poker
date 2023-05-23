import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private final String[] suits = {"Diamond", "Club", "Heart", "Spade"};
    private final String[] numbers = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

    public ArrayList<String> fullDeck(){
        ArrayList<String> deck = new ArrayList<String>();
        for (String suit : suits) {
            for (String number : numbers) {
                deck.add(number + " of " + suit + "s");
            }
        }
        return deck;
    }

    public ArrayList<String> shuffleDeck(){
        // Directly shuffling the whole deck instead of using the random import to pick a random card
        ArrayList<String> deck = fullDeck();
        Collections.shuffle(deck);
        return deck;
    }

    public String[] returnCardsNeeded(){
        // 4 pairs of hole cards, or each player's starting hand
        // 3 flop cards, 1 turn card and 1 river card.
        // 13 cards needed
        ArrayList<String> deck = shuffleDeck();
        String[] finalCardsNeeded = new String[13];
        for(int i = 0; i < 13; i++){
            finalCardsNeeded[i] = deck.get(i);
            /*
            So here index 0 and 1 would be player 1's cards,
            2 and 3 would be player 2's cards, etc until index 7
            Index 8-10 would be the flop cards
            Index 11 would be the turn card
            Index 12 would be the river card
            (No need to burn cards, which is a rule in regular poker
            as the deck is already shuffled)
             */
        }
        return finalCardsNeeded;
    }
}
