package br.com.sarti.JavaSpring.controllers;

import br.com.sarti.JavaSpring.services.PersonServices;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestLogController {

    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @GetMapping("/api/test/v1")
    public String testeLog(){
        logger.debug("This is an DEBUG log");
        logger.info("This is an INFO log");
        logger.warn("This is an WARN log");
        logger.error("This is an ERROR log");

        return "Logs generated successfully!!";

    }
}
