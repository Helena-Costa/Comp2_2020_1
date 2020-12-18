import java.util.Date;

public class Pessoa {

    private String nome;

    private final long cpf;

    private Date dataDeNascimento;

    private String endereco;

    public Pessoa(String nome, long cpf){
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = "Endereço desconhecido";
    }

    public void setEndereco(String endereco){
        if (endereco.length() > 40){
            return;
        }
        this.endereco = endereco;
    }

    public String getEndereco(){
        return this.endereco;
    }

    public long getCpf(){
        return this.cpf;
    }
}
