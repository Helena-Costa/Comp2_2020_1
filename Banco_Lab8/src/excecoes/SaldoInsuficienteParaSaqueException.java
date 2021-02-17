package excecoes;

public class SaldoInsuficienteParaSaqueException extends Exception {

    private float valorAlemDoLimite;

    public SaldoInsuficienteParaSaqueException(float valorAlemDoLimite) {

        this.valorAlemDoLimite = valorAlemDoLimite;
    }

    public float getValorAlemDoLimite() {
        return valorAlemDoLimite;
    }
}
