package magicalarena;

import magicalarena.dto.Player;

public class MagicalArenaApplication {
    // Main method to run the Magical Arena application
    public static void main(String[] args) {
        Player playerA = new Player("Player A", 50, 5, 10);
        Player playerB = new Player("Player B", 100, 10, 5);

        Dice dice = new Dice();
        Battle battle = new Battle(playerA, playerB, dice);
        battle.start();
    }
}
