import java.util.Random;

public class temperature extends Observation {
    private double celsiusValue = 0;
    public enum Units {
        Celsius,
        Farenheit
     };
     public String type(){
        return "Temperature";
     }
     public String data(){
        String data = toString();
        return data;
    }
     public boolean dangerous(){
        return fever();
     }
     public void sample(Random random){
        celsiusValue = (random.nextDouble() > dangerousProb) ? 35 : 39;
     }

    public temperature( double value, Units units ) {
        if (units == Units.Celsius) {
            setCelsius(value);
        } else {
            setFarenheit(value);
        }
    }
 
    public void setCelsius( double value ) {
        celsiusValue = value;
    }
 
    public void setFarenheit( double value ) {
        celsiusValue = farenheitToCelsius( value );
    }
 
    public double getCelsius() { return celsiusValue; }
 
    public double getFarenheit() { return celsiusToFarenheit(celsiusValue); }
 
    public String toString() {
        return (String.format("%.1f C %.1f F", getCelsius(), getFarenheit()) );
    }
 
   public boolean equals( Object obj ) {
        if (obj == this) return true;
        if (!(obj instanceof temperature)) return false;
        temperature temp = (temperature) obj;
        return (Math.abs(temp.getCelsius() - getCelsius()) < 0.05);
    }
    // Returns true if this temperature represents a fever
    public boolean fever() {
        return (celsiusValue >= 37.8); 
    }
 
    private double farenheitToCelsius( double fVal ) {
        return ((fVal - 32.0) * 5.0) / 9.0;
    }
 
    private double celsiusToFarenheit( double cVal ) {
        return ( (cVal * 9.0)/5.0 + 32.0 );
    }
}
