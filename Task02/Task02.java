public class Task02 {
 public static void main(String[] args) {
  int[] sizes={3,2,4}; String[][] slots=new String[sizes.length][];
  for(int f=0;f<sizes.length;f++) slots[f]=new String[sizes[f]];
  slots[1][1]="TN01AB1234"; int minutes=125;
  int hours=(minutes+59)/60;
  System.out.println("Floors: "+slots.length);
  System.out.println("Vehicle: "+slots[1][1]);
  System.out.println("Billable hours: "+hours);
  System.out.println("Fee at Rs 30/hour: Rs "+(hours*30.0));
  System.out.println("Incorrect 125/60: "+(125/60));
 }
}