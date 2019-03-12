package com.romanconverter.service.impl;

import javax.inject.Named;
import com.romanconverter.service.api.HealthCheckService;

@Named
public class HealthCheckServiceImpl implements HealthCheckService {
    @Override
    public boolean isHealthy() {
        return true;
    }
}
