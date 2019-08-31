package ax.joint.bank.interfaces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

public class BankApiTest extends JerseyTest {

    private static final Logger LOG = LogManager.getLogger(BankApiTest.class);

    @Override
    protected Application configure() {
        return new ResourceConfig(BankApi.class);
    }

    @Test
    public void generatePlanOk() {

        final Response response = ClientBuilder
                .newClient()
                .target("http://localhost:9998/api")
                .path("generate-plan")
                .request()
                .post(Entity.json("{\"duration\":\"24\", \"interestRate\":\"5\", \"principalAmount\":\"5000\", \"startDate\":\"2018-01-01\"}"));

        Assert.assertTrue("Response status should be 200 OK, was: " + response.getStatus(), 200 == response.getStatus());

    }

    @Test
    public void generatePlanBadRequest() {

        final Response response = ClientBuilder
                .newClient()
                .target("http://localhost:9998/api")
                .path("generate-plan")
                .request()
                .post(Entity.json("{\"name\":\"strawberry\",\"weight\":20}"));

        LOG.info("Response text: " + response.readEntity(String.class));
        Assert.assertTrue("Response status should be 400 Bad Request, was: " + response.getStatus(), 400 == response.getStatus());

    }

}
