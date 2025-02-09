package br.ufc.quixada.stream;

import java.io.InputStream;

public class PessoasInputStream extends InputStream {

    private InputStream in;

    public PessoasInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() {
        try {
            return in.read();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void close() {
        try {
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String lerPessoas() {
        try {
            byte[] buffer = new byte[1024];
            int len = in.read(buffer);
            return new String(buffer, 0, len);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
