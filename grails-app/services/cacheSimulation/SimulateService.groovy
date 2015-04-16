package cacheSimulation

import cachesimulation.Cache
import cachesimulation.FileProcess
import cachesimulation.associativeCache
import cachesimulation.directAccessCache
import cachesimulation.fullyAssociativeCache
import utils.AppUtilTraffic

class SimulateService {

    def simulateUtilService


    public ArrayList performLogic(Simulate simulate) {

        Cache cache = null;

        if (simulate?.cacheType == 1 || simulate?.degreeOfAssociates == 1) {
            cache = new directAccessCache(simulate?.blockSize, simulate?.cacheSize, simulate?.batch)
        } else if (simulate?.cacheType == 2 || simulate?.degreeOfAssociates == 16) {
            cache = new fullyAssociativeCache(simulate?.blockSize, simulate?.cacheSize,
                    simulate?.replacementPolicy, simulate?.batch);
        } else {
            cache = new associativeCache(simulate?.blockSize, simulate?.cacheSize, simulate?.degreeOfAssociates,
                    simulate?.replacementPolicy, simulate?.batch);
        }


        println("-----------------cache-----------------" + cache)


        LinkedList TotalAddresses = new LinkedList();
        TotalAddresses = getAddressesfromfile(simulate);
        ArrayList list = cache.calculate(TotalAddresses);

        return list
    }


    private static LinkedList getAddressesfromfile(Simulate simulate) throws FileNotFoundException {


        SimulateDocuments simulateDocuments = SimulateDocuments?.findBySimulate(simulate)

        String imageName = simulateDocuments?.name

        println("---------------i am hear----------1-----------------" + imageName)

        def webRootDir = AppUtilTraffic.staticResourcesDirPath

        String filename = webRootDir + "/uploadedFile/${simulate?.id}/" + imageName

        println("---------------i am hear---------------------------" + filename)
        println("---------------simulate?.fileSelection-------------------------" + simulate?.fileSelection)

        FileProcess fileProcess = new FileProcess(filename);

        try {
            switch (getEnumValue(simulate?.fileSelection)) {
                case 1:

                    println("--------------------------1----------------")

                    return fileProcess.getaddressesFromHexFile();

                case 2:
                    println("--------------------------2----------------")

                    return fileProcess.getaddressesFromLogFile();

                case 3:
                    println("--------------------------3----------------")
                    return fileProcess.getaddressesFromTStampAndIpFormat();
                case 4:
                    println("--------------------------4----------------")
                    return fileProcess.getaddressesFromFrequencyFile();

                case 5:
                    println("--------------------------5----------------")
                    return fileProcess.getaddressesFromTimeSearchFile();

            }
        } catch (Exception e) {
            println("-----------------------------------------")
        }


        return new LinkedList();
    }


    public static int getEnumValue(String fileSelection) {
        if (fileSelection?.equals("Hex value file")) {
            return 1
        } else if (fileSelection?.equals("Sys log file")) {
            return 2
        } else if (fileSelection?.equals("File containing time stamp")) {
            return 3
        } else if (fileSelection?.equals("IP Address List")) {
            return 5
        } else if (fileSelection?.equals("IP address time search file")) {
            return 5
        } else {
            return 1
        }
    }


}
