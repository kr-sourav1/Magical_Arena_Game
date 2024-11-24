package test;

import magicalarena.dto.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testPlayerInitialization() {
        // Test proper initialization of Player object
        Player player = new Player("Test", 100, 10, 5);
        assertEquals(100, player.getHealth());
        assertEquals(10, player.getStrength());
        assertEquals(5, player.getAttack());
    }

    @Test
    // Test player health drops to zero when damage exceeds health
    public void testTakeDamage() {
        // Test player health drops to zero when damage exceeds health
        Player player = new Player("Test", 100, 10, 5);
        player.takeDamage(101);
        assertEquals(0, player.getHealth());
    }

    @Test
    public void testTakeDamagePositive() {
        // Test player health decreases correctly when damage is less than health
        Player player = new Player("Test", 100, 10, 5);
        player.takeDamage(90);
        assertEquals(10, player.getHealth());
    }

    @Test
    public void testIsAlive() {
        // Test isAlive() returns true for a player with health > 0
        Player player = new Player("Test", 100, 10, 5);
        assertTrue(player.isAlive());
    }

    @Test
    public void testIsNotAlive() {
        // Test isAlive() returns false for a player with health = 0
        Player player = new Player("Test", 0, 10, 5);
        assertFalse(player.isAlive());
    }

}
