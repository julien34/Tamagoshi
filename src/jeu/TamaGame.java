package jeu;

import java.util.ArrayList;
import tamagoshis.Tamagoshi;
import util.Utilisateur;

public class TamaGame {
	
	private ArrayList<Tamagoshi> listeDeTamagoshi = new ArrayList<Tamagoshi>();
	private ArrayList<Tamagoshi> listeDeTamagoshiMort = new ArrayList<Tamagoshi>();
	private int difficulte = 0;
	private double score = 0;
	private boolean ageLimite = false;
	
	/**
	 * Constructeur sans parammètres, qui créer un jeu.
	 */
	public TamaGame(){
		
	}
	
	
	/**
	 * Méthode permettant d'initialiser un jeu de tamagoshi en saisissant clavier (avec la classe Personne).
	 */
	public void initialisation(){
		
		//Saisie au clavier du nombre de Tamagoshis à crer :
		System.out.println("Combien voulez vous créer de Tamagoshis ?");
		String nbDeTamagoshisString = Utilisateur.saisieClavier();
		int nbDeTamagoshisInt = new Integer(nbDeTamagoshisString);
		this.difficulte = nbDeTamagoshisInt;
		
		//Boucle qui créer des Tamagoshis au nombre choisi par l'utilisateur :
		for (int i=0 ; i<nbDeTamagoshisInt ; i++){
			System.out.println("Nommez votre Tamagoshi "+(i+1));
			String nomDuTamagoshi = Utilisateur.saisieClavier();
			this.listeDeTamagoshi.add(new Tamagoshi(nomDuTamagoshi));
		}

	}
	
	
	/**
	 * Méthode permettant de supprimer un Tamagoshi de la liste s'il est mort et les rajoutent dans la liste des morts (énergie <=0).
	 */
	public void verifTamagoshi(){
		for (int i=0 ; i<this.listeDeTamagoshi.size() ; i++){
			if(!this.listeDeTamagoshi.get(i).getEtat()){
				this.listeDeTamagoshiMort.add(this.listeDeTamagoshi.get(i));
				this.listeDeTamagoshi.remove(i);
			}
		}
	}
	
	/**
	 * Méthode qui vérifie à chaque tour si l'age maximal des Tamagoshis est atteint.
	 */
	public void verifAgeMax(){
		for (int i=0 ; i<this.listeDeTamagoshi.size(); i++){
			if (this.listeDeTamagoshi.get(i).getAge() == listeDeTamagoshi.get(i).getLifeTime()){
				this.ageLimite = true; 
			}
		}
	}
	
	
	/**
	 * Méthode permettant de jouer une partie complete de jeu.
	 */
	public void play(){
		int nbTours = 0;
		this.initialisation();
		
		while(this.listeDeTamagoshi.size()>0 && !this.ageLimite){
			System.out.println("\n----- Cycle No."+nbTours+" -----");
			
			//Vérifie que les Tamagoshis ne sont pas tous morts (les supprime de la liste s'ils sont morts) :
			this.verifTamagoshi();
			
			//Vérifie que les tamagoshis n'ont pas atteint leur age maximal :
			this.verifAgeMax();
		
			//Boucle pour les faire parler :
			for (int i=0 ; i<this.listeDeTamagoshi.size() ; i++){
				this.listeDeTamagoshi.get(i).parle();
			}
			
			System.out.println("Quel Tamagoshi souhaitez vous nourrir ?");
			
			//Choisir le tamagoshi à nourir :
			for (int i=0 ; i<this.listeDeTamagoshi.size() ; i++){
				System.out.print("("+(i)+") "+this.listeDeTamagoshi.get(i).getName()+"     ");
			}
			
			//Fais manger le tamagoshi désiré :
			int tamagoshiANourrir = new Integer(Utilisateur.saisieClavier());
			this.listeDeTamagoshi.get(tamagoshiANourrir).mange();
			
			
			//Consommation d'énergie des Tamagoshis :
			for(int i =0 ; i<this.listeDeTamagoshi.size() ; i++){
				this.listeDeTamagoshi.get(i).consommeEnergie();
			}
			
			//Fait vieillir tous les Tamagoshi de la liste à la fin du tour :
			for (int i=0; i<this.listeDeTamagoshi.size() ; i++){
				this.listeDeTamagoshi.get(i).vieillir();
			}
			
			//Incrémente le nombre de tours :
			nbTours++;
		}
		
		if(this.listeDeTamagoshi.size()<=0 || this.ageLimite){
			this.resultat();
		}
	}
	
	/**
	 * Méthode qui retourne un entier calculé en fonction de la partie en cours.
	 * @return score (int) - le score de la partie en cours.
	 */
	public double score(){
		int sommeAgeTamagoshis = 0;
		int sommeAgeMaxTamagoshi = 0;
		
		//boucle dans la liste des vivants :
		for (int i=0 ; i<this.listeDeTamagoshi.size() ; i++){
			sommeAgeTamagoshis += this.listeDeTamagoshi.get(i).getAge()-1;
			sommeAgeMaxTamagoshi += 10;
		}
		
		//Boucle dans la liste des morts :
		for (int i=0 ; i<this.listeDeTamagoshiMort.size() ; i++){
			sommeAgeTamagoshis += this.listeDeTamagoshiMort.get(i).getAge()-1;
			sommeAgeMaxTamagoshi += 10;
		}
		
		//Affichage des ages :
		//System.out.println("somme age = "+sommeAgeTamagoshis+"\nsomme age max = "+sommeAgeMaxTamagoshi);
		
		//Calcul du score :
		this.score = ((double)sommeAgeTamagoshis/(double)sommeAgeMaxTamagoshi)*(double)100;
		
		return this.score;
	}
	
	public void resultat(){
		this.score();
		
		//Affichage d'un mini bilan :
		System.out.println("------- Partie Terminée ! -------\n\n-------- Bilan --------");
		for (int i = 0; i < this.listeDeTamagoshi.size(); i++) {
			System.out.println(this.listeDeTamagoshi.get(i).getName()+" a survécu et vous remercie :)");
		}
		
		for (int i = 0; i < this.listeDeTamagoshiMort.size(); i++) {
			System.out.println(this.listeDeTamagoshiMort.get(i).getName()+" n'est pas arrivé au bout et ne vous félicite pas :(");
		}
		
		System.out.println("\nVotre score est de : "+(int)this.score+"%");
		
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
