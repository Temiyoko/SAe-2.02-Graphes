package graphe;

import java.util.*;

public class GrapheMAdj implements IGraphe{
    private int[][] matrice;
    private Map<String, Integer> indices;

    public GrapheMAdj(){
        matrice = new int[0][0];
        indices = new HashMap<>();
    }

    private void agrandirMatrice(){
        int[][] nouvelleMatrice = new int[matrice.length + 1][matrice[0].length + 1];
        for (int i = 0 ; i < matrice.length ; ++i){
            nouvelleMatrice[i] = Arrays.copyOf(matrice[i], matrice[0].length + 1);
            nouvelleMatrice[i][matrice[0].length] = -1;
        }
        matrice = nouvelleMatrice;
    }

    private void retrecirMatrice(int noeud){
        int ligne = 0;
        int[][] nouvelleMatrice = new int[matrice.length - 1][matrice[0].length - 1];
        for (int i = 0 ; i < nouvelleMatrice.length ; ++i){
            if(i != noeud) {
                int col = 0;
                for (int j = 0 ; j < matrice[0].length ; ++j) {
                    if (j != noeud){
                        nouvelleMatrice[ligne][col++] = matrice[i][j];
                    }
                }
                ligne++;
            }
        }
        matrice = nouvelleMatrice;
    }

    private String getCle(Map<String, Integer> map, int index){
        for(String s : map.keySet()){
            if(map.get(s) == index){
                return s;
            }
        }
        return null;
    }

    @Override
    public void ajouterSommet(String noeud) {
        Integer src = indices.get(noeud);
        if (src == null) {
            indices.put(noeud, indices.size());
            agrandirMatrice();
            Arrays.fill(matrice[src], -1);
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        ajouterSommet(source);
        ajouterSommet(destination);
        matrice[indices.get(source)][indices.get(destination)] = valeur;
    }

    @Override
    public void oterSommet(String noeud) {
        Integer src = indices.get(noeud);
        indices.remove(noeud);
        retrecirMatrice(src);
    }

    @Override
    public void oterArc(String source, String destination) {
        matrice[indices.get(source)][indices.get(destination)] = -1;
    }

    @Override
    public List<String> getSommets() {
        return new ArrayList<>(indices.keySet());
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> successeurs = new ArrayList<>();
        Integer indice = indices.get(sommet);
        for (int i = 0 ; i < matrice.length ; ++i){
            if (matrice[indice][i] != -1){
                String s = getCle(indices, i);
                successeurs.add(s);
            }
        }
        return successeurs;
    }

    @Override
    public int getValuation(String src, String dest) {
        assert(contientSommet(src) && contientSommet(dest));
        return matrice[indices.get(src)][indices.get(dest)];
    }

    @Override
    public boolean contientSommet(String sommet) {
        return indices.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        return getValuation(src, dest) != -1;
    }
}
