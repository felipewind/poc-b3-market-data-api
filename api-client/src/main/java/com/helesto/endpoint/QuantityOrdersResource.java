package com.helesto.endpoint;

import java.time.temporal.ChronoUnit;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.helesto.client.B3OrdersClient;
import com.helesto.client.RequestUUIDHeaderFactory;
import com.helesto.dto.QuantityOrdersRsp;

import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/OrdersInformation")
@RequestScoped
public class QuantityOrdersResource {

    private static final Logger LOG = LoggerFactory.getLogger(QuantityOrdersResource.class.getName());

    @Inject
    @RestClient
    B3OrdersClient b3OrdersClient;

    String sessionStartDate, sessionEndDate, symbol;
    QuantityOrdersRsp quantityOrdersRsp = new QuantityOrdersRsp();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = QuantityOrdersRsp.class)) })
    @APIResponse(responseCode = "201", description = "No Content")
    @APIResponse(responseCode = "400", description = "Bad Request")
    @APIResponse(responseCode = "401", description = "Unauthorized")
    @APIResponse(responseCode = "403", description = "Forbidden")
    @APIResponse(responseCode = "404", description = "The specified resource was not found")
    @APIResponse(responseCode = "422", description = "Unprocessable Entity")
    @APIResponse(responseCode = "500", description = "Internal server error")
    @APIResponse(responseCode = "501", description = "Not Implemented")
    @APIResponse(responseCode = "503", description = "Service Unavailable")
    public Response ordersInformation(
            @Parameter(example = "73d90e13-c4cb-4b5f-acd6-7f3d89ce7d8b", description = "keyId from your https://developers.b3.com.br/ user") @QueryParam("keyId") String keyId,
            @Parameter(example = "2020-01-10") @QueryParam("SessionStartDate") String sessionStartDate,
            @Parameter(example = "2020-01-10") @QueryParam("SessionEndDate") String sessionEndDate,
            @Parameter(example = "PETR4") @QueryParam("Symbol") String symbol) {

        LOG.debug("GET request - keyId=[" + keyId + "] sessionStartDate=[" + sessionStartDate + "] sessionEndDate=["
                + sessionEndDate + "] symbol=[" + symbol + "]");

        RequestUUIDHeaderFactory.setKeyId(keyId);
        this.sessionStartDate = sessionStartDate;
        this.sessionEndDate = sessionEndDate;
        this.symbol = symbol;

        getB3Endpoint();

        Jsonb jsonb = JsonbBuilder.create();
        String jsonString = jsonb.toJson(quantityOrdersRsp);
        LOG.debug("GET response - " + jsonString);

        return Response.status(Response.Status.OK).entity(quantityOrdersRsp).build();
    }

    // @Timeout(value = 10, unit = ChronoUnit.SECONDS)
    public void getB3Endpoint() {

        LOG.debug("Before the B3 endpoint");

        quantityOrdersRsp = b3OrdersClient.getQuantityOrdersRspget(sessionStartDate, sessionEndDate, symbol);

        LOG.debug("After the B3 endpoint");

    }

}
