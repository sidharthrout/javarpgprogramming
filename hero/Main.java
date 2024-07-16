package hero;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import item.Shield;
import item.Weapon;
import monster.Goblin;
import monster.Monster;
import monster.Ogre;
import monster.Zombie;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Hero> heroes = new ArrayList<>();
        Random rand = new Random();

        System.out.println("Enter the name for Wizard:");
        String wizardName = scanner.nextLine();
        Wizard wizard = new Wizard(wizardName);
        wizard.chooseEquipment();
        heroes.add(wizard);

        System.out.println("Enter the name for Vanguard:");
        String vanguardName = scanner.nextLine();
        Vanguard vanguard = new Vanguard(vanguardName);
        vanguard.chooseEquipment();
        heroes.add(vanguard);

        int turnCount = 0;

        while (true) {
            turnCount++;
            System.out.println("\nTurn " + turnCount);
            System.out.println("----------------------------------");

            Monster monster = getRandomMonster(turnCount);
            System.out.println("A wild " + monster.getName() + " appears!");

            while (monster.isAlive() && heroes.stream().anyMatch(Hero::isAlive)) {
                System.out.println("----------------------------------");
                System.out.println("Heroes attack!");

                for (Hero hero : heroes) {
                    if (hero.isAlive()) {
                        hero.chooseAction(monster);
                    }
                }

                System.out.println("----------------------------------");

                if (monster.isAlive()) {
                    for (Hero hero : heroes) {
                        if (hero.isAlive()) {
                            monster.attack(hero);
                        }
                    }
                }

                System.out.println("----------------------------------");
                for (Hero hero : heroes) {
                    System.out.println(hero.getName() + " HP: " + hero.getHp() + " Mana: " + hero.getMana());
                }
                System.out.println(monster.getName() + " HP: " + monster.getHp());
                System.out.println("----------------------------------");
            }

            if (!monster.isAlive()) {
                for (Hero hero : heroes) {
                    hero.earnGold(monster.getGoldReward());
                    hero.levelUp();
                }
                System.out.println("The heroes have defeated " + monster.getName() + "!");
                openShop(heroes);
            } else {
                System.out.println("Game Over!");
                break;
            }
        }
        scanner.close();
    }

    public static Monster getRandomMonster(int turnCount) {
        Random rand = new Random();
        int monsterType = rand.nextInt(3);
        switch (monsterType) {
            case 0:
                return new Zombie();
            case 1:
                return new Goblin();
            case 2:
                return new Ogre();
            default:
                return new Zombie();
        }
    }

    public static void openShop(List<Hero> heroes) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the shop! You can buy items and potions here.");
        boolean shopping = true;

        while (shopping) {
            System.out.println("Choose an option:");
            System.out.println("1. Buy health potion (10 gold)");
            System.out.println("2. Buy mana potion (10 gold)");
            System.out.println("3. Buy items");
            System.out.println("4. Exit shop");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    for (Hero hero : heroes) {
                        hero.buyItem("Health Potion", 10, 20, 0);
                    }
                    break;
                case 2:
                    for (Hero hero : heroes) {
                        hero.buyItem("Mana Potion", 10, 0, 20);
                    }
                    break;
                case 3:
                    for (Hero hero : heroes) {
                        System.out.println(hero.getName() + ", choose an item:");
                        System.out.println("1. Sword (+10 damage, 20 gold)");
                        System.out.println("2. Bow (+8 damage, 20 gold)");
                        System.out.println("3. Knife (+9 damage, 20 gold)");
                        System.out.println("4. Iron Shield (+2 armor, 20 gold)");
                        System.out.println("5. Gold Shield (+4 armor, 30 gold)");
                        System.out.println("6. Diamond Shield (+6 armor, 40 gold)");

                        int itemChoice = scanner.nextInt();
                        switch (itemChoice) {
                            case 1:
                                hero.buyItem("Sword", 20, 0, 0);
                                hero.weapon = new Weapon("Sword", 10);
                                break;
                            case 2:
                                hero.buyItem("Bow", 20, 0, 0);
                                hero.weapon = new Weapon("Bow", 8);
                                break;
                            case 3:
                                hero.buyItem("Knife", 20, 0, 0);
                                hero.weapon = new Weapon("Knife", 9);
                                break;
                            case 4:
                                hero.buyItem("Iron Shield", 20, 0, 0);
                                hero.shield = new Shield("Iron Shield", 2);
                                break;
                            case 5:
                                hero.buyItem("Gold Shield", 30, 0, 0);
                                hero.shield = new Shield("Gold Shield", 4);
                                break;
                            case 6:
                                hero.buyItem("Diamond Shield", 40, 0, 0);
                                hero.shield = new Shield("Diamond Shield", 6);
                                break;
                            default:
                                System.out.println("Invalid choice.");
                                break;
                        }
                    }
                    break;
                case 4:
                    shopping = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }
}
