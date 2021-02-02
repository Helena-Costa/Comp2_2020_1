import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MapaUsandoArrayUnico<C, V> implements Map<C, V> {

    private ArrayList<ParChaveValor<C, V>> minhaListaDePares;

    public MapaUsandoArrayUnico() {
        this.minhaListaDePares = new ArrayList<>();
    }

    @Override
    public int size() {
        // ToDo IMPLEMENT ME!!!
        return this.minhaListaDePares.size();
    }

    @Override
    public boolean isEmpty() {
        // ToDo IMPLEMENT ME!!!
        return this.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        // ToDo IMPLEMENT ME!!!
        return this.get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        // ToDo IMPLEMENT ME!!!
        for (ParChaveValor<C, V> par : this.minhaListaDePares) {
            if (par.getValor().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V put(C chave, V valor) {
        ParChaveValor<C, V> parPreExistente = obterParChaveValor(chave);

        if (parPreExistente == null) {  // chave inédita!!
            this.minhaListaDePares.add(new ParChaveValor<>(chave, valor));
            return null;

        } else {  // chave pré-existente
            V valorAntesDaAtualizacao = parPreExistente.getValor();
            parPreExistente.setValor(valor);
            return valorAntesDaAtualizacao;
        }
        // ToDo IMPLEMENT ME!!! (precisa retornar o valor pré-existente!!)
    }

    @Override
    public V remove(Object key) {
        for (int i = 0; i < minhaListaDePares.size(); i++) {
            if (minhaListaDePares.get(i).getChave().equals(key)) {
                ParChaveValor<C, V> parASerRemovido = this.minhaListaDePares.get(i);
                this.minhaListaDePares.remove(parASerRemovido);
                return parASerRemovido.getValor();
            }
        }
        return null;  // ToDo IMPLEMENT ME!!!
    }

    @Override
    public void putAll(Map<? extends C, ? extends V> m) {
        throw new RuntimeException("Operação não suportada!");
    }

    @Override
    public void clear() {
        // ToDo IMPLEMENT ME!!!
        this.minhaListaDePares.clear();
    }

    @Override
    public Set<C> keySet() {
        throw new RuntimeException("Operação não suportada!");
    }

    @Override
    public Collection<V> values() {
        throw new RuntimeException("Operação não suportada!");
    }

    @Override
    public Set<Entry<C, V>> entrySet() {
        throw new RuntimeException("Operação não suportada!");
    }

    @Override
    public V get(Object chaveDeBusca) {
        ParChaveValor<C, V> par = obterParChaveValor(chaveDeBusca);
        return par == null
                ? null
                : par.getValor();
    }

    private ParChaveValor<C, V> obterParChaveValor(Object chave) {
        for (ParChaveValor<C, V> par : this.minhaListaDePares) {
            if (par.getChave().equals(chave)) {
                return par;
            }
        }
        return null;
    }
}
