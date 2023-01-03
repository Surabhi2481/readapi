package dev.imsurabhi.readapi.controllers;

import dev.imsurabhi.readapi.services.MatchDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchDataController {

    private static final Logger logger = LoggerFactory.getLogger(MatchDataController.class);

    @Autowired
    MatchDataService matchDataService;

    @RequestMapping(value = "/match-data-auto", method = RequestMethod.GET)
    public ResponseEntity<?> matchDataAuto() {
        logger.info("Got request to fetch data from Mongo and Cassandra and match them.");
        // TODO: call service to fetch match data.
        return new ResponseEntity<>(matchDataService.matchDataAutomatedFlow(), HttpStatus.OK);
    }

}
