/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cachesimulation;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author CODE
 */
public class directAccessCache extends Cache {

    public directAccessCache(Integer block, Integer read_in_cache_size, Integer batch) {
        super(block, read_in_cache_size, batch);
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
                + " Direct Access." + "\n\n");

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
            if (!previousAddresses.contains(block_num))
                previousAddresses.addFirst(block_num);
            else
                wasInCache = true;

            count++;
            if (my_cache_array[cache_slot] == block_num) {
                hits++;
                continue;
            }
            // it was never in cache ==> compulsory miss
            if (!wasInCache) {
                Comp_Miss++;
                my_cache_array[cache_slot] = block_num;
                continue;
            }

            boolean capacitymiss = true;
            // if cache full then Capacity miss
            for (int i = 0; i < my_cache_array.length; ++i) {
                if (my_cache_array[i] == -1) {
                    capacitymiss = false;
                    break;
                }
            }
            if (capacitymiss) {
                Capac_Miss++;
                my_cache_array[cache_slot] = block_num;
                continue;
            }

            Conf_Miss++;
            my_cache_array[cache_slot] = block_num;
        }

        if (lastcountprint != count) {
            System.out.println("After " + (count) + " Entries/Addresses ==> "
                    + " hits = " + hits + " Compulsory Miss = " + Comp_Miss
                    + " Capacity Miss = " + Capac_Miss + " Conflict Miss = "
                    + Conf_Miss + " Total Miss = "
                    + (int) (Comp_Miss + Capac_Miss + Conf_Miss));

            Output output = new Output();
            output.count = count;
            output.hits = hits;
            output.Comp_Miss = Comp_Miss;
            output.Capac_Miss = Capac_Miss;
            output.Conf_Miss = Conf_Miss;
            output.total = Comp_Miss + Capac_Miss + Conf_Miss;

            list.add(output);
        }
        return list;

    }
}
