package com.ilroberts;

import com.google.inject.AbstractModule;
import com.ilroberts.service.FHIRFlyService;
import com.ilroberts.service.FHIRFlyServiceImpl;
import com.ilroberts.service.ResourceService;
import com.ilroberts.service.ResourceServiceImpl;

public class FHIRFlyModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(FHIRFlyService.class).to(FHIRFlyServiceImpl.class);
        bind(ResourceService.class).to(ResourceServiceImpl.class);
    }
}
