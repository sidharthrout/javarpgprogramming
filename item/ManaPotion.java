package item;

public class ManaPotion extends Item {
    private int manaAmount;

    public ManaPotion(int manaAmount) {
        super("Mana Potion");
        this.manaAmount = manaAmount;
    }

    public int getManaAmount() {
        return manaAmount;
    }
}
