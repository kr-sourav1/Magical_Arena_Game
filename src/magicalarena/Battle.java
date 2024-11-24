package magicalarena;

import magicalarena.dto.Player;

public class Battle {
    private final Player player1;
    private final Player player2;
    private final IDice dice;

    //Constructor
    public Battle(final Player player1, final Player player2, IDice dice) {
        this.player1 = player1;
        this.player2 = player2;
        this.dice = dice;
    }

    public void start() {
        // Method to start the battle between two players
        System.out.println("Starting battle between " + player1.getName() + " and " + player2.getName());

        while (player1.isAlive() && player2.isAlive()) {
            Player attacker = (player1.getHealth() <= player2.getHealth()) ? player1 : player2;
            Player defender = (attacker == player1) ? player2 : player1;

            executeTurn(attacker, defender);

            System.out.println(attacker.getName() + " Health: " + attacker.getHealth());
            System.out.println(defender.getName() + " Health: " + defender.getHealth());
        }

        Player winner = player1.isAlive() ? player1 : player2;
        System.out.println("Winner is " + winner.getName());
    }

    private void executeTurn(final Player attacker, final Player defender) {
        // Method to execute a single turn in the battle
        int attackRoll = dice.roll();
        int defendRoll = dice.roll();
        int netDamage = findNetDamage(attacker, defender, attackRoll, defendRoll);
        System.out.println(defender.getName() + " takes " + netDamage + " damage.");
    }

    public int findNetDamage(final Player attacker, Player defender, int attackRoll, int defendRoll) {
        // Method to calculate and apply net damage from attacker to defender
        int attackDamage = attacker.getAttack() * attackRoll;
        int defendDamage = defender.getStrength() * defendRoll;

        int netDamage = Math.max(0, attackDamage - defendDamage);
        defender.takeDamage(netDamage);
        System.out.println(attacker.getName() + " attacks (Roll: " + attackRoll + ") for " + attackDamage + " damage.");
        System.out.println(defender.getName() + " defends (Roll: " + defendRoll + ") for " + defendDamage + " defense.");
        return netDamage;
    }
}
