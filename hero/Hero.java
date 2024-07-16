package hero;

import java.util.Random;
import java.util.Scanner;

import item.Shield;
import item.Weapon;
import monster.Monster;

public abstract class Hero {
    protected String name;
    protected int hp;
    protected int mana;
    protected int level;
    protected int gold;
    protected static final int MAX_HP = 100;
    protected static final int MAX_MANA = 50;
    protected Weapon weapon;
    protected Shield shield;
    protected Random rand = new Random();
    protected Scanner scanner = new Scanner(System.in);

    public Hero(String name) {
        this.name = name;
        this.hp = MAX_HP;
        this.mana = MAX_MANA;
        this.level = 1;
        this.gold = 0;
    }

    public abstract void attack(Monster m);

    public void takeDamage(int damage) {
        if (shield != null) {
            damage -= shield.getArmor();
            if (damage < 0) {
                damage = 0;
            }
        }
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
        System.out.println(this.name + " took " + damage + " points of damage!");
    }

    public void heal(int healAmount) {
        this.hp += healAmount;
        if (this.hp > MAX_HP) {
            this.hp = MAX_HP;
        }
        System.out.println(this.name + " healed " + healAmount + " HP!");
    }

    public void useMana(int manaAmount) {
        this.mana -= manaAmount;
        if (this.mana < 0) {
            this.mana = 0;
        }
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public String getName() {
        return this.name;
    }

    public int getHp() {
        return this.hp;
    }

    public int getMana() {
        return this.mana;
    }

    public int getGold() {
        return this.gold;
    }

    public void earnGold(int amount) {
        this.gold += amount;
        System.out.println(this.name + " earned " + amount + " gold!");
    }

    public void spendGold(int amount) {
        this.gold -= amount;
        if (this.gold < 0) {
            this.gold = 0;
        }
    }

    public void chooseEquipment() {
        Scanner scanner = new Scanner(System.in);
        boolean validChoice = false;
        while (!validChoice) {
            try {
                System.out.println(this.name + ", choose your weapon:");
                System.out.println("1. Sword (+10 damage)");
                System.out.println("2. Bow (+8 damage)");
                System.out.println("3. Knife (+9 damage)");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        this.weapon = new Weapon("Sword", 10);
                        validChoice = true;
                        break;
                    case 2:
                        this.weapon = new Weapon("Bow", 8);
                        validChoice = true;
                        break;
                    case 3:
                        this.weapon = new Weapon("Knife", 9);
                        validChoice = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
            }
        }
        System.out.println(this.name + " equipped " + weapon.getName() + "!");

        validChoice = false;
        while (!validChoice) {
            try {
                System.out.println(this.name + ", choose your shield:");
                System.out.println("1. Iron Shield (+2 armor)");
                System.out.println("2. Gold Shield (+4 armor)");
                System.out.println("3. Diamond Shield (+6 armor)");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        this.shield = new Shield("Iron Shield", 2);
                        validChoice = true;
                        break;
                    case 2:
                        this.shield = new Shield("Gold Shield", 4);
                        validChoice = true;
                        break;
                    case 3:
                        this.shield = new Shield("Diamond Shield", 6);
                        validChoice = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
            }
        }
        System.out.println(this.name + " equipped " + shield.getName() + "!");
    }

    protected int calculateDamage(int baseDamage) {
        int damage = baseDamage + (weapon != null ? weapon.getDamage() : 0);
        if (rand.nextInt(100) < 20) { // 20% chance for critical hit
            System.out.println("Critical hit!");
            damage *= 2;
        }
        return damage;
    }

    public void levelUp() {
        this.level++;
        this.hp = MAX_HP;
        this.mana = MAX_MANA;
        System.out.println(this.name + " leveled up to level " + this.level + "!");
    }

    public abstract void specialSkill(Monster m);

    public abstract void castSpell(Monster m);

    public void chooseAction(Monster m) {
        System.out.println(this.name + ", choose your action:");
        System.out.println("1. Attack");
        System.out.println("2. Heal");
        System.out.println("3. Use Special Skill");
        System.out.println("4. Cast Spell");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                attack(m);
                break;
            case 2:
                heal(20);
                break;
            case 3:
                specialSkill(m);
                break;
            case 4:
                castSpell(m);
                break;
            default:
                System.out.println("Invalid choice. You lose your turn.");
                break;
        }
    }

    public void buyItem(String item, int cost, int healAmount, int manaAmount) {
        if (this.gold >= cost) {
            spendGold(cost);
            if (healAmount > 0) {
                heal(healAmount);
            }
            if (manaAmount > 0) {
                this.mana += manaAmount;
                if (this.mana > MAX_MANA) {
                    this.mana = MAX_MANA;
                }
            }
            System.out.println(this.name + " bought " + item + "!");
        } else {
            System.out.println(this.name + " doesn't have enough gold to buy " + item + ".");
        }
    }
}
