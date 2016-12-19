/*
 * @Author Anish Katariya
 * creates a class for complex numbers
 * has methods for squaring the complex number
 * returning the modular square and getter methods for
 * getting imaginary and real numbers
 */

public class ComplexNumbers {
	private double real;
	private double imaginary;

	// Constructor to get values for real and imaginary
	public ComplexNumbers(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}

	// method to square the complex number
	public void square() {
		double tempReal = real;
		real = modularSquare();
		imaginary = 2 * tempReal * imaginary;
	}

	// Returns the modular square of the numbers
	public double modularSquare() {
		return Math.pow(real, 2) - Math.pow(imaginary, 2);
	}

	// Setter method for Real part of the complex number
	public void setReal(double real) {
		this.real=real;
	}

	// Setter method for the imaginary part of the complex number
	public void setImaginary(double imaginary) {
		this.imaginary = imaginary;
	}
	
	// Getter method for Real part of the complex number
	public double getReal() {
		return real;
	}

	// Getter method for the imaginary part of the complex number
	public double getImaginary() {
		return imaginary;
	}

	public void bsOperation() {
		real = Math.pow(real, 2) + Math.pow(imaginary, 2) - 2 * real * imaginary;
	}

	public void subtract(ComplexNumbers number) {
		real -= number.getReal();
		imaginary -= number.getImaginary();
	}

	// Adding complex Numbers
	public void add(ComplexNumbers number) {
		real += number.getReal();
		imaginary += number.getImaginary();
	}

	public void divide(double x, double y) {
		real = real / x;
		imaginary = imaginary / y;
	}
}
