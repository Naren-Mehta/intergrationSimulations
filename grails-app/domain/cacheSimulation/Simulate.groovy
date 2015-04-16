package cacheSimulation

class Simulate {

    String cacheType
    Integer cacheSize
    Integer blockSize
    Integer degreeOfAssociates
    Integer replacementPolicy
    Integer batch

    String fileSelection

//    static hasOne = [simulateDocuments: SimulateDocuments]


    static constraints = {
        cacheType(blank: false, nullable: false)
        fileSelection(blank: false, nullable: false)
        replacementPolicy(blank: false, nullable: false)
        cacheSize(blank: false, nullable: false)
        blockSize(blank: false, nullable: false)
    }
}
