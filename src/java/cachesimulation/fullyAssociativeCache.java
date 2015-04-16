/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cachesimulation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * @author CODE
 */
public class fullyAssociativeCache extends Cache {

    int replace_policy;

    public fullyAssociativeCache(int block, int read_in_cache_size,
                                 int rep_policy, int batch) {
        super(block, read_in_cache_size, batch);
        replace_policy = rep_policy;
    }

    @Override
    public ArrayList calculate(LinkedList addresses) {
        ArrayList list = new ArrayList();

        int count = 0;
        int lastcountprint = 0;
        System.out.println("******* Starting Simulation *******");

        System.out.println("Configuration : Cache Size = "
                + ((cache_array_lines * block_size) / 1024) + " KB "
                + " Block Size =  " + block_size + " B " + " Cache Type = "
                + " Fully Associative " + "\n\n");

        for (Object item : addresses) {
            if ((count % batch) == 0 && count >= batch) {
                System.out.println("After " + (count)
                        + " Entries/Addresses ==>" + " hits = " + hits
                        + " Compulsory Miss = " + Comp_Miss
                        + " Capacity Miss = " + Capac_Miss
                        + " Conflict Miss = " + Conf_Miss + " Total Miss = "
                        + (int) (Comp_Miss + Capac_Miss + Conf_Miss));
                lastcountprint = count;

                Output output = new Output();
                output.count = count;
                output.hits = hits;
                output.Comp_Miss = Comp_Miss;
                output.Capac_Miss = Capac_Miss;
                output.Conf_Miss = Conf_Miss;
                output.total = Comp_Miss + Capac_Miss + Conf_Miss;

                list.add(output);
            }

            long address = (Long) item;
            long block_num = address / block_size;
            int cache_slot = (int) (block_num % cache_array_lines);

            boolean wasInCache = false;
            if (previousAddresses.remove(block_num)) {
                wasInCache = true;
            }

            previousAddresses.addFirst(block_num);

            count++;
            boolean hit = false;
            int freeSlot = -1;
            for (int i = 0; i < my_cache_array.length; ++i) {
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
                long LRU = (Long) previousAddresses.removeLast();
                for (int i = 0; i < my_cache_array.length; ++i) {
                    if (my_cache_array[i] == LRU) {
                        my_cache_array[i] = block_num;
                        break;
                    }
                }
                previousAddresses.addFirst(LRU); // so that we can keep track of
                // it that it was in cache
                continue;
            }

            Random R = new Random(20);
            my_cache_array[R.nextInt() % my_cache_array.length] = block_num;

        }

        if (lastcountprint != count) {

            Output output = new Output();
            output.count = count;
            output.hits = hits;
            output.Comp_Miss = Comp_Miss;
            output.Capac_Miss = Capac_Miss;
            output.Conf_Miss = Conf_Miss;
            output.total = Comp_Miss + Capac_Miss + Conf_Miss;

            list.add(output);
            System.out.println("After " + (count) + " Entries/Addresses ==> "
                    + " hits = " + hits + " Compulsory Miss = " + Comp_Miss
                    + " Capacity Miss = " + Capac_Miss + " Conflict Miss = "
                    + Conf_Miss + " Total Miss = "
                    + (int) (Comp_Miss + Capac_Miss + Conf_Miss));
        }
        return list;

    }
}
