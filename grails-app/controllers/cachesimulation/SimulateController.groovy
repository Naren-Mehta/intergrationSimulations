package cachesimulation

import cacheSimulation.Simulate
import cacheSimulation.SimulateCO
import org.springframework.web.multipart.commons.CommonsMultipartFile
import utils.AppUtilTraffic

class SimulateController {

    def simulateService
    def simulateUtilService

    def simulateViewPage() {}

    def calculateSimulation = { SimulateCO simulateCO ->

        CommonsMultipartFile uploadFile = simulateCO?.uploadFile as CommonsMultipartFile
        if (simulateCO.validate() && uploadFile?.bytes) {

            Simulate simulate = new Simulate()
            simulate?.batch = simulateCO?.batch
            simulate?.blockSize = simulateCO?.blockSize
            simulate?.cacheSize = simulateCO?.cacheSize
            simulate?.cacheType = simulateCO?.cacheType
            simulate?.degreeOfAssociates = simulateCO?.degreeOfAssociates
            simulate?.fileSelection = simulateCO?.fileSelection
            simulate?.replacementPolicy = simulateCO?.replacementPolicy
            AppUtilTraffic?.save(simulate)

            simulateUtilService?.storeImages(uploadFile, simulate)
            ArrayList list = []
            try {
                list = simulateService?.performLogic(simulate)
            }
            catch (Exception e) {
                println("--------------------exception------------")
            }

            println("----------------------simulate?.fileSelection------------" + simulate?.fileSelection)

            render(view: "/simulate/result", model: [simulate: simulate, simulateCO: simulateCO, list: list])

        } else {
            if (!uploadFile?.bytes) {
                flash.message = g.message(code: "Please.Upload.a.file")
            }
            render(view: "/simulate/simulateViewPage", model: [simulateCO: simulateCO])
        }
    }
}
