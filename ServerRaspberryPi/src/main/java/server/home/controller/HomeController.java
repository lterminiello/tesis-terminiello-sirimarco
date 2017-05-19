package server.home.controller;

import org.quartz.JobExecutionException;
import org.springframework.web.bind.annotation.*;
import server.home.exeption.NotAllowedActionExeption;
import server.home.model.*;
import server.home.service.HouseService;
import server.home.service.LightsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/house")
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    private HouseService houseService;

    public HomeController(HouseService houseService) {
        super();
        this.houseService = houseService;
    }

    @RequestMapping(value = "/control", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> actionRoom(@RequestParam(value = "roomName") String roomName,
                                             @RequestParam(value = "arctifactName") String arctifactName,
                                             @RequestParam(value = "action") String action,
                                             @RequestParam(value = "power", required = false) Integer power) {
        Artifact artifact = houseService.getHouse().getRoom(roomName).getArtifact(arctifactName);
        String response = artifact.runAction(action, power);
        LOGGER.info(response.toString());
        return new ResponseEntity<String>((response), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/houseScheme", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<House> houseScheme(@RequestParam(value = "scheme", required = false) String scheme) {
        if (scheme != null) {
            houseService.setHouseScheme(scheme);
        }
        return new ResponseEntity<House>((houseService.getHouse()), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/roomState", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Room> roomState(@RequestParam(value = "name") String name) {
        return new ResponseEntity<Room>((houseService.getHouse().getRoom(name)), HttpStatus.ACCEPTED);
    }


//TODO ejemplo de multiplicacion de potencia para el controlador
/*    @RequestMapping(value = "/bedroom", method = RequestMethod.GET)*/
/*    @ResponseBody*/
/*    public ResponseEntity<Lights> genereteBestTrip(@RequestParam(value = "power", required = true) Integer power) {*/
/*
*/
/*        if (power <= 10 && power >= 0) {*/
/*            Lights response = lightsService.bedroomLight(power * 100);*/
/*            LOGGER.info(response.toString());*/
/*            return new ResponseEntity<Lights>((response), HttpStatus.ACCEPTED);*/
/*        } else {*/
/*            return new ResponseEntity<Lights>(HttpStatus.BAD_REQUEST);*/
/*        }*/
/*    }*/
/*
*/

    // @RequestMapping(method = RequestMethod.GET)
    // @ResponseBody
    // public ResponseEntity<String> genereteBestTrip(@RequestParam(value =
    // "state", required = true) Integer state) {
    //
    // if (state < 2 && state >= 0) {
    // Process p;
    // String s = "";
    // try {
    // String[] cmd = { "python", "ledtest.py", state.toString() };
    // p = Runtime.getRuntime().exec(cmd);
    // BufferedReader br = new BufferedReader(new
    // InputStreamReader(p.getInputStream()));
    // s = br.readLine();
    // LOGGER.info(s);
    // p.destroy();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return new ResponseEntity<String>((s), HttpStatus.ACCEPTED);
    // } else {
    // return new ResponseEntity<String>(("Debe ingresar 1 para encender o 0
    // para apagar"),
    // HttpStatus.BAD_REQUEST);
    // }
    // }

/*    @RequestMapping(value = "/status", method = RequestMethod.GET)*/
/*    @ResponseBody*/
/*    public ResponseEntity<Lights> generateBranch() {*/
/*        Lights response = lightsService.statusLights();*/
/*        LOGGER.info(response.toString());*/
/*        return new ResponseEntity<Lights>((response), HttpStatus.ACCEPTED);*/
/*    }*/


//TODO ejemplo de como ejecutar un codigo python
    // @RequestMapping(method = RequestMethod.GET)
    // @ResponseBody
    // public ResponseEntity<String> genereteBestTrip(@RequestParam(value =
    // "from", required = true) String from,
    // @RequestParam(value = "money", required = true) Double money,
    // @RequestParam(value = "strategy", required = false) String strategy) {
    //
    // Process p;
    // String s = "";
    // try {
    // String[] cmd = { "python", "helloword.py" };
    // p = Runtime.getRuntime().exec(cmd);
    // BufferedReader br = new BufferedReader(new
    // InputStreamReader(p.getInputStream()));
    // s = br.readLine();
    // System.out.println(s);
    // p.destroy();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    //
    // return new ResponseEntity<String>((s), HttpStatus.ACCEPTED);
    //
    // }


    @ExceptionHandler(NotAllowedActionExeption.class)
    public ResponseEntity<ErrorApi>
    handleNotAllowedActionException(NotAllowedActionExeption ex) {
        LOGGER.error(ex.getMessage(), ex);
        return new ResponseEntity<ErrorApi>(new ErrorApi("400", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }


 /*   @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<House> test(@RequestParam(value = "scheme", required = false) String scheme) {
        if (scheme != null) {
            houseService.setHouseScheme(scheme);
        }
        CronJob cronJob = new CronJob("puta","puta","on",null);
        try {
            cronJob.execute(null);
        } catch (JobExecutionException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<House>((houseService.getHouse()), HttpStatus.ACCEPTED);
    }*/
}
