package ax.joint.bank.interfaces;

import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

public class BankApiTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(BankApi.class);
    }


    @Test
    public void moneyTransfer() {

        final Form objForm = new Form();
        objForm.param("whenCharged", "2019-08-01")
                .param("whenBooked", "2019-08-03")
                .param("amount", "123.45")
                .param("creditAccountId", "1")
                .param("debitAccountId", "2");

        final Response response = ClientBuilder
                .newClient()
                .target("http://localhost:9998/api")
                .path("moneyTransfer")
                .request()
                .post(Entity.form(objForm));
        System.out.println(response.getStatus());
        Assert.assertTrue(200 == response.getStatus());
    }

}
