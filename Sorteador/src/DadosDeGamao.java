public class DadosDeGamao implements Sorteador{

    public int sortear(){
        Dado dado = new Dado();

        int valor1 = dado.sortear();
        int valor2 = dado.sortear();

        if (valor1 == valor2){
            return 4*valor1;
        }

        return valor1 + valor2;
    }
}
