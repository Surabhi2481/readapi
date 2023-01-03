package dev.imsurabhi.readapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadApiController {

    private static final Logger logger = LoggerFactory.getLogger(ReadApiController.class);


    @RequestMapping(value = "/mongo/read-all", method = RequestMethod.GET)
    public ResponseEntity<?> readAllFromMongoDB() {
        logger.info("Got request to fetch all data from MongoDB.");
        // TODO: call service to fetch data.
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
