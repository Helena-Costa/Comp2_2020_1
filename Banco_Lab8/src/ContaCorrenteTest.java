import excecoes.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContaCorrenteTest {

    private static final float ERRO_ACEITAVEL_NOS_FLOATS = 0.000001f;

    private Pessoa maria;
    private Pessoa joao;

    private Banco banco;

    private Agencia minhaAgencia;

    private ContaCorrente contaDaMaria;
    private ContaCorrente contaDoJoao;

    @Before
    public void setUp() {
        banco = new Banco();

        // cria algumas pessoas
        maria = new Pessoa("Maria", 12345678);
        joao = new Pessoa("Joao", 23222);

        // cria uma agencia
        minhaAgencia = new Agencia(banco, 1, "Agência Principal");

        ContaCorrente.numeroDeContasCriadas = 0;  // reseta o static da classe

        // cria algumas contas
        contaDaMaria = new ContaCorrente(maria, minhaAgencia);
        contaDoJoao = new ContaCorrente(joao, minhaAgencia);
    }

    @Test
    public void testarNumerosAutomaticosDeContas() {
        assertEquals(1, contaDaMaria.getNumeroDaConta());
        assertEquals(2, contaDoJoao.getNumeroDaConta());
    }

    @Test
    public void testeFuncionamentoNormalDeDeposito() throws DepositoComValorNaoPositivoException {
        checarSaldoInicial(contaDaMaria);

        contaDaMaria.depositar(1000);
        assertFloatEquals(1010f, contaDaMaria.getSaldoEmReais());

        contaDaMaria.depositar(500);
        assertFloatEquals(1510f, contaDaMaria.getSaldoEmReais());

        contaDaMaria.depositar(10);
        assertFloatEquals(1520f, contaDaMaria.getSaldoEmReais());
   }

   @Test
   public void testeSituacaoDeTentativaDeDepositoComValorNaoPositivo() {
       rodarTesteSituacaoDeTentativaDeDepositoComValorNaoPositivo(0);
       rodarTesteSituacaoDeTentativaDeDepositoComValorNaoPositivo(-1);
   }

   private void rodarTesteSituacaoDeTentativaDeDepositoComValorNaoPositivo(float valor) {
        checarSaldoInicial(contaDaMaria);

        try {
            contaDaMaria.depositar(valor);
            fail();
        } catch (DepositoComValorNaoPositivoException e) {
            //ok
        }
   }

    @Test
    public void testeFuncionamentoNormalDeSaque() throws SaqueComValorNaoPositivoException, SaldoInsuficienteParaSaqueException {
        checarSaldoInicial(contaDaMaria);
        contaDaMaria.sacar(7);
        assertEquals(3f, contaDaMaria.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
    }

    @Test
    public void testeSituacaoDeSaldoInsuficienteParaSaque() throws SaqueComValorNaoPositivoException {
        checarSaldoInicial(contaDaMaria);
        try {
            contaDaMaria.sacar(contaDaMaria.getSaldoEmReais() + ContaCorrente.LIMITE_CHEQUE_ESPECIAL + 1);
            assertFloatEquals(10f, contaDaMaria.getSaldoEmReais());
            fail("Uma SaldoInsuficienteException deve ser produzida quando o parâmetro exceder saldoEmReais + LIMITE_CHEQUE_ESPECIAL");
        } catch(SaldoInsuficienteParaSaqueException e){
            //ok
        }
    }

    @Test
    public void testeSituacaoDeTentativaDeSaqueComValorNaoPositivoException() throws SaqueComValorNaoPositivoException, SaldoInsuficienteParaSaqueException {
        rodarTesteSituacaoDeTentativaDeSaqueComValorNaoPositivoException(0);
        rodarTesteSituacaoDeTentativaDeSaqueComValorNaoPositivoException(-10);
    }

    private void rodarTesteSituacaoDeTentativaDeSaqueComValorNaoPositivoException(float valor) throws SaqueComValorNaoPositivoException, SaldoInsuficienteParaSaqueException {
        checarSaldoInicial(contaDaMaria);
        try {
            contaDaMaria.sacar(valor);
        } catch (SaqueComValorNaoPositivoException e) {
            //ok
        }
    }

    @Test
    public void testeFuncionamentoNormalDeTransferencia() throws SaldoInsuficienteParaTransferenciaException, TransferenciaComValorNaoPositivoException {
        // sanity check: as contas já começam com saldo 10 (regra de negócio)
        checarSaldoInicial(contaDaMaria);
        checarSaldoInicial(contaDoJoao);

        contaDaMaria.transferir(7, contaDoJoao);

        assertFloatEquals(3f, contaDaMaria.getSaldoEmReais());
        assertFloatEquals(17f, contaDoJoao.getSaldoEmReais());
    }

    @Test
    public void testeSituacaoDeSaldoInsuficienteParaTransferencia() throws TransferenciaComValorNaoPositivoException {
        try {
            // sanity check: as contas já começam com saldo 10 (regra de negócio)
            assertFloatEquals(10f, contaDaMaria.getSaldoEmReais());
            assertFloatEquals(10f, contaDoJoao.getSaldoEmReais());

            // a transferência NÃO DEVE SER REALIZADA, porque não há fundos na conta de origem (Maria).
            contaDaMaria.transferir(contaDaMaria.getSaldoEmReais() + ContaCorrente.LIMITE_CHEQUE_ESPECIAL + 1, contaDoJoao);

            assertFloatEquals(10f, contaDaMaria.getSaldoEmReais());
            assertFloatEquals(10f, contaDoJoao.getSaldoEmReais());
            fail();
        } catch (SaldoInsuficienteParaTransferenciaException e) {
            //ok
        }
    }

    @Test
    public void testeSituacaoDeTentativaDeTransferenciaComValorNaoPositivo() throws SaldoInsuficienteParaTransferenciaException {
        rodarTesteSituacaoDeTentativaDeTransferenciaComValorNaoPositivo(0);
        rodarTesteSituacaoDeTentativaDeTransferenciaComValorNaoPositivo(-1);
    }

    private void rodarTesteSituacaoDeTentativaDeTransferenciaComValorNaoPositivo(float valor) throws SaldoInsuficienteParaTransferenciaException {
        checarSaldoInicial(contaDaMaria);
        checarSaldoInicial(contaDoJoao);

        try {
            contaDaMaria.transferir(valor, contaDoJoao);
        } catch (TransferenciaComValorNaoPositivoException e) {
            //ok
        }
    }

    private void checarSaldoInicial(ContaCorrente conta) {
        // sanity check: as contas já começam com saldo 10 (regra de negócio)
        assertFloatEquals(
                ContaCorrente.SALDO_INICIAL_DE_NOVAS_CONTAS,
                conta.getSaldoEmReais());
    }

    private static void assertFloatEquals(float expected, float actual) {
        assertEquals(expected, actual, ERRO_ACEITAVEL_NOS_FLOATS);
    }
}