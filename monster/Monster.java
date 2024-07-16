package monster;

import hero.Hero;

public abstract class Monster {
    protected String name;
    protected int hp;
    protected int attackPower;
    protected int goldReward;

    public Monster(String name, int hp, int attackPower, int goldReward) {
        this.name = name;
        this.hp = hp;
        this.attackPower = attackPower;
        this.goldReward = goldReward;
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
        System.out.println(this.name + " took " + damage + " points of damage!");
    }

    public void attack(Hero hero) {
        hero.takeDamage(attackPower);
        System.out.println("Dealt " + attackPower + " points of damage!");
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public int getHp() {
        return this.hp;
    }

    public int getGoldReward() {
        return this.goldReward;
    }

    public String getName() {
        return this.name;
    }
}
