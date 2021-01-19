public class Principal {
    public static void main(String[] args) {

        DadosDeGamao dadoDaMaria = new DadosDeGamao() ;
        DadosTriplos dadoDoJoao = new DadosTriplos();

        JogoMalucoComSorteadores jogo = new JogoMalucoComSorteadores("Jogo", "Maria", "Joao", 0, dadoDaMaria, dadoDoJoao);

        for (int numeroDeRodadas = 1; numeroDeRodadas <= 100; numeroDeRodadas++) {
            jogo.setNumeroDeRodadas(numeroDeRodadas);
            jogo.jogar();
            System.out.println(jogo.jogar());
        }
    }
}
