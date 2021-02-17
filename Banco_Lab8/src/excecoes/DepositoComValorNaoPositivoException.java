package excecoes;

public class DepositoComValorNaoPositivoException extends Exception{

    private float valorNaoPositivo;

    public DepositoComValorNaoPositivoException(float valorNaoPositivo) {
        this.valorNaoPositivo = valorNaoPositivo;
    }

    public float getValorNaoPositivo() {
        return valorNaoPositivo;
    }
}
