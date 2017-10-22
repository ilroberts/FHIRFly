package com.ilroberts.service.impl;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.validation.FhirValidator;
import ca.uhn.fhir.validation.IValidatorModule;
import ca.uhn.fhir.validation.SchemaBaseValidator;
import ca.uhn.fhir.validation.ValidationResult;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ilroberts.service.ResourceService;
import org.hl7.fhir.instance.model.api.IBaseResource;

import java.io.File;
import java.io.IOException;

public class ResourceServiceImpl implements ResourceService {

    @Override
    public void processResource(String filename) {

        try {
            File file = new File(filename);
            String contents = Files.asCharSource(file, Charsets.UTF_8).read();
            String resourceType = getResourceType(contents);
            ValidationResult result = validateResource(contents);

            if (result.isSuccessful()) {
                System.out.println("everything checks out, this is a valid " +resourceType+ " resource");
            } else {
                System.out.println("oops, I found a few validation errors:");
                result.getMessages().forEach(m -> System.out.println(m.getMessage()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResourceType(String json) {

        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(json);
        JsonElement resourceType = jsonObject.get("resourceType");
        return resourceType.getAsString();
    }

    public ValidationResult validateResource(String json) {

        FhirContext ctx = FhirContext.forDstu3();
        FhirValidator validator = ctx.newValidator();
        IValidatorModule module1 = new SchemaBaseValidator(ctx);
        validator.registerValidatorModule(module1);

        IParser p = ctx.newJsonParser();
        IBaseResource r = p.parseResource(json);
        return validator.validateWithResult(r);
    }

}
