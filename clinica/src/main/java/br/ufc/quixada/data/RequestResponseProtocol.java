package br.ufc.quixada.data;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestResponseProtocol {

    private String objectReference;
    private String methodId;
    private String payload;

    // Construtor padrão necessário para desserialização JSON
    public RequestResponseProtocol() {
    }

    // Construtor parametrizado
    public RequestResponseProtocol(String objectReference, String methodId, String payload) {
        this.objectReference = objectReference;
        this.methodId = methodId;
        this.payload = payload;
    }

    // Getters e Setters
    public String getObjectReference() {
        return objectReference;
    }

    public void setObjectReference(String objectReference) {
        this.objectReference = objectReference;
    }

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    // Método para converter o objeto para JSON
    public String toJson() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

    // Método para converter JSON em um objeto RequestResponseProtocol
    public static RequestResponseProtocol fromJson(String json) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, RequestResponseProtocol.class);
    }
}
