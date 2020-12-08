import java.util.Scanner;
import java.util.Locale;

public class Principal {


    /**
     * Retorna a média de n notas, dadas a soma e a quantidade de notas
     * @param somaDasNotas é a soma de n notas
     * @param quantidadeDeNotas é a quantidade de notas
     * @return a média das notas
     */
    public static float calcularMedia(float somaDasNotas, int quantidadeDeNotas){
        if (quantidadeDeNotas == 0) return 0;
        else return somaDasNotas/quantidadeDeNotas;

    }


    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o dre e a nota do aluno: ");
        long dre = scanner.nextLong();
        float nota = scanner.useLocale(Locale.ENGLISH).nextFloat();
        int quantidadeDeNotas = 0;
        float somaDasNotas = 0, maiorNota = 0;
        long dreMaiorNota = 0;

        while(nota >= 0){
            quantidadeDeNotas++;
            somaDasNotas += nota;
            if (nota > maiorNota){
                dreMaiorNota = dre;
                maiorNota = nota;
            }
            System.out.print("Digite o DRE e a nota do aluno: ");
            dre = scanner.nextLong();
            nota = scanner.useLocale(Locale.ENGLISH).nextFloat();
        }

        float mediaDaTurma = calcularMedia(somaDasNotas, quantidadeDeNotas);

        System.out.printf("\n%d notas digitadas\n", quantidadeDeNotas);
        System.out.printf("Média da turma: %.1f\n", mediaDaTurma);
        System.out.printf("DRE com maior média: %d", dreMaiorNota);
    }


}