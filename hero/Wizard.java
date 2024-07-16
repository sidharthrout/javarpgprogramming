package hero;

import monster.Monster;

public class Wizard extends Hero {
    public Wizard(String name) {
        super(name);
    }

    @Override
    public void attack(Monster m) {
        int damage = calculateDamage(14);
        System.out.println(this.name + " casts a spell!");
        m.takeDamage(damage);
        System.out.println("Dealt " + damage + " points of damage!");
    }

    @Override
    public void specialSkill(Monster m) {
        int damage = calculateDamage(20);
        System.out.println(this.name + " uses Fireball!");
        m.takeDamage(damage);
        useMana(10);
        System.out.println("Dealt " + damage + " points of damage!");
    }

    @Override
    public void castSpell(Monster m) {
        int damage = calculateDamage(15);
        System.out.println(this.name + " casts Lightning Strike!");
        m.takeDamage(damage);
        useMana(5);
        System.out.println("Dealt " + damage + " points of damage!");
    }
}
