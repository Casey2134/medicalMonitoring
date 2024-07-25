import java.util.Random;
public class nurse {
    final static long seed = 12345;
    public int name;
    private alert currentAlert;
    final double resolutionProb = .70;
    public static queue resolvedAlerts = new queue();
    protected static Random random = new Random(seed);
    public int getNurseName(){
        return name;
    }
    public void setCurrentAlert(alert incomingAlert){
        currentAlert = incomingAlert;
    }
    public alert getCurrentAlert(){
        return currentAlert;
    }
    public nurse(int new_name){
        name = new_name;
    }
    public void resolveAlerts(queue alertQueue, int currentTime){
        if (currentAlert == null){
            currentAlert = alertQueue.dequeue();
        }
        while(currentAlert != null && resolve(currentAlert)){
            currentAlert.endTime = currentTime;
            //add to resolved alerts queue
            resolvedAlerts.enqueue(currentAlert);
            currentAlert = alertQueue.dequeue();
        }
    }

    private boolean resolve(alert currentAlert){
        return random.nextDouble() <= resolutionProb;
    }

}
