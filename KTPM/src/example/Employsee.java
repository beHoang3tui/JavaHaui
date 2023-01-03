package example;


/* A factory pays its employers each week on the basis of a data record prepared for each employee.
This record contains Employee Name, Hours Worked, Hourly Pay Rate . 
It is the factory policy to pay time-and-a-half for hours worked beyond 40 in any week.
Taxation is to be calculated at 20% of any wage greater than $200 for a week and nil otherwise.
The program is to output each employees name, gross pay, tax and net pay.*/

/* The following program was written by a programmer */

public class Employsee {
	String name; // tên
	int hoursWorked; // Số giờ làm việc
	float hourlyRate; // số tiền cho mỗi giờ làm việc
	float tax; // thuế 
	float grossPay; // số nhận được chưa trừ thuế
	float netPay; // số tiền thực nhận 
	
	public Employsee(String name, int hoursWorked, float hourlyRate){
		this.name = name;
		this.hoursWorked = hoursWorked;
        this.hourlyRate= hourlyRate;
	}
	
	public String getName(){
		return name;
	}
	
	public void calculatePay(){
		int payableHours;
		
		if( hoursWorked <= 40){
			payableHours = hoursWorked;
		}
		else{
			payableHours = 40+(hoursWorked - 40)* 3/2;
		}
		
		//grossPay = hoursWorked * hourlyRate; 
		grossPay = payableHours * hourlyRate;
		
        if(grossPay >= 200){
        	
        	//tax = grossPay  * 20/100;
        	tax = (grossPay-200)  * 20/100;
        	
        }
		
		netPay = grossPay - tax;
	}// end calculatePay()
	
	public static void main(String[] args) {
		Employsee ps1 = new Employsee("ADAMS", 35, 5);
		Employsee ps2 = new Employsee("BAKER", 40, 5);
		Employsee ps3 = new Employsee("CAIRNS", 44, 5);
		Employsee ps4 = new Employsee("DONALD", 20, 6);
		ps1.calculatePay();
		ps2.calculatePay();
		ps3.calculatePay();
		ps4.calculatePay();
		System.out.println(ps1.grossPay + "-" + ps1.tax + "-" + ps1.netPay);
		System.out.println(ps2.grossPay + "-" + ps2.tax + "-" + ps2.netPay);
		System.out.println(ps3.grossPay + "-" + ps3.tax + "-" + ps3.netPay);
		System.out.println(ps4.grossPay + "-" + ps4.tax + "-" + ps4.netPay);
	}

}

