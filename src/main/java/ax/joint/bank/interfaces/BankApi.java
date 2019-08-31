package ax.joint.bank.interfaces;

import ax.joint.bank.application.BankApp;
import ax.joint.bank.application.Instalment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


@Path("api")
public class BankApi {

    private static final Logger LOG = LogManager.getLogger(BankApi.class);

    private static final BankApp bankApp = new BankApp();

    @XmlRootElement
    static class GeneratePlanRequest {
        @XmlElement(name="duration")
        String duration;
        @XmlElement(name="interestRate")
        String interestRate;
        @XmlElement(name="principalAmount")
        String principalAmount;
        @XmlElement(name="startDate")
        String startDate;
    }

    /**
     *
     * @param generatePlanRequest
     * @return
     */
    @POST
    @Path("/generate-plan")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generatePlan(final GeneratePlanRequest generatePlanRequest) {

        LOG.info(generatePlanRequest);

        final int durationAsInt;
        try {
            durationAsInt = Integer.parseInt(generatePlanRequest.duration);
        }
        catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

        final BigDecimal interestRateAsBigDecimal;
        try {
            interestRateAsBigDecimal = new BigDecimal(generatePlanRequest.interestRate);
        }
        catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

        final BigDecimal principalAmountAsBigDecimal;
        try {
            principalAmountAsBigDecimal = new BigDecimal(generatePlanRequest.principalAmount);
        }
        catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

        final LocalDate startDateAsDate;
        try {
            startDateAsDate = LocalDate.parse(generatePlanRequest.startDate);
        }
        catch (DateTimeParseException | NullPointerException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }


        final List<InstalmentDto> instalmentDtos = new ArrayList<>();
        for (Instalment instalment : bankApp.calculate(durationAsInt, interestRateAsBigDecimal,
                principalAmountAsBigDecimal, startDateAsDate)) {
            instalmentDtos.add(new InstalmentDto(instalment));
        }
        return Response.status(Response.Status.OK).entity(instalmentDtos).build();
    }


}
