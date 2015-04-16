/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cachesimulation;

//C:\\Users\\IP_Addresses.txt

import utils.quitException;

import java.io.*;
import java.util.LinkedList;

public class CacheSimulation {
	static int block_size = 0; // For user to select different block sizes
	static int deg_assoc = 0;
	static int cache_type = 0;
	static int rep_policy = 0;
	static int read_in_cache_size = 0;

	static int fileNo = 0;
	static String filename = "";
	static int batch = 1000;

	public static void main(String[] args) {
		try {
			configMenu();
			while (true) {
				filemenu();
				Cache cache = null;
				if (cache_type == 1 || deg_assoc == 1)
					cache = new directAccessCache(block_size,
							read_in_cache_size, batch);
				else if (cache_type == 2 && deg_assoc == 16)
					cache = new fullyAssociativeCache(block_size,
							read_in_cache_size, rep_policy, batch);
				else
					cache = new associativeCache(block_size,
							read_in_cache_size, deg_assoc, rep_policy, batch);

				LinkedList TotalAddresses = new LinkedList();
				TotalAddresses = getAddressesfromfile();
				cache.calculate(TotalAddresses);

			}

		} catch (IOException ex) {
			System.out.println("\nError while reading input ..... "
					+ ex.toString());
		} catch (quitException ex) {

		} finally {
			System.out.println("\nExiting Cache Simulator ..... \n");
		}

	}

	public static void configMenu() throws IOException, quitException {
		System.out.println("\n--------------------------------------");
		System.out.println("type 'quit' or 'exit' to terminate the program.");
		System.out.println("---------- Configuration Menu ----------");

		while (true) {
			System.out
					.print("Enter the Cache Type '1' for Direct Mapped, '2' for Associative: ");
			cache_type = Integer.parseInt(readConsole());
			if (cache_type == 1 || cache_type == 2)
				break;
			System.out.println("Wrong Input");
		}

		do { // until u correctly input a pow of 2 value

			System.out
					.print("Enter Cache Size in units of K: 1, 2, 4....[e.g. 2 for 2K]: ");
			read_in_cache_size = Integer.parseInt(readConsole());

		} while (!isMulofTwo(read_in_cache_size));

		do {
			System.out.print("Enter Block Size 1,2,4,8,16,..: ");
			block_size = Integer.parseInt(readConsole());
		} while (!isMulofTwo(block_size));

		do {
			System.out
					.print("Enter Degree of Associativity 1,2,4,8 or 16... [1 for direct mapped. 2, 4 , 8 for set associative . 16 for fully associative.]: ");
			deg_assoc = Integer.parseInt(readConsole());
		} while (!isMulofTwo(deg_assoc) || (deg_assoc > 16 && deg_assoc < 1));

		while (true) {
			System.out
					.print("Enter Replacement Policy 0 for random 1 for LRU: ");
			rep_policy = Integer.parseInt(readConsole());
			if (rep_policy == 0 || rep_policy == 1)
				break;
			System.out.println("Wrong Input");
		}
	}

	public static boolean isMulofTwo(int cache_size) {

		double logNbase2 = Math.log(cache_size) / Math.log(2);
		int logNbase2Integer = (int) (Math.floor(logNbase2));

		if (logNbase2 - logNbase2Integer == 0) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean isQuitSignal(String sig) {
		if (sig.compareToIgnoreCase("quit") == 0
				|| sig.compareToIgnoreCase("exit") == 0) {
			return true;
		}
		return false;
	}

	public static String readConsole() throws IOException, quitException {
		BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
		String line = BR.readLine();
		if (isQuitSignal(line)) {
			throw new quitException();
		}
		return line;

	}

	private static void filemenu() throws IOException, quitException {

		while (true) {
			System.out
					.println("\n------------------------------------------------------");
			System.out
					.println("type 'quit' or 'exit' to terminate the program.");
			System.out
					.println("\n---------------- File Selection menu -----------------");
			System.out.println("1- for Hex Value file.");
			System.out.println("2- for IpAddress Log file.");
			System.out
					.println("3- for file containing TimeStamp and IpAddress ( like IP_Addresses.txt ).");
			System.out.println("4- for IpAddress Frequency file");
			System.out.println("5- for IpAddress Time Search file.");
			System.out.println("6- for configuration menu.");
			System.out.print("Enter the Option : ");
			fileNo = Integer.parseInt(readConsole());
			if (fileNo > 0 && fileNo < 6) {
				break;
			}
			configMenu();
			System.out.print("\n\n");
		}

		while (true) {

			System.out.print("Enter the Filename : ");

			filename = readConsole();

			File f = new File(filename);
			if (f.exists())
				break;
			System.out.println("File not found");
		}

		while (true) {
			System.out
					.print("Enter the number of Entries to process at a time : ");
			batch = Integer.parseInt(readConsole());
			if (batch > 0)
				break;
			System.out.println("Not a Valid number . ");
		}

	}

	private static LinkedList getAddressesfromfile()
			throws FileNotFoundException {

		FileProcess fileProcess = new FileProcess(filename);
		switch (fileNo) {
		case 1:
			return fileProcess.getaddressesFromHexFile();

		case 2:
			return fileProcess.getaddressesFromLogFile();

		case 3:
			return fileProcess.getaddressesFromTStampAndIpFormat();
		case 4:
			return fileProcess.getaddressesFromFrequencyFile();

		case 5:
			return fileProcess.getaddressesFromTimeSearchFile();

		}
		return new LinkedList();
	}

}
