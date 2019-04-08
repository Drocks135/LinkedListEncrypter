package proj4;
import java.io.*;
import java.util.Scanner;

public class UnMix {
	private DoubleLinkedList<Character> message;

	public UnMix() {
		message = new DoubleLinkedList<Character>();
	}

	public static void main(String[] args) {
	    UnMix v = new UnMix();
		v.unMixture(args[0], args[1]);
	}

	public void processCommand(String command) {
		Scanner scan = new Scanner(command);
		String[] split;

		try {
			switch (command.charAt(0)) {
				case 'a':
					split = command.split("\\s+");
					if(split.length == 2)
						message.insertBefore(command.charAt(2), Integer.parseInt(split[1]));
					else
						message.insertBefore(split[1].charAt(0), Integer.parseInt(split[2]));
					break;
				case 'r':
					split = command.split("\\s+");
					message.remove(Integer.parseInt(split[1]));
					break;
				case 'e':
					message.replace(command.charAt(2), command.charAt(4));
					break;

			}
		} catch (Exception e) {
			System.out.println("Error in command!  Problem!!!! in undo commands");
			System.exit(0);
		}
		finally {
			scan.close();
		}

	}

	private void unMixture(String filename, String userMessage) {
		for(int i = 0; i < userMessage.length(); i++)
			message.addToBottom(userMessage.charAt(i));
		String original = UnMixUsingFile (filename, userMessage);
		System.out.println ("The Original message was: " + original);
	}


	public String UnMixUsingFile(String filename, String userMessage) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (scanner.hasNext()) {
			String command = scanner.nextLine();
			processCommand(command);
		} 
		return message.toString();
	}
}
