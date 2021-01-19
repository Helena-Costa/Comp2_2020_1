public class JogoMalucoComSorteadores extends JogoDeDoisJogadores{

    Sorteador sorteador1;

    Sorteador sorteador2;

    int inteiroParaOJogador1;

    int inteiroParaOJogador2;

    public JogoMalucoComSorteadores(String nomeDoJogo, String nomeJogador1, String nomeJogador2, int numeroDeRodadas, Sorteador sorteador1, Sorteador sorteador2) {
        super(nomeDoJogo, nomeJogador1, nomeJogador2, numeroDeRodadas);
        this.sorteador1 = sorteador1;
        this.sorteador2 = sorteador2;
    }

    @Override
    protected int executarRodadaDoJogo() {
        inteiroParaOJogador1 = sorteador1.sortear();
        inteiroParaOJogador2 = sorteador2.sortear();
        if (inteiroParaOJogador1 > inteiroParaOJogador2){
            return 1;
        }
        if (inteiroParaOJogador2 > inteiroParaOJogador1){
            return 2;
        }
        return 0;
    }
}
