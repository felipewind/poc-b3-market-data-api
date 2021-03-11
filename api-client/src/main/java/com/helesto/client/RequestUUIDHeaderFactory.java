package com.helesto.client;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MultivaluedMap;

import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;
import org.jboss.resteasy.specimpl.MultivaluedMapImpl;

@ApplicationScoped
public class RequestUUIDHeaderFactory implements ClientHeadersFactory {

    private static String keyId = "Put your key Id here";

    @Override
    public MultivaluedMap<String, String> update(MultivaluedMap<String, String> incomingHeaders,
            MultivaluedMap<String, String> clientOutgoingHeaders) {
        MultivaluedMap<String, String> result = new MultivaluedMapImpl<>();
        // result.add("KeyId", keyId);
        // result.add("KeyId", "73d90e13-c4cb-4b5f-acd6-7f3d89ce7d8b");
        result.add("KeyId", "FELIPE");
        return result;
    }

    public static String getKeyId() {
        return keyId;
    }

    public static void setKeyId(String keyId) {
        RequestUUIDHeaderFactory.keyId = keyId;
    }

}
