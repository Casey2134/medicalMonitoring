public class alert {
    int patientId;
    String alertDetails;
    int startTime;
    int endTime;
    public alert(patient alert_patient, Observation dangerousObservation, int start_Time){
        patientId = alert_patient.getPatientIdentifier();
        alertDetails = dangerousObservation.toString();
        startTime = start_Time;
        endTime = 0;
    }
    
}
