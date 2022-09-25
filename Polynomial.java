public class Polynomial {
	public double [] coefficients;
	
	public Polynomial() {
		coefficients = new double [1];
		coefficients[0]=0;
	}
	
	public Polynomial(double [] inputcoefficient) {
		coefficients = new double [inputcoefficient.length];
		for (int i=0; i<inputcoefficient.length; i++) {
			coefficients[i] = inputcoefficient[i];
		}
	}
	
	public Polynomial add(Polynomial addee) {
		int maxlen = Math.max(coefficients.length, addee.coefficients.length);
		int minlen = Math.min(coefficients.length, addee.coefficients.length);
		double [] resultarray = new double [maxlen];
		for (int i=0; i<minlen; i++) {
			resultarray[i] = coefficients[i] + addee.coefficients[i];
		}
		for (int i=minlen; i<maxlen; i++) {
			if (maxlen==addee.coefficients.length) {
				resultarray[i] = addee.coefficients[i];
			}
			else {
				resultarray[i] = coefficients[i];
			}
		}
		Polynomial result = new Polynomial(resultarray);
		return result;
	}
	
	public double evaluate(double inputx) {
		double result=0;
		for (int i=0; i<coefficients.length; i++) {
			result = result + coefficients[i] * Math.pow(inputx, i);
		}
		return result;
	}
	
	public boolean hasRoot(double input) {
		return (evaluate(input)==0);
	}
}