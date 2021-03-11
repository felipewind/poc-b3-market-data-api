package com.helesto.client;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.helesto.dto.QuantityOrdersRsp;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@RegisterClientHeaders(RequestUUIDHeaderFactory.class)
@Singleton
public interface B3OrdersClient {

    @GET
    @Path("/v1.0/QuantityOrders")
    @Produces(MediaType.APPLICATION_JSON)
    public QuantityOrdersRsp getQuantityOrdersRspget(@QueryParam("SessionStartDate") String sessionStartDate,
            @QueryParam("SessionEndDate") String sessionEndDate, @QueryParam("Symbol") String symbol);

}
