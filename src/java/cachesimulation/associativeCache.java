/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cachesimulation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

/**
 * @author CODE
 */
public class associativeCache extends Cache {

    int degree_assoc = 0;
    int set_indices = 0;
    int replace_policy;

    public associativeCache(int block, int read_in_cache_size,
                            int degree_association, int replace_policy, int batch) {
        super(block, read_in_cache_size, batch);
        degree_assoc = degree_association;
        this.replace_policy = replace_policy;
        set_indices = cache_array_lines / degree_assoc;

    }

    @Override
    public ArrayList calculate(LinkedList addresses) {

        ArrayList list = new ArrayList();

        int count = 0;
        int lastcountprint = 0;
        System.out.println("******* Starting Simulation *******");

        System.out.println("Configuration : Cache Size = "
                + ((cache_array_lines * block_size) / 1024) + " KB "
                + " Block Size  =  " + block_size + " B "
                + " replacement policy = " + " Associativity = " + degree_assoc
                + "-way Associative"
                + (replace_policy == 0 ? " random " : " LRU ")
                + " Cache Type = " + " Associative" + "\n\n");

        for (Object item : addresses) {
            if ((count % batch) == 0 && count >= batch) {
                System.out.println("After===1--> " + (count)
                        + " Entries/Addresses ==>" + " hits = " + hits
                        + " Compulsory Miss = " + Comp_Miss
                        + " Capacity Miss = " + Capac_Miss
                        + " Conflict Miss = " + Conf_Miss + " Total Miss = "
                        + (int) (Comp_Miss + Capac_Miss + Conf_Miss));

                Output output = new Output();
                output.count = count;
                output.hits = hits;
                output.Comp_Miss = Comp_Miss;
                output.Capac_Miss = Capac_Miss;
                output.Conf_Miss = Conf_Miss;
                output.total = Comp_Miss + Capac_Miss + Conf_Miss;

                list.add(output);
                lastcountprint = count;
            }

            long address = (Long) item;
            long block_num = address / block_size;
            int cache_index = (int) (block_num % set_indices);
            int cache_slot = cache_index * degree_assoc;

            boolean wasInCache = false;

            // remove the block from list and make it most recent
            if (previousAddresses.remove(block_num)) {
                wasInCache = true;
            }
            previousAddresses.addFirst(block_num);

            count++;
            boolean hit = false;
            int freeSlot = -1;
            for (int i = cache_slot; i < cache_slot + degree_assoc; ++i) {
                if (my_cache_array[i] == block_num) {
                    hits++;
                    hit = true;
                }
                if (freeSlot == -1 && my_cache_array[i] == -1)
                    freeSlot = i;
            }
            if (hit)
                continue;

            // it was never in cache ==> compulsory miss
            if (!wasInCache) {
                Comp_Miss++;
            } else if (freeSlot == -1) {
                Capac_Miss++;
            } else {
                Conf_Miss++;
            }

            if (freeSlot != -1) {
                my_cache_array[freeSlot] = block_num;
                continue;
            }
            if (replace_policy == 1) {
                int index = getLRU(cache_slot);
                long LRU = my_cache_array[index];
                my_cache_array[index] = block_num;
                previousAddresses.remove(LRU);
                previousAddresses.addFirst(LRU); // so that we can keep track of
                // it that it was in cache
                continue;
            }

            Random R = new Random(20);
            my_cache_array[cache_slot + (R.nextInt() % degree_assoc)] = block_num;

        }

        if (lastcountprint != count) {
            Output output = new Output();
            output.hits = hits;
            output.Comp_Miss = Comp_Miss;
            output.Capac_Miss = Capac_Miss;
            output.Conf_Miss = Conf_Miss;
            output.total = Comp_Miss + Capac_Miss + Conf_Miss;

            list.add(output);
            System.out.println("After-2--> " + (count) + " Entries/Addresses ==> "
                    + " hits = " + hits + " Compulsory Miss = " + Comp_Miss
                    + " Capacity Miss = " + Capac_Miss + " Conflict Miss = "
                    + Conf_Miss + " Total Miss = "
                    + (int) (Comp_Miss + Capac_Miss + Conf_Miss));
        }

        return list;

    }

    private int getLRU(int cache_slot) {
        if (previousAddresses.size() == 0)
            return 0;
        ListIterator itr = previousAddresses.listIterator(previousAddresses
                .size() - 1);

        while (itr.hasPrevious()) {

            long item = (Long) itr.previous();

            for (int i = cache_slot; i < cache_slot + degree_assoc; ++i) {
                if (item == my_cache_array[i])
                    return i;
            }
        }
        return 0; // not possible
    }

}
