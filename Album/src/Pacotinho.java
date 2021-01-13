import java.util.ArrayList;
import java.util.Random;

public class Pacotinho extends ArrayList<Figurinha> {

    private Album album;

    public Pacotinho(Album album) {
        this.album = album;
        adicionarFigurinhasAleatorias();
    }

    // sobrecarga no costrutor, passando aqui as posições desejadas
    public Pacotinho(Album album, int[] posicoes) {
        this.album = album;

        // verificar se o tamanho do array está correto;
        // caso não esteja, throw new RuntimeException("Pacotinho no tamanho errado!");
        if (posicoes.length > album.getQuantFigurinhasPorPacotinho()) {
            throw new RuntimeException("Pacotinho do tamanho errado!");
        }

        for (int i = 0; i < posicoes.length; i++) {
            Figurinha figurinha = new Figurinha(posicoes[i]);
            this.add(figurinha);
        }
    }

    private void adicionarFigurinhasAleatorias() {
        Random aleatorio  = new Random();
        int maxPosicao = album.getTamanhoDoAlbum();
        int quantFigurinhasPorPacotinho = album.getQuantFigurinhasPorPacotinho();

        for (int i = 1; i <= quantFigurinhasPorPacotinho; i++) {
            // sorteia uma posição entre 1 e o tamanho do álbum
            int posicao = aleatorio.nextInt(maxPosicao) + 1;

            // cria um novo objeto Figurinha informando a posição sorteada
            Figurinha figurinha = new Figurinha(posicao);

            // adiciona ao pacotinho
            this.add(figurinha);
        }
    }

    public Album getAlbum() {
        return this.album;
    }
}
