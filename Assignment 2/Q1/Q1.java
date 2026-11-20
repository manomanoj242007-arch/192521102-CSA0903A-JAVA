import java.util.*;
/* Analysis: String + in a loop creates intermediate immutable strings; StringBuilder/StringBuffer append to mutable buffers. Repeated concatenation can copy O(nL) characters; builder/buffer O(L). Use StringBuilder per ticket and synchronized append/StringBuffer for shared log. Timings are JVM-dependent. */
public class Q1 {
 static volatile int sink;
 static String plus(String[]f){String s="";for(String x:f)s+=x;return s;}
 static String sb(String[]f){StringBuilder b=new StringBuilder();for(String x:f)b.append(x);return b.toString();}
 static String sf(String[]f){StringBuffer b=new StringBuffer();for(String x:f)b.append(x);return b.toString();}
 static long run(int n,int k,int m){String[]f=new String[k];for(int i=0;i<k;i++)f[i]="F"+i;long t=System.nanoTime();for(int j=0;j<n;j++){String s=m==0?plus(f):m==1?sb(f):sf(f);sink+=s.length();}return System.nanoTime()-t;}
 public static void main(String[]x){int[][]w={{1,6},{1000,50},{5000,20}};for(int[]a:w){System.out.println(Arrays.toString(a));for(int m=0;m<3;m++){long t=0;for(int r=0;r<5;r++)t+=run(a[0],a[1],m);System.out.printf("%s %.3f ms avg%n",new String[]{"String +","StringBuilder","StringBuffer"}[m],t/5e6);}}}
}