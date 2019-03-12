package com.romanconverter.web.rest;
import javax.inject.*;
import com.romanconverter.service.api.HealthCheckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/health")
public class HealthCheckController {
    private final HealthCheckService healthCheckService;

    @Inject
    public HealthCheckController(HealthCheckService healthCheckService) {
        this.healthCheckService = healthCheckService;
    }

    /**
     * A simple Health Check Get Request where True is returned if invoked and no errors happen.
     * @return boolean
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD}, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> get() {
        if (!healthCheckService.isHealthy()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok().body("OK");
    }
}


