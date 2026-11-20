class Vehicle {
 protected final String regNo;
 Vehicle(String regNo){this.regNo=regNo;System.out.println("Vehicle constructor");}
 double rate(){return 0;}
}
class Car extends Vehicle {
 Car(String regNo){super(regNo);System.out.println("Car constructor");}
 @Override double rate(){return 30;}
}
class ElectricCar extends Car {
 ElectricCar(String regNo){super(regNo);System.out.println("ElectricCar constructor");}
}
public class Task06 {
 public static void main(String[] args){
  ElectricCar ev=new ElectricCar("TN03C3");
  System.out.println("Rate: Rs "+ev.rate());
 }
}