import java.io.File;
import java.io.FileNotFoundException;

public class Driver { 
 public static void main(String [] args) throws FileNotFoundException { 
  Polynomial p = new Polynomial(); 
  System.out.println(p.evaluate(3)); 
  double [] c1 = {6,4,0,5}; 
  int [] e1 = {0, 2,3,4};
  Polynomial p1 = new Polynomial(c1,e1); 
  double [] c2 = {0,-2,0,3,-9}; 
  int [] e2 = {1,2,5,6,7};
  Polynomial p2 = new Polynomial(c2,e2); 
  Polynomial s = p1.add(p2); 
  
//  System.out.println("s[0]=" + s.coefficients[0]);
//  System.out.println("s[1]=" + s.coefficients[1]);
//  System.out.println("s[2]=" + s.coefficients[2]);
//  System.out.println("s[3]=" + s.coefficients[3]);
//  System.out.println("s[4]=" + s.coefficients[4]);
  
  System.out.println("s(0.1) = " + s.evaluate(0.1)); 
  if(s.hasRoot(1)) 
   System.out.println("1 is a root of s"); 
  else 
   System.out.println("1 is not a root of s"); 
  
  Polynomial m = p1.multiply(p2);
  System.out.println("m(2) = " + m.evaluate(2));
  
  File myObj = new File("/Users/liuxinyu/Desktop/lab2.txt");
  Polynomial t = new Polynomial(myObj);
  for (int i=0; i<t.coefficients.length; i++) System.out.println("t[i]=" + t.coefficients[i]);
  
  //p2.saveToFile("newhihi");
 }
 
}