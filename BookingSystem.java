//Collected all of my imports needed in my program which includes reading from the scanner and file as well as writing to the file as well as the Array list
import java.io.FileNotFoundException;fff
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingSystem {
	static Scanner console = new Scanner(System.in);
	static ArrayList<Room> roomInput = new ArrayList<Room>();

	public static void main(String[] args) throws FileNotFoundException {
		loadRooms();
		String choice = "";
		// I used a do while statement which allowed the menu to appear again after all the contents had passed on the choices. Using Q on the menu simply stopped the program
		do {
			System.out.println("--Room Booking System--\n\n");
			System.out.println("-- Main Menu--");
			System.out.println("1-Reserve Room");
			System.out.println("2-Cancel Room");
			System.out.println("3-View Room Reservations");
			System.out.println("Q-Quit");
			System.out.print("Choice: ");

			choice = console.next();

			switch (choice) {
			case "1":
				reserveRoom();
				break;
			case "2":
				cancelRoom();

				break;
			case "3":
				viewReservations();
				break;
					}
		} while (!choice.equalsIgnoreCase("Q"));
		System.exit(0);
	}

	public static void reserveRoom() throws FileNotFoundException {
		// Initialised my boolean variables for the balcony and lounge 
				boolean withBalcony = false;
		boolean withLounge = false;
		System.out.println("What type of room would you like, Single, Double or Suite?: ");
		String roomChoice = console.next();
 
		//Validation where it would repeat the question until Single, Double or Suite is entered in any case
		while (!roomChoice.equalsIgnoreCase("Single") && !roomChoice.equalsIgnoreCase("Double")
				&& !roomChoice.equalsIgnoreCase("Suite")) {
			System.out.println("Invalid input, Try again:");
			roomChoice = console.next();
		}

		System.out.println("Room with Balcony? (Y / N): ");
		String balconyChoice = console.next();
//More Validation where it will keep asking the question until Y or N is entered which is then converted to a boolean value.
		while ((!balconyChoice.equalsIgnoreCase("Y") && (!balconyChoice.equalsIgnoreCase("N")))) {
			System.out.println("Invalid input, try again:");
			balconyChoice = console.next();
		}

		if (balconyChoice.equalsIgnoreCase("Y")) {
			withBalcony = true;
		} else if (balconyChoice.equalsIgnoreCase("N")) {
			withBalcony = false;
		}
		System.out.println("Room with Lounge? (Y / N): ");
		String loungeChoice = console.next();
		while ((!loungeChoice.equalsIgnoreCase("Y") && (!loungeChoice.equalsIgnoreCase("N")))) {
			System.out.println("Invalid input, try again:");
			loungeChoice = console.next();
		}

		if (loungeChoice.equalsIgnoreCase("Y")) {
			withLounge = true;
		} else if (loungeChoice.equalsIgnoreCase("N")) {
			withLounge = false;
		}
//I made a budget counter where the program would display every room under the price that the user entered. It loops if they enter a negative value
		System.out.print("What is your maximum budget?:\n£");
		float maxBudget = console.nextFloat();
		while (maxBudget < 0) {
			System.out.println("Invalid budget, Try again:");
			maxBudget = console.nextFloat();
		}
//Using a counter it goes through each section of the file displaying only the rooms which match the criteria entered by the user
		for (int c = 0; c < roomInput.size(); c++) {
			if (roomInput.get(c).getRoomType().equalsIgnoreCase(roomChoice)
					&& (maxBudget >= roomInput.get(c).getRoomPrice() && (roomInput.get(c).isHasBalcony() == withBalcony)
							&& (roomInput.get(c).isHasLounge() == withLounge)
							&& (roomInput.get(c).getReservation().equals("unreserved")))) {
				System.out.println(roomInput.get(c).toString());

			}

		}

		System.out.println("\n Please enter the room number which you would like to book:");
		String roomBook = console.next();
//This checks that the room number entered by the user is checked with the rooms available to book
		for (int n = 0; n < roomInput.size(); n++) {
			roomInput.get(n).getRoomNum().equals(roomBook);

		}
//This validates and confirms that the user has entered the right email, if not process will be repeated
		System.out.println("Please enter the email you would like to book with: ");
		String email = console.next();
		System.out.println("Please confirm your email: ");
		String emailComfirmation = console.next();

		while (!email.equalsIgnoreCase(emailComfirmation)) {
			System.out.println("Invalid, re-enter email:");
			email = console.next();
			System.out.println("re-enter comfirmation email:");
			emailComfirmation = console.next();
		}
		System.out.println("Room successfully booked");

//If the room number is confirmed and the email's match then the customer email is written to the reservation section of the file and the room is successfully booked		
		for (int i = 0; i < roomInput.size(); i++) {

			if (roomInput.get(i).getRoomNum().equals(roomBook)) {
				roomInput.get(i).setReservation(email);
			}

		}
	printWriter();
	}
	//I made a separate method for the print writer which meant I can call it at any time when writing to the file. (i, or index is used for counting through the arrayList)
public static void printWriter() throws FileNotFoundException{
	PrintWriter writer = new PrintWriter("M:\\data\\rooms2.txt");
	for (int i = 0; i < roomInput.size(); i++){
	writer.println(roomInput.get(i).toFile());
		}
	writer.close();
}
//This method allows the user to cancel any rooms which have previously been booked.
	public static void cancelRoom() throws FileNotFoundException {
				System.out.println("----Room Cancellation----");
		System.out.println("Please enter your email that you booked with:");
		String email = console.next();
		System.out.println("Please confirm your email:");
		String emailComfirmation = console.next();
// Validates the emails entered by the user to ensure that it has been entered correctly.
		while (!email.equalsIgnoreCase(emailComfirmation)) {
			System.out.println("Emails do not match, try again:");
			System.out.print("Email:");
			email = console.next();
			System.out.print("Comfirmation:");
			emailComfirmation = console.next();
		}
//This gets all the rooms from the arrayList / file with the users email and displays it to them
		for (int i = 0; i < roomInput.size(); i++) {

			if (roomInput.get(i).getReservation().equalsIgnoreCase(email)) {
				System.out.println(roomInput.get(i).toString());
			}
		}
		System.out.println("Please enter the room number that you would like to cancel");
		String cancelNum = console.next();
		for (int i = 0; i < roomInput.size(); i++) {
//This now cancels the users booking and sets it back to "unreserved"
			if (roomInput.get(i).getRoomNum().equals(cancelNum)) {
				roomInput.get(i).setReservation("unreserved");
			}
			
			}
		printWriter();
		System.out.println("Room Successfully Cancelled");
	}

	
//This method is loads the data from the file into arrayList
	public static void loadRooms() throws FileNotFoundException {
//Where the FileReader is getting the data from
		FileReader file = new FileReader("M:\\data\\rooms2.txt");
		Scanner read = new Scanner(file);
//While loop which collects the data in a 2d arrayList by detecting while space below the data
		while (read.hasNext()) {
			String roomNo = read.next();
			String roomType = read.next();
			float roomPrice = read.nextFloat();
			boolean hasBalcony = read.nextBoolean();
			boolean hasLounge = read.nextBoolean();
			String reservation = read.next();

			roomInput.add(new Room(roomNo, roomType, roomPrice, hasBalcony, hasLounge, reservation));

		}
		read.close();
	}

	public static void viewReservations() throws FileNotFoundException {
		//Loops through and displays all the rooms without the "unreserved" reservation field meaning they are reserved
		System.out.printf("\n----Booked Rooms----\n \n");
		for (int i = 0; i < roomInput.size(); i++) {
			if (!roomInput.get(i).getReservation().equals("unreserved")) {
				System.out.println(roomInput.get(i).toString());
			}
		}
		//Loops through all the rooms and looks for "unreserved" in the reserved field meaning the room is free to book
		System.out.printf("\n----Free Rooms----\n \n");
		for (int j = 0; j < roomInput.size(); j++) {

			if (roomInput.get(j).getReservation().equals("unreserved")) {
				System.out.println(roomInput.get(j).toString());

			}

		}
	}
}
