public class queue {
    private queueRecord head = null;
    private queueRecord tail = null;
    private class queueRecord{
        public alert Alert;
        public queueRecord next;
        public queueRecord (alert incomingAlert){
            Alert = incomingAlert;
        }
    }
    public void enqueue(alert Alert){
        if(Alert == null)return;
        queueRecord record = new queueRecord(Alert);
        //has a head and tail of the queue
        //queue record is an alert and the next queue record
        if (tail != null){
        tail.next = record;
        tail = record;
        }
        else{
            head = record;
            tail = record;
        }


    }
    public alert dequeue(){
        if (head != null){
            queueRecord tmp = head;
            head = head.next;
            if (head == null){
                tail = null;
            }
            return tmp.Alert;
        }
        else{
            return null;
        }
    

    }

    public int queueLength(){
        queueRecord tmp = head;
        int lengthCount = 0;
        while (tmp != null){
            tmp = tmp.next;
            lengthCount++;
        }
        return lengthCount;
    }
    public static void unitTests(){
        int successCount = 0;
        int failCount = 0;
        queue testQueue = new queue();
        patient testPatient1 = new patient(0);
        patient testPatient2 = new patient(1);
        patient testPatient3 = new patient(2);
        patient testPatient4 = new patient(3);
        temperature dangerousTemp = new temperature(100, temperature.Units.Farenheit);
        pulse dangerousPulse = new pulse(100);
        bloodPressure dangerousBloodPressure = new bloodPressure(140, 90);
        resperationRate dangerouResperationRate = new resperationRate(20);
        alert testAlert1 = new alert(testPatient1, dangerousTemp,0);
        alert testAlert2 = new alert(testPatient2, dangerousPulse,0);
        alert testAlert3 = new alert(testPatient3, dangerousBloodPressure,0);
        alert testAlert4 = new alert(testPatient4, dangerouResperationRate,0);
        alert[] testAlerts = {testAlert1, testAlert2,testAlert3, testAlert4, null};

        //checks length method without any alerts in the queue
        if(testQueue.queueLength() == 0)successCount++;
        else failCount++;
        //queuing and dequeing with only one alert in the array
        for(int i = 0; i < testAlerts.length; i++){
            testQueue.enqueue(testAlerts[i]);
            if(testQueue.dequeue() == testAlerts[i]){
                successCount++;
            }
            else{
                failCount++;
            }
        }
        //queuing 4 alerts and the dequeing all 4 alerts
        for(int x = 0; x < testAlerts.length; x++){
            testQueue.enqueue(testAlerts[x]);
        }
        if(testQueue.queueLength() == 4){
            successCount++;
        }
        else failCount++;
        for(int y = 0; y < testAlerts.length; y++){
            if(testQueue.dequeue() == testAlerts[y]){
                successCount++;
            }
            else{
                failCount++;
            }
        }
        System.out.println(successCount+ " Successes, " +failCount+" Failures");

    }
}
