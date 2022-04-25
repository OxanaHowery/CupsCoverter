package Oxana;

import java.util.LinkedHashMap;
import java.util.Map;

public class Spoons implements Printable {
    final double DELTA = -0.042;   //Observational error
    LinkedHashMap<String,Double> measurements = new LinkedHashMap<>();

    public void fillTheMap(){
        measurements.put("Tablespoon", 1.0);
        measurements.put("Teaspoon", 0.3333);
        measurements.put("3/4 Teaspoon", 0.25);
        measurements.put("1/2 Teaspoon", 0.1666);
        measurements.put("1/4 Teaspoon", 0.0833);
        measurements.put("1/8 Teaspoon", 0.0416);
    }

    public double getNeededAmountDecimal(double initialServings, double initialAmount, double neededServings){
        double conversionFactor = neededServings / initialServings;
        return initialAmount * conversionFactor;
    }

    public StringBuilder getAmountInSpoons(double decimalAmount) {
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
        if(((amount.equals("Tablespoon") || (amount.equals("Teaspoon"))) && counter == 1)){
            result.append(" ").append(counter).append(" ").append(amount).append(",");
        } else if(counter == 1){
            result.append(" ").append(amount).append(",");
        } else if(counter > 1) {
            result.append(" ").append(counter).append(" ").append(amount).append("s").append(",");
        }
        return result;
    }

}
