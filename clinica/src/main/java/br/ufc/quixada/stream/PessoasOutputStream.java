package br.ufc.quixada.stream;

import java.io.DataOutputStream;
import java.io.OutputStream;

public class PessoasOutputStream extends OutputStream {

    private OutputStream out;

    public PessoasOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) {
        try {
            out.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writePessoas(String pessoas) {
        try {
            DataOutputStream dataOut = new DataOutputStream(out);
            dataOut.writeUTF(pessoas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
