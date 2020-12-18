public class Principal {

    public static void main(String[] args){


        Pessoa pessoa = new Pessoa("Maria", 123);
        pessoa.setEndereco("Rua A");
        Agencia minhaAgencia = new Agencia();

        ContaCorrente minhaConta = new ContaCorrente(123, pessoa, minhaAgencia);

        minhaConta.depositar(1000f);

        System.out.println(minhaConta.getSaldoEmReais());
        System.out.println(minhaConta.getHistoricoDeOperacoes());

        minhaConta.depositar(54f);

        System.out.println(minhaConta.getSaldoEmReais());
        System.out.println(minhaConta.getHistoricoDeOperacoes());
        System.out.println(pessoa);

    }
}
