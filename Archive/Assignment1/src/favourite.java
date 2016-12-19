/*
 * @Author Anish Katariya
 * This program creates a data type for storing favourite co-ordinates
 */

public class favourite {
	private double real;
	private double imaginary;
	private String favouriteNumber;
	double reMax;
	double reMin;
	double imMax;
	double imMin;
	private JSPanel panel;
	//Initializing values 
	public favourite(double real, double imaginary, JSPanel panel) {
		this.real = real;
		this.imaginary = imaginary;
		reMax = 2;
		reMin = -2;
		imMax = 1.6;
		imMin = -1.6;
		this.panel = panel;
		this.favouriteNumber = String.format("%.2f  + %.2fi", real * (reMax - reMin) / panel.getWidth(),
				imaginary * (imMax - imMin) / panel.getHeight());
	}
	//getter method for imaginary part
	public double getImaginary() {
		return imaginary;
	}
	//getter method for real part
	public double getReal() {
		return real;
	}
	//return the string of the favourite number
	public String getFavouriteNumber() {
		return favouriteNumber;
	}
}