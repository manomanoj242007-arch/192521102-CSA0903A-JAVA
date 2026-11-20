import java.util.*;
/* Analysis: HashSet uses hashCode to choose a bucket then equals. Equal objects must share hashCode. Immutable key fields are essential. HashSet average O(1), TreeSet O(log n), HashMap keyed by String O(1) average. */
public class Q3 {
 static class Broken{final String id;Broken(String i){id=i;}public boolean equals(Object o){return o instanceof Broken b&&id.equals(b.id);}}
 static class Student{final String id,name;Student(String i,String n){id=i;name=n;}public boolean equals(Object o){return this==o||(o instanceof Student s&&id.equals(s.id));}public int hashCode(){return Objects.hash(id);}}
 static class Mutable{String id;Mutable(String i){id=i;}public boolean equals(Object o){return o instanceof Mutable m&&id.equals(m.id);}public int hashCode(){return Objects.hash(id);}}
 public static void main(String[]x){Set<Broken>b=new HashSet<>();b.add(new Broken("A"));b.add(new Broken("A"));System.out.println("Broken set size (typical): "+b.size());Set<Student>s=new HashSet<>();s.add(new Student("A","Arun"));s.add(new Student("B","Divya"));s.add(new Student("A","Arun"));System.out.println("Corrected unique students: "+s.size());Mutable m=new Mutable("A");Set<Mutable>z=new HashSet<>();z.add(m);m.id="B";System.out.println("Contains after key mutation: "+z.contains(m));}
}