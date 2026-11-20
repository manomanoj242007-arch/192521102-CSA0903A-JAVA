class TicketCounter {
 private static int next=1000;
 public static synchronized int issue(){return ++next;}
}
class FacilityUnit {
 private int occupied;
 void enter(){occupied++;}
 int occupancy(){return occupied;}
}
public class Task05 {
 public static void main(String[] args){
  FacilityUnit north=new FacilityUnit(),south=new FacilityUnit();
  north.enter();south.enter();
  System.out.println("Tickets: "+TicketCounter.issue()+", "+TicketCounter.issue());
  System.out.println("North occupancy: "+north.occupancy());
  System.out.println("South occupancy: "+south.occupancy());
 }
}