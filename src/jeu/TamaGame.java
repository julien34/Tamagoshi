package jeu;

import java.util.ArrayList;

import tamagoshis.GrosJoueur;
import tamagoshis.GrosMangeur;
import tamagoshis.Tamagoshi;
import util.Utilisateur;

import java.util.Random;

public class TamaGame {
	
	private ArrayList<Tamagoshi> listeDeTamagoshi = new ArrayList<Tamagoshi>();
	private ArrayList<Tamagoshi> listeDeTamagoshiMort = new ArrayList<Tamagoshi>();
	private ArrayList<String> listeDePrenoms = new ArrayList<String>();
	private int difficulte = 0;
	private double score = 0;
	private boolean ageLimite = false;
	private Random rand = new Random();
	
	/**
	 * Constructeur sans parammètres, qui créer un jeu.
	 */
	public TamaGame(){
		
	}
	
	
	/**
	 * Méthode permettant d'initialiser un jeu de Tamagoshi en saisissant au clavier le nombre.
	 */
	public void initialisation(){
		
		//On ajoute des prénoms à l'arraylist :
		this.ajoutPrenoms();
		
		//Saisie au clavier du nombre de Tamagoshis à crer :
		System.out.println("Combien voulez vous créer de Tamagoshis ?");
		String nbDeTamagoshisString = Utilisateur.saisieClavier();
		int nbDeTamagoshisInt = new Integer(nbDeTamagoshisString);
		this.difficulte = nbDeTamagoshisInt;
		
		
		//Boucle qui créer des Tamagoshis au nombre choisi par l'utilisateur :
		System.out.println(this.difficulte+" Tamagoshis crées : ");
		for (int i=0 ; i<nbDeTamagoshisInt ; i++){
			
			//50% de chance de créer un Tamagoshi :
			if (rand.nextInt(2) == 0){
				this.listeDeTamagoshi.add(new Tamagoshi(this.listeDePrenoms.get(rand.nextInt(this.listeDePrenoms.size()))));
			}
			
			//50% de chance de créer un GrosMangeur ou un GrosJoueur :
			else{
				
				//25% de change de créer un GrosMangeur :
				if(rand.nextInt(2) == 0){
					this.listeDeTamagoshi.add(new GrosMangeur(this.listeDePrenoms.get(rand.nextInt(this.listeDePrenoms.size()))));
				}
				
				//25% de change de créer un GrosJoueur :
				else {
					this.listeDeTamagoshi.add(new GrosJoueur(this.listeDePrenoms.get(rand.nextInt(this.listeDePrenoms.size()))));
				}
			}
			
			System.out.println(this.listeDeTamagoshi.get(i).getName());
		}

	}
	
	
	/**
	 * Méthode qui ajoute à l'arraylist des prénoms :
	 */
	public void ajoutPrenoms(){
		this.listeDePrenoms.add("Julien");
		this.listeDePrenoms.add("Clément");
		this.listeDePrenoms.add("Marine");
		this.listeDePrenoms.add("Florent");
		this.listeDePrenoms.add("Brigitte");
		this.listeDePrenoms.add("Nicole");
		this.listeDePrenoms.add("Camille");
	}
	
	
	/**
	 * Méthode permettant de supprimer un Tamagoshi de la liste s'il est mort et les rajoutent dans la liste des morts (énergie <=0).
	 */
	public void verifTamagoshi(){
		for (int i=0 ; i<this.listeDeTamagoshi.size(); i++){
			if (!this.listeDeTamagoshi.get(i).getEtat()){
				this.listeDeTamagoshiMort.add(this.listeDeTamagoshi.get(i));
				this.listeDeTamagoshi.remove(this.listeDeTamagoshi.get(i));
			}
		}
		
		/*La boucle suivante génère une exception mais je ne sais pas pourquoi ...*/
		/*for(Tamagoshi tamagoshiCourant : this.listeDeTamagoshi){
			if (!tamagoshiCourant.getEtat()){
				
				this.listeDeTamagoshiMort.add(tamagoshiCourant);
				this.listeDeTamagoshi.remove(tamagoshiCourant);
			}
		}*/
		
	}
	
	/**
	 * Méthode qui vérifie à chaque tour si l'age maximal des Tamagoshis est atteint.
	 */
	public void verifAgeMax(){
		for (Tamagoshi tamagoshiCourant : this.listeDeTamagoshi){
			if (tamagoshiCourant.getAge() == tamagoshiCourant.getLifeTime()){
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
			for (Tamagoshi tamagoshiCourant : this.listeDeTamagoshi){
				tamagoshiCourant.parle();
			}
			
			
			//Choisir le tamagoshi à nourir :
			System.out.println("Quel Tamagoshi souhaitez vous nourrir ?");
			
			for (Tamagoshi tamagoshiCourant : this.listeDeTamagoshi){
				System.out.print("("+(this.listeDeTamagoshi.indexOf(tamagoshiCourant))+") "+tamagoshiCourant.getName()+"     ");
			}
			
			//Fais manger le tamagoshi désiré :
			int tamagoshiANourrir = new Integer(Utilisateur.saisieClavier());
			this.listeDeTamagoshi.get(tamagoshiANourrir).mange();
			
			//Choisir le Tamagoshi à faire jouer :
			System.out.println("\nAvec quel Tamagoshi souhaitez vous jouer ?");
			
			for (Tamagoshi tamagoshiCourant : this.listeDeTamagoshi){
				System.out.print("("+(this.listeDeTamagoshi.indexOf(tamagoshiCourant))+") "+tamagoshiCourant.getName()+"     ");
			}
			
			//Fais jouer le Tamagoshi sélectionné :
			int tamagoshiAFaireJouer = new Integer(Utilisateur.saisieClavier());
			this.listeDeTamagoshi.get(tamagoshiAFaireJouer).joue();
			
			
			//Consommation d'énergie et de fun des Tamagoshis et vieillissement:
			for (Tamagoshi tamagoshiCourant : this.listeDeTamagoshi){
				tamagoshiCourant.consommeEnergie();
				tamagoshiCourant.consommeFun();
				tamagoshiCourant.vieillir();
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
		for (Tamagoshi tamagoshiCourant : this.listeDeTamagoshi){
			sommeAgeTamagoshis += tamagoshiCourant.getAge()-1;
			sommeAgeMaxTamagoshi += 10;
		}
		
		//Boucle dans la liste des morts :
		for (Tamagoshi tamagoshiCourant : this.listeDeTamagoshiMort){
			sommeAgeTamagoshis += tamagoshiCourant.getAge()-1;
			sommeAgeMaxTamagoshi += 10;
		}
		//Affichage des ages :
		//System.out.println("somme age = "+sommeAgeTamagoshis+"\nsomme age max = "+sommeAgeMaxTamagoshi);
		
		//Calcul du score :
		this.score = ((double)sommeAgeTamagoshis/(double)sommeAgeMaxTamagoshi)*(double)100;
		
		return this.score;
	}
	
	
	/**
	 * La méthode résultat utilise la méthode score() afin de calculer le score et de l'afficher en console à l'utilisateur par la suite.
	 */
	public void resultat(){
		this.score();
		
		//Affichage d'un mini bilan :
		System.out.println("------- Partie Terminée ! -------\n\n-------- Bilan --------");
		
		for (Tamagoshi tamagoshiCourant : this.listeDeTamagoshi){
			System.out.println(tamagoshiCourant.getName()+" qui était un "+tamagoshiCourant.getClass().getSimpleName()+" a survécu et vous remercie :)");
		}
		
		for (Tamagoshi tamagoshiCourant : this.listeDeTamagoshiMort){
			System.out.println(tamagoshiCourant.getName()+" qui était un "+tamagoshiCourant.getClass().getSimpleName()+" n'est pas arrivé au bout et ne vous félicite pas :(");
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
