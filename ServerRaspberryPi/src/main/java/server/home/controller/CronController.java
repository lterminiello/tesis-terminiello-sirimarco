package server.home.controller;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import server.home.model.CronJob;
import server.home.model.CronManager;
import server.home.service.CronService;


@Controller
@RequestMapping("/cron")
public class CronController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CronController.class);

    private CronService cronService;

    public CronController(CronService cronService) {
        this.cronService = cronService;
    }

    @RequestMapping(value = "/addCron", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> addCron(@RequestParam(value = "roomName") String roomName,
                                          @RequestParam(value = "arctifactName") String arctifactName,
                                          @RequestParam(value = "action") String action,
                                          @RequestParam(value = "value", required = false) String value,
                                          @RequestParam(value = "min") String min,
                                          @RequestParam(value = "hour") String hour,
                                          @RequestParam(value = "days") String days) throws SchedulerException {
        CronJob cronJob = new CronJob(roomName, arctifactName, action, value, min, hour, days);
        cronService.addCrono(cronJob);
        return new ResponseEntity<String>(("ACCEPTED"), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/deleteCron", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> deleteCron(@RequestParam(value = "roomName") String roomName,
                                             @RequestParam(value = "arctifactName") String arctifactName,
                                             @RequestParam(value = "action") String action,
                                             @RequestParam(value = "value", required = false) String value,
                                             @RequestParam(value = "min") String min,
                                             @RequestParam(value = "hour") String hour,
                                             @RequestParam(value = "days") String days) throws SchedulerException {
        CronJob cronJob = new CronJob(roomName, arctifactName, action, value, min, hour, days);
        cronService.deleteCrono(cronJob);
        return new ResponseEntity<String>(("ACCEPTED"), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getCronos", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<CronManager> getCronos() {
        CronManager cronManager = cronService.getCronJobs();
        return new ResponseEntity<CronManager>((cronManager), HttpStatus.ACCEPTED);
    }
}
