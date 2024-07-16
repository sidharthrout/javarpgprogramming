package item;

public class Shield {
    private String name;
    private int armor;

    public Shield(String name, int armor) {
        this.name = name;
        this.armor = armor;
    }

    public String getName() {
        return name;
    }

    public int getArmor() {
        return armor;
    }
}
