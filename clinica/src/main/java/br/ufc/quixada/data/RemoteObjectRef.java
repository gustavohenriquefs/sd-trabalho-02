package br.ufc.quixada.data;

import java.io.Serializable;

public class RemoteObjectRef implements Serializable {

    private static final long serialVersionUID = 1L;
    private String objectName;

    public RemoteObjectRef() { }

    public RemoteObjectRef(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
