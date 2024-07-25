import java.util.Random;
import java.util.Scanner;
public class main{
    public static void main(String args[]){
        unitTests();
        final int maxNurses = 5;
        final int totalIterations = 144;
        final double addObservationProb = 0.5;
        long seed = 12345;
        //25214899796
        //simulation statics
        int maxSolvetime = 0;
        int totalTasks = 0;
        int completedTasks = 0;
        double meanSolvetime = 0;
        double totalSolveTime = 0;
        //alert for statistic analysts
        alert currentAlert = null;
        //detirmines number of actives nurses
        int activeNurses = 0;
        //holds all alerts through the duration of the simulation
        queue alertQueue = new queue();
        //random object for all samples
        Random random = new Random(seed);
        //creates all patient objects
        patient patients[] = factory(100);
        //populates observations for all patient objects
        for (int x = 0; x < patients.length; x++){
            if (coinflip(random, addObservationProb)){
                patients[x].addObservation(new pulse(70));
            }
            if (coinflip(random, addObservationProb)){
                patients[x].addObservation(new temperature(98, temperature.Units.Farenheit));
            }
            if (coinflip(random, addObservationProb)){
                patients[x].addObservation(new bloodPressure(110,70));
            }
            if (coinflip(random, addObservationProb)){
                patients[x].addObservation(new resperationRate(20));
            }
        }
        //nurse array
        nurse[] totalNurses = new nurse[maxNurses];
        for (int i = 0; i < totalNurses.length; i++){
            totalNurses[i] = new nurse(i);
        }
        //simulations
        for (int iterations = 0; iterations < totalIterations; iterations++){
            
            //determines number of active nurses
            if(alertQueue.queueLength() > 4){
                activeNurses = (int)(alertQueue.queueLength() / 3);
                if (activeNurses > maxNurses){
                    activeNurses = maxNurses;
                }
            }
            else if(alertQueue.queueLength() < 4 && alertQueue.queueLength() > 0){
                activeNurses = 1;
            }
            else{
                activeNurses = 0;
            }
            //checks if there are any active nurses
            if(activeNurses != 0)
            {
                for (int x = 0; x < activeNurses; x++){
                    //uses nurse class to resolve alerts
                    totalNurses[x].resolveAlerts(alertQueue, iterations);
                
                }
            }
            //iterates through all patients
            for (int i = 0; i < patients.length; i++){
                //updates all patient observations
                patients[i].updateData(random);
                //generates alerts for all dangerous observations
                patients[i].generateAlerts(alertQueue, iterations, patients[i]);
            }
        }
        //gets statistics from the simulation
        for (int i = 0; i < nurse.resolvedAlerts.queueLength(); i++){
            currentAlert = nurse.resolvedAlerts.dequeue();
            if(currentAlert.endTime - currentAlert.startTime > maxSolvetime){
                maxSolvetime = currentAlert.endTime - currentAlert.startTime;
            }
            totalSolveTime += currentAlert.endTime - currentAlert.startTime;
            completedTasks++;
            totalTasks++;
        }
        for (int i = 0; i < alertQueue.queueLength(); i++){
            alertQueue.dequeue();
            totalTasks++;
        }
        meanSolvetime = totalSolveTime / completedTasks;
        

        System.out.println("Mean solve time: " + meanSolvetime + " Max solve time: " + maxSolvetime + ", Total tasks completed: " + completedTasks + " Total tasks: " + totalTasks);
    }
    //factory function to generate patient objects
    public static patient[] factory(int numberOfPatients){
        patient[] patients = new patient[numberOfPatients];
        for (int i = 0; i < patients.length; i++){
            patients[i] = patient.generatePatient(i);
        }
        return patients;
    }
    public static boolean coinflip(Random random, double addObservationProb){
        return (random.nextDouble()  < addObservationProb);
    }
    public static void unitTests(){
        patient[] testPatArray = factory(10);
        int successCount = 0;
        int failCount = 0;
        if (testPatArray.length == 10){
            successCount++;
        }
        else{
            failCount++;
        }
        for(int i = 0; i < testPatArray.length; i++){
            testPatArray[i].addObservation(new pulse(70));
        }
        for (int i = 0; i < testPatArray.length; i++){
            if (testPatArray[i].toString().equals("Patient ID " + i + ",Observations [70 bpm, null, null, null]")){
            successCount++;

            }
            else{
                failCount++;
            System.out.println("Failed at first observation check");
            } 
        }
        for (int i = 0; i < 4; i++){
            //checks no errors are thrown when the observation list in a patient is full and another obeservation is added
            testPatArray[0].addObservation(new temperature(98, temperature.Units.Farenheit));
            //should print an error message in the terminal
        }
        if(testPatArray[0].toString().equals("Patient ID 0,Observations [70 bpm, 36.7 C 98.0 F, 36.7 C 98.0 F, 36.7 C 98.0 F]")){
            successCount++;
        }
        else{
            failCount++;
        }
        System.out.println("Successes: " + successCount + " Failures: " + failCount);
    }
}
