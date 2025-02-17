package br.ufc.quixada.models;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IRequestResponseProtocol {

    String getObjectReference();

    void setObjectReference(String objectReference);

    String getMethodId();

    void setMethodId(String methodId);

    String getPayload();

    void setPayload(String payload);

    // Serializa para JSON
    String toJson() throws JsonProcessingException;

}
