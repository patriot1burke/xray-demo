package org.acme;

import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.entities.Subsegment;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {
    private static final String LAMBDA_TRACE_HEADER_PROP = "com.amazonaws.xray.traceHeader";

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        String aws_region = System.getenv("AWS_REGION");
        String x_amzn_trace_id = System.getenv("_X_AMZN_TRACE_ID");
        System.out.println("Lambda AWS Region: " + aws_region);
        System.out.println("Lambda _X_AMZN_TRACE_ID: " + x_amzn_trace_id);

        String env = "--ENV--\n"
                + "AWS_REGION: " + aws_region + "\n"
                + "_X_AMZN_TRACE_ID: " + x_amzn_trace_id + "\n"
                + "Trace Property: " + System.getProperty(LAMBDA_TRACE_HEADER_PROP) + "\n";

        Subsegment subsegment = AWSXRay.beginSubsegment("Save Game");
        try {
            Thread.sleep(12);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AWSXRay.endSubsegment();


        return env;
    }
}