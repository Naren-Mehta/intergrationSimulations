package cacheSimulation

import grails.validation.Validateable
import org.springframework.web.multipart.commons.CommonsMultipartFile

/**
 * Created by narendra on 26/2/15.
 */

@Validateable
class SimulateCO {

    String cacheType

    Integer cacheSize
    Integer blockSize
    Integer degreeOfAssociates
    Integer replacementPolicy
    Integer batch

    String fileSelection
    CommonsMultipartFile uploadFile

    static constraints = {
        cacheType(blank: false, nullable: false)
        fileSelection(blank: false, nullable: false)

        replacementPolicy(blank: false, nullable: false)
        cacheSize(blank: false, nullable: false)
        blockSize(blank: false, nullable: false)
        degreeOfAssociates(blank: false, nullable: false)
        batch(blank: false, nullable: false)
    }


}
