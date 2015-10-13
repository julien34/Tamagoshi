package jeu;

import java.util.ArrayList;
import tamagoshis.Tamagoshi;
import jeu.Utilisateur;

public class TamaGame {
	
	private ArrayList<Tamagoshi> listeDeTamagoshi = new ArrayList<Tamagoshi>();
	
	/**
	 * Constructeur sans parammètres, qui créer un jeu.
	 */
	public TamaGame(){
		
	}
	
	
	/**
	 * Méthode permettant d'initialiser un jeu de tamagoshi en saisissant clavier (avec la classe Personne).
	 */
	public void initialisation(){
		
		System.out.println("Combien voulez vous créer de Tamagoshis ?");
		String nbDeTamagoshisString = Utilisateur.saisieClavier();
		int nbDeTamagoshisInt = new Integer(nbDeTamagoshisString);
		
		//Boucle qui créer des Tamagoshis au nombre choisi par l'utilisateur
		for (int i=0 ; i<nbDeTamagoshisInt ; i++){
			System.out.println("Nommez votre Tamagoshi "+(i+1));
			String nomDuTamagoshi = Utilisateur.saisieClavier();
			this.listeDeTamagoshi.add(new Tamagoshi(nomDuTamagoshi));
		}

	}
	
	
	/**
	 * Méthode permettant de jouer une partie complete de jeu.
	 */
	public void play(){
		int nbTours = 0;
		this.initialisation();
		
		while(this.listeDeTamagoshi.size()>0){
			System.out.println("\n----- Cycle No."+nbTours+" -----");
		
			//Boucle pour les faire parler :
			for (int i=0 ; i<this.listeDeTamagoshi.size() ; i++){
				if(this.listeDeTamagoshi.get(i).getPartie()){
					this.listeDeTamagoshi.get(i).parle();
				}
				else{
					this.listeDeTamagoshi.remove(i);
				}
			}
			
			System.out.println("Quel Tamagoshi souhaitez vous nourrir ?");
			
			//Choisir le tamagoshi à nourir :
			for (int i=0 ; i<this.listeDeTamagoshi.size() ; i++){
				System.out.print("("+(i)+") "+this.listeDeTamagoshi.get(i).getName()+"     ");
			}
			
			//Fais manger le tamagoshi désiré :
			int tamagoshiANourrir = new Integer(Utilisateur.saisieClavier());
			this.listeDeTamagoshi.get(tamagoshiANourrir).mange();
			
			
			//Consommation d'énergie des tamagoshis :
			for(int i =0 ; i<this.listeDeTamagoshi.size() ; i++){
				this.listeDeTamagoshi.get(i).consommeEnergie();
			}
			
			//Incrémente le nombre de tours :
			nbTours++;
		}
	}
	
	
	
	/**
	 * Méthode main qui permet de vérifier et tester la classe TamaGame.
	 * @param args
	 */
	public static void main(String args[]){
		TamaGame jeu = new TamaGame();
		jeu.play();
	}
}
