package item;

public class HealingPotion extends Item {
    private int healAmount;

    public HealingPotion(int healAmount) {
        super("Healing Potion");
        this.healAmount = healAmount;
    }

    public int getHealAmount() {
        return healAmount;
    }
}
