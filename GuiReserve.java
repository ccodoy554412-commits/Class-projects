package cce103;

import javax.swing.*;

public class GuiReserve {
    public static void main(String[]args){

        int[][] hotel = new int[7][5];

        hotel[1][2] = 1;
        hotel[0][1] = 1;
        hotel[0][4] = 1;

        int choice;

        do {
            String menu = "=== HOTEL RESERVATION SYSTEM ===\n\n" +
                         "1. View Rooms\n" +
                         "2. Check In\n" +
                         "3. Check Out\n" +
                         "4. Exit\n\n" +
                         "Enter your choice (1-4):";

            String choiceStr = JOptionPane.showInputDialog(null, menu, "Hotel Reservation", JOptionPane.QUESTION_MESSAGE);

            if (choiceStr == null) {
                choice = 4; // Exit if cancelled
            } else {
                try {
                    choice = Integer.parseInt(choiceStr);
                } catch (NumberFormatException e) {
                    choice = 0; // Invalid
                }
            }

            switch(choice){
                case 1:
                    // View Rooms
                    StringBuilder roomDisplay = new StringBuilder("Room Status:\n\n");
                    for(int floor = 6; floor >= 0; floor--) {  // Display from Floor 7 down to 1
                        roomDisplay.append("Floor ").append(floor + 1).append(": ");
                        for(int room = 0; room < 5; room++) {
                            roomDisplay.append("[").append(hotel[floor][room]).append("]");
                        }
                        roomDisplay.append("\n");
                    }
                    JOptionPane.showMessageDialog(null, roomDisplay.toString(), "Room Status", JOptionPane.INFORMATION_MESSAGE);
                    break;

                case 2:
                    // Check In
                    String floorStr = JOptionPane.showInputDialog(null, "Enter floor (1-7):", "Check In", JOptionPane.QUESTION_MESSAGE);
                    if (floorStr == null) break;
                    String roomStr = JOptionPane.showInputDialog(null, "Enter room number (1-5):", "Check In", JOptionPane.QUESTION_MESSAGE);
                    if (roomStr == null) break;

                    try {
                        int floor = Integer.parseInt(floorStr);
                        int room = Integer.parseInt(roomStr);

                        if(floor < 1 || floor > 7 || room < 1 || room > 5) {
                            JOptionPane.showMessageDialog(null, "Invalid floor or room number!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else if(hotel[floor-1][room-1] == 0) {
                            int confirm = JOptionPane.showConfirmDialog(null,
                                "Confirm check-in for Floor " + floor + ", Room " + room + "?",
                                "Confirm Check-In", JOptionPane.YES_NO_OPTION);
                            if (confirm == JOptionPane.YES_OPTION) {
                                hotel[floor-1][room-1] = 1;
                                JOptionPane.showMessageDialog(null, "Check-in successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Room already occupied!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid input! Please enter numbers only.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case 3:
                    // Check Out
                    floorStr = JOptionPane.showInputDialog(null, "Enter floor (1-7):", "Check Out", JOptionPane.QUESTION_MESSAGE);
                    if (floorStr == null) break;
                    roomStr = JOptionPane.showInputDialog(null, "Enter room number (1-5):", "Check Out", JOptionPane.QUESTION_MESSAGE);
                    if (roomStr == null) break;

                    try {
                        int floor = Integer.parseInt(floorStr);
                        int room = Integer.parseInt(roomStr);

                        if(floor < 1 || floor > 7 || room < 1 || room > 5) {
                            JOptionPane.showMessageDialog(null, "Invalid floor or room number!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else if(hotel[floor-1][room-1] == 1) {
                            int confirm = JOptionPane.showConfirmDialog(null,
                                "Confirm check-out for Floor " + floor + ", Room " + room + "?",
                                "Confirm Check-Out", JOptionPane.YES_NO_OPTION);
                            if (confirm == JOptionPane.YES_OPTION) {
                                hotel[floor-1][room-1] = 0;
                                JOptionPane.showMessageDialog(null, "Check-out successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Room already empty!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid input! Please enter numbers only.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case 4:
                    JOptionPane.showMessageDialog(null, "Exiting...", "Exit", JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }


        } while (choice !=4);
    }
}