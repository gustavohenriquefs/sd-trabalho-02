package br.ufc.quixada.models;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import br.ufc.quixada.data.RemoteObjectRef;

public interface IEstoque extends Remote {

    byte[] doOperation(RemoteObjectRef o, int methodId, byte[] arguments) throws RemoteException, UnknownHostException;

    byte[] getRequest() throws RemoteException;

    void sendReply(byte[] reply, InetAddress clientHost, int clientPort) throws RemoteException;
}
