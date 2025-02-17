package br.ufc.quixada.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufc.quixada.models.IRequestResponseProtocol;

public class RequestResponseProtocol implements IRequestResponseProtocol {

    private String objectReference;
    private String methodId;
    private String payload;

    public RequestResponseProtocol() {
    }

    public RequestResponseProtocol(String objectReference, String methodId, String payload) {
        this.objectReference = objectReference;
        this.methodId = methodId;
        this.payload = payload;
    }

    @Override
    public String getObjectReference() {
        return objectReference;
    }

    @Override
    public void setObjectReference(String objectReference) {
        this.objectReference = objectReference;
    }

    @Override
    public String getMethodId() {
        return methodId;
    }

    @Override
    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    @Override
    public String getPayload() {
        return payload;
    }

    @Override
    public void setPayload(String payload) {
        this.payload = payload;
    }

    // Serializa para JSON
    @Override
    public String toJson() throws JsonProcessingException {
        return new ObjectMapper()
                .writeValueAsString(this);
    }

    // Desserializa de JSON
    public static RequestResponseProtocol fromJson(String json) throws JsonProcessingException {
        return new ObjectMapper()
                .readValue(json, RequestResponseProtocol.class);
    }
}
