package com.ilroberts.service.impl;

import com.ilroberts.service.FHIRFlyService;
import com.ilroberts.service.ResourceService;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

import joptsimple.OptionException;

import javax.inject.Inject;

public class FHIRFlyServiceImpl implements FHIRFlyService {

    @Inject
    private ResourceService resourceService;

    public void run(String args[]) {

        OptionParser parser = new OptionParser("r:");

        try {
            OptionSet options = parser.parse(args);

            if(options.has("r") && options.hasArgument("r")) {
                resourceService.processResource((String)options.valueOf("r"));
            } else {
                System.out.println("Usage: firefly -r resource_file");
            }
        } catch (OptionException e ) {
            System.out.println("Usage: firefly -r resource_file");
        }
    }
}
