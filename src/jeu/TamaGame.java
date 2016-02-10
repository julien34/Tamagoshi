package jeu;

import graphic.TamaFrame;
import graphic.TamaJFrameResultat;
import graphic.TamaJPanel;

import java.util.ArrayList;

import tamagoshis.GrosJoueur;
import tamagoshis.GrosMangeur;
import tamagoshis.Tamagoshi;

import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class TamaGame {
	
	/**
	 * Spinner qui permet de récupérer la quantité de tamagoshis à créer.
	 */
	private JSpinner qte = new JSpinner(new SpinnerNumberModel(1, 1, 8, 1));//Valeur initiale, Valeur mini, valeur maxi, saut
	
	private ArrayList<Tamagoshi> listeDeTamagoshi = new ArrayList<Tamagoshi>();
	private ArrayList<Tamagoshi> listeDeTamagoshiMort = new ArrayList<Tamagoshi>();
	private ArrayList<String> listeDePrenoms = new ArrayList<String>();
	private int difficulte = 0;
	private double score = 0;
	private boolean ageLimite = false;
	private Random rand = new Random();
	
	public static ArrayList<TamaFrame> listeDesFenetres = new ArrayList<TamaFrame>();
	private int nbTours = 0;
	
	private static TamaGame jeu = new TamaGame();
	
	/**
	 * Constructeur sans parammètres, qui créer un jeu.
	 */
	public TamaGame(){
		
	}
	
	
	/**
	 * Méthode permettant d'initialiser un jeu de Tamagoshi en saisissant au clavier le nombre.
	 */
	private void initialisation(){
		
		//On ajoute des prénoms à l'arraylist :
		this.ajoutPrenoms();
		
		//Choix de la quantité de tamagoshis à créer
		int nbDeTamagoshisInt = this.choixQte();
		this.difficulte = nbDeTamagoshisInt;
		
		
		//Boucle qui créer des Tamagoshis au nombre choisi par l'utilisateur :
		for (int i=0 ; i<nbDeTamagoshisInt ; i++){
			
			//50% de chance de créer un Tamagoshi :
			if (rand.nextInt(2) == 0){
				int pif = rand.nextInt(this.listeDePrenoms.size());//Valeur au hasard
				Tamagoshi t = new Tamagoshi(this.listeDePrenoms.get(pif));
				this.listeDePrenoms.remove(pif);//On supprime le nom dans la liste
				int pos = this.listeDeTamagoshi.size();
				this.listeDeTamagoshi.add(t);//On ajoute le tamagoshi à la liste
				listeDesFenetres.add(new TamaFrame(t, pos));//On créer une fenetre avec ce tamagoshi en paramètre, ainsi que la position dans la liste des fenetres
			}
			
			//50% de chance de créer un GrosMangeur ou un GrosJoueur :
			else{
				
				//25% de change de créer un GrosMangeur :
				if(rand.nextInt(2) == 0){
					int pif = rand.nextInt(this.listeDePrenoms.size());//Valeur au hasard
					GrosMangeur gm = new GrosMangeur(this.listeDePrenoms.get(pif));
					this.listeDePrenoms.remove(pif);//On supprime le nom dans la liste
					int pos = this.listeDeTamagoshi.size();
					this.listeDeTamagoshi.add(gm);
					listeDesFenetres.add(new TamaFrame(gm, pos));
				}
				
				//25% de change de créer un GrosJoueur :
				else {
					int pif = rand.nextInt(this.listeDePrenoms.size());//Valeur au hasard
					GrosJoueur gj = new GrosJoueur(this.listeDePrenoms.get(pif));
					this.listeDePrenoms.remove(pif);//On supprime le nom dans la liste
					int pos = this.listeDeTamagoshi.size();
					this.listeDeTamagoshi.add(gj);
					listeDesFenetres.add(new TamaFrame(gj, pos));
				}
			}
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
		this.listeDePrenoms.add("Patrick");
	}
	
	
	/**
	 * Méthode permettant de supprimer un Tamagoshi de la liste s'il est mort et les rajoutent dans la liste des morts (énergie <=0).
	 */
	public void verifTamagoshi(){
		
		//On supprime les tamagoshi morts de la liste des vivants et on les ajoute à la liste des morts
		for (int i=0 ; i<this.listeDeTamagoshi.size(); i++){
			if (!this.listeDeTamagoshi.get(i).getEtat()){
				
				this.listeDeTamagoshiMort.add(this.listeDeTamagoshi.get(i));
				this.listeDeTamagoshi.remove(this.listeDeTamagoshi.get(i));
			}
		}
		
		//On remet tous les boutons clickable pour les tamagoshis vivants
		for(Tamagoshi tam : this.listeDeTamagoshi){
			for(TamaFrame t : listeDesFenetres){
				if(tam.equals(t.getTama())){
					t.getTjp().setBtn(3, true);//On rend tous le sboutons clickable
				}
			}
		}
		
		//Si un tamagoshi meurt, on grise ses boutons et on lui affecte l'image de "mort"
		for(Tamagoshi tam : this.listeDeTamagoshiMort){
			for(TamaFrame t : listeDesFenetres){
				if(t.getTama().equals(tam)){
					
					//On met l'ensemble des boutons non-clickable sur les tamagoshis  morts
					t.getTjp().setBtn(3, false);
					
					//Selon la classe du tamagoshi, on lui affecte son image de "mort"
					switch (tam.getClass().getSimpleName()) {
					case "Tamagoshi":
						t.getTjp().setImg(1);
						break;
					case "GrosMangeur":
						t.getTjp().setImg(2);
						break;
					case "GrosJoueur":
						t.getTjp().setImg(3);
						break;

					default:
						break;
					}
				}
			}	
		}
		
		//Si la liste de tamagoshi est vide, on affiche le résultat, la partie s'arrete
		if(this.listeDeTamagoshi.isEmpty()){
			this.resultat();
		}
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
		
		this.nbTours++;//On incrémente le nombre de tour
		
		//Si la partie n'est pas finie, on refait un tour
		if(this.listeDeTamagoshi.size()>0 && !this.ageLimite){
			System.out.println("\n----- Cycle No."+nbTours+" -----");
			
			//Boucle pour les faire parler + Consommation d'énergie et de fun des Tamagoshis et vieillissement:
			for (Tamagoshi tamagoshiCourant : this.listeDeTamagoshi){
				tamagoshiCourant.parle();
				tamagoshiCourant.consommeEnergie();
				tamagoshiCourant.consommeFun();
				tamagoshiCourant.vieillir();
			}
		}
		
		//Si la partie est finie alors on affiche le résultat
		else{
			this.resultat();
		}
		
		//Vérifie que les Tamagoshis ne sont pas tous morts (les supprime de la liste s'ils sont morts) :
		this.verifTamagoshi();
		
		//Vérifie que les tamagoshis n'ont pas atteint leur age maximal :
		this.verifAgeMax();
		
		//On affiche les états pour chaque tamagoshi dans les fenetres
		afficherEtats();
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
		
		//Calcul du score :
		this.score = ((double)sommeAgeTamagoshis/(double)sommeAgeMaxTamagoshi)*(double)100;
		
		return this.score;
	}
	
	
	/**
	 * La méthode résultat utilise la méthode score() afin de calculer le score et de l'afficher en console à l'utilisateur par la suite.
	 */
	public void resultat(){
		this.score();//calcul du scrore
		
		//On ferme toutes les fenetres qui ont été instanciées lors de la création
		for(TamaFrame tjf : listeDesFenetres){
			tjf.dispose();
		}
		
		//On ouvre une fenetre de résultat (bilan)
		new TamaJFrameResultat(this.listeDeTamagoshi, this.listeDeTamagoshiMort, (int)this.score);
				
		//Affichage d'un mini bilan :
		System.out.println("------- Partie Terminée ! -------\n\n-------- Bilan --------");
		
		for (Tamagoshi tamagoshiCourant : this.listeDeTamagoshi){
			System.out.println(tamagoshiCourant.getName()+" qui était un "+tamagoshiCourant.getClass().getSimpleName()+" a survécu et vous remercie :)");
		}
		
		for (Tamagoshi tamagoshiCourant : this.listeDeTamagoshiMort){
			System.out.println(tamagoshiCourant.getName()+" qui était un "+tamagoshiCourant.getClass().getSimpleName()+" n'est pas arrivé au bout et ne vous félicite pas :(");
		}
		
	}
	
	
	
	
	
	/**
	 * Méthode qui ouvre une popup pour sélectionner la quantité de Tamagoshis à créer. Il est possible de créer entre 1 et 8 Tamagoshis.
	 */
	private int choixQte(){
		
		//Si l'utilisateur clique sur "OK"
		if(JOptionPane.showOptionDialog(null, this.qte, "Tamagoshis à créer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null) == JOptionPane.OK_OPTION){
			return (Integer)this.qte.getValue();
		}
		else{
			return 0;
		}
	}


	/**
	 * Méthode qui retourne le TamaGame courant.
	 * @return, le TamaGame courant.
	 */
	public static TamaGame getJeu() {
		return jeu;
	}
	
	
	/**
	 * Méthode qui permet de vérifier et tester la classe TamaGame.
	 */
	public void jouer(){
		jeu.initialisation();
	}
	
	
	/**
	 * Méthode qui permet d'afficher dans les bulles de chaque TamaFrame, l'état du tamagoshi.
	 */
	private static void afficherEtats(){
		for(TamaFrame tf : listeDesFenetres){
			tf.getTjp().afficherEtatsDansBulles();
		}
	}
}
