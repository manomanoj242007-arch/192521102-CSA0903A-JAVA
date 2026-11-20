import java.util.HashMap;
import java.util.Map;
class Ticket {
 final String regNo;final int entry;final double rate;
 Ticket(String r,int e,double rate){regNo=r;entry=e;this.rate=rate;}
}
class ParkingService {
 private final Map<String,Ticket> active=new HashMap<>();
 void enter(String r,int m,double rate){
  if(active.containsKey(r))throw new IllegalArgumentException("Duplicate registration");
  active.put(r,new Ticket(r,m,rate));
 }
 double exit(String r,int m){
  Ticket t=active.remove(r);if(t==null)throw new IllegalArgumentException("No active ticket");
  int duration=Math.max(0,m-t.entry);return ((duration+59)/60)*t.rate;
 }
}
public class Task11 {
 public static void main(String[] args){
  ParkingService p=new ParkingService();p.enter("TN01A1",0,10);p.enter("TN02B2",0,30);
  double total=p.exit("TN01A1",60)+p.exit("TN02B2",120);
  System.out.println("Two-wheeler fee: Rs 10.0");System.out.println("Car fee: Rs 60.0");
  System.out.println("Daily collection: Rs "+total);System.out.println("Active tickets: 0");
 }
}