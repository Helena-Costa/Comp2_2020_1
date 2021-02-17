package excecoes;

public class SaldoInsuficienteParaTransferenciaException extends Exception{

    private float valorAlemDoLimite;

    public SaldoInsuficienteParaTransferenciaException(float valorAlemDoLimite) {
        this.valorAlemDoLimite = valorAlemDoLimite;
    }

    public float getValorAlemDoLimite() {
        return this.valorAlemDoLimite;
    }
}
