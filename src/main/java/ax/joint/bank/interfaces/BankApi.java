package ax.joint.bank.interfaces;

import ax.joint.bank.application.BankApp;
import ax.joint.bank.model.AccountingEntry;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;


@Path("api")
public class BankApi {

    static final BankApp bankApp = new BankApp();



    /**
     * Test with:
     * curl -i --data "whenCharged=2019-08-02" --data "whenBooked=2019-08-04" --data "amount=5.50" --data "creditAccountId=1"  --data "debitAccountId=2"  localhost:8080/rest/api/moneyTransfer
     *
     * Using only String parameters so an error message can be shown if a parameter has a bad value.
     *
     * Using POST since the method is not idempotent. Repeated requests will create more transactions.
     *
     * @return
     */
    @POST
    @Path("/moneyTransfer")
    public Response moneyTransfer(@FormParam("whenCharged") String whenChargedString,
                                  @FormParam("whenBooked") String whenBookedString,
                                  @FormParam("amount") String amountAsString,
                                  @FormParam("creditAccountId") final String creditAccountIdString,
                                  @FormParam("debitAccountId") final String debitAccountIdString) {

        final LocalDate whenCharged;
        try {
            whenCharged = LocalDate.parse(whenChargedString);
        }
        catch (DateTimeParseException | NullPointerException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        final LocalDate whenBooked;
        try {
            whenBooked = LocalDate.parse(whenBookedString);
        }
        catch (DateTimeParseException | NullPointerException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

        final double amountAsDouble;
        try {
            amountAsDouble = Double.parseDouble(amountAsString);
        }
        catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

        // We do not handle other currencies yet.
        final CurrencyUnit eur = Monetary.getCurrency("EUR");
        final MonetaryAmount amount = Monetary.getDefaultAmountFactory()
                .setCurrency(eur).setNumber(amountAsDouble).create();

        final int creditAccountId;
        try {
            creditAccountId = Integer.parseInt(creditAccountIdString);
        }
        catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        
        final int debitAccountId;
        try {
            debitAccountId = Integer.parseInt(debitAccountIdString);
        }
        catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        
        bankApp.addTransaction(whenCharged, whenBooked, amount, creditAccountId, debitAccountId);


        final String message = "Transfer was done";
        return Response.status(Response.Status.OK).entity(message).build();
    }

}
