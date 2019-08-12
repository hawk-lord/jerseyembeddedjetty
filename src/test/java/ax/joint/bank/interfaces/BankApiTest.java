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
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class BankApiTest extends JerseyTest {

    private static final Logger LOG = LogManager.getLogger(BankApiTest.class);

    @Override
    protected Application configure() {
        return new ResourceConfig(BankApi.class);
    }


    @Test
    public void moneyTransferOk() {

        final Form objForm = new Form();
        objForm.param("whenCharged", "2019-08-01")
                .param("whenBooked", "2019-08-03")
                .param("amount", "123.45")
                .param("creditAccountId", "1")
                .param("debitAccountId", "2");

        final Response postResponse = ClientBuilder
                .newClient()
                .target("http://localhost:9998/api")
                .path("moneyTransfer")
                .request()
                .post(Entity.form(objForm));

        final String createdId = postResponse.readEntity(String.class);
        LOG.info("Response text: " + createdId);
        Assert.assertTrue("Response status should be 200 OK, was: " + postResponse.getStatus(), 200 == postResponse.getStatus());

        final Response getResponse = ClientBuilder
                .newClient()
                .target("http://localhost:9998/api")
                .path("moneyTransfer")
                .queryParam("id", "1")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
        Assert.assertNotNull(getResponse);
        final AccountingEntryDto foundAccountingEntry = getResponse.readEntity(AccountingEntryDto.class);
        final String foundId = String.valueOf(foundAccountingEntry.getId());
        LOG.info("Response text: " + foundId);
        Assert.assertTrue("Response status should be 200 OK, was: " + postResponse.getStatus(), 200 == postResponse.getStatus());
        Assert.assertEquals(createdId, foundId);


    }


    @Test
    public void moneyTransferWrongAccountId() {

        final Form objForm = new Form();
        objForm.param("whenCharged", "2019-08-01")
                .param("whenBooked", "2019-08-03")
                .param("amount", "123.45")
                .param("creditAccountId", "1")
                .param("debitAccountId", "22");

        final Response response = ClientBuilder
                .newClient()
                .target("http://localhost:9998/api")
                .path("moneyTransfer")
                .request()
                .post(Entity.form(objForm));

        LOG.info("Response text: " + response.readEntity(String.class));
        Assert.assertTrue("Response status should be 400 Bad Request, was: " + response.getStatus(), 400 == response.getStatus());

    }

}
