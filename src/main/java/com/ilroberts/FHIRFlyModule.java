package com.ilroberts;

import com.google.inject.AbstractModule;
import com.ilroberts.service.FHIRFlyService;
import com.ilroberts.service.FHIRFlyServiceImpl;

public class FHIRFlyModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(FHIRFlyService.class).to(FHIRFlyServiceImpl.class);
    }
}
