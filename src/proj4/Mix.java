package proj4;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class Mix {

	private DoubleLinkedList<Character> message;
	private String undoCommands;
	private clipBdLinkedList clipBoards;

	private String userMessage;
	private Scanner scan;

	private JPanel pane = new JPanel();

	public Mix() {
		scan = new Scanner(System.in);
		message = new DoubleLinkedList<Character>();
		clipBoards = new clipBdLinkedList();

		undoCommands = "";
	}

	public static void main(String[] args) {
		Mix mix = new Mix();
		mix.userMessage = args[0];
		System.out.println (mix.userMessage);
		mix.mixture();
	}


	private void mixture() {
		for(int i = 0; i < userMessage.length(); i++)
			message.addToBottom(userMessage.charAt(i));
		do {
			// save state
			DoubleLinkedList<Character> currMessage = message;

			DisplayMessage();

			System.out.println("Command: ");

			String currUndoCommands = undoCommands;

			try {
				String command = scan.next("[Qbrpcxh]");

				switch (command) {
				case "Q":
					save(scan.next());
					System.out.println ("Final mixed up message: \"" + message + "\"");
					System.exit(0);
				case "b":
					insertbefore(scan.next(), scan.nextInt());
					break;
				case "r":
					remove(scan.nextInt(), scan.nextInt());
					break;
				case "c":
					copy(scan.nextInt(), scan.nextInt(), scan.nextInt());
					break;
				case "x":
					cut(scan.nextInt(), scan.nextInt(), scan.nextInt());
					break;
				case "p":
					paste(scan.nextInt(), scan.nextInt());
					break;
				case "h":
					helpPage();
					break;

					// all the rest of the commands have not been done
                    // No "real" error checking has been done.
				}
				scan.nextLine();   // should flush the buffer
				System.out.println("For demonstration purposes only:\n" + undoCommands);
			}
			catch (Exception e ) {
				System.out.println ("Error on input, previous state restored.");
				scan = new Scanner(System.in);  // should completely flush the buffer

				// restore state;
				undoCommands = currUndoCommands;
				message = currMessage ;
			}

		} while (true);
	}

	/******************************************************************
	 * @param start An index of the position to start deleting at
	 * @param stop An index of the position to stop deleting at
	 * A method to remove part of the message from a starting index
	 * to an stop index
	 *****************************************************************/
	private void remove(int start, int stop) {
		char remove;
		if (start < 0 || start > stop
				|| stop > message.getListLength()
				|| start > message.getListLength())
			JOptionPane.showMessageDialog(pane, "Invalid command");
		else
			for(int i = stop; i >= start; i--) {
				remove = message.remove(i);
				setUndoCommands("a " + remove + " " + i);
			}

	}

	private void setUndoCommands(String command){
		undoCommands = undoCommands + command + "\n";
	}

	private void cut(int start, int stop, int clipNum) {

	}

	private void copy(int start, int stop, int clipNum) {
		if (start < 0 || start > stop
				|| stop > message.getListLength()
				|| start > message.getListLength()) {
			JOptionPane.showMessageDialog(pane, "Invalid command");
		} else {
			DoubleLinkedList temp = new DoubleLinkedList();
			String s = message.getSection(start, stop);
			char[] c = s.toCharArray();
			for (int i = 0; i < c.length; i++)
				temp.addToBottom(c[i]);

			clipBoards.addClipBoard(clipNum, temp.getTop());
		}
	}

	private void paste( int index, int clipNum) {

	}
         
	private void insertbefore(String token, int index) {
		char c[] = token.toCharArray();
		if (index < 0 || index > message.getListLength()) {
			JOptionPane.showMessageDialog(pane, "Invalid command");
		} else {
			for (int i = c.length - 1; i >= 0; i--) {
				message.insertBefore(c[i], index);
				setUndoCommands("r " + c[i] + " " + index);
			}
		}
	}




	private void DisplayMessage() {
		System.out.print ("Message:\n");
		userMessage = message.toString();

		for (int i = 0; i < userMessage.length(); i++) 
			System.out.format ("%3d", i);
		System.out.format ("\n");
		for (char c : userMessage.toCharArray()) 
			System.out.format("%3c",c);
		System.out.format ("\n");

		System.out.println(message.toBackString());
	}

	public void save(String filename) {

		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));

		} catch (IOException e) {
			e.printStackTrace();
		}

		out.println(undoCommands);
		out.close();
	}

	private void helpPage() {
		System.out.println("Commands:");
		System.out.println("\tQ filename	means, quit! " + " save to filename" );			
		System.out.println("\t  ~ is used for a space character" );		
		System.out.println("\t .... etc" );		
		System.out.println("\th\tmeans to show this help page");
	}
}
