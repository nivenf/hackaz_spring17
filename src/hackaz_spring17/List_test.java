package hackaz_spring17;

import java.util.Scanner;

public class List_test {
	static Scanner keyboard;
	static List_client li;
	
	public static void main(String[] args) {
		keyboard = new Scanner(System.in);
		li = new List_client();
		System.out.println("Current list: " + li.listString());
		test();
		}
	
	public static void test() {
		System.out.println("What would you like to do: add/remove/swap/move");
		String input = keyboard.next();

		if(input.equals("add"))
			add();
		else if(input.equals("remove"))
			remove();
		else if(input.equals("swap"))
			swap();
		else if(input.equals("move"))
			move();
		else
			System.out.println("Usage: Please type a command: add/remove/swap/move");
	
	}
	
	public static void add() {
		System.out.print("Enter what you would like to add: ");
		String in = keyboard.next();
		
		if(li.add(in))
			System.out.println("Current list: \n" + li.listString());
		else
			System.out.println("Couldn't add. Current list: " + li.listString());
		test();
	}
	
	public static void remove() {
		System.out.print("Which index would you like to remove (starting at 0)?");
		int in = Integer.parseInt(keyboard.next());
		
		if(li.removeIndex(in))
			System.out.println("Current list: \n" + li.listString());
		else
			System.out.println("Couldn't remove. Current list: " + li.listString());
		test();
	}
	
	public static void swap() {
		System.out.println("What would you like to swap? Enter two integers");
		int in1 = Integer.parseInt(keyboard.next());
		int in2 = Integer.parseInt(keyboard.next());
		
		if(li.swap(in1, in2))
			System.out.println("Current list: \n" + li.listString());
		else
			System.out.println("Couldn't swap. Current list: " + li.listString());
		test();
	}
	
	public static void move() {
		System.out.println("What would you like to move to top? Enter an integer");
		int in = Integer.parseInt(keyboard.next());
		
		if(li.moveToTop(in))
			System.out.println("Current list: \n" + li.listString());
		else
			System.out.println("Couldn't swap. Current list: " + li.listString());
		test();
	}
}
