import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LojaEstoqueTest {

    private static long TAMANHO = 10_000;

    Loja minhaLoja;
    Usuario usuario;
    Transportadora minhaTransportadora;
    Impressora impressoraMatricial;
    Bicicleta bicicleta1;
    CD cd1;
    Livro livro1;

    @Before
    public void setUp() {
        minhaTransportadora = new Transportadora();
        impressoraMatricial = new ImpressoraMatricial();
        minhaLoja = new Loja(minhaTransportadora, impressoraMatricial);

        usuario = new Usuario(111, "Maria");
        usuario.setEndereco("Rua A, numero 0");

        livro1 = new Livro(1, "Da Terra à Lua", "Júlio Verne", null, 1865);
        cd1 = new CD(2, "disco1", null, 0);
        bicicleta1 = new Bicicleta(9999, 700, "Pinarello");
    }

    @Test
    public void testarInsercaoNoEstoqueDaLojaParaLivro() {
        // sanity check
        assertNull(minhaLoja.buscarItemNoEstoqueDaLoja(livro1));
        assertNull(minhaLoja.buscarItemNoEstoqueDaLoja(bicicleta1));


        minhaLoja.incluirItemNoEstoqueDaLoja(livro1, 90);
        assertEquals((Integer)90, minhaLoja.buscarItemNoEstoqueDaLoja(livro1));

        Vendavel livro2 = new Livro(1, "titulo", null, null, 0);
        assertEquals((Integer)90, minhaLoja.buscarItemNoEstoqueDaLoja(livro2));
    }

    @Test
    public void testarInsercaoNoEstoqueDaLojaParaCd() {
        // sanity check
        assertNull(minhaLoja.buscarItemNoEstoqueDaLoja(cd1));

        minhaLoja.incluirItemNoEstoqueDaLoja(cd1, 4);
        assertEquals((Integer)4, minhaLoja.buscarItemNoEstoqueDaLoja(cd1));

        Vendavel cd2 = new CD(2, "disco2", null, 0);
        assertEquals((Integer)4, minhaLoja.buscarItemNoEstoqueDaLoja(cd2));
    }

    @Test
    public void testarInsercaoNoEstoqueDaLojaParaBicicleta() {
        // sanity check
        assertNull(minhaLoja.buscarItemNoEstoqueDaLoja(bicicleta1));

        minhaLoja.incluirItemNoEstoqueDaLoja(bicicleta1, 10);
        assertEquals((Integer)10, minhaLoja.buscarItemNoEstoqueDaLoja(bicicleta1));

        Vendavel bicicleta2 = new Bicicleta(9999, 0, null);
        assertEquals((Integer)10, minhaLoja.buscarItemNoEstoqueDaLoja(bicicleta2));
    }

    @Test
    public void testarVendaParaLivro() {
        //sanity check
        Vendavel livro2 = new Livro(1, "titulo2", "autor", null, 0);
        assertNull(minhaLoja.receberPedido(livro2, 6, usuario));

        minhaLoja.incluirItemNoEstoqueDaLoja(livro1, 31);
        minhaLoja.incluirItemNoCatalogo(livro1);

        assertNotNull(minhaLoja.receberPedido(livro2, 6, usuario));
        assertEquals((Integer)25, minhaLoja.buscarItemNoEstoqueDaLoja(livro1));
    }

    @Test
    public void testarVendaParaCd() {
        //sanity check
        Vendavel cd2 = new CD(2, "disco2", null, 0);
        assertNull(minhaLoja.receberPedido(cd2, 2, usuario));

        minhaLoja.incluirItemNoEstoqueDaLoja(cd1, 27);
        minhaLoja.incluirItemNoCatalogo(cd1);

        assertNotNull(minhaLoja.receberPedido(cd2, 2, usuario));
        assertEquals((Integer)25, minhaLoja.buscarItemNoEstoqueDaLoja(cd2));
    }

    @Test
    public void testarVendaParaBicicleta() {
        //sanity check
        Vendavel bicicleta2 = new Bicicleta(9999, 700, null);
        assertNull(minhaLoja.receberPedido(bicicleta2, 10, usuario));

        minhaLoja.incluirItemNoEstoqueDaLoja(bicicleta1, 13);
        minhaLoja.incluirItemNoCatalogo(bicicleta1);

        assertNotNull(minhaLoja.receberPedido(bicicleta2, 10, usuario));
        assertEquals((Integer)3, minhaLoja.buscarItemNoEstoqueDaLoja(bicicleta2));
    }

}
