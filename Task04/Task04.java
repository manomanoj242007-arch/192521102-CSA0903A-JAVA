class ParkingTicket {
 private final int number; private final String regNo; private final long entryTime;
 ParkingTicket(){this(0,"UNKNOWN",System.currentTimeMillis());}
 ParkingTicket(int number,String regNo){this(number,regNo,System.currentTimeMillis());}
 ParkingTicket(int number,String regNo,long entryTime){
  if(number<0||regNo==null||regNo.isBlank()) throw new IllegalArgumentException("Invalid ticket");
  this.number=number;this.regNo=regNo;this.entryTime=entryTime;
 }
 ParkingTicket(ParkingTicket other){this(other.number,other.regNo,other.entryTime);}
 public String toString(){return "Ticket "+number+" | "+regNo+" | "+entryTime;}
}
public class Task04 {
 public static void main(String[] args){
  ParkingTicket t=new ParkingTicket(101,"TN01AB1234");
  System.out.println(t);System.out.println("Copy: "+new ParkingTicket(t));
 }
}