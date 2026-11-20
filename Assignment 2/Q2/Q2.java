import java.util.*;
/* Analysis: ArrayList get(k) O(1), front/middle insert O(n); LinkedList get(k) O(n), end insert O(1); ArrayDeque end operations amortized O(1), no indexed access. ArrayList suits random-access playlist; ArrayDeque suits Up Next. */
public class Q2 {
 static volatile long sink;static long ms(Runnable r){long t=System.nanoTime();r.run();return (System.nanoTime()-t)/1000000;}
 static void test(int n){ArrayList<Integer>a=new ArrayList<>();LinkedList<Integer>l=new LinkedList<>();ArrayDeque<Integer>d=new ArrayDeque<>();for(int i=0;i<n;i++){a.add(i);l.add(i);d.add(i);}int k=Math.min(n,1000);
 long aa=ms(()->{for(int i=0;i<k;i++)a.add(i);}),la=ms(()->{for(int i=0;i<k;i++)l.add(i);}),da=ms(()->{for(int i=0;i<k;i++)d.addLast(i);});
 long af=ms(()->{for(int i=0;i<k;i++)a.add(0,-i);}),lf=ms(()->{for(int i=0;i<k;i++)l.add(0,-i);}),df=ms(()->{for(int i=0;i<k;i++)d.addFirst(-i);});
 long ag=ms(()->{for(int i=0;i<k;i++)sink+=a.get(a.size()/2);}),lg=ms(()->{for(int i=0;i<k;i++)sink+=l.get(l.size()/2);});
 System.out.println("N="+n+" append A/L/D="+aa+"/"+la+"/"+da+"ms");System.out.println("front insert A/L/D="+af+"/"+lf+"/"+df+"ms; middle get A/L="+ag+"/"+lg+"ms");}
 public static void main(String[]x){test(1000);test(5000);test(10000);ArrayDeque<String>q=new ArrayDeque<>();q.addFirst("Urgent song");System.out.println("Up Next: "+q.removeFirst());}
}