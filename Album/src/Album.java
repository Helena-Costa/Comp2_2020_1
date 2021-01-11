import java.util.ArrayList;

public class Album {

    private int quantPacotinhosComprados;

    public static final int PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR = 90;  // 90%

    private final int quantFigurinhasPorPacotinho;

    private final int tamanhoDoAlbum;

    private Figurinha[] album;

    private ArrayList<Figurinha> figurinhasRepetidas;

    public Album(int tamanhoDoAlbum, int quantFigurinhasPorPacotinho) {
        this.tamanhoDoAlbum = tamanhoDoAlbum;
        this.quantFigurinhasPorPacotinho = quantFigurinhasPorPacotinho;
        this.album = new Figurinha[tamanhoDoAlbum + 1];
        this.figurinhasRepetidas = new ArrayList<>();
    }

    public void receberNovoPacotinho(Pacotinho pacotinho) {
        this.quantPacotinhosComprados++;
        for (Figurinha fig : pacotinho) {
            if (album[fig.getPosicao()] == null) {
                album[fig.getPosicao()] = fig;
            }else{
                this.figurinhasRepetidas.add(fig);
            }
        }
    }

    public void autoCompletar() {
        // verifica se o álbum já está suficientemente cheio
        // caso não esteja, isto é, se o número de figurinhas coladas não representa mais de 90% do total de figurinhas,
        // então o álbum não pode se auto completar.

        if (this.getQuantFigurinhasFaltantes() == 0) {
            return;
        }

        int minimoFigurinhasColadasParaAutoCompletar = (int)(tamanhoDoAlbum * PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR/100.0f);
        if (!(this.getQuantFigurinhasColadas() > minimoFigurinhasColadasParaAutoCompletar)){
            return;
        }

        for (int i = 1; i <= tamanhoDoAlbum; i++){
            if (album[i] == null){
                Figurinha figurinha = new Figurinha(i);
                colarFigurinha(figurinha);
            }
        }
    }

    public void colarFigurinha(Figurinha figurinha){
        this.album[figurinha.getPosicao()] = figurinha;
    }

    public int getQuantFigurinhasColadas() {
        int quantFigurinhasColadas = 0;
        for (int i = 1; i < this.album.length; i++) {
            if (album[i] != null){
                quantFigurinhasColadas++;
            }
        }
        return quantFigurinhasColadas;
    }

    public int getQuantFigurinhasRepetidas() {
        return figurinhasRepetidas.size();
    }

    public boolean possuiFigurinhaColada(int posicao) {
        if(posicao < 1 || posicao > this.getTamanhoDoAlbum()) {
            return false;
        }
        if (album[posicao] != null) {
            return true;
        }
        return false;
    }

    public boolean possuiFigurinhaRepetida(int posicao) {
        for (Figurinha fig : figurinhasRepetidas){
            if (fig.getPosicao() == posicao) {
                return true;
            }
        }
        return false;
    }

    public int getQuantFigurinhasFaltantes() {
        return this.tamanhoDoAlbum - this.getQuantFigurinhasColadas();
    }

    public int getTamanhoDoAlbum() {
        return tamanhoDoAlbum;
    }

    public int getQuantFigurinhasPorPacotinho() {
        return quantFigurinhasPorPacotinho;
    }

    public int getQuantPacotinhosComprados() {
        return quantPacotinhosComprados;
    }
}
