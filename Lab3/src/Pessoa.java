import java.util.Date;

public class Pessoa {

    private String nome;
    private final long cpf;
    private Date dataDeNascimento;
    private String endereco;

    public Pessoa(String nome, long cpf){
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = "endereco desconhecido";
    }

    public void setEndereco(String endereco){
        if(endereco.length() > 40){
            return;
        }
        this.endereco = endereco;
    }

    public long  getCpf(){
        return this.cpf;
    }

    public String getNome(){
        return this.nome;
    }
}