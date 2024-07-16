package monster;

import java.util.Random;

public class Dragon extends Monster {
    public Dragon(String type) {
        super(type + " Dragon", 500, 40, 100);
    }

    @Override
    public void attack(hero.Hero hero) {
        Random rand = new Random();
        int attackType = rand.nextInt(3);
        switch (attackType) {
            case 0:
                System.out.println(this.name + " uses Fire Breath!");
                break;
            case 1:
                System.out.println(this.name + " uses Storm Attack!");
                break;
            case 2:
                System.out.println(this.name + " uses Black Hole!");
                break;
        }
        super.attack(hero);
    }
}
