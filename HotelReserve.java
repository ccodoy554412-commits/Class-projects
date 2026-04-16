package cCe103;

import java.util.Scanner;

public class HotelReserve {
    public static void main(String[]args){
        Scanner scan = new Scanner(System.in);

        int[][] hotel = new int[7][5];

        hotel[1][2] = 1;
        hotel[0][1] = 1;
        hotel[0][4] = 1;

        int choice;

        do { 
            System.out.println("\n=== HOTEL RESERVATION SYSTEM ===");
            System.out.println("1.View Rooms");
            System.out.println("2.Check In");
            System.out.println("3.Check Out");
            System.out.println("4.Exit");
            System.out.print("Enter choice: ");
            choice = scan.nextInt();

            switch(choice){
                case 1:
                     for(int floor = 6; floor >= 0; floor--) {  // Display from Floor 7 down to 1
                        System.out.print("Floor " + (floor + 1) + ": ");
                        for(int room = 0; room < 5; room++) {
                            System.out.print("[" + hotel[floor][room] + "]");
                        }
                        System.out.println();
                    }
                    break;
                case 2:
                    //check in
                    System.out.print("\nEnter floor (1-7): ");
                    int floor = scan.nextInt();
                    System.out.print("Enter room number (1-5): ");
                    int room = scan.nextInt();

                     if(floor < 1 || floor > 7 || room < 1 || room > 5) {
                        System.out.println("Invalid floor or room number!");
                    } 
                    else if(hotel[floor-1][room-1] == 0) {
                        hotel[floor-1][room-1] = 1;
                        System.out.println("Check-in successful!");
                    } 
                    else {
                        System.out.println("Room already occupied!");
                    }
                    break;
                case 3:
                    System.out.println("Enter floor (1-7): ");
                    floor = scan.nextInt();
                    System.out.println("Enter room number (1-5): ");
                    room = scan.nextInt();

                     if(floor < 1 || floor > 7 || room < 1 || room > 5) {
                        System.out.println("Invalid floor or room number!");
                    }
                    else if(hotel[floor-1][room-1] == 1) {
                        hotel[floor-1][room-1] = 0;
                        System.out.println("Check-out successful!");
                    }
                    else {
                        System.out.println("Room already empty!");
                    }
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            

        } while (choice !=4);

        scan.close();
    } 
}
