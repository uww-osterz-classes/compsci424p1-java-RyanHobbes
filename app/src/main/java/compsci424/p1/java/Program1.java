/* COMPSCI 424 Program 1
 * Name:
 * 
 * This is a template. Program1.java *must* contain the main class
 * for this program. Otherwise, feel free to edit these files, even
 * these pre-written comments or my provided code. You can add any
 * classes, methods, and data structures that you need to solve the
 * problem and display your solution in the correct format.
 */

package compsci424.p1.java;

import java.util.Scanner;

/**
 * Main class for this program. The required steps have been copied into the
 * main method as comments. Feel free to add more comments to help you
 * understand your code, or for any other reason. Also feel free to edit this
 * comment to be more helpful for you.
 */
public class Program1 {
	// Declare any class/instance variables that you need here.

	/**
	 * @param args
	 *            command-line arguments, which can be ignored
	 */
	public static void main(String[] args) {
		String userInput = "";
		Scanner myscan = new Scanner(System.in);
		String cmdList = "";
		String tmpcmd;
		long time;
		long time2;
		long timeV1;
		long timeV2;
		int userNum = 0;

		// 1. Ask the user to enter commands of the form "create N",
		// "destroy N", or "end", where N is an integer between 0
		// and 15.
		System.out.println("Please enter a command:\n" + "create N\ndestroy N\nend\nN can be 0-15");
		userInput = myscan.next();
		// 2. While the user has not typed "end", continue accepting
		// commands. Add each command to a list of actions to take
		// while you run the simulation.
		while (!userInput.equals("end")) {
			if (userInput.equals("create")) {
				userNum = myscan.nextInt();
				cmdList += userInput + userNum + "\n";
				userInput = myscan.next();
			} else if (userInput.equals("destroy")) {
				userNum = myscan.nextInt();
				cmdList += userInput + userNum + "\n";
				userInput = myscan.next();
			}
		}
		myscan.close();
		// 3. When the user types "end" (or optionally any word that
		// is not "create" or "destroy"), stop accepting commands
		// and complete the following steps.
		//
		// Hint: Steps 2 and 3 refer to the same loop. ;-)

		// 4. Create an object of the Version 1 class and an object of
		// the Version 2 class.
		Version1 Ver1 = new Version1();
		Version2 Ver2 = new Version2();
		// 5. Run the command sequence once with the Version 1 object,
		// calling its showProcessTree method after each command to
		// show the changes in the tree after each command.
		Scanner myscan1 = new Scanner(cmdList);
		while (myscan1.hasNext()) {
			tmpcmd = myscan1.next();
			if (tmpcmd.equals("create")) {
				Ver1.create(myscan1.nextInt());
				Ver1.showProcessInfo();
			} else if (tmpcmd.equals("destroy")) {
				Ver1.destroy(myscan1.nextInt());
				Ver1.showProcessInfo();
			}

		}
		myscan1.close();

		// 6. Repeat step 5, but with the Version 2 object.
		Scanner myscan2 = new Scanner(cmdList);
		while (myscan2.hasNext()) {
			tmpcmd = myscan2.next();
			if (tmpcmd.equals("create")) {
				Ver2.create(myscan2.nextInt());
				Ver2.showProcessInfo();
			} else if (tmpcmd.equals("destroy")) {
				Ver2.destroy(myscan2.nextInt());
				Ver2.showProcessInfo();
			}

		}
		myscan2.close();
		// 7. Store the current system time in a variable
		time = System.currentTimeMillis();
		// ... then run the command sequence 200 times with Version 1.
		for (int i = 0; i < 200; i++) {
			Scanner myscan3 = new Scanner(cmdList);
			while (myscan3.hasNext()) {
				tmpcmd = myscan3.next();
				if (tmpcmd.equals("create")) {
					Ver1.create(myscan3.nextInt());
				} else if (tmpcmd.equals("destroy")) {
					Ver1.destroy(myscan3.nextInt());
				}
			}
			myscan3.close();
		}

		// ... After this, store the new current system time in a
		// second variable. Subtract the start time from the end
		// time to get the Version 1 running time, then display
		// the Version 1 running time.
		time2 = System.currentTimeMillis();
		timeV1 = time2 - time;
		System.out.println("Version 1 run time: " + timeV1);

		// 8. Repeat step 7, but with the Version 2 object.
		time = System.currentTimeMillis();
		// ... then run the command sequence 200 times with Version 1.
		for (int i = 0; i < 200; i++) {
			Scanner myscan4 = new Scanner(cmdList);
			while (myscan4.hasNext()) {
				tmpcmd = myscan4.next();
				if (tmpcmd.equals("create")) {
					Ver2.create(myscan4.nextInt());
				} else if (tmpcmd.equals("destroy")) {
					Ver2.destroy(myscan4.nextInt());
				}
			}
			myscan4.close();
		}
		time2 = System.currentTimeMillis();
		timeV2 = time2 - time;
		System.out.println("Version 2 run time: " + timeV2);
		// This line is here just to test the Gradle build procedure.
		// You can delete it.
		System.out.println("Builds without errors and runs to completion.");
	}
}
