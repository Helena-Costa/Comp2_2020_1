import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContaCorrenteTest {

    private final float ERRO_ACEITAVEL_NOS_FLOATS = 0.000001f;


    @Test
    public void testarDeposito(){
        Pessoa pessoa1 = new Pessoa("pessoa1", 12345678);
        Agencia agenciaDaPessoa1 = new Agencia();
        ContaCorrente contaDaPessoa1 = new ContaCorrente(111, pessoa1, agenciaDaPessoa1);

        // sanity check
        assertEquals(10, contaDaPessoa1.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        contaDaPessoa1.depositar(1000);

        assertEquals(1010, contaDaPessoa1.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        contaDaPessoa1.depositar(500);

        assertEquals(1510, contaDaPessoa1.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        contaDaPessoa1.depositar(-1);

        assertEquals(1510, contaDaPessoa1.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
    }

    @Test
    public void testarSaqueComNumeroPositivo(){
        Pessoa pessoa2 = new Pessoa("pessoa2", 12345678);
        Agencia agenciaDaPessoa2 = new Agencia();
        ContaCorrente contaDaPessoa2 = new ContaCorrente(222, pessoa2, agenciaDaPessoa2);

        // sanity check
        assertEquals(10f, contaDaPessoa2.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        contaDaPessoa2.sacar(9);

        assertEquals(1f, contaDaPessoa2.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
    }

    @Test
    public void testarSaqueComNumeroNegativo(){
        Pessoa pessoa3 = new Pessoa("pessoa3", 12345678);
        Agencia agenciaDaPessoa3 = new Agencia();
        ContaCorrente contaDaPessoa3 = new ContaCorrente(333, pessoa3, agenciaDaPessoa3);

        // sanity check
        assertEquals(10f, contaDaPessoa3.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        contaDaPessoa3.sacar(-1);

        assertEquals(10f, contaDaPessoa3.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
    }

    @Test
    public void testarTranferenciaComFundosNaContaDeOrigem(){
        Agencia agencia = new Agencia();

        Pessoa pessoa4 = new Pessoa("pessoa4", 44444444);
        Pessoa pessoa5 = new Pessoa("pessoa5", 55555555);

        ContaCorrente contaDaPessoa4 = new ContaCorrente(444, pessoa4, agencia);
        ContaCorrente contaDaPessoa5 = new ContaCorrente(555, pessoa5, agencia);

        // sanity check: as contas já começam com saldo 10 (regra de negócio)
        assertEquals(10f, contaDaPessoa4.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
        assertEquals(10f, contaDaPessoa5.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        contaDaPessoa4.transferir(7, contaDaPessoa5);

        assertEquals(3f, contaDaPessoa4.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
        assertEquals(17f, contaDaPessoa5.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        contaDaPessoa5.transferir(17, contaDaPessoa4);

        assertEquals(20f, contaDaPessoa4.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
        assertEquals(0f, contaDaPessoa5.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

    }

    @Test
    public void testarTranferenciaSemFundosNaContaDeOrigem(){
        Agencia agencia = new Agencia();

        Pessoa pessoa6 = new Pessoa("pessoa6", 66666666);
        Pessoa pessoa7 = new Pessoa("pessoa7", 77777777);

        ContaCorrente contaDaPessoa6 = new ContaCorrente(666, pessoa6, agencia);
        ContaCorrente contaDaPessoa7 = new ContaCorrente(777, pessoa7, agencia);

        // sanity check: as contas já começam com saldo 10 (regra de negócio)
        assertEquals(10f, contaDaPessoa6.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
        assertEquals(10f, contaDaPessoa7.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        contaDaPessoa6.transferir(200, contaDaPessoa7);

        assertEquals(10f, contaDaPessoa6.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
        assertEquals(10f, contaDaPessoa7.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        contaDaPessoa7.transferir(11f, contaDaPessoa6);

        assertEquals(10f, contaDaPessoa6.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
        assertEquals(10f, contaDaPessoa7.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
    }

    @Test
    public void testarTranferenciaComNumeroNegativo(){
        Agencia agencia = new Agencia();

        Pessoa pessoa8 = new Pessoa("pessoa6", 88888888);
        Pessoa pessoa9 = new Pessoa("pessoa7", 99999999);

        ContaCorrente contaDaPessoa8 = new ContaCorrente(888, pessoa8, agencia);
        ContaCorrente contaDaPessoa9 = new ContaCorrente(999, pessoa9, agencia);

        // sanity check: as contas já começam com saldo 10 (regra de negócio)
        assertEquals(10f, contaDaPessoa8.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
        assertEquals(10f, contaDaPessoa9.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        contaDaPessoa8.transferir(-1, contaDaPessoa9);

        assertEquals(10f, contaDaPessoa8.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
        assertEquals(10f, contaDaPessoa9.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        contaDaPessoa9.transferir(-200, contaDaPessoa8);

        assertEquals(10f, contaDaPessoa8.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
        assertEquals(10f, contaDaPessoa9.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
    }























}
