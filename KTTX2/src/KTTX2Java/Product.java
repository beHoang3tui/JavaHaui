package KTTX2Java;

import java.util.Scanner;

public class Product {
	private String product_id ;
	private String product_name ;
	private double product_price ;
	private int product_total ;
	
	public Product() {
		super();
	}

	public Product(String product_id, String product_name, double product_price, int product_total) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_total = product_total;
	}
	
	public void Input() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Input product_id: ");
		product_id = sc.nextLine();
		System.out.print("Input product_name: ");
		product_name = sc.nextLine();
		System.out.print("Input product_price: ");
		product_price = sc.nextDouble();
		System.out.print("Input product_total: ");
		product_total = sc.nextInt();
	}
	
	public void Output() {
        System.out.printf("%-10s %-20s %-10f %-10d ", product_id, product_name, product_price, product_total);
	}
	
	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public double getProduct_price() {
		return product_price;
	}

	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}

	public int getProduct_total() {
		return product_total;
	}

	public void setProduct_total(int product_total) {
		this.product_total = product_total;
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", product_name=" + product_name + ", product_price="
				+ product_price + ", product_total=" + product_total + "]";
	}
	
	
	
}
