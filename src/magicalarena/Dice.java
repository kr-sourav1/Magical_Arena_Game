package magicalarena;

public class Dice implements IDice{
    @Override
    public int roll() {
        return (int) (Math.random() * 6) + 1;
    }
    //Dice Random values
}
