package graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrapheLAdj implements IGraphe{
    private Map<String, List<Arc>> ladj;

    public GrapheLAdj() {
        ladj = new HashMap<>();
    }

    @Override
    public void ajouterSommet(String noeud) {
        ladj.put(noeud, new ArrayList<>());
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        assert(ladj.containsKey(source) && ladj.containsKey(destination));
        ladj.get(source).add(new Arc(source, destination, valeur));
    }

    @Override
    public void oterSommet(String noeud) {
        assert(ladj.containsKey(noeud));
        for (Arc a : ladj.get(noeud)){
            oterArc(a.getSource(), a.getDestination());
        }
        ladj.remove(noeud);
    }

    @Override
    public void oterArc(String source, String destination) {
        ladj.get(source).removeIf(a -> a.getDestination().equals(destination));
    }

    @Override
    public List<String> getSommets() {
        return new ArrayList<>(ladj.keySet());
    }

    @Override
    public List<String> getSucc(String sommet) {
        assert(ladj.containsKey(sommet));
        List<String> succeseurs = new ArrayList<>();
        for (Arc a : ladj.get(sommet)){
            succeseurs.add(a.getDestination());
        }
        return succeseurs;
    }

    @Override
    public int getValuation(String src, String dest) {
        assert(contientSommet(src));
        for (Arc a : ladj.get(src)){
            if (a.getDestination().equals(dest)){
                return a.getValuation();
            }
        }
        return -1;
    }

    @Override
    public boolean contientSommet(String sommet) {
        return ladj.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        if (!contientSommet(src)) {
            return false;
        }
        for (Arc a : ladj.get(src)){
            if (a.getDestination().equals(dest)){
                return true;
            }
        }
        return false;
    }
}
