import java.util.Random;

public class resperationRate extends Observation {
    private int resperationRate;
    public resperationRate(int resperationRate){
        setResperationRate(resperationRate);
    }
    public String type(){
        return "Resperation Rate";
    }
    public String data(){
        return toString();
    }
    public boolean dangerous(){
        return dangerousResperationRate();
    }
    public void sample(Random random){
        resperationRate = (random.nextDouble() > dangerousProb) ? 15 : 20;
    }
    public void setResperationRate(int value){
        resperationRate = value;
    }
    
    public boolean dangerousResperationRate(){
        return(resperationRate < 12 || resperationRate > 18);
    }
    public int getResperationRate(){
        return resperationRate;
    }
    public String toString(){
        return String.format("%d Breaths per Miniute", getResperationRate() );
    }


}
