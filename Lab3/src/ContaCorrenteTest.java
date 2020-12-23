import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContaCorrenteTest {

    private static final float ERRO_ACEITAVEL_NOS_FLOATS = 0.000001f;
    private Pessoa maria;
    private Pessoa joao;
    private Agencia agencia;
    private ContaCorrente contaDaMaria;
    private ContaCorrente contaDoJoao;

    @Before
    public void setUp(){
        maria = new Pessoa("maria", 12345678);
        joao = new Pessoa("joao", 23222);

        agencia = new Agencia();

        contaDaMaria = new ContaCorrente(maria, agencia);
        contaDoJoao = new ContaCorrente(joao, agencia);

        assertFloatsEquals(ContaCorrente.SALDO_INICIAL_DE_NOVAS_CONTAS, contaDaMaria.getSaldoEmReais());
        assertFloatsEquals(ContaCorrente.SALDO_INICIAL_DE_NOVAS_CONTAS, contaDoJoao.getSaldoEmReais());
    }

    @Test
    public void testarNumerosAutomaticosDeContas(){
        assertEquals(1, contaDaMaria.getNumeroDaConta());
        assertEquals(2, contaDoJoao.getNumeroDaConta());
    }

    @Test
    public void testarDeposito(){

        //sanity check
        checarSaldoInicial(contaDaMaria);

        contaDaMaria.depositar(1000);
        assertFloatsEquals(1010f, contaDaMaria.getSaldoEmReais());

        contaDaMaria.depositar(500);
        assertFloatsEquals(1510f, contaDaMaria.getSaldoEmReais());

        contaDaMaria.depositar(-900);
        assertFloatsEquals(1510, contaDaMaria.getSaldoEmReais());
    }

    @Test
    public void testarSaque(){

        //sanity check
        checarSaldoInicial(contaDaMaria);

        contaDaMaria.sacar(7);

        assertFloatsEquals(3f, contaDaMaria.getSaldoEmReais());
    }

    @Test
    public void testarSaqueSemFundos(){

        //sanity check
        checarSaldoInicial(contaDaMaria);

        contaDaMaria.sacar(17);

        assertFloatsEquals(10f, contaDaMaria.getSaldoEmReais());
    }

    @Test
    public void testarTransferencia(){

        //sanity check
        checarSaldoInicial(contaDaMaria);
        checarSaldoInicial(contaDoJoao);

        contaDaMaria.transferir(7, contaDoJoao);

        assertFloatsEquals(3f, contaDaMaria.getSaldoEmReais());
        assertFloatsEquals(17f, contaDoJoao.getSaldoEmReais());
    }

    @Test
    public void testarTransferenciaSemFundos(){

        //sanity check
        checarSaldoInicial(contaDaMaria);
        checarSaldoInicial(contaDoJoao);

        contaDaMaria.transferir(200f, contaDoJoao);

        checarSaldoInicial(contaDaMaria);
        checarSaldoInicial(contaDoJoao);
    }

    private void checarSaldoInicial(ContaCorrente conta){

        //sanity check
        assertEquals(ContaCorrente.SALDO_INICIAL_DE_NOVAS_CONTAS, conta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
    }

    private static void assertFloatsEquals(float expected, float actual){

        assertEquals(expected, actual, ERRO_ACEITAVEL_NOS_FLOATS);
    }
}