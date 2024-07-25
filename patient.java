import java.util.Arrays;
import java.util.Random;
public class patient {
    private int patient_Identifier;
    private Observation[] observationList;

    // Constructor
    public patient(int patientIdentifier) {
        patient_Identifier = patientIdentifier;
        observationList = new Observation[4];
    }
    
    public static patient generatePatient(int patientIdentifier) {
        return new patient(patientIdentifier);
    }

    // Get method for patientIdentifier
    public int getPatientIdentifier() {
        return patient_Identifier;
    }

    // Get method for observationList
    public Observation[] getObservationList() {
        return observationList;
    }

    // Method to add a new Observation to the array
    public void addObservation(Observation newObservation) {
        for (int i = 0; i < observationList.length; i++) {
            //throws a error message if more observations are attempting to be added than spots in the array
            if (i < observationList.length && observationList[i] == null) {
                observationList[i] = newObservation;
                return;
            }
        }
        System.out.println("Cannot add observation. Observation list is full.");
    }

    //toString method for the patient class
    public String toString(){
        return String.format("Patient ID %d,Observations %s", patient_Identifier, Arrays.toString(getObservationList()));
    }
    //updates the observations in a patients observation list. Only for observations that are already in the list
    public void updateData(Random random){
        for(int i = 0; i < observationList.length; i++){
            if(observationList[i] != null){
                observationList[i].sample(random);
            }
        }
    }
    //generates alerts for dangerous observations in the patient's observation array
    public void generateAlerts(queue alertQueue, int currentTime, patient currentPatient){
        for(int z = 0; z < observationList.length; z++){
            if(observationList[z] != null && observationList[z].dangerous()){
                alert newAlert = new alert(currentPatient, observationList[z], currentTime);
                alertQueue.enqueue(newAlert);
            }
        }
    }
}
