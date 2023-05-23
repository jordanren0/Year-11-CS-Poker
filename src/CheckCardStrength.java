import java.lang.reflect.Array;
import java.util.Arrays;

public class CheckCardStrength {

    public static int rankHand(String[] cards){
        if(checkFourOfAKind(cards)) return 1;
        else if(checkFullHouse(cards)) return 2;
        else if(checkFlush(cards)) return 3;
        else if(checkStraight(cards)) return 4;
        else if(checkThreeOfAKind(cards)) return 5;
        else if(checkPair(cards)){
            int[] pairs = checkPairReturnValue(cards);
            if(pairs[1] == -1){
                return 6;
            }
            else{
                return 7;
            }
        }
        else return 8;
    }

    public static String highCard(String[] cards){
        int[] numbers = valuesToInt(cards);
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] == 1){
                numbers[i] = 14;
            } // Make ace the highest value
        }
        Arrays.sort(numbers);
        return "High Card: " + intToString(numbers[6]);
    }

    public static boolean checkPair(String[] cards){
        int[] numbers = valuesToInt(cards);
        Arrays.sort(numbers);

        //Check for first pair
        int firstPair = -1;
        for(int i = 0; i < numbers.length - 1; i++){
            if(numbers[i] == numbers[i+1]){
                firstPair = numbers[i];
                break;
            }
        }
        if(firstPair == -1){
            return false;
        }
        // Check for second pair
        int secondPair = -1;
        for(int i = cards.length - 1; i > 0; i--){
            if(numbers[i] == numbers[i - 1] && numbers[i] != firstPair){
                secondPair = numbers[i];
                break;
            }
        }
        if(secondPair == -1){ //If there is only one pair
            return true;
        }
        return true;
    }

    public static void showPair(String[] cards){
        int[] numbers = valuesToInt(cards);
        Arrays.sort(numbers);

        //Check for first pair
        int firstPair = -1;
        for(int i = 0; i < numbers.length - 1; i++){
            if(numbers[i] == numbers[i+1]){
                firstPair = numbers[i];
                break;
            }
        }
        // Check for second pair
        int secondPair = -1;
        for(int i = cards.length - 1; i > 0; i--){
            if(numbers[i] == numbers[i - 1] && numbers[i] != firstPair){
                secondPair = numbers[i];
                break;
            }
        }
        if(secondPair == -1){ //If there is only one pair
            System.out.println("Pair of " + intToString(firstPair) + "s");
            return;
        }
        System.out.println("Pair of " + intToString(firstPair) + "s and Pair of " + intToString(secondPair) + "s" );
    }

    public static boolean checkThreeOfAKind(String[] cards){
        int[] numbers = valuesToInt(cards);
        boolean threeOfAKind = false;
        String value = "";
        for(int i = 0; i <= numbers.length - 3; i++){
            if (numbers[i] == numbers[i + 1] && numbers[i + 1] == numbers[i + 2]) {
                threeOfAKind = true;
                value = intToString(numbers[i]);
                break;
            }
        }
        return threeOfAKind;
    }

    public static void showThreeOfAKind(String[] cards){
        int[] numbers = valuesToInt(cards);
        boolean threeOfAKind = false;
        String value = "";
        for(int i = 0; i <= numbers.length - 3; i++){
            if (numbers[i] == numbers[i + 1] && numbers[i + 1] == numbers[i + 2]) {
                threeOfAKind = true;
                value = intToString(numbers[i]);
                break;
            }
        }
        System.out.println("Triple " + value + "s");
    }

    public static boolean checkFourOfAKind(String[] cards){
        int[] numbers = valuesToInt(cards);
        boolean fourOfAKind = false;
        String value = "";
        for (int number : numbers) {
            int count = 0;
            for (int i : numbers) {
                if (number == i) {
                    count++;
                }
            }
            if (count >= 4) {
                fourOfAKind = true;
                value = intToString(number);
            }
        }
        return fourOfAKind;
    }

    public static void showFourOfAKind(String[] cards){
        int[] numbers = valuesToInt(cards);
        boolean fourOfAKind = false;
        String value = "";
        for (int number : numbers) {
            int count = 0;
            for (int i : numbers) {
                if (number == i) {
                    count++;
                }
            }
            if (count >= 4) {
                fourOfAKind = true;
                value = intToString(number);
            }
        }
        System.out.println("Quad " + value + "s");
    }

    public static boolean checkFlush(String[] cards){
        String[] suits = new String[7];
        for(int i = 0; i < 7; i++){
            String[] parts = cards[i].split(" ", 3);
            suits[i] = parts[2];
        }
        int spades = 0;
        int clubs = 0;
        int diamonds = 0;
        int hearts = 0;
        for (String suit : suits) {
            switch (suit) {
                case "Spades" -> spades++;
                case "Clubs" -> clubs++;
                case "Diamonds" -> diamonds++;
                case "Hearts" -> hearts++;
            }
        }
        if(spades >= 5){
            return true;
        }
        else if(clubs >= 5){
            return true;
        }
        else if(diamonds >= 5){
            return true;
        }
        else return hearts >= 5;
    }

    public static void showFlush(String[] cards){
        String[] suits = new String[7];
        for(int i = 0; i < 7; i++){
            String[] parts = cards[i].split(" ", 3);
            suits[i] = parts[2];
        }
        int spades = 0;
        int clubs = 0;
        int diamonds = 0;
        int hearts = 0;
        for (String suit : suits) {
            switch (suit) {
                case "Spades" -> spades++;
                case "Clubs" -> clubs++;
                case "Diamonds" -> diamonds++;
                case "Hearts" -> hearts++;
            }
        }
        if(spades >= 5){
            System.out.println("Spades flush");
        }
        else if(clubs >= 5){
            System.out.println("Clubs flush");
        }
        else if(diamonds >= 5){
            System.out.println("Diamonds flush");
        }
        else if(hearts >= 5){
            System.out.println("Hearts flush");
        }
    }

    public static boolean checkStraight(String[] cards){
        int[] numbers = valuesToInt(cards);
        Arrays.sort(numbers); // Sorts cards in ascending order

        // Check for a straight
        int count = 0;
        boolean straight = false;
        int startStraight = 0;
        int endStraight = 0;
        for(int i = 0; i < numbers.length - 1; i++){
            if(numbers[i] == numbers[i+1]){
                continue; //Skips duplicate cards
            }
            else if(numbers[i + 1] - numbers[i] == 1){
                count++;
                if(count == 4){ // Found a straight
                    straight = true;
                    endStraight = numbers[i + 1];
                    startStraight = endStraight - 4;
                }
            }
            else count = 0; // The cards do not continue in ascending order by a difference of 1
        }

        if(count == 3 && numbers[0] == 1){
            /* Checks for the special case 10-J-Q-K-A straight
               Count will equal 3 as if this is true, the end values of the array will be
               10-J-Q-K, which won't be a straight, but if the first value is an Ace,
               then this would satisfy the special case of a straight
            */
            straight = true;
            startStraight = 10;
            endStraight = 1;
        }
        return straight;
    }

    public static void showStraight(String[] cards){
        int[] numbers = valuesToInt(cards);
        Arrays.sort(numbers); // Sorts cards in ascending order

        // Check for a straight
        int count = 0;
        boolean straight = false;
        int startStraight = 0;
        int endStraight = 0;
        for(int i = 0; i < numbers.length - 1; i++){
            if(numbers[i] == numbers[i+1]){
                continue; //Skips duplicate cards
            }
            else if(numbers[i + 1] - numbers[i] == 1){
                count++;
                if(count == 4){ // Found a straight
                    straight = true;
                    endStraight = numbers[i + 1];
                    startStraight = endStraight - 4;
                }
            }
            else count = 0; // The cards do not continue in ascending order by a difference of 1
        }

        if(count == 3 && numbers[0] == 1){
            /* Checks for the special case 10-J-Q-K-A straight
               Count will equal 3 as if this is true, the end values of the array will be
               10-J-Q-K, which won't be a straight, but if the first value is an Ace,
               then this would satisfy the special case of a straight
            */
            straight = true;
            startStraight = 10;
            endStraight = 1;
        }

        if(straight) System.out.println("Straight from: " + intToString(startStraight) + " to " + intToString(endStraight));
    }

    public static boolean checkFullHouse(String[] cards){
        if(checkThreeOfAKind(cards) && checkPair(cards)){
            int three = checkThreeOfAKindReturnValue(cards);
            int[] pairs = checkPairReturnValue(cards);
            if(pairs[1] != -1){
                if(three != pairs[0]){
                    return true;
                }
                else if(three != pairs[1]){
                    return true;
                }
            }
        }
        return false;
    }

    public static void showFullHouse(String[] cards){
        if(checkThreeOfAKind(cards) && checkPair(cards)){
            int three = checkThreeOfAKindReturnValue(cards);
            int[] pairs = checkPairReturnValue(cards);
            if(pairs[1] != -1){
                if(three != pairs[0]){
                    System.out.println("Full House, 3 " + intToString(three) + "s and 2 " + intToString(pairs[0]) + "s");
                }
                else if(three != pairs[1]){
                    System.out.println("Full House, 3 " + intToString(three) + "s and 2 " + intToString(pairs[1]) + "s");
                }
            }
        }
    }

    private static int[] valuesToInt(String[] cards){
        int[] numbers = new int[cards.length];
        for(int i = 0; i < cards.length; i++){
            String[] parts = cards[i].split(" ", 3);
            switch (parts[0]) {
                case "Ace" -> numbers[i] = 1;
                case "Two" -> numbers[i] = 2;
                case "Three" -> numbers[i] = 3;
                case "Four" -> numbers[i] = 4;
                case "Five" -> numbers[i] = 5;
                case "Six" -> numbers[i] = 6;
                case "Seven" -> numbers[i] = 7;
                case "Eight" -> numbers[i] = 8;
                case "Nine" -> numbers[i] = 9;
                case "Ten" -> numbers[i] = 10;
                case "Jack" -> numbers[i] = 11;
                case "Queen" -> numbers[i] = 12;
                case "King" -> numbers[i] = 13;
            }
        }
        return numbers;
    }

    private static String intToString(int number){
        String value = null;
        switch (number) {
            case 1, 14 -> value = "Ace";
            case 2 -> value = "Two";
            case 3 -> value = "Three";
            case 4 -> value = "Four";
            case 5 -> value = "Five";
            case 6 -> value = "Six";
            case 7 -> value = "Seven";
            case 8 -> value = "Eight";
            case 9 -> value = "Nine";
            case 10 -> value = "Ten";
            case 11 -> value = "Jack";
            case 12 -> value = "Queen";
            case 13 -> value = "King";
        }
        return value;
    }

    private static int checkThreeOfAKindReturnValue(String[] cards){
        int[] numbers = valuesToInt(cards);
        boolean threeOfAKind = false;
        int value = 0;
        for(int i = 0; i <= numbers.length - 3; i++){
            if (numbers[i] == numbers[i + 1] && numbers[i + 1] == numbers[i + 2]) {
                threeOfAKind = true;
                value = numbers[i];
                break;
            }
        }
        return value;
    }

    private static int[] checkPairReturnValue(String[] cards){
        int[] numbers = valuesToInt(cards);
        Arrays.sort(numbers);

        //Check for first pair
        int firstPair = -1;
        for(int i = 0; i < numbers.length - 1; i++){
            if(numbers[i] == numbers[i+1]){
                firstPair = numbers[i];
                break;
            }
        }
        // Check for second pair
        int secondPair = -1;
        for(int i = cards.length - 1; i > 0; i--){
            if(numbers[i] == numbers[i - 1] && numbers[i] != firstPair){
                secondPair = numbers[i];
                break;
            }
        }
        if(secondPair == -1){ //If there is only one pair
            return new int[]{firstPair, -1};
        }
        return new int[]{firstPair, secondPair};
    }
}
