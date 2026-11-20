interface Chargeable {
 double chargingFee(int kWh);
 default String message(){return "Charging service available";}
 static boolean validEnergy(int kWh){return kWh>=0;}
}
abstract class Vehicle {
 private final String regNo;
 protected Vehicle(String r){regNo=r;}
 public String getRegNo(){return regNo;}
 public abstract double calculateFee(int minutes);
}
class ElectricCar extends Vehicle implements Chargeable {
 ElectricCar(String r){super(r);}
 @Override public double calculateFee(int m){return ((m+59)/60)*30.0;}
 @Override public double chargingFee(int kWh){
  if(!Chargeable.validEnergy(kWh)) throw new IllegalArgumentException("Invalid kWh");
  return kWh*12.0;
 }
}
public class Task09 {
 public static void main(String[] args){
  ElectricCar ev=new ElectricCar("TN03C3");
  System.out.println(ev.getRegNo());System.out.println(ev.calculateFee(61));
  System.out.println(ev.chargingFee(5));System.out.println(ev.message());
 }
}