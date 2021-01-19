import java.util.ArrayList;

public abstract class JogoDeDoisJogadores {

    String nomeDoJogo;

    String nomeJogador1;

    String nomeJogador2;

    int numeroDeRodadas;

    int numeroDeRodadasVencidasPeloJogador1;

    int numeroDeRodadasVencidasPeloJogador2;

    int resultadoDaRodada;

    ArrayList<String> historicoResultados;

    public JogoDeDoisJogadores(String nomeDoJogo, String nomeJogador1, String nomeJogador2, int numeroDeRodadas){
        historicoResultados = new ArrayList<>();
        this.nomeDoJogo = nomeDoJogo;
        this.nomeJogador1 = nomeJogador1;
        this.nomeJogador2 = nomeJogador2;
        this.numeroDeRodadas = numeroDeRodadas;
    }

    public String getNomeDoJogo(){
        return nomeDoJogo;
    }

    public String getNomeJogador1(){
        return nomeJogador1;
    }

    public String getNomeJogador2(){
        return nomeJogador2;
    }

    public int getNumeroDeRodadas(){
        return numeroDeRodadas;
    }

    public String jogar(){
        numeroDeRodadasVencidasPeloJogador1 = 0;
        numeroDeRodadasVencidasPeloJogador2 = 0;
        for (int i = 0; i < this.numeroDeRodadas; i++){
            resultadoDaRodada = executarRodadaDoJogo();
            if (resultadoDaRodada == 1){
                numeroDeRodadasVencidasPeloJogador1 += 1;
            }
            if (resultadoDaRodada == 2){
                numeroDeRodadasVencidasPeloJogador2 += 1;
            }
        }

        atualizarHistorico(obterStringDoResultadoDaRodada());

        return obterStringDoResultadoDaRodada();
    }

    protected abstract int executarRodadaDoJogo();

    private String obterStringDoResultadoDaRodada(){
        if (numeroDeRodadasVencidasPeloJogador1 > numeroDeRodadasVencidasPeloJogador2) {
            return String.format("O jogador %s venceu o jogo por %d a %d", nomeJogador1, numeroDeRodadasVencidasPeloJogador1, numeroDeRodadasVencidasPeloJogador2);
        }
        if (numeroDeRodadasVencidasPeloJogador2 > numeroDeRodadasVencidasPeloJogador1) {
            return String.format("O jogador %s venceu o jogo por %d a %d", nomeJogador2, numeroDeRodadasVencidasPeloJogador2, numeroDeRodadasVencidasPeloJogador1);
        }

        return String.format("O jogo terminou em empate após %d rodadas", numeroDeRodadas);
    }

    public void setNumeroDeRodadas(int numeroDeRodadas){
        this.numeroDeRodadas = numeroDeRodadas;
    }

    public ArrayList<String> getHistoricoResultados() {
        return historicoResultados;
    }

    private void atualizarHistorico(String resultado){
        historicoResultados.add(resultado);
    }
}
