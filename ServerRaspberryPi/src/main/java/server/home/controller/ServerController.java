package server.home.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import server.home.service.ServerService;

@Controller
@RequestMapping("/server")
public class ServerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerController.class);

    private ServerService serverService;

    public ServerController(ServerService serverService) {
        this.serverService = serverService;
    }

    @RequestMapping(value = "/addNetwork", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> addNetwork(@RequestParam(value = "ssid", required = true) String ssid,
                                              @RequestParam(value = "psk", required = true) String psk) {
        return new ResponseEntity<String>(serverService.addNetwork(ssid, psk), HttpStatus.ACCEPTED);
    }

}
