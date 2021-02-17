package excecoes;

public class TransferenciaComValorNaoPositivoException extends Exception{

    private float valorNaoPositivo;

    public TransferenciaComValorNaoPositivoException(float valorNaoPositivo) {
        this.valorNaoPositivo = valorNaoPositivo;
    }

    public float getValorNaoPositivo() {
        return valorNaoPositivo;
    }
}
