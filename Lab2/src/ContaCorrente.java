import java.util.ArrayList;
import java.util.Date;

public class ContaCorrente {

    private long numeroDaConta;

    private Agencia agencia;

    private float saldoEmReais;

    private Date dataDeCriacao;

    private Pessoa correntista;

    private ArrayList<String> historicoDeOperacoes = new ArrayList<String>();

    /**
     * Método que retorna o saldo da conta corrente
     * @return saldo da conta corrente
     */
    public float getSaldoEmReais(){
        return saldoEmReais;
    }

    /**
     * Método construtor da classe ContaCorrente
     * @param numeroDaConta número identificador da conta
     * @param correntista titular da conta
     * @param agencia agência à qual pertence a conta
     */
    public ContaCorrente(long numeroDaConta, Pessoa correntista, Agencia agencia){
        this.numeroDaConta = numeroDaConta;
        this.correntista = correntista;
        this.agencia = agencia;
        this.historicoDeOperacoes = new ArrayList<>();
        this.dataDeCriacao = new Date();
        this.saldoEmReais = 10;
    }

    /**
     * Método que retorna o histórico de operações da conta corrente
     * @return o histórico de operações da conta corrente
     */
    public ArrayList<String> getHistoricoDeOperacoes(){
        return this.historicoDeOperacoes;
    }

    /**
     * Deposita o valor passado como parâmetro no saldo da conta,
     * caso esse valor seja positivo.
     * Caso contrário, nada é feito.
     * @param deposito Valor que se deseja depositar.
     */
    public void depositar(float deposito){
        if(deposito <= 0){
            return; // toDO lançar exceção!!!
        }

        this.saldoEmReais += deposito;
        this.historicoDeOperacoes.add(String.format("Deposito em dinheiro de %.2f na data %s", deposito, new Date()));

    }

    /**
     * Saca o valor passado como parâmetro do saldo da conta,
     * caso esse valor seja positivo e menor ou igual ao saldo.
     * Caso contrário, nada é feito.
     * @param saque Valor que se deseja sacar.
     */
    public void sacar(float saque){
        if (saque <= 0 || saque > this.saldoEmReais){
            return;
        }

        this.saldoEmReais -= saque;
        this.historicoDeOperacoes.add(String.format("Saque em dinheiro de %.2f na data %s", saque, new Date()));
    }

    /**
     * Tranfere valor passado como parâmetro de uma conta para a outra,
     * caso o valor seja positivo e menor ou igual ao saldo da conta de destino.
     * Caso contário, nada é feito.
     * @param tranferencia Valor que se deseja transferir
     * @param contaDeDestino Conta para a qual se deseja tranferir o valor.
     */
    public void transferir(float tranferencia, ContaCorrente contaDeDestino){
        if (tranferencia > this.saldoEmReais || tranferencia <= 0){
            return;
        }
        this.saldoEmReais -= tranferencia;
        contaDeDestino.depositar(tranferencia);
    }
}
