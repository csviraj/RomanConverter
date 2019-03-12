package com.romanconverter.service.impl;

import javax.inject.Named;
import com.romanconverter.service.api.HealthCheckService;

@Named
public class HealthCheckServiceImpl implements HealthCheckService {
    /**
     * Simple boolean function that returns true when invoked.
     * @return
     */
    @Override
    public boolean isHealthy() {
        return true;
    }
}
