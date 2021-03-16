package com.helesto.client;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MultivaluedMap;

import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;
import org.jboss.resteasy.specimpl.MultivaluedMapImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class RequestUUIDHeaderFactory implements ClientHeadersFactory {

    private static final Logger LOG = LoggerFactory.getLogger(RequestUUIDHeaderFactory.class.getName());

    private static String keyId = "Put your key Id here";

    @Override
    public MultivaluedMap<String, String> update(MultivaluedMap<String, String> incomingHeaders,
            MultivaluedMap<String, String> clientOutgoingHeaders) {
        LOG.debug("KeyId=[" + keyId + "]");
        MultivaluedMap<String, String> result = new MultivaluedMapImpl<>();
        result.add("KeyId", keyId);
        return result;
    }

    public static String getKeyId() {
        return keyId;
    }

    public static void setKeyId(String keyId) {
        RequestUUIDHeaderFactory.keyId = keyId;
    }

}
