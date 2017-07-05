package server.home.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import server.home.exeption.NotAllowedActionExeption;
import server.home.model.Artifact;
import server.home.model.ErrorApi;
import server.home.model.House;
import server.home.service.AnnouncementService;
import server.home.service.HouseService;
import server.home.utils.Constants;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnnouncementController.class);

    private AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @RequestMapping(value = "/device", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> announcementDevice(@RequestParam(value = "id", required = true) String id,
                                                     @RequestParam(value = "ip", required = true) String ip,
                                                     @RequestParam(value = "code", required = true) String code) {
        if (code.equals(Constants.ANNOUNCEMENT_CODE_APP)) {
            announcementService.addDevice(id, ip);
            System.out.println("hola");
            return new ResponseEntity<String>(("Vinculado"), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<String>(("Error de autentificacion"), HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/board", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> announcementBoard(@RequestParam(value = "id", required = true) String id,
                                                    @RequestParam(value = "ip", required = true) String ip,
                                                    @RequestParam(value = "code", required = true) String code) {
        if (code.equals(Constants.ANNOUNCEMENT_CODE_BOARD)) {
            announcementService.addBoard(id, ip);
            LOGGER.info(ip);
            return new ResponseEntity<String>(("Vinculado"), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<String>(("Error de autentificacion"), HttpStatus.FORBIDDEN);
        }
    }


    /*@ExceptionHandler(NotAllowedActionExeption.class)
    public ResponseEntity<ErrorApi>
    handleNotAllowedActionException(NotAllowedActionExeption ex) {
        LOGGER.error(ex.getMessage(), ex);
        return new ResponseEntity<ErrorApi>(new ErrorApi("400", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }*/


}
