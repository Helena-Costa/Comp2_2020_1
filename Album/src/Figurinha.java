public class Figurinha {

    private String urlDaImagem;

    private int posicao;

    public Figurinha(int posicao) {
        this.posicao = posicao;
    }

    public String getUrlDaImagem() {
        return urlDaImagem;
    }

    public void setUrlDaImagem(String urlDaImagem) {
        this.urlDaImagem = urlDaImagem;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    /**
     * @return A posição que esta figurinha deve ocupar no álbum
     */
    public int getPosicao() {
       return this.posicao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figurinha outraFigurinha = (Figurinha) o;

        return this.getPosicao() == outraFigurinha.getPosicao();
    }
}

