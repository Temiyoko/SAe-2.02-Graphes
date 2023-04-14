package graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrapheHHAdj extends Graphe implements IGraphe{
    private Map<String, Map<String, Integer>> hhadj;

    public GrapheHHAdj(){
        hhadj = new HashMap<>();
    }

    @Override
    public void ajouterSommet(String noeud) {
        if(!contientSommet(noeud)){
            hhadj.put(noeud, new HashMap<>());
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) throws IllegalArgumentException {
        if(contientArc(source, destination) || valeur < 0){
            throw new IllegalArgumentException();
        }
        if(!contientSommet(source)){
            ajouterSommet(source);
        }
        if(!contientSommet(destination)){
            ajouterSommet(destination);
        }
        HashMap<String, Integer> arcs = new HashMap<>(Map.copyOf(hhadj.get(source)));
        arcs.put(destination,valeur);
        hhadj.put(source, arcs);
    }

    @Override
    public void oterSommet(String noeud) {
        if(contientSommet(noeud)){
            hhadj.remove(noeud);
        }
    }

    @Override
    public void oterArc(String source, String destination) throws IllegalArgumentException {
        if (!contientArc(source,destination)){
            throw new IllegalArgumentException();
        }
        hhadj.get(source).remove(destination);
    }

    @Override
    public List<String> getSommets() {
        return new ArrayList<>(hhadj.keySet());
    }

    @Override
    public List<String> getSucc(String sommet) {
        return new ArrayList<>(hhadj.get(sommet).keySet());
    }

    @Override
    public int getValuation(String src, String dest) {
        assert(contientSommet(src));
        for (String s : hhadj.get(src).keySet()){
            if (s.equals(dest)){
                return hhadj.get(src).get(s);
            }
        }
        return -1;
    }

    @Override
    public boolean contientSommet(String sommet) {
        return hhadj.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        if (!contientSommet(src)) {
            return false;
        }
        for (String s : hhadj.get(src).keySet()){
            if(s.equals(dest)){
                return true;
            }
        }
        return false;
    }
}
