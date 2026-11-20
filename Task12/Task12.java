interface ParkingPass{boolean isValid();}
class OneDayVisitorPass implements ParkingPass {
 private final boolean paid;OneDayVisitorPass(boolean p){paid=p;}
 public boolean isValid(){return paid;}
}
abstract class NewVehicle{abstract double fee(int hours,int hour);}
class EBike extends NewVehicle{@Override double fee(int h,int hour){return 25.0;}}
class DeliveryVan extends NewVehicle{
 @Override double fee(int h,int hour){double normal=h*30.0;return hour>=9&&hour<17?normal*2:normal;}
}
public class Task12 {
 public static void main(String[] args){
  int[] sizes={20,25,30,35,40};NewVehicle bike=new EBike(),van=new DeliveryVan();
  ParkingPass pass=new OneDayVisitorPass(true);
  System.out.println("Floors: "+sizes.length);
  System.out.println("Slots per floor: "+java.util.Arrays.toString(sizes));
  System.out.println("E-bike flat fee: Rs "+bike.fee(3,11));
  System.out.println("Delivery van peak fee: Rs "+van.fee(2,10));
  System.out.println("One-day visitor pass valid: "+pass.isValid());
 }
}