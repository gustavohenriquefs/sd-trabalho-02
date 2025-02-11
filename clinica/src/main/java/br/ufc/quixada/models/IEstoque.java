package br.ufc.quixada.models;

import java.io.Serializable;
import java.rmi.Remote;

public interface IEstoque extends Remote, Serializable {

    public String doOperation(String jsonRequest) throws java.rmi.RemoteException;

}
