package KTTX2Java;

import java.io.Serializable;
import java.util.Scanner;

public class Housing extends Product implements Serializable{
	private String Featured ;
	private double Area;
	
	
	public Housing() {
		super();
	}

	public Housing(String product_id, String product_name, double product_price, int product_total ,String featured, double area) {
		super(product_id, product_name,product_price, product_total);
		Featured = featured;
		Area = area;
	}
	
	public void Input() {
		super.Input();
		Scanner sc = new Scanner(System.in);
		System.out.print("Input Featured : ");
		Featured = sc.nextLine();
		System.out.print("Input Area : ");
		Area = sc.nextDouble();
	}
	
	public void Output() {
		super.Output();
        System.out.printf("%-15s %-15f \n", Featured, Area);
	}
	
	public String getFeatured() {
		return Featured;
	}

	public void setFeatured(String featured) {
		Featured = featured;
	}

	public double getArea() {
		return Area;
	}

	public void setArea(double area) {
		Area = area;
	}

	@Override
	public String toString() {
		return "Housing [Featured=" + Featured + ", Area=" + Area + ", getProduct_id()=" + getProduct_id()
				+ ", getProduct_name()=" + getProduct_name() + ", getProduct_price()=" + getProduct_price()
				+ ", getProduct_total()=" + getProduct_total() + "]";
	}

	
}
