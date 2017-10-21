package com.ilroberts.service;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

import joptsimple.OptionException;

import javax.inject.Inject;

public class FHIRFlyServiceImpl implements FHIRFlyService {

    @Inject
    private ResourceService resourceService;

    public void run(String args[]) {

        OptionParser parser = new OptionParser("h*r:");

        try {
            OptionSet options = parser.parse(args);

            if(options.has("r") && options.hasArgument("r")) {
                resourceService.processResource((String)options.valueOf("r"));
            }

            if(options.has("h")) {
                System.out.println("Usage: firefly [-r resource_file | -b bundle_file]");
            }

        } catch (OptionException e ) {
            System.out.println("Usage: firefly [-r resource_file | -b bundle_file]");
        }
    }
}
