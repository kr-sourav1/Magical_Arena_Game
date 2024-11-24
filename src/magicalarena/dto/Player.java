package magicalarena.dto;

public class Player {
    private String name; // Player's name
    private int health; // Player's health points
    private int strength; // Player's strength value
    private int attack; // Player's attack value

    //Constructor
    public Player(String name, int health, int strength, int attack) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.attack = attack;
    }

    //Getter for Player name, health, strength, attack
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getAttack() {
        return attack;
    }

    // Method to check if the player is alive
    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        // Method to reduce player's health by the given damage amount
        this.health = Math.max(0, this.health - damage);
    }
}
