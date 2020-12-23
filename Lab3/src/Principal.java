import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args){


        Agencia agenciaPadrao = new Agencia();
        ArrayList<ContaCorrente> arrayContaCorrente = new ArrayList<>();
        ArrayList<Pessoa> arrayCorrentistas = new ArrayList<>();

        int numeroDaContaDeOrigem;
        int numeroDaContaDeDestino;
        ContaCorrente contaOrigem;
        ContaCorrente contaDestino;
        long cpf;
        Pessoa correntista;
        String nome;

        while(true){
            System.out.println("\n=============MENU=============");
            System.out.println("(D)epositar");
            System.out.println("(S)acar");
            System.out.println("(T)ransferir");
            System.out.println("(C)onsultar saldo");
            System.out.println("Cadastrar (P)essoa como correntista");
            System.out.println("Criar (N)ova conta");
            System.out.println("(X) para sair");
            System.out.println("===============================");
            System.out.println("");
            System.out.print("Opção desejada: ");

            Scanner scanner = new Scanner(System.in);
            String opcao = scanner.nextLine();
            opcao = opcao.toUpperCase();
            opcao = opcao.replaceAll(" ", "");
            String entradaDoUsuario;

            if (opcao.equals("X")){
                System.out.println("Finalizando programa...");
                break;
            }

            switch(opcao){
                case("D"):
                    System.out.print("Digite o número da conta: ");
                    numeroDaContaDeOrigem = scanner.nextInt();
                    if(arrayContaCorrente.size() == 0 || numeroDaContaDeOrigem > arrayContaCorrente.size()){
                        System.out.println("Número de conta inválido.");
                        continue;
                    }
                    contaOrigem = arrayContaCorrente.get(numeroDaContaDeOrigem - 1);

                    System.out.print("Digite o valor que deseja depositar: R$ ");
                    float valorParaDeposito = scanner.useLocale(Locale.ENGLISH).nextFloat();

                    contaOrigem.depositar(valorParaDeposito);
                    ArrayList<String> historicoDeposito = contaOrigem.getHistoricoDeOperacoes();

                    System.out.println(historicoDeposito.get(historicoDeposito.size() - 1));

                    break;
                case("S"):
                    System.out.print("Digite o número da conta: ");
                    numeroDaContaDeOrigem = scanner.nextInt();
                    if(arrayContaCorrente.size() == 0 || numeroDaContaDeOrigem > arrayContaCorrente.size()){
                        System.out.println("Desculpe, número da conta inválido.");
                        continue;
                    }
                    contaOrigem = arrayContaCorrente.get(numeroDaContaDeOrigem - 1);

                    System.out.print("Digite o valor que deseja sacar: R$ ");
                    float valorParaSaque = scanner.useLocale(Locale.ENGLISH).nextFloat();


                    if(valorParaSaque <= contaOrigem.getSaldoEmReais() && valorParaSaque > 0){
                        contaOrigem.sacar(valorParaSaque);
                        ArrayList<String> historicoSaque = contaOrigem.getHistoricoDeOperacoes();
                        System.out.println(historicoSaque.get(historicoSaque.size() - 1));
                    }else{
                        System.out.print("Não foi possível realizar o saque.");
                        System.out.println(" Sem fundos na conta.");
                    }

                    break;
                case("T"):
                    System.out.print("Digite o número da conta de origem: ");
                    numeroDaContaDeOrigem = scanner.nextInt();
                    if(arrayContaCorrente.size() == 0 || numeroDaContaDeOrigem > arrayContaCorrente.size()){
                        System.out.println("Numero de conta invalido");
                        continue;
                    }

                    contaOrigem = arrayContaCorrente.get(numeroDaContaDeOrigem - 1);

                    System.out.print("Digite o número da conta de destino: ");
                    numeroDaContaDeDestino = scanner.nextInt();
                    if(arrayContaCorrente.size() == 0 || numeroDaContaDeOrigem > arrayContaCorrente.size()){
                        System.out.println("Número da conta inválido.");
                        continue;
                    }

                    contaDestino = arrayContaCorrente.get(numeroDaContaDeDestino - 1);

                    System.out.print("Digite o valor que deseja transferir: R$ ");
                    float valorParaTranferencia = scanner.useLocale(Locale.ENGLISH).nextFloat();

                    if(valorParaTranferencia <= contaOrigem.getSaldoEmReais() && valorParaTranferencia > 0){
                        contaOrigem.transferir(valorParaTranferencia, contaDestino);
                        ArrayList<String> historicoTransferencia = contaOrigem.getHistoricoDeOperacoes();
                        System.out.println(historicoTransferencia.get(historicoTransferencia.size() - 1));
                    }else{
                        System.out.println("Não foi possível realizar a tranferência");
                        System.out.println("Sem fundos na conta de origem");
                    }

                    break;
                case("C"):
                    System.out.print("Digite o número da conta: ");
                    int numeroDaConta = scanner.nextInt();
                    if(arrayContaCorrente.size() == 0 || numeroDaConta > arrayContaCorrente.size()){
                        System.out.println("Numero de conta invalido");
                        continue;
                    }
                    contaOrigem = arrayContaCorrente.get(numeroDaConta - 1);
                    System.out.print(String.format("%s, o saldo da sua conta é R$%.2f.\n",contaOrigem.getCorrentista().getNome(), contaOrigem.getSaldoEmReais()));
                    break;
                case("P"):
                    System.out.print("Digite o nome do correntista: ");
                    nome = scanner.nextLine();
                    System.out.print("Digite o CPF do correntista: ");

                    entradaDoUsuario = scanner.nextLine();
                    while(!apenasNumero(entradaDoUsuario) || entradaDoUsuario.length() != 11){
                        System.out.print("CPF inválido. Por favor, digite uma sequência de 11 dígitos: ");
                        entradaDoUsuario = scanner.nextLine();
                    }

                    cpf = Long.parseLong(entradaDoUsuario);
                    correntista = new Pessoa(nome, cpf);
                    arrayCorrentistas.add(correntista);
                    System.out.printf("Correntista %s (CPF %d) cadastrado(a) com sucesso.\n", nome, cpf);
                    break;
                case("N"):
                    System.out.print("Digite o CPF do correntista: ");
                    entradaDoUsuario = scanner.nextLine();
                    while(!apenasNumero(entradaDoUsuario) || entradaDoUsuario.length() != 11){
                        System.out.print("CPF inválido. Por favor, digite uma sequência de 11 dígitos: ");
                        entradaDoUsuario = scanner.nextLine();
                    }

                    cpf = Long.parseLong(entradaDoUsuario);

                    boolean cadastroEncontrado = false;
                    for(int ind = 0; ind < arrayCorrentistas.size(); ind++){
                        correntista = arrayCorrentistas.get(ind);
                        if(correntista.getCpf() == cpf){
                            nome = correntista.getNome();
                            correntista = new Pessoa(nome, cpf);
                            ContaCorrente novaConta = new ContaCorrente(correntista, agenciaPadrao);
                            arrayContaCorrente.add(novaConta);
                            System.out.printf("Conta corrente de %s criada com sucesso ", novaConta.getCorrentista().getNome());
                            System.out.print(String.format("(numero da conta: %d).\n", novaConta.getNumeroDaConta()));
                            cadastroEncontrado = true;
                            break;
                        }
                    }
                    if(!cadastroEncontrado){
                        System.out.println("Esse CPF não está cadastrado como correntista no sistema.");
                        System.out.println("Para cadastrá-lo, por favor, pressione a tecla 'P'.");
                    }
                    break;
                default:
                    System.out.println("Entrada inválida. Por favor, escolha uma opção");
            }
        }
    }

    public static boolean apenasNumero(String entradaDoUsuario){
        char[] chars = entradaDoUsuario.toCharArray();

        for(char c : chars){
            if(!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
}
