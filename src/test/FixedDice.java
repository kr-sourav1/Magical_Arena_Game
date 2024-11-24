package test;

import magicalarena.IDice;

public class FixedDice implements IDice {
    // Class for deterministic dice rolls for testing purposes
    private final int[] rolls;
    private int index;

    // Constructor to initialize the predefined rolls
    public FixedDice(int... rolls) {
        this.rolls = rolls;
        this.index = 0;
    }

    @Override
    public int roll() {
        int roll = rolls[index % rolls.length]; // Cycle through rolls if index exceeds length
        index++;
        return roll;
    }
}

