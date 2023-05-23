import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("How many human players do you have (max 4): ");
        int humanAmount = scan.nextInt();
        while(humanAmount > 4 || humanAmount < 0){
            //Ensure that there is a suitable amount of humans,
            //or else the program will be unable to create player classes for each player
            System.out.print("Input a value 1-4: ");
            humanAmount = scan.nextInt();
        }
        String[] playerNames = new String[4];

        for(int i = 0; i < 4; i++){
            if(humanAmount != 0){
                System.out.print("Insert player " + (i+1) + "'s name: ");
                String name = scan.next();
                playerNames[i] = name;
                humanAmount--;
            }
            else{
                String name = "Bot " + (i+1);
                playerNames[i] = name;
            }
        }

        System.out.print("How many chips do you want each player to have: ");
        int chips = scan.nextInt();
        while(chips < 1){ //Ensure that the chip values are positive, otherwise the game cannot be played
            System.out.print("Input a positive chip value: ");
            chips = scan.nextInt();
        }

        Player player1 = new Player(playerNames[0], chips);
        Player player2 = new Player(playerNames[1], chips);
        Player player3 = new Player(playerNames[2], chips);
        Player player4 = new Player(playerNames[3], chips);

        System.out.println("\nHere are the initial chip values for each player:");
        System.out.println(player1.getName() + ": " + player1.getChips() + " chips");
        System.out.println(player2.getName() + ": " + player2.getChips() + " chips");
        System.out.println(player3.getName() + ": " + player3.getChips() + " chips");
        System.out.println(player4.getName() + ": " + player4.getChips() + " chips");

        playHand(player1, player2, player3, player4, chips);

        Deck deck = new Deck();
        String[] finaldeck = deck.returnCardsNeeded();
//        System.out.println(Arrays.toString(finaldeck));
//        String[] finalDeck = {
//                "Ace of Spades", "Ace of Diamonds",
//                "Four of Hearts", "Three of Spades",
//                "Three of Hearts", "Five of Hearts",
//                "Six of Hearts", "Six of Diamonds",
//                "Ace of Hearts", "Two of Hearts", "Queen of Clubs", "Two of Diamonds", "Queen of Hearts"
//        };
//        boolean[] inGame = {true, true, true, false};
//        determineWinner(player1, player2, player3, player4, finalDeck, inGame, 100);

//        String[] cards = {"Ace of Hearts", "Ace of Spades", "Three of Spades", "Three of Spades", "Five of Spades", "Six of Spades", "Seven of Spades"};
//        boolean truefalse = CheckCardStrength.checkPair(cards);
//        System.out.println(truefalse);
//
//        String[] cards2 = {"Ace of Hearts", "Ace of Spades", "Three of Spades", "Four of Spades", "Five of Spades", "Six of Spades", "Seven of Spades"};
//        boolean truefalse2 = CheckCardStrength.checkFlush(cards2);
//        System.out.println(truefalse2);
//
//        String[] cards3 = {"Ace of Hearts", "Ace of Spades", "Ace of Spades", "Four of Spades", "Five of Spades", "Ace of Spades", "Seven of Spades"};
//        boolean truefalse3 = CheckCardStrength.checkFourOfAKind(cards3);
//        System.out.println(truefalse3);
//        boolean truefalse4 = CheckCardStrength.checkThreeOfAKind(cards3);
//        System.out.println(truefalse4);
//
//        String[] cards5 = {"Ace of Hearts", "Ten of Spades", "Queen of Spades", "Jack of Spades", "King of Spades", "Six of Spades", "Seven of Spades"};
//        boolean truefalse5 = CheckCardStrength.checkStraight(cards5);
//        System.out.println(truefalse5);
//        System.out.println(CheckCardStrength.highCard(cards5));
//
//        String[] cards6 = {"Ace of Hearts", "Ace of Spades", "Ace of Diamonds", "Ten of Spades", "Jack of Diamonds", "Six of Spades", "Seven of Spades"};
//        boolean truefalse6 = CheckCardStrength.checkFullHouse(cards6);
//        System.out.println(truefalse6);
    }
    public static void playHand(Player player1, Player player2, Player player3, Player player4, int initialChip){
        Scanner scan = new Scanner(System.in);
        String[] playerOneIfBot = player1.getName().split(" ", 100);
        String[] playerTwoIfBot = player2.getName().split(" ", 100);
        String[] playerThreeIfBot = player3.getName().split(" ", 100);
        String[] playerFourIfBot = player4.getName().split(" ", 100);
        boolean playerOneBot = playerOneIfBot[0].equals("Bot");
        boolean playerTwoBot = playerTwoIfBot[0].equals("Bot");
        boolean playerThreeBot = playerThreeIfBot[0].equals("Bot");
        boolean playerFourBot = playerFourIfBot[0].equals("Bot");
        System.out.println(playerOneBot + " " + playerTwoBot + " " + playerThreeBot + " " + playerFourBot +"\n");

        Deck deck = new Deck();
        String[] finalDeck = deck.returnCardsNeeded();

        String[] player1Cards = {finalDeck[0], finalDeck[1]};
        if(!playerOneBot){
            System.out.println(player1.getName() + ", these are your cards: " + player1Cards[0] + " and " + player1Cards[1]);
        }

        String[] player2Cards = {finalDeck[2], finalDeck[3]};
        if(!playerTwoBot){
            System.out.println(player2.getName() + ", these are your cards: " + player2Cards[0] + " and " + player2Cards[1]);
        }

        String[] player3Cards = {finalDeck[4], finalDeck[5]};
        if(!playerThreeBot){
            System.out.println(player3.getName() + ", these are your cards: " + player3Cards[0] + " and " + player3Cards[1]);
        }

        String[] player4Cards = {finalDeck[6], finalDeck[7]};
        if(!playerFourBot){
            System.out.println(player4.getName() + ", these are your cards: " + player4Cards[0] + " and " + player4Cards[1]);
        }

        bettingLogic(player1, player2, player3, player4, initialChip);

        String[] flop = {finalDeck[8], finalDeck[9], finalDeck[10]};
        System.out.println("\nThese are the flop cards: " + flop[0] + ", " + flop[1] + ", and " + flop[2]);

        if(!playerOneBot){
            System.out.println(player1.getName() + ", these are your cards: " + player1Cards[0] + " and " + player1Cards[1]);
        }

        if(!playerTwoBot){
            System.out.println(player2.getName() + ", these are your cards: " + player2Cards[0] + " and " + player2Cards[1]);
        }

        if(!playerThreeBot){
            System.out.println(player3.getName() + ", these are your cards: " + player3Cards[0] + " and " + player3Cards[1]);
        }

        if(!playerFourBot){
            System.out.println(player4.getName() + ", these are your cards: " + player4Cards[0] + " and " + player4Cards[1]);
        }
        System.out.println();
        bettingLogic(player1, player2, player3, player4, initialChip);

        String[] turn = {finalDeck[11]};
        System.out.println("\nThis is the turn card: " + turn[0]);
        System.out.println("All community cards: " + flop[0] + ", " + flop[1] + ", " + flop[2] + ", and " + turn[0]);
        if(!playerOneBot){
            System.out.println(player1.getName() + ", these are your cards: " + player1Cards[0] + " and " + player1Cards[1]);
        }

        if(!playerTwoBot){
            System.out.println(player2.getName() + ", these are your cards: " + player2Cards[0] + " and " + player2Cards[1]);
        }

        if(!playerThreeBot){
            System.out.println(player3.getName() + ", these are your cards: " + player3Cards[0] + " and " + player3Cards[1]);
        }

        if(!playerFourBot){
            System.out.println(player4.getName() + ", these are your cards: " + player4Cards[0] + " and " + player4Cards[1]);
        }
        System.out.println();
        bettingLogic(player1, player2, player3, player4, initialChip);

        String[] river = {finalDeck[12]};
        System.out.println("\nThis is the river card: " + river[0]);
        System.out.println("All community cards: " + flop[0] + ", " + flop[1] + ", " + flop[2] + ", " + turn[0] + ", and " + river[0]);
        if(!playerOneBot){
            System.out.println(player1.getName() + ", these are your cards: " + player1Cards[0] + " and " + player1Cards[1]);
        }

        if(!playerTwoBot){
            System.out.println(player2.getName() + ", these are your cards: " + player2Cards[0] + " and " + player2Cards[1]);
        }

        if(!playerThreeBot){
            System.out.println(player3.getName() + ", these are your cards: " + player3Cards[0] + " and " + player3Cards[1]);
        }

        if(!playerFourBot){
            System.out.println(player4.getName() + ", these are your cards: " + player4Cards[0] + " and " + player4Cards[1]);
        }
        System.out.println();
        boolean[] inGame = bettingLogic(player1, player2, player3, player4, initialChip);

        int pot = (initialChip - player1.getChips()) + (initialChip - player2.getChips()) + (initialChip - player3.getChips()) + (initialChip - player4.getChips());
        determineWinner(player1, player2, player3, player4, finalDeck, inGame, pot);
        System.out.println("Starting new hand...");
    }

    public static boolean[] bettingLogic(Player player1, Player player2, Player player3, Player player4, int initialChip){
        String[] playerOneIfBot = player1.getName().split(" ", 100);
        String[] playerTwoIfBot = player2.getName().split(" ", 100);
        String[] playerThreeIfBot = player3.getName().split(" ", 100);
        String[] playerFourIfBot = player4.getName().split(" ", 100);
        boolean playerOneBot = playerOneIfBot[0].equals("Bot");
        boolean playerTwoBot = playerTwoIfBot[0].equals("Bot");
        boolean playerThreeBot = playerThreeIfBot[0].equals("Bot");
        boolean playerFourBot = playerFourIfBot[0].equals("Bot");
//        System.out.println(playerOneBot + " " + playerTwoBot + " " + playerThreeBot + " " + playerFourBot +"\n");

        Scanner scan = new Scanner(System.in);
        boolean[] isBot = {playerOneBot, playerTwoBot, playerThreeBot, playerFourBot};
        Player[] players = { player1, player2, player3, player4 };
        int playerNum = 4;
        boolean[] canBreak = {false, false, false, false};
        boolean[] inGame = {true, true, true, true};
        boolean breakCheck = false;
        boolean botOneCheck = false;
        int botOneBet = 0;
        int playerBet = Integer.MIN_VALUE;
        int botBet = 0;
        int[] playerBets = {0, 0, 0, 0};
        int roundCount = 0;

        while(botBet != playerBet) {
            for(int i = 0; i < 4; i++) {
                if(!isBot[i]) {
                    if(roundCount == 0) {
                        System.out.print(players[i].getName() + ", input your bet (-1 to fold): ");
                        int bet = scan.nextInt();
                        if(bet != (-1)) {
                            while(bet > players[i].getChips() || bet < -1){
                                if(bet > players[i].getChips()) System.out.print("Bet a value you can afford (You have " + players[i].getChips() + " chips): ");
                                else System.out.print("Input a positive bet: ");
                                bet = scan.nextInt();
                            }
                            playerBets[i] = bet;
                        }
                        else {
                            playerNum--;
                            canBreak[i] = true;
                            inGame[i] = false;
                        }
                    }
                    else { // roundCount != 0
                        int bet = 0;
                        if(playerBets[playerNum-1] - playerBets[i] == 0 && (i == 0 && playerBets[i] - playerBets[playerNum-1] == 0)){
                            canBreak[i] = true;
                            breakCheck = true;
                            break;
                        }
                        int largestBet = Integer.MIN_VALUE;
                        for(int j = 0; j < 4; j++){
                            int currentBet = playerBets[j];
                            if(currentBet > largestBet){
                                largestBet = currentBet;
                            }
                        }
                        if(i == 0 && inGame[i]){
                            System.out.println(players[i].getName() + ", the previous bet was: " + largestBet + ". You need "
                                    + (largestBet - playerBets[i]) + " to call");
                            System.out.print("Input the value to call, a higher value to raise, or -1 to fold: ");
                            bet = scan.nextInt();
                        }
                        else if(inGame[i]){
                            System.out.println(players[i].getName() + ", the previous bet was: " +largestBet + ". You need "
                                    + (largestBet - playerBets[i]) + " to call");
                            System.out.print("Input the value to call, a higher value to raise, or -1 to fold: ");
                            bet = scan.nextInt();
                        }
                        if(bet != (-1)) {
                            while(bet > players[i].getChips() || bet < -1){
                                if(bet > players[i].getChips()) System.out.print("Bet a value you can afford (You have " + players[i].getChips() + " chips): ");
                                else System.out.print("Input a positive bet: ");
                                bet = scan.nextInt();
                            }
                            int amountToCall = 0;
                            if(i == 0){
                                amountToCall = playerBets[playerNum-1] - playerBets[i];
                            }
                            else{
                                amountToCall = playerBets[i-1] - playerBets[i];
                            }
                            if(amountToCall == 0){
                                canBreak[i] = true;
                            }
//                            System.out.println(Arrays.toString(canBreak));
                            if (bet >= amountToCall) {
                                int raiseAmount = bet - amountToCall;
                                if(i == 0){
                                    playerBets[i] = playerBets[playerNum-1] + raiseAmount;
                                }
                                else{
                                    playerBets[i] = playerBets[i-1] + raiseAmount;
                                }
                                playerBet = bet;
                            }
                            else {
                                System.out.println("Invalid bet amount. You must call at least " + (largestBet - playerBets[i]) + " chips.");
                                i--;
                            }
                        }
                        else {
                            playerNum--;
                            canBreak[i] = true;
                            inGame[i] = false;
                        }
                    }
                }
                else {
                    if(roundCount == 0){
                        if(!botOneCheck) {
                            botBet = players[i].botBet(playerBets[i-1]);
                            botOneBet = botBet;
                            System.out.println("Bot " + (i+1) +  " bet: " + botBet);
                            playerBets[i] = botBet;
                            botOneCheck = true;
                        }
                        else {
                            playerBets[i] = botOneBet;
                            System.out.println("Bot " + (i+1) +  " bet: " + botOneBet);
                        }
                    }
                    else{
                        int bet = playerBets[i-1];
                        playerBets[i] = bet;
                        System.out.println("Bot " + (i+1) +  " bet: " + bet);
                        botBet = playerBets[i];
                        canBreak[i] = true;
                    }
                }
                System.out.println();
//                System.out.println(Arrays.toString(playerBets));
            }
            roundCount++;
            if(breakCheck) break;
        }

        for(int i = 0; i < 4; i++){
            players[i].removeChips(playerBets[i]);
        }
//        System.out.println("\n" + playerNum +" players");
        int pot = (initialChip - player1.getChips()) + (initialChip - player2.getChips()) + (initialChip - player3.getChips()) + (initialChip - player4.getChips());
        System.out.println(player1.getName() + ": " + player1.getChips() + " chips");
        System.out.println(player2.getName() + ": " + player2.getChips() + " chips");
        System.out.println(player3.getName() + ": " + player3.getChips() + " chips");
        System.out.println(player4.getName() + ": " + player4.getChips() + " chips");
        System.out.println("Pot: " + pot);
        return inGame;
    }

    public static void determineWinner(Player player1, Player player2, Player player3, Player player4, String[] finaldeck, boolean[] inGame, int pot){
        String[] player1cards = {finaldeck[0], finaldeck[1]};
        String[] player1CardsAndComm = {finaldeck[0], finaldeck[1], finaldeck[8], finaldeck[9], finaldeck[10], finaldeck[11], finaldeck[12]};

        String[] player2cards = {finaldeck[2], finaldeck[3]};
        String[] player2CardsAndComm = {finaldeck[2], finaldeck[3], finaldeck[8], finaldeck[9], finaldeck[10], finaldeck[11], finaldeck[12]};

        String[] player3cards = {finaldeck[4], finaldeck[5]};
        String[] player3CardsAndComm = {finaldeck[4], finaldeck[5], finaldeck[8], finaldeck[9], finaldeck[10], finaldeck[11], finaldeck[12]};

        String[] player4cards = {finaldeck[6], finaldeck[7]};
        String[] player4CardsAndComm = {finaldeck[6], finaldeck[7], finaldeck[8], finaldeck[9], finaldeck[10], finaldeck[11], finaldeck[12]};

        String[][] playerCards = {player1cards, player2cards, player3cards, player4cards};
        String[][] playerCardsAndComm = {player1CardsAndComm, player2CardsAndComm, player3CardsAndComm, player4CardsAndComm};
        String[] playerNames = {player1.getName(), player2.getName(), player3.getName(), player4.getName()};

        int p1Ranking = CheckCardStrength.rankHand(player1CardsAndComm);
        int p2Ranking = CheckCardStrength.rankHand(player2CardsAndComm);
        int p3Ranking = CheckCardStrength.rankHand(player3CardsAndComm);
        int p4Ranking = CheckCardStrength.rankHand(player4CardsAndComm);
        int[] handRankings = {p1Ranking, p2Ranking, p3Ranking, p4Ranking};
        int highest = 9;
        int winner = 0;
        for(int i = 0; i < 4; i++){
            if(handRankings[i] < highest && inGame[i]){
                highest = handRankings[i];
                winner = i+1;
            }
        }
//        System.out.println(highest);
        System.out.println("\nCongrats " + playerNames[winner-1] + ", you won! Your cards and the community cards were: ");
        System.out.println(Arrays.toString(playerCardsAndComm[winner - 1]));
        System.out.print("You managed to make: ");
        if(highest == 1) CheckCardStrength.checkFourOfAKind(playerCardsAndComm[winner-1]);
        else if(highest == 2) CheckCardStrength.showFullHouse(playerCardsAndComm[winner-1]);
        else if(highest == 3) CheckCardStrength.showFlush(playerCardsAndComm[winner-1]);
        else if(highest == 4) CheckCardStrength.showStraight(playerCardsAndComm[winner-1]);
        else if(highest == 5) CheckCardStrength.showThreeOfAKind(playerCardsAndComm[winner-1]);
        else if(highest == 6 || highest == 7) CheckCardStrength.showPair(playerCardsAndComm[winner-1]);
        else if(highest == 8) CheckCardStrength.highCard(playerCardsAndComm[winner-1]);

        System.out.println("\nEveryones cards were: ");
        for(int i = 0; i < 4; i++){
            System.out.println(playerNames[i] + ": " + Arrays.toString(playerCards[i]));
        }
        System.out.println();

        if(winner == 1) player1.addChips(pot);
        else if(winner == 2) player2.addChips(pot);
        else if(winner == 3) player3.addChips(pot);
        else player4.addChips(pot);

        System.out.println("Updated Chips: ");
        System.out.println(player1.getName() + ": " + player1.getChips() + " chips");
        System.out.println(player2.getName() + ": " + player2.getChips() + " chips");
        System.out.println(player3.getName() + ": " + player3.getChips() + " chips");
        System.out.println(player4.getName() + ": " + player4.getChips() + " chips");
    }

}
