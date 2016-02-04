package tamagoshis;
import graphic.TamaFrame;
import graphic.TamaJPanel;

import java.util.Random;

import jeu.TamaGame;

/**
 * Les variables sont les suivantes :
 * <ul>
 * 		<li>age (int): l'age du tamagoshi.</li>
 * 		<li>maxEnergy (int) : l'énergie qu'il peut avoir au maximum.</li>
 * 		<li>energy (int) : son énergie actuelle.</li>
 *	 	<li>maxFun (int) : le fun qu'il peut avoir au maximum.</li>
 * 		<li>fun (int) : son fun actuel.</li>
 * 		<li>name (String) : le nom du Tamagoshi.</li>
 * 		<li>etat (Boolean) : l'état du Tamagoshi (mort ou vif).</li>
 * 		<li>lifeTime (int) : Le nombre de tours de jeu.</li>
 * </ul>
 * 
 * @author Siauvaud Julien
 */
public class Tamagoshi {
	private int age, maxEnergy;
	protected int energy;
	protected int fun;
	private int maxFun;
	protected String name;
	private static int lifeTime = 10;
	private Random rand = new Random();
	protected boolean etat = true;
	private String etatDeForme = "";
	
	
	/**
	 * Constructeur d'un Tamagoshi.<br><br>
	 * Initialise le Tamagoshi avec un nom placé en paramètre, un age = 0, une énergie comprise entre 3 et 7, ainsi qu'une énergie maximale comprise entre 5 et 9.
	 * @param chaine représente le nom du Tamagoshi
	 */
	public Tamagoshi(String chaine) {
		this.name = chaine;
		this.age = 0;
		this.energy = 3 + rand.nextInt(4); //Random sur l'énergie entre 3 et 3+4 (7).
		this.maxEnergy = 5 + rand.nextInt(4);
		this.fun = 3 + rand.nextInt(4);
		this.maxFun = 5 + rand.nextInt(4);
	}
	
	/**
	 * Cette méthode retourne l'état du tamagoshi. Elle permet d'afficher son nom et son énergie également.
	 * @return etatDeForme (booléen) : true si énergie > 5, si non false.
	 */
	public boolean parle(){
		
		if (this.energy > 5 && this.fun > 5){
			this.etatDeForme = "Tout va bien";
			System.out.println(this.name+" : "+this.etatDeForme);
			return true;
		}
		
		else if (this.energy <= 5 && this.fun <= 5){
			this.etatDeForme = "Je suis affamé et je m'ennuie à mourrir";
			System.out.println(this.name+" : "+this.etatDeForme);
			return false;
		}
		
		else if (this.energy <= 5){
			this.etatDeForme = "Je suis affamé";
			System.out.println(this.name+" : "+this.etatDeForme);
			return false;
		}
		
		else {
			this.etatDeForme = "Je m'ennuie à mourrir";
			System.out.println(this.name+" : "+this.etatDeForme);
			return false;
		}
	}
	
	
	
	/**
	 * Cette méthode permet de voir si le tamagoshi a gagné 1 point d'énergie ou s'il avait déjà une énergie suffisante.
	 * La méthode retourne true s'il accepte de manger ou false dans le cas contraire.
	 * @return true si le tamagoshi a été nourri. ou false dans le cas contraire.
	 */
	public boolean mange(){
		
		//On grise l'ensemble des boutons pour "manger"
		for (TamaFrame t : TamaGame.listeDesFenetres) {
			t.setBtn(1, false);
		}
			
		if(this.maxEnergy>this.energy){
			this.energy += 1+rand.nextInt(2);
			if(this.energy>this.maxEnergy){
				this.energy = this.maxEnergy;
			}
			System.out.println(this.name+" : Merci de m'avoir donné à manger.");
			return true;
		}
		else {
			System.out.println(this.name+" : Je n'ai pas faim.");
			return false;
		}
	}
	
	
	/**
	 * Méthode qui fait jouer un Tamagoshi durant un tour.
	 * @return true dans le cas ou le Tamagoshi a joué, false dans le cas inverse.
	 */
	public boolean joue(){
		
		//On grise l'ensemble des boutons pour "jouer"
		for (TamaFrame t : TamaGame.listeDesFenetres) {
			t.setBtn(2, false);
		}
		
		if(this.maxFun>this.fun){
			this.fun += 1+rand.nextInt(2);
			if(this.fun>this.maxFun){
				this.fun = this.maxFun;
			}
			System.out.println(this.name+" : Merci de m'avoir fait jouer.");
			this.etatDeForme = "Merci de m'avoir fait jouer";
			return true;
		}
		else {
			System.out.println(this.name+" : Je n'ai pas envie de jouer.");
			this.etatDeForme = "Je n'ai pas envie de jouer";
			return false;
		}
	}
	
	
	/**
	 * Méthode qui décrémente l'énergie de 1. Et affiche s'il est KO dans le cas ou l'énergie est <= 0.
	 * @return true si l'énergie est positive, false dans le cas contraire.
	 */
	public boolean consommeEnergie(){
		this.energy--;
		
		if(this.energy <= 0){
			System.out.println(this.name+" : je suis KO (de faim).");
			this.etatDeForme = "je suis KO (de faim)";
			this.etat = false;
			return false;
		}
		else {
			return true;
		}
	}
	
	
	/**
	 * Méthode qui décrémente le fun de 1, et affiche si le Tamagoshi est KO (si fun<0).
	 * @return true si son fun est positif, false dans le cas contraire.
	 */
	public boolean consommeFun(){
		this.fun--;
		
		if(this.fun <= 0){
			System.out.println(this.name+" : je suis KO (de non amusement).");
			this.etatDeForme = "je suis KO (de non amusement)";
			this.etat = false;
			return false;
		}
		
		else {
			return true;
		}
	}
	
	
	/**
	 * Méthode qui ajoute 1 à l'age du Tamagoshi.
	 */
	public void vieillir(){
		this.age++;
	}
	
	/**
	 * @param chaine (String) : représente la chaine de caractère que l'on souhaite retourner et afficher.
	 * @return la chaine de caractère placée en paramètre.
	 */
	public String toString(){
		String chaine = "\nNom : "+this.name+"\nEnergie : "+this.energy+"\nMax energie : "+this.maxEnergy+"\nAge : "+this.age;
		return chaine;
	}
	
	
	/**
	 * Getter du nom du Tamagoshi.
	 * @return name du Tamagoshi.
	 */
	public String getName(){
		return this.name;
	}
	
	
	/**
	 * Getter de l'état du Tamagoshi.
	 * @return etat du Tamagoshi.
	 */
	public boolean getEtat(){
		return this.etat;
	}
	
	
	/**
	 * Getter de l'age du Tamagoshi.
	 * @return age du Tamagoshi.
	 */
	public int getAge(){
		return this.age;
	}
	
	
	/**
	 * Méthode getter de la LifeTime.
	 * @return, la vie du tamagoshi.
	 */
	public int getLifeTime(){
		return Tamagoshi.lifeTime;
	}
	
	public String getEtatDeForme(){
		return this.etatDeForme;
	}
}
