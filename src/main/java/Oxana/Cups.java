package Oxana;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;

public class Cups implements Printable {
    final double DELTA = -0.63;   //Observational error
    LinkedHashMap<String,Double> measurements = new LinkedHashMap<>();

    public void fillTheMap(){
        measurements.put("Cup", 1.0);
        measurements.put("3/4 Cup", 0.75);
        measurements.put("2/3 Cup", 0.666);
        measurements.put("1/2 Cup", 0.5);
        measurements.put("1/3 Cup", 0.33);
        measurements.put("1/4 Cup", 0.25);
        measurements.put("Tablespoon", 0.0625);
    }

    public double getNeededAmountDecimal(double initialServings, double initialAmount, double neededServings){
        double conversionFactor = neededServings / initialServings;
        return initialAmount * conversionFactor;
    }

    public StringBuilder getAmountInCups(double decimalAmount) {
        StringBuilder result = new StringBuilder("You will need");
        fillTheMap();
         for(Map.Entry<String, Double> measurement : measurements.entrySet()) {
             int countTimesSubtracted = 0;
             while(decimalAmount - measurement.getValue() > DELTA) {
                 decimalAmount -= measurement.getValue();
                 countTimesSubtracted ++;
             }
             if(countTimesSubtracted > 0) {
                 result.append(amountToString(countTimesSubtracted, measurement.getKey()));
             }
         }
        result.deleteCharAt(result.length()-1);
        return result;
    }

    @Override
    public StringBuilder amountToString(int counter, String amount){
        StringBuilder result = new StringBuilder();
        if(((amount.equals("Tablespoon") || (amount.equals("Cup"))) && counter == 1)){
            result.append(" ").append(counter).append(" ").append(amount).append(",");
        }else if(counter == 1){
            result.append(" ").append(amount).append(",");
        } else if(counter > 1) {
            result.append(" ").append(counter).append(" ").append(amount).append("s").append(",");
        }
        return result;
    }


}
