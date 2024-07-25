import java.util.Random;

public class pulse extends Observation {
    private int bpm;

    public pulse (int bpm){
        setBeatsPerMinute(bpm);
    }
    public String type(){
        return "Pulse";
    }

    public String data(){
        return toString();
    }
    public boolean dangerous(){
        return unhealthyPulse();
    }
    public void sample(Random random){
        bpm = (random.nextDouble() > dangerousProb) ? 70 : 200;
    }
    private boolean unhealthyPulse(){
        return (bpm > 160 || bpm < 60);
    }
    public void setBeatsPerMinute(int value){
        bpm = value;
    }
    public int getBeatsPerMiniute(){
        return bpm;
    }
    public String toString(){
        return String.format("%d bpm", getBeatsPerMiniute() );
    }
}   
