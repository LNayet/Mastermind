import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Jeu mastermind = new Jeu();
        List<Character> tmp = new ArrayList<Character>();
        int i;
        mastermind.initialise_pions();
        mastermind.selectionne_combinaison_ordi();
        mastermind.affiche_combi_ordi();
        System.out.println("Ordinateur > J'ai choisi ma combinaison, à vous de deviner !");
        System.out.println("Les couleurs possibles sont R J B O V et N");
        System.out.println("Tapez (RJBO) pour tenter les couleurs R J B O dans l'ordre");
        mastermind.affiche_grille(0);
        for (i = 1; i <= 10; i++) {
            System.out.print("Vous > ");
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            for(int j = 0; j < str.length(); j++)
                tmp.add(str.charAt(j));
            while(mastermind.verif_saisie(tmp)==false) {
                tmp.clear();
                System.out.println("\nVous n'avez pas saisi assez de pions ou qui ne correspondent pas aux couleurs, recommencez");
                System.out.print("Vous > ");
                Scanner sc2 = new Scanner(System.in);
                String str2 = sc2.nextLine();
                for(int j = 0; j < str2.length(); j++)
                    tmp.add(str2.charAt(j));
            }
            for(int j = 0; j < 4; j++)
                mastermind.add_combinaison_joueur(tmp.get(j));
            tmp.clear();
            mastermind.verif_placement_pions(i);
            mastermind.verif_couleur_pions(i);
            mastermind.affiche_grille(i);
            if(mastermind.valide_partie(i)==true) {
                System.out.println("Bravo ! Vous avez gagné en " + i + " tour(s) !");
                break;
            }
        }
        if (i == 11 && (mastermind.valide_partie(i-1)==false))
            System.out.println("Les 10 tours sont passés ! Vous avez perdu !");
    }
}