package com.leon.springboot.controller.test;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	private Logger log = LogManager.getLogger(TestController.class);

	@GetMapping("/IO/{level}")
	public ResponseEntity<String> throwIOException(@PathVariable String level) throws IOException {
		switch (level) {
		case "trace":
			log.trace("【TRACE】 IOException");
		case "debug":
			log.debug("【DEBUG】 IOException");
		case "info":
			log.info("【INFO】 IOException");
		case "warn":
			log.warn("【WARN】 IOException");
		case "error":
			log.error("【ERROR】 IOException");
		case "fatal":
			log.fatal("【FATAL】 IOException");
			break;
		}
		return ResponseEntity.status(HttpStatus.OK).body("Log 紀錄 OK !" + level);
	}

	@GetMapping("/SQL/{level}")
	public ResponseEntity<String> throwSQLException(@PathVariable String level) throws SQLException {
		// test
		switch (level) {
		case "trace":
			log.trace("【TRACE】 SQLException");
			break;
		case "debug":
			log.debug("【DEBUG】 SQLException");
			break;
		case "info":
			log.info("【INFO】 SQLException");
			break;
		case "warn":
			log.warn("【WARN】 SQLException");
			break;
		case "error":
			log.error("【ERROR】 SQLException");
			break;
		case "fatal":
			log.fatal("【FATAL】 SQLException");
			break;
		}
		return ResponseEntity.status(HttpStatus.OK).body("Log 紀錄 OK !" + level);
	}

}
