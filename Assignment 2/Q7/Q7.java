import java.util.*;
/* Diagnosis: ArrayList front insert/remove shifts O(n) references. Redesign Up Next only with ArrayDeque addFirst/removeFirst amortized O(1); retain ArrayList for playlist get(k) O(1). Both use O(n) memory; deque has no indexed access. Benchmark uses identical 5000 operation pairs at three sizes; repeat and average. */
public class Q7 {
 static volatile int sink;
 static long list(int n,int k){ArrayList<Integer>a=new ArrayList<>();for(int i=0;i<n;i++)a.add(i);long t=System.nanoTime();for(int i=0;i<k;i++){a.add(0,i);sink+=a.remove(0);}return System.nanoTime()-t;}
 static long deque(int n,int k){ArrayDeque<Integer>d=new ArrayDeque<>();for(int i=0;i<n;i++)d.addLast(i);long t=System.nanoTime();for(int i=0;i<k;i++){d.addFirst(i);sink+=d.removeFirst();}return System.nanoTime()-t;}
 public static void main(String[]x){int k=5000;for(int n:new int[]{1000,10000,50000})System.out.printf("N=%d ArrayList=%.3f ms ArrayDeque=%.3f ms%n",n,list(n,k)/1e6,deque(n,k)/1e6);System.out.println("Front operations: O(n) shifts redesigned to amortized O(1).");}
}