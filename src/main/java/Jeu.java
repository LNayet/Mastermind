import java.util.ArrayList;
import java.util.List;

public class Jeu {
    private int nombre_tire = 0;
    private List<Character> pions_possibles = new ArrayList<Character>();
    private List<Character> combinaison_ordi = new ArrayList<Character>();
    private List<Character> combinaison_joueur = new ArrayList<Character>();
    private List<Integer> nb_pions_bien_places = new ArrayList<Integer>();
    private List<Integer> nb_pions_bonne_couleur = new ArrayList<Integer>();

    public Jeu(){

    }

    public void initialise_pions(){
        pions_possibles.add('R');
        pions_possibles.add('J');
        pions_possibles.add('B');
        pions_possibles.add('O');
        pions_possibles.add('V');
        pions_possibles.add('N');
    }

    public void selectionne_combinaison_ordi(){
        for(int i = 0; i < 4; i++) {
            nombre_tire = (int) (Math.random() * ((pions_possibles.size())));
            combinaison_ordi.add(pions_possibles.get(nombre_tire));
        }
    }

    public void affiche_grille(int i){
        System.out.println("|-------------------|");
        for(int j=1; j<=i; j++) {
            System.out.print("|");
            for (int k = 0; k < 4; k++) {
                if (i != 1)
                    System.out.print(combinaison_joueur.get(k + 4 * (j-1)));
                else System.out.print(combinaison_joueur.get(k));
            }
            System.out.print("|");
            System.out.print(" " + nb_pions_bien_places.get(j-1)+ " ");
            System.out.print("|");
            System.out.print(" " + nb_pions_bonne_couleur.get(j-1)+ " ");
            System.out.print("| ");
            System.out.println(j + "/10 |");
        }
        if(i!=10){
            System.out.print("|....| . | . | " + (i+1) + "/10 |\n");
        }
        System.out.println("|-------------------|");
    }

    public boolean verif_saisie(List<Character> saisie){
        int j;
        for(int i = 0; i < saisie.size(); i++){
            for(j = 0; j < pions_possibles.size(); j++){
                if(saisie.get(i).equals(pions_possibles.get(j)))
                    break;
            }
            if(j==pions_possibles.size())return false;
        }
        return true;
    }

    public void add_combinaison_joueur(char c){
        combinaison_joueur.add(c);
    }

    public void verif_placement_pions(int round){
        int cpt = 0;
        nb_pions_bien_places.add(0);
        for(int i = 0; i < 4; i++){
            if(round!=0) {
                if (combinaison_joueur.get(i + ((round - 1) * 4)).equals(combinaison_ordi.get(i)))
                    nb_pions_bien_places.set(round-1, ++cpt);
            }
            else
                if (combinaison_joueur.get(i).equals(combinaison_ordi.get(i)))
                    nb_pions_bien_places.set(round-1, ++cpt);
        }
    }

    public void verif_couleur_pions(int round){
        int cpt = 0;
        nb_pions_bonne_couleur.add(0);
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (round != 0) {
                    if(j!=i) {
                        if (combinaison_joueur.get(j + ((round - 1) * 4)).equals(combinaison_ordi.get(i)))
                            nb_pions_bonne_couleur.set(round - 1, ++cpt);
                    }
                }
                else
                    if(j!=i) {
                        if (combinaison_joueur.get(j).equals(combinaison_ordi.get(i)))
                            nb_pions_bonne_couleur.set(round - 1, ++cpt);
                    }
            }
        }
    }

    public boolean valide_partie(int round)
    {
        if(nb_pions_bien_places.get(round-1)==4) return true;
        return false;
    }

    public void affiche_combi_ordi(){
        for(int i = 0; i < 4; i++)
            System.out.print(combinaison_ordi.get(i));
        System.out.println("\n");
    }
}
