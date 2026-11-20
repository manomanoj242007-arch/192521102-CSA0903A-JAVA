class Vehicle {
 private final String regNo;
 Vehicle(String regNo){this.regNo=regNo;}
 double fee(int minutes){return ((minutes+59)/60)*20.0;}
 @Override public String toString(){return "Vehicle["+regNo+"]";}
 @Override public boolean equals(Object o){return o instanceof Vehicle&&regNo.equals(((Vehicle)o).regNo);}
 @Override public int hashCode(){return regNo.hashCode();}
}
class Car extends Vehicle {
 Car(String r){super(r);}
 @Override double fee(int minutes){return ((minutes+59)/60)*30.0;}
}
public class Task07 {
 public static void main(String[] args){
  Vehicle a=new Car("TN02B2"),b=new Car("TN02B2");
  System.out.println(a.fee(90));System.out.println(a);
  System.out.println("Equal: "+a.equals(b));
  System.out.println("Same hash: "+(a.hashCode()==b.hashCode()));
 }
}