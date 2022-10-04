import java.util.Arrays;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Polynomial {
	public double [] coefficients;
	public int [] exponents;
	
	public Polynomial() {
		coefficients = new double [1];
		exponents = new int [1];
		coefficients[0]=0;
		exponents[0] = 0;
	}
	
	public Polynomial(double[] inputcoefficient, int[] inputexponent) {
		coefficients = inputcoefficient;
		exponents = inputexponent;
	}
	
	public Polynomial (File inputfile) throws FileNotFoundException{
		Scanner input = new Scanner(inputfile);
		String line = input.nextLine();
		String [] spl = line.split("[+-]");
		
		int [] expoList = new int [spl.length];
		double [] coeffList = new double [spl.length];
		
		if (line.length() == 0) {
			Polynomial result = new Polynomial();
		}
		else {
			for (int i=0; i<spl.length; i++) {
				int findX = spl[i].indexOf("x");
				int index1 = line.indexOf(spl[i]);
				if (findX == -1) {
					expoList[i] = 0;
					if (i==0) {
						//coeffList[i] = Double.parseDouble(line.substring(0, line.indexOf(spl[i]) + spl[i].length()));
						if (line.charAt(0)=='-') coeffList[i] = Double.parseDouble('-' + spl[i]);
						else coeffList[i] = Double.parseDouble(spl[i]);
					}
					else coeffList[i] = Double.parseDouble (line.charAt(line.indexOf(spl[i]) -1) + spl[i]);
				}
				else {
					expoList[i] = Integer.parseInt(spl[i].substring(findX+1, spl[i].length()));
					if (i==0) coeffList[i] = Integer.parseInt(spl[i].substring(0,findX));
					else coeffList[i] = Integer.parseInt((line.charAt(index1-1)) + (spl[i].substring(0, findX)));
				}
			}
			
			Polynomial result = new Polynomial(coeffList, expoList);
		}
	}

	public int getIndex(int [] input, int a) {
		for (int i=0; i<input.length; i++) {
			if (input[i] == a) return i;
		}
		return -1;
	}
	
	
	public Polynomial add(Polynomial addee) {
//		int maxlen = Math.max(coefficients.length, addee.coefficients.length);
//		int minlen = Math.min(coefficients.length, addee.coefficients.length);
//		double [] resultarray = new double [maxlen];
//		for (int i=0; i<minlen; i++) {
//			resultarray[i] = coefficients[i] + addee.coefficients[i];
//		}
//		for (int i=minlen; i<maxlen; i++) {
//			if (maxlen==addee.coefficients.length) {
//				resultarray[i] = addee.coefficients[i];
//			}
//			else {
//				resultarray[i] = coefficients[i];
//			}
//		}
//		Polynomial result = new Polynomial(resultarray);
//		return result;
		int [] copyExpo1 = exponents.clone();
		int [] copyExpo2 = addee.exponents.clone();
		Arrays.sort(copyExpo1);
		Arrays.sort(copyExpo2);
		int lenNewExpo = Math.max(copyExpo1[copyExpo1.length-1], copyExpo2[copyExpo2.length-1])+1;
		
		int [] expoList = new int [lenNewExpo];
		double [] coeffList = new double [lenNewExpo];
		
		for (int i=0; i<lenNewExpo; i++) {
			expoList[i] = i;
			double coe=0;
			int index1 = getIndex(copyExpo1,i);
			int index2 = getIndex(copyExpo2,i);
			if (index1 != -1) coe = coe+coefficients[index1];
			if (index2 != -1) coe = coe+addee.coefficients[index2];
			coeffList[i] = coe;
		}
		
		Polynomial result = new Polynomial(coeffList, expoList);
		return result;
	}
	
	public double evaluate(double inputx) {
		double result=0;
		for (int i=0; i<coefficients.length; i++) {
			result = result + coefficients[i] * Math.pow(inputx, exponents[i]);
		}
		return result;
	}
	
	public boolean hasRoot(double input) {
		return (evaluate(input)==0);
	}
	
	public Polynomial multiply(Polynomial input) {
		int [] copyExpo1 = exponents.clone();
		int [] copyExpo2 = input.exponents.clone();
		Arrays.sort(copyExpo1);
		Arrays.sort(copyExpo2);
		int lenNewExpo = copyExpo1[copyExpo1.length-1]+copyExpo2[copyExpo2.length-1]+1;
		
		int [] expoList = new int [lenNewExpo];
		double [] coeffList = new double [lenNewExpo];
		
		for (int k=0; k<lenNewExpo; k++) {
			expoList[k] = k;
			coeffList[k] = 0;
		}
		
		for (int i=0; i<coefficients.length; i++) {
			for (int j=0; j<input.coefficients.length; j++) {
				int multExpo = exponents[i] + input.exponents[j];
				int index1 = getIndex(expoList, multExpo);
				coeffList[index1] = coeffList[index1] + coefficients[i] * input.coefficients[j];
			}
		}
		
		Polynomial result = new Polynomial(coeffList, expoList);
		return result;
	}
	
	public void saveToFile(String fileName) {
		try {
		      File myObj = new File(fileName + ".txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		     
	    String content = new String("");
	    for (int i=0; i<exponents.length; i++) {
	    	if (exponents[i] == 0) {
	    		content = content.concat(String.valueOf(coefficients[i]));
	    	}
	    	else {
	    		if (String.valueOf(coefficients[i]).indexOf('-')!=-1) {
	    			content = content.concat(String.valueOf(coefficients[i]));
	    		}
	    		else {
	    			content = content.concat("+" + String.valueOf(coefficients[i]));
	    		}
	    		content = content.concat("x" + String.valueOf(exponents[i]));
	    	}
	    }
	    
	    try {
			FileWriter fw=new FileWriter("/Users/liuxinyu/Desktop" + fileName+".txt");    
	        fw.write(content);    
	        fw.close();
	        System.out.println("Successfully wrote to the file.");
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	}
}