import java.util.Random;
abstract class Observation {
    final double dangerousProb = 0.05;
    abstract public String type();
    abstract public String data();
    abstract public boolean dangerous();
    abstract public void sample(Random random);
}
