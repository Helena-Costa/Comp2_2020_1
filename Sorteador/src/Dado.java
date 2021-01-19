import java.util.Random;

public class Dado implements Sorteador{

    Random random = new Random();

    public int sortear(){
        return random.nextInt(6) + 1;
    }

}
