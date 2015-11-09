/*********************************************************
 *
 * 08-722 Data Structures for Application Programmers
 * Lab 3 Simple Sorting and Stability
 *
 * Insertion Sort Implementation
 *
 * Andrew ID: mizhang
 * Name:  Mi Zhang
 *
 *********************************************************/
import java.util.*;


public class InsertionSortApp {

	public static void main(String[] args) {
		Employee[] list = new Employee[10];

		// employee data : first name, last name, zip
		list[0] = new Employee("Patty", "Evans", 15213);
		list[1] = new Employee("Doc", "Smith", 15214);
		list[2] = new Employee("Lorraine", "Smith", 15216);
		list[3] = new Employee("Paul", "Smith", 15216);
		list[4] = new Employee("Tom", "Yee", 15216);
		list[5] = new Employee("Sato", "Hashimoto", 15218);
		list[6] = new Employee("Henry", "Stimson", 15215);
		list[7] = new Employee("Jose", "Vela", 15211);
		list[8] = new Employee("Minh", "Vela", 15211);
		list[9] = new Employee("Lucinda", "Craswell", 15210);

		System.out.println("Before Insertion Sorting: ");
		for (Employee e : list) {
			System.out.println(e.toString());
		}
		System.out.println("");

		InsertionSortApp sorter = new InsertionSortApp();
		sorter.insertionSort(list, "last");

		System.out.println("After Insertion Sorting by last name: ");
		for (Employee e : list) {
			System.out.println(e.toString());
		}
		System.out.println("");

		sorter.insertionSort(list, "zip");

		System.out.println("After Insertion Sorting by zip code: ");
		for (Employee e : list) {
			System.out.println(e.toString());
		}

	}

	/**
	 * Sort employees either by last name or zip using Insertion Sort
	 *
	 * @param key key param value should be either "last" or "zip"
	 */
	public void insertionSort(Employee[] list, String key) {
		// TODO implement insertion sort here
		if(key == "last"){
			for (int out=1;out<list.length;out++){
				Employee tmp = list[out];
				int in = out; 
				while(in >0 && tmp.getLastName().compareTo(list[in-1].getLastName())<0){
					list[in] = list[in-1];
					in--;
				}
				list[in]=tmp;
			}
		}
		if(key == "zip"){
			for (int out=1; out<list.length; out++){
				Employee tmp = list[out];
				int in = out;
				while(in> 0 && tmp.getZipCode()<list[in-1].getZipCode()){
					list[in]=list[in-1];
					in--;
				}
				list[in] = tmp;
			}		
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
}