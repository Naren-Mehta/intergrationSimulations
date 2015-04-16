/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cachesimulation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 
 * @author CODE
 */
public class FileProcess {

	private String _fFile = null;

	FileProcess(String fFile) {
		_fFile = fFile;

	}

	public final LinkedList getaddressesFromLogFile()
			throws FileNotFoundException {
		// Note that FileReader is used, not File, since File is not Closeable
		LinkedList list = new LinkedList();

		if (_fFile == null) {
			throw new FileNotFoundException("File '" + _fFile
					+ "' not found - Occured during Reading from file ");
		}

		Scanner scanner = new Scanner(new FileReader(_fFile));
		try {
			// first use a Scanner to get each line
			while (scanner.hasNextLine()) {
				ipAddress ip = processLine(scanner.nextLine());
				if (ip != null && !list.contains(ip)) {
					list.addFirst(ip.toDecimal());
				}
			}
		} finally {
			// ensure the underlying stream is always closed
			// this only has any effect if the item passed to the Scanner
			// constructor implements Closeable (which it does in this case).
			scanner.close();
		}
		return list;
	}

	private ipAddress processLine(String nextLine) {
		String ip_address = null;
		String[] parts = null;
		String[] strings = null;
		Date date = null;
		// finding date
		// DateFormat DF = DateFormat.getDateInstance(style)
		strings = nextLine.split(" ");
		try {
			SimpleDateFormat format = new SimpleDateFormat("MMM d hh:mm:ss");
			date = format.parse(strings[0] + " " + strings[1] + " "
					+ strings[2]);
		} catch (ParseException ex) {
			System.out.println("Wrong File Format !!!!!!!! ");
			System.out.println(ex.toString());

		} catch (RuntimeException ex) {
			System.out.println("Wrong File Format !! ");
			throw ex;
		}

		// 'Mail from' tokken checker
		strings = nextLine.split("Mail from");
		if (strings.length > 1) {
			parts = strings[1].trim().split(" ");
			ip_address = parts[0];
			return new ipAddress(ip_address, date);
		}
		// init from token checker
		strings = nextLine.split("init from");
		if (strings.length > 1) {
			parts = strings[1].trim().split(" ");
			ip_address = parts[0];
			return new ipAddress(ip_address, date);
		}
		// simple from token checker
		strings = nextLine.split(" ");
		if (strings.length > 1) {
			for (int i = strings.length - 1; i >= 0; --i) {
				String str = strings[i];
				// try
				// {
				if (str.compareTo("") == 0 || str == null || str.length() < 7) // minimum
																				// length
																				// for
																				// an
																				// ip
					continue;
				str = String
						.copyValueOf(str.toCharArray(), 1, str.length() - 2);
				// }
				/*
				 * catch ( StringIndexOutOfBoundsException ex) {
				 * 
				 * }
				 */
				if (ipAddress.isvalid(str)) {
					ip_address = str;
					return new ipAddress(ip_address, date);
				}
			}
		}
		return null;
	}

	public static int hex2decimal(String s) {

		String digits = "0123456789ABCDEF";
		s = s.toUpperCase();
		int val = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int d = digits.indexOf(c);
			val = 16 * val + d;
		}
		return val;
	}

	public LinkedList getaddressesFromHexFile() throws FileNotFoundException {
		LinkedList list = new LinkedList();

		if (_fFile == null) {
			throw new FileNotFoundException("File '" + _fFile
					+ "' not found - Occured during Reading from file ");
		}

		Scanner scanner = new Scanner(new FileReader(_fFile));
		try {
			// first use a Scanner to get each line
			while (scanner.hasNextLine()) {
				String read = scanner.nextLine();
				String[] tokens = read.split(" ");
				for (String hexValue : tokens) {
					if (hexValue.compareTo("") != 0)
						list.addFirst((long) hex2decimal(hexValue));
				}
			}
		} finally {
			// ensure the underlying stream is always closed
			// this only has any effect if the item passed to the Scanner
			// constructor implements Closeable (which it does in this case).
			scanner.close();
		}
		return list;

	}

	LinkedList getaddressesFromFrequencyFile() throws FileNotFoundException {

		LinkedList list = new LinkedList();

		if (_fFile == null) {
			throw new FileNotFoundException("File '" + _fFile
					+ "' not found - Occured during Reading from file ");
		}

		Scanner scanner = new Scanner(new FileReader(_fFile));

		try {
			// first use a Scanner to get each line
			while (scanner.hasNextLine()) {
				String read = scanner.nextLine();
				int index = read.indexOf("[");
				read = String.copyValueOf(read.toCharArray(), 0, index);
				read = read.trim();

				if (ipAddress.isvalid(read))
					list.addFirst(ipAddress.toNumeric(read));

			}
		} finally {
			// ensure the underlying stream is always closed
			// this only has any effect if the item passed to the Scanner
			// constructor implements Closeable (which it does in this case).
			scanner.close();
		}
		return list;

	}

	LinkedList getaddressesFromTimeSearchFile() throws FileNotFoundException {
		LinkedList list = new LinkedList();

		if (_fFile == null) {
			throw new FileNotFoundException("File '" + _fFile
					+ "' not found - Occured during Reading from file ");
		}

		Scanner scanner = new Scanner(new FileReader(_fFile));
		if (scanner.hasNextLine())
			System.out.println(scanner.nextLine()); // printing time information

		try {
			// first use a Scanner to get each line
			while (scanner.hasNextLine()) {
				String read = scanner.nextLine();
				if (ipAddress.isvalid(read))
					list.addFirst(ipAddress.toNumeric(read));

			}
		} finally {
			// ensure the underlying stream is always closed
			// this only has any effect if the item passed to the Scanner
			// constructor implements Closeable (which it does in this case).
			scanner.close();
		}
		return list;

	}

	LinkedList getaddressesFromTStampAndIpFormat() throws FileNotFoundException {
		LinkedList list = new LinkedList();

		if (_fFile == null) {
			throw new FileNotFoundException("File '" + _fFile
					+ "' not found - Occured during Reading from file ");
		}

		Scanner scanner = new Scanner(new FileReader(_fFile));
		try {
			// first use a Scanner to get each line
			while (scanner.hasNextLine()) {
				String read = scanner.nextLine();
				String[] token = read.split(" ");
				token[1] = token[1].trim();
				if (ipAddress.isvalid(token[3]))
					list.addFirst(ipAddress.toNumeric(token[3]));

			}
		} finally {
			// ensure the underlying stream is always closed
			// this only has any effect if the item passed to the Scanner
			// constructor implements Closeable (which it does in this case).
			scanner.close();
		}
		return list;
	}

}
