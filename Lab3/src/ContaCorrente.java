import java.util.ArrayList;
import java.util.Date;

public class ContaCorrente {

    private static long contadorNumeroDasContas = 0L;
    public static final float SALDO_INICIAL_DE_NOVAS_CONTAS = 10.0f;
    private final long numeroDaConta;
    private final Agencia agencia;
    private float saldoEmReais;
    private Date dataDeCriacao;
    private Pessoa correntista;
    private ArrayList<String> historicoDeOperacoes;
    private Pessoa gerenteDaConta;

    public ContaCorrente(Pessoa correntista, Agencia agencia){
        this.historicoDeOperacoes = new ArrayList<>();
        this.dataDeCriacao = new Date();
        this.saldoEmReais = SALDO_INICIAL_DE_NOVAS_CONTAS;
        contadorNumeroDasContas = contadorNumeroDasContas + 1L;
        this.numeroDaConta = contadorNumeroDasContas;
        this.correntista = correntista;
        this.agencia = agencia;
    }

    public long getNumeroDaConta(){
        return this.numeroDaConta;
    }

    public float getSaldoEmReais(){
        return this.saldoEmReais;
    }

    public void depositar(float valor){
        if(valor <= 0){
            return;
        }
        this.saldoEmReais += valor;
        this.historicoDeOperacoes.add(String.format("Depósito em dinheiro de R$%.2f na data %s.", valor, new Date()));
    }

    public void sacar(float valor){
        // valida o parametro
        if(valor <= 0){
            return; // ToDo lançar uma exceção
        }

        //verifica se há fundos na conta
        if(valor > this.saldoEmReais){
            return; // ToDo lançar uma exceção
        }

        this.saldoEmReais -= valor;
        this.historicoDeOperacoes.add(String.format("Saque em dinheiro de R$%.2f na data %s.", valor, new Date()));

    }

    /**
     * Transfere um valor desta conta para a conta destino informada, se houver saldo suficiente nesta conta.
     * @param valor o valor desejado
     * @param contaDestino a conta destino
     */
    public void transferir(float valor, ContaCorrente contaDestino){
        // valida o parametro
        if(valor <= 0){
            return; // ToDo lançar uma exceção
        }

        //verifica se há fundos na conta
        if(valor > this.saldoEmReais){
            return; // ToDo lançar uma exceção
        }

        this.saldoEmReais -= valor;
        contaDestino.saldoEmReais += valor;

        this.historicoDeOperacoes.add(String.format("Transferência efetuada para a conta %d: R$%.2f na data %s.", contaDestino.numeroDaConta, valor, new Date()));
        contaDestino.historicoDeOperacoes.add(String.format("Transferência recebida da conta %d: R$%.2f na data %s.", this.numeroDaConta, valor, new Date()));

    }

    public ArrayList<String> getHistoricoDeOperacoes(){
        return this.historicoDeOperacoes;
    }

    public Pessoa getCorrentista(){
        return this.correntista;
    }

}