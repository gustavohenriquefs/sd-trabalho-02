package br.ufc.quixada.stream;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.entities.Animal;
import br.ufc.quixada.entities.Cachorro;
import br.ufc.quixada.entities.Coelho;
import br.ufc.quixada.entities.Gato;

public class AnimaisInputStream extends InputStream {

    private final InputStream inputStream;
    private final List<Animal> animaisLidos;
    private int currentPosition;
    private byte[] buffer;

    public AnimaisInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        this.animaisLidos = new ArrayList<>();
        this.buffer = new byte[1024];
        this.currentPosition = 0;
    }

    @Override
    public int read() throws IOException {
        if (currentPosition >= buffer.length) {
            return -1;
        }
        return buffer[currentPosition++] & 0xff;
    }

    public List<Animal> lerAnimais() throws IOException {
        DataInputStream dis = new DataInputStream(inputStream);

        // Num animals
        int numAnimais = dis.readInt();

        for (int i = 0; i < numAnimais; i++) {
            // Tipo animal
            int tipo = dis.readInt();

            // Nome
            int tamanhoNome = dis.readInt();
            byte[] nomeBytes = new byte[tamanhoNome];
            dis.read(nomeBytes);
            String nome = new String(nomeBytes);

            // Idade
            int idade = dis.readInt();

            // CPF Dono
            int tamanhoCpfDono = dis.readInt();
            byte[] cpfDonoBytes = new byte[tamanhoCpfDono];
            dis.read(cpfDonoBytes);
            String cpfDono = new String(cpfDonoBytes);

            // Atributos específicos
            switch (tipo) {
                case 1:
                    // Gato
                    int tamanhoCorGato = dis.readInt();
                    byte[] corGatoBytes = new byte[tamanhoCorGato];
                    dis.read(corGatoBytes);
                    String corGato = new String(corGatoBytes);
                    animaisLidos.add(new Gato(nome, idade, cpfDono, corGato));
                    break;
                case 2:
                    // Coelho
                    int tamanhoCorCoelho = dis.readInt();
                    byte[] corCoelhoBytes = new byte[tamanhoCorCoelho];
                    dis.read(corCoelhoBytes);
                    String corCoelho = new String(corCoelhoBytes);
                    animaisLidos.add(new Coelho(nome, idade, cpfDono, corCoelho));
                    break;
                case 3:
                    // Cachorro
                    int tamanhoRaca = dis.readInt();
                    byte[] racaBytes = new byte[tamanhoRaca];
                    dis.read(racaBytes);
                    String raca = new String(racaBytes);
                    animaisLidos.add(new Cachorro(nome, idade, cpfDono, raca));
                    break;
            }
        }

        return animaisLidos;
    }
}
