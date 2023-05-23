import java.util.Random;
public class Player {
    private String name;
    private int chips;
    public Player(String name, int chips) {
        this.name = name;
        this.chips = chips;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChips() {
        return chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public void addChips(int amount) {
        chips += amount;
    }

    public void removeChips(int amount) {
        chips -= amount;
    }

    public void humanBet(int bet){
        chips -= bet;
    }

    public int botBet(int range){
        Random rand = new Random();
        return rand.nextInt((chips - range + 1)/2) + range;
    }

}
