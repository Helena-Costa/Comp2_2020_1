import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Loja virtual para a venda de livros (a princípio).
 */
public class Loja {

    private static final int NUMERO_DE_PEDIDOS_POR_RELATORIO = 1;

    private String nomeDaLoja;

    private ArrayList<Vendavel> catalogo;

    private Map<Vendavel, Integer> quantidadeEmEstoquePorItem;

    private Transportadora frete;

    private Impressora impressora;

    private long quantPedidosRecebidos;

    public Loja(Transportadora transportadora,
                Impressora impressora) {

        catalogo = new ArrayList<>();  // COMPOSIÇÃO
        setFrete(transportadora);      // AGREGAÇÃO
        this.impressora = impressora;  // AGREGAÇÃO
        this.quantPedidosRecebidos = 0;
        this.quantidadeEmEstoquePorItem = new HashMap<>();
    }

    public void setFrete(Transportadora transportadora) {
        frete = transportadora;
    }

    public String receberPedido(Vendavel item, int quantidade, Usuario usuario)
            throws
            ItemNaoExisteNoCatalogoException,
            EstoqueInsuficienteException,
            EnderecoInvalidoException,
            ErroNoPagamentoException {

        // verifica se existe no catálogo da loja
        if (buscarItem(item.getId()) == null) {
            throw new ItemNaoExisteNoCatalogoException();
        }

        // verifica se existe aquela quantidade do produto desejado
        // no estoque da loja
        final Integer quantidadeEmEstoque = quantidadeEmEstoquePorItem.get(item);
        if (quantidadeEmEstoque < quantidade) {
            throw new EstoqueInsuficienteException(quantidadeEmEstoque);
        }

        // verifica se o usuário tem um endereço de entrega válido
        if (usuario.getEndereco() == null) {
            throw new EnderecoInvalidoException();
        }

        float precoTotal = quantidade * item.getPrecoEmReais();
        if (item instanceof Livro) {
            // agora sim já sabemos que item é um Livro,
            // então podemos fazer um typecast e usar coisas específicas
            // de livro, como por exemplo perguntar sua categoria

            // ToDo colocar a lógica nova

            Livro livro = (Livro) item;
            if (livro.getCategoria() == CategoriaDeLivro.DIDATICO && quantidade >= 3) {
                precoTotal *= 0.8f;
            }
        }

        if (!processarPagamento(precoTotal)) {
            throw new ErroNoPagamentoException();
        }

        if (item instanceof Transportavel) {  // é transportável?
            // cria um array com todos os itens que precisarão ser entregues
            // (possivelmente várias unidades do mesmo item)
            ArrayList<Transportavel> pedido = new ArrayList<>();
            for (int i = 0; i < quantidade; i++) {
                pedido.add((Transportavel) item);
            }
            frete.transportar(pedido, usuario.getEndereco());
        }

        if (++this.quantPedidosRecebidos % NUMERO_DE_PEDIDOS_POR_RELATORIO == 0) {
            imprimirRelatorioUltimasVendas();
        }

        String recibo = String.format("Recibo no valor de R$%.2f referente à " +
                "compra de %d unidades do item: %s",
                precoTotal, quantidade, item);

        return recibo;
    }

    public void atualizarQuantidadeEmEstoqueDeItem(Vendavel item, int quantidade) {
        Integer quantidadeAntiga = this.quantidadeEmEstoquePorItem.get(item);
        if (this.quantidadeEmEstoquePorItem.get(item) == null) {
            this.quantidadeEmEstoquePorItem.put(item, quantidade);
        } else {
            this.quantidadeEmEstoquePorItem.put(item, quantidadeAntiga + quantidade);
        }
    }

    private void imprimirRelatorioUltimasVendas() {
        String relatorio = ".................To Do...............";
        this.impressora.imprimir(relatorio);
    }

    public void incluirItem(Vendavel vendavel) {
        if (buscarItem(vendavel.getId()) != null) {
            // produto já existe no catálogo -- nada a fazer
            return;
        }
        catalogo.add(vendavel);
    }

    /**
     * Busca um ítem no catálogo da loja a partir de sua descrição.
     *
     * @param descricao a descrição do ítem desejado (ou parte dela)
     * @return o primeiro Vendavel que case com a descrição fornecida, caso encontre;
     *         ou null, caso contrário
     */
    public Vendavel buscarItem(String descricao) {
        for (Vendavel item : catalogo) {
            if (item.getDescricao().contains(descricao)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Busca um ítem no catálogo da loja a partir de seu código.
     *
     * @param id o código de identificação do ítem desejado
     * @return o Vendavel cujo código seja igual ao código fornecido, caso encontre;
     *         ou null, caso contrário
     */
    public Vendavel buscarItem(long id) {
        for (Vendavel item : catalogo) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    private boolean processarPagamento(float valor) {
        // ToDo passar o cartão de crédito, ou emitir boleto, etc.
        System.out.println(String.format(
                "Processando pagamento no valor de R$%.2f...",
                valor));
        return true;  // ToDo retornar false caso o pagamento falhe
    }
}
