package KTTX2Java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class RunMain {
	
	static List<Housing> housings = new ArrayList<>();

	public static void main(String[] args) {
		HousingManagerImpl housingImpl = new HousingManagerImpl();

		Scanner sc = new Scanner(System.in);
		
		int choose;
		
		do{
			System.out.println("-----------MENU-----------");
			System.out.println("1. Add Housing!");
			System.out.println("2. Edit Housing!");
			System.out.println("3. Delete Housing!");
			System.out.println("4. Search Housing by Name!");
			System.out.println("5. Sorted Housing by Price!");
			System.out.println("6. Display List Housing!");
			System.out.println("0. Exit");
			System.out.print("Your choose: ");
			choose = sc.nextInt();
			switch (choose) {
			case 1:
				System.out.println("Input Housing need add.");
				Housing iAdd = new Housing();
				iAdd.Input();
				housingImpl.addHousing(iAdd);
				System.out.println("\n");
				break;
			case 2: 
				System.out.println("Input Housing need edit.");
				Housing iEdit = new Housing();
				iEdit.Input();
				housingImpl.editHousing(iEdit);
				System.out.println("\n");
				break;
			case 3: 
				System.out.println("Input Housing need delete.");
				Housing iDel = new Housing();
				iDel.Input();
				housingImpl.editHousing(iDel);
				System.out.println("\n");
				break;	
			case 4: 
				System.out.println("Input Housing Name need search : ");
				String HSname;
				HSname = new Scanner(System.in).nextLine();
				housingImpl.searchHousing(HSname).forEach(System.out::println);
				System.out.println("\n");
				break;
			case 5: 
				System.out.println("Do you want to sort ascending(1) or descending(0) ?");
				int isINC;
				isINC = sc.nextInt();
				if(isINC == 1) {
					housingImpl.sortedHousing(housings, true);
				}
				else {
					housingImpl.sortedHousing(housings, false);
				}
				System.out.println("\nSuccessfully arranged\n");
				break;
			case 6: 
				System.out.println("List Housing");
				System.out.printf("%-10s %-20s %-10s %-10s %-15s %-15s\n","id" , "Name", "Price", 
						"Total" , "Featured" , "Area");
				for (Housing hs : housings) {
					hs.Output();
				}
				System.out.println("\n");
				break;	
			case 0: 
				System.exit(0);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + choose);
			}
		} while (true); 
	}

}
