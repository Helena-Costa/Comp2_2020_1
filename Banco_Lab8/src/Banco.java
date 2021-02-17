import excecoes.ContaCorrenteNaoExistenteException;
import excecoes.PessoaNaoCadastradaException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Banco {

    Map<Long, ContaCorrente> contaPorNumero;
    Map<Long, Pessoa> correntistaPorCpf;

    Set<Pessoa> correntistasComSaldoNegativo;

    private Agencia agenciaMatriz;

    public Banco() {
        contaPorNumero = new HashMap<>();
        correntistaPorCpf = new HashMap<>();
        correntistasComSaldoNegativo = new HashSet<>();
        agenciaMatriz = new Agencia(this, 1, "Agência Um");
    }

    public Pessoa cadastrarCorrentista(String nome, long cpf) {
        Pessoa p;
        try {
            p = localizarCorrentista(cpf);
            // Localizei o correntista! Não é preciso recriá-lo! Podemos atualizar o nome, se for o caso.
            p.setNome(nome);
        } catch (PessoaNaoCadastradaException e) {
            // Correntista novo
            p = new Pessoa(nome, cpf);
            correntistaPorCpf.put(cpf, p);
        }
        return p;
    }

    public ContaCorrente cadastrarConta(Pessoa correntista) throws PessoaNaoCadastradaException {
        // verifica correntista
        // a exceção PessoaNaoCadastradaException é lançada caso a pessoa não esteja cadastrada
        localizarCorrentista(correntista.getCpf());

        // aceitamos mais de uma conta para o mesmo correntista

        ContaCorrente novaConta = new ContaCorrente(correntista, this.agenciaMatriz);
        this.contaPorNumero.put(novaConta.getNumeroDaConta(), novaConta);

        return novaConta;
    }

    public Pessoa localizarCorrentista(long cpf) throws PessoaNaoCadastradaException {
        Pessoa correntista = this.correntistaPorCpf.get(cpf);
        if (correntista == null) {
            throw new PessoaNaoCadastradaException();
        } else {
            return correntista;
        }
    }

    public ContaCorrente localizarConta(long numeroDaConta) throws ContaCorrenteNaoExistenteException {
        ContaCorrente contaCorrente = this.contaPorNumero.get(numeroDaConta);
        if (contaCorrente == null) {
            throw new ContaCorrenteNaoExistenteException();
        }
        return contaCorrente;
    }

    void registrarCorrentistaComSaldoNegativo(Pessoa correntista) {
        this.correntistasComSaldoNegativo.add(correntista);
    }

    Set<Pessoa> getCorrentistasComSaldoNegativo() {
        return this.correntistasComSaldoNegativo;
    }
}
