import java.util.*;
/* Analysis: HashMap put/get average O(1), unspecified iteration; TreeMap O(log n), sorted iteration; LinkedHashMap average O(1), insertion order. Sorted-array lookup O(log n), insert O(n). */
public class Q4 {
 static long look(Map<String,Integer>m,List<String>k){long t=System.nanoTime(),sum=0;for(String s:k)sum+=m.getOrDefault(s,0);return(System.nanoTime()-t)/1000000;}
 static void test(int n){Map<String,Integer>h=new HashMap<>(),t=new TreeMap<>(),l=new LinkedHashMap<>();for(int i=n-1;i>=0;i--){String k="M"+i;h.put(k,i);t.put(k,i);l.put(k,i);}List<String>keys=new ArrayList<>();for(int i=0;i<100000;i++)keys.add("M"+(i%n));System.out.println("N="+n+" lookup H/T/L ms="+look(h,keys)+"/"+look(t,keys)+"/"+look(l,keys));System.out.println("Sorted first="+t.keySet().iterator().next()+" insertion first="+l.keySet().iterator().next());}
 public static void main(String[]x){test(1000);test(5000);test(10000);}
}