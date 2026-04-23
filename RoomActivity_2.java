package cce103;

import java.util.Scanner;

public class RoomActivity_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		//idk what we were doing here :(

int students = 3;
int subjects = 3;

		double[][] grades = new double[students][subjects];	
		String[] subjectNames = {"Math","Science","English"};

		for(int i = 0;i<grades.length;i++) {
			System.out.println("Enter grades for student " + (i+1) + ":");
			for(int j = 0;j<grades[i].length;j++) {
				System.out.print(subjectNames[j] + ": ");
				grades[i][j] = sc.nextDouble();
			}

		}
				System.out.println("\nStudent\t\tMath\t\tScience\tEnglish");
				for(int i = 0;i<grades.length;i++) {
					System.out.print("Student " + (i+1) + "\t");
					for(int l = 0;l<grades[i].length;l++) {
						System.out.print(grades[i][l] + "\t");
					}
					System.out.println();
				}


			}
		
	
		

		
	}

