package cachesimulation;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Cache {

    protected int block_size = 0;
    protected int cache_array_lines = 0;
    protected long my_cache_array[];
    protected LinkedList previousAddresses;
    public Integer hits = 0;
    public Integer Comp_Miss = 0;
    public Integer Conf_Miss = 0;
    public Integer Capac_Miss = 0;
    public Integer batch = 1000;

    public Cache(Integer block, Integer read_in_cache_size, Integer batch_value) {
        block_size = block;
        cache_array_lines = (read_in_cache_size * 1024) / block_size;
        my_cache_array = new long[cache_array_lines];
        batch = batch_value;
        for (int i = 0; i < my_cache_array.length; ++i)
            my_cache_array[i] = -1;
        previousAddresses = new LinkedList();
    }

    public abstract ArrayList calculate(LinkedList addresses);

	/*
     * public static void main1(String args[]) {
	 * 
	 * try {
	 * 
	 * 
	 * case 2:
	 * 
	 * // create a cache array int my_cache_array2[] = new int[block_size /
	 * deg_assoc]; // for rows (keep track for hits, so that line no. doesn't //
	 * exceed)
	 * 
	 * System.out.print("\nBefore CPU requests...");
	 * System.out.println(" blocks contents\n");
	 * 
	 * for (int i = 0; i < block_size / deg_assoc; i++) { my_cache_array2[i] =
	 * 0; System.out.print("[" + i + "]:" + my_cache_array2[i] + " "); }
	 * 
	 * System.out.print("\n");
	 * 
	 * int cache_location_number2[] = new int[block_size]; for (int k = 0; k <
	 * block_size; k++) { // loop with all // addresses
	 * cache_location_number2[k] = k; }
	 * 
	 * System.out.println(
	 * "\n\nCPU Address[hexadecimal/decimal]\tBlock Number\tSet Number\tCache Location"
	 * );
	 * 
	 * CompMiss = 0; ConfMiss = 0; CapaMiss = 0;
	 * 
	 * for (int j = 0; j < n; j++) { // loop with all addresses
	 * 
	 * block_num[j] = CPU_address_dec[j] / block_size; // floor // value
	 * set_no[j] = block_num[j] % deg_assoc; cache_loc[j] = block_num[j] %
	 * (block_size / deg_assoc);
	 * 
	 * System.out.println("\t" + CPU_address_hex[j] + "/" + CPU_address_dec[j] +
	 * "\t\t\t\t\t" + block_num[j] + "\t\t\t\t" + set_no[j] + "\t\t\t" +
	 * (set_no[j] + "" + cache_loc[j]));
	 * 
	 * if (my_cache_array2[cache_loc[j]] == 0) { my_cache_array2[cache_loc[j]] =
	 * block_num[j]; CompMiss++; } else { hits++; // if
	 * (my_cache_array[cache_loc[j]]= block_num[j]) }
	 * 
	 * // confMiss => if earlier block no was replaced and is // requested again
	 * ConfMiss = 0; // conflict for (int l = j; l > 0; l--) { // loop with all
	 * addresses if (block_num[j] == block_num[l]) { ConfMiss++; } }
	 * 
	 * // capaMiss => cache_lines; CapaMiss = 0; if (CompMiss > block_size) {
	 * CapaMiss++; } } // read CPU addresses end
	 * 
	 * System.out.println("\nTotal hits:" + hits + ", Compulsory Miss:" +
	 * CompMiss + ", Conflict Miss:" + ConfMiss + ", Capacity Miss:" + CapaMiss
	 * + ", Total Miss:" + (CompMiss + ConfMiss + CapaMiss));
	 * 
	 * System.out.print("\nAfter CPU requests...");
	 * System.out.print("Cache contents (block numbers)..\n");
	 * 
	 * for (int k = 0; k < block_size / deg_assoc; k++) { // check // all //
	 * blocks // 0..15 System.out.print("[" + k + "]:" + my_cache_array2[k] +
	 * "\t"); }
	 * 
	 * System.out.println("\n\nAnother run? 1 for yes, any other number for no:")
	 * ; again = sc.nextInt();
	 * 
	 * System.out.println("You input  " + again + "\n"); if (again == 1) { hits
	 * = 0; // Zero your hit counter before the next run continue; // ??? }
	 * break;
	 * 
	 * default: System.out.println("Invalid Choice!!!."); break; }
	 * 
	 * finished = false; } // main loop
	 * 
	 * } // try catch (IOException e) {
	 * System.out.println("Uh oh, got an IOException error!" + e.getMessage());
	 * }
	 * 
	 * System.out.println("\nSimulator is now ending....\n");
	 * 
	 * } // 'main method' loop
	 */
}
