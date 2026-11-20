class ParkingSlot {
 private final int number;private String vehicleNo;
 ParkingSlot(int n){if(n<0)throw new IllegalArgumentException("Invalid slot");number=n;}
 public int getNumber(){return number;}
 public String getVehicleNo(){return vehicleNo;}
 public void park(String r){
  if(r==null||r.isBlank())throw new IllegalArgumentException("Registration required");
  if(vehicleNo!=null)throw new IllegalStateException("Occupied");vehicleNo=r;
 }
 public void release(){vehicleNo=null;}
}
public class Task10 {
 public static void main(String[] args){
  ParkingSlot s=new ParkingSlot(5);s.park("TN05XY5555");Integer n=s.getNumber();
  System.out.println("Slot: "+n);System.out.println("Vehicle: "+s.getVehicleNo());
  s.release();System.out.println("After release: "+s.getVehicleNo());
 }
}