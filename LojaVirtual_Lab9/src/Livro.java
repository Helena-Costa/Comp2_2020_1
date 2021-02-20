import java.util.Objects;

public class Livro extends ArtigoCultural {

    private final int codigoISBN;

    private String titulo;

    private String autor;

    private String editora;

    private int anoPublicacao;

    private int numeroDePaginas;

    private CategoriaDeLivro categoria;

    public Livro(int codigoISBN,
                 String titulo, String autor, String editora, int anoPublicacao, CategoriaDeLivro categoria) {

        super(codigoISBN,
                String.format("Livro: %s (%s, %d), Categoria: %s",
                titulo, autor, anoPublicacao, categoria.getCodigo()));

        this.categoria = categoria;
        this.codigoISBN = codigoISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
    }

    public CategoriaDeLivro getCategoria() {
        return this.categoria;
    }

    public int getCodigoISBN() {
        return codigoISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public void setNumeroDePaginas(int numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return codigoISBN == livro.codigoISBN &&
                categoria == livro.categoria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoISBN, categoria);
    }
}
