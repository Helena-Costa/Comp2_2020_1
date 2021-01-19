public class DadosTriplos implements Sorteador{

    public int sortear(){
        Dado dado = new Dado();
        int soma = 0;

        for (int i = 0; i < 3; i++){
            soma += dado.sortear();
        }
        return soma;
    }
}
