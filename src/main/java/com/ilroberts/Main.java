package com.ilroberts;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ilroberts.service.FHIRFlyService;

public class Main {

    public static void main(String args[]) {

        Injector injector = Guice.createInjector(new FHIRFlyModule());
        FHIRFlyService service = injector.getInstance(FHIRFlyService.class);
        service.run(args);
    }
}
