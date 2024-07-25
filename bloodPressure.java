import java.util.Random;

public class bloodPressure extends Observation {
    private int systolic;
    private int diastolic;
    public bloodPressure (int systolic, int diastolic){
        setSystolic(systolic);
        setDiastolic(diastolic);
    }
    public String type(){
        return "Blood Pressure";
    }
    public String data(){
        String data = toString();
        return data;
    }
    public boolean dangerous(){
        return checkBP();
    }
    public void sample(Random random){
        systolic = (random.nextDouble() < dangerousProb) ? 140 : 110;
        diastolic = (random.nextDouble() < dangerousProb) ? 90 : 70;
    }
    public boolean checkBP(){
        return (systolic > 130 || diastolic > 80);

    }
    public void setDiastolic(int value){
        diastolic = value;
    }
    public void setSystolic(int value){
        systolic = value;
    }
    public int getSystolic(){
        return systolic;
    }
    public int getDiastolic(){
        return diastolic;
    }
    public String toString(){
        return String.format("%d/%d", getSystolic(), getDiastolic() );
    }
}
