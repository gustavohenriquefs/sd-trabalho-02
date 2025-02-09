package br.ufc.quixada.stream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import br.ufc.quixada.entities.Animal;
import br.ufc.quixada.entities.Cachorro;
import br.ufc.quixada.entities.Coelho;
import br.ufc.quixada.entities.Estoque;
import br.ufc.quixada.entities.Gato;
import br.ufc.quixada.models.IAnimal;

public class AnimaisOutputStream extends OutputStream {

    private final Estoque estoque;
    private final OutputStream outputStream;
    private int currentPosition = 0;
    private byte[] buffer;

    public AnimaisOutputStream(Estoque estoque, OutputStream outputStream) {
        this.estoque = estoque;
        this.outputStream = outputStream;
        this.buffer = new byte[1024];
    }

    @Override
    public void write(int b) throws IOException {
        if (currentPosition >= buffer.length) {
            flush();
        }
        buffer[currentPosition++] = (byte) b;
    }

    @Override
    public void flush() throws IOException {
        outputStream.write(buffer, 0, currentPosition);
        currentPosition = 0;
    }

    public void writeAnimais() throws IOException {
        DataOutputStream dos = new DataOutputStream(outputStream);
        List<IAnimal> animais = estoque.getAnimais();

        // Write total number of animals
        dos.writeInt(animais.size());

        // Write each animal's data
        for (IAnimal animal : animais) {
            byte[] nomeBytes = animal.getNome().getBytes();
            byte[] cpfDonoBytes = animal.getCpfDono().getBytes();

            // Write animal type
            if (animal instanceof Cachorro) {
                dos.writeInt(1); 
            } else if (animal instanceof Gato) {
                dos.writeInt(2);
            } else if (animal instanceof Coelho) {
                dos.writeInt(3);
            }

            // Write animal name
            dos.writeInt(nomeBytes.length);
            dos.write(nomeBytes);

            // Write animal age
            dos.writeInt(animal.getIdade());

            // Write owner's CPF
            dos.writeInt(cpfDonoBytes.length);

            dos.write(cpfDonoBytes);

            // Write specific attributes
            if (animal instanceof Cachorro) {
                Cachorro cachorro = (Cachorro) animal;
                byte[] racaBytes = cachorro.getRaca().getBytes();
                dos.writeInt(racaBytes.length);
                dos.write(racaBytes);
            } else if (animal instanceof Gato) {
                Gato gato = (Gato) animal;
                byte[] corBytes = gato.getCor().getBytes();
                dos.writeInt(corBytes.length);
                dos.write(corBytes);
            } else if (animal instanceof Coelho) {
                Coelho coelho = (Coelho) animal;
                var corPeloBytes = coelho.getCor().getBytes();
                dos.writeInt(corPeloBytes.length);
                dos.write(corPeloBytes);
            }
        }

        dos.flush();
    }
}
