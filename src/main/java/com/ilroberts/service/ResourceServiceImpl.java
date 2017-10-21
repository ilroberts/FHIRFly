package com.ilroberts.service;

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
import org.hl7.fhir.instance.model.api.IBaseResource;

import java.io.File;
import java.io.IOException;

public class ResourceServiceImpl implements ResourceService {

    @Override
    public void processResource(String filename) {

        File file = new File(filename);
        try {
            String contents = Files.asCharSource(file, Charsets.UTF_8).read();

            // need to find the resource type, so convert the file to Json
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(contents);
            JsonElement resourceType = jsonObject.get("resourceType");
            System.out.println(resourceType.getAsString());

            FhirContext ctx = FhirContext.forDstu3();
            FhirValidator validator = ctx.newValidator();
            IValidatorModule module1 = new SchemaBaseValidator(ctx);
            validator.registerValidatorModule(module1);

            IParser p = ctx.newJsonParser();
            IBaseResource r = p.parseResource(contents);
            ValidationResult result = validator.validateWithResult(r);
            System.out.println("result was " + result.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
