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
        HashMap<String, Integer> vide = new HashMap<>();
        vide.put("", 0);
        hhadj.put(noeud,vide);
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        assert(contientSommet(source));
        if (contientArc(source, "")){
            oterArc(source, "");
        }
        HashMap<String, Integer> arcs = new HashMap<>();
        arcs.put(destination,valeur);
        hhadj.put(source, arcs);
    }

    @Override
    public void oterSommet(String noeud) {
        assert(contientSommet(noeud));
    }

    @Override
    public void oterArc(String source, String destination) {
        assert(contientArc(source, destination));
        hhadj.get(source).remove(destination);
        if(hhadj.get(source).isEmpty()){
            ajouterArc(source, "", 0);
        }
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
        assert(contientSommet(src));
        for (String s : hhadj.get(src).keySet()){
            if(s.equals(dest)){
                return true;
            }
        }
        return false;
    }
}
