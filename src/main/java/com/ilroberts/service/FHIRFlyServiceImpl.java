package com.ilroberts.service;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class FHIRFlyServiceImpl implements FHIRFlyService {

    public void run(String args[]) {


        OptionParser parser = new OptionParser("h");
        OptionSet options = parser.parse(args);

        if(options.has("h")) {
            System.out.println("Usage: firefly [-r resource_file | -b bundle_file]");
        } else {
            System.out.println("no options specified");
        }
    }
}
