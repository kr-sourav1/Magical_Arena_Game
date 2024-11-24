package test;

import magicalarena.Battle;
import magicalarena.IDice;
import magicalarena.dto.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class BattleTest {

    private PrintStream originalOut;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() {
        // Redirect System.out to a ByteArrayOutputStream for capturing output
        outputStream = new ByteArrayOutputStream();
        originalOut = new PrintStream(outputStream);
        System.setOut(originalOut);
    }

    @After
    public void cleanUp() {
        // Restore original System.out and close the output stream
        System.setOut(System.out);
        originalOut.flush();
        originalOut.close();
        try {
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            fail("Failed to close output stream: " + e.getMessage());
        }
    }

    @Test
    public void testAttackerDealsDamage() {
        //net damage calculation and defender's health after attack
        Player attacker = new Player("Attacker", 100, 5, 10);
        Player defender = new Player("Defender", 100, 3, 8);

        IDice fixedDice = new FixedDice(5); // Rolls will always return 5

        Battle battle = new Battle(attacker, defender, fixedDice);
        int netDamage = battle.findNetDamage(attacker, defender, 5, 2);

        assertEquals(44, netDamage);
        assertEquals(56, defender.getHealth());
    }

    @Test
    public void testAttackerDealsDamageTwo() {
        //if Player 2 wins a battle scenario
        Player attacker = new Player("Attacker", 100, 5, 10);
        Player defender = new Player("Defender", 50, 5, 8);

        IDice fixedDice = new FixedDice(5);

        Battle battle = new Battle(attacker, defender, fixedDice);
        int netDamage = battle.findNetDamage(attacker, defender, 6, 2);

        // Assert net damage and defender health reaches zero
        assertEquals(50, netDamage);
        assertEquals(0, defender.getHealth());
    }

    @Test
    public void testBattleStart_Player1Wins() {
        // if Player 1 wins a battle scenario
        Player player1 = new Player("Player 1", 100, 5, 10);
        Player player2 = new Player("Player 2", 50, 5, 5);

        // Using fixed dice rolls
        IDice fixedDice = new FixedDice(5, 3, 6, 2);

        Battle battle = new Battle(player1, player2, fixedDice);


        battle.start();
        String output = outputStream.toString();


        assertTrue(output.contains("Winner is Player 1"));
        assertFalse(player2.isAlive());
        assertTrue(player1.isAlive());
        assertEquals(0, player2.getHealth());
        assertTrue(player1.getHealth() > 0);
    }

    @Test
    public void testBattleStart_Player2Wins() {
        //if Player 2 wins a battle scenario
        Player player1 = new Player("Player 1", 50, 5, 5);
        Player player2 = new Player("Player 2", 100, 5, 10);

        IDice fixedDice = new FixedDice(5, 3, 6, 2);

        Battle battle = new Battle(player1, player2, fixedDice);


        battle.start();
        String output = outputStream.toString();

        assertTrue(output.contains("Winner is Player 2"));
        assertFalse(player1.isAlive());
        assertTrue(player2.isAlive());
        assertEquals(0, player1.getHealth());
        assertTrue(player2.getHealth() > 0);
    }
}
