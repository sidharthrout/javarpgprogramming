package hero;

import monster.Monster;

public class Vanguard extends Hero {
    public Vanguard(String name) {
        super(name);
    }

    @Override
    public void attack(Monster m) {
        int damage = calculateDamage(10);
        System.out.println(this.name + " strikes with shield!");
        m.takeDamage(damage);
        System.out.println("Dealt " + damage + " points of damage!");
    }

    @Override
    public void specialSkill(Monster m) {
        int damage = calculateDamage(15);
        System.out.println(this.name + " uses Shield Bash!");
        m.takeDamage(damage);
        System.out.println("Dealt " + damage + " points of damage!");
    }

    @Override
    public void castSpell(Monster m) {
        int damage = calculateDamage(12);
        System.out.println(this.name + " casts Thunderclap!");
        m.takeDamage(damage);
        useMana(5);
        System.out.println("Dealt " + damage + " points of damage!");
    }
}
