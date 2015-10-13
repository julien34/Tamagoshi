package tamagoshis;
import java.util.Random;

/**
 * Les variables sont les suivantes :
 * <ul>
 * 		<li>age (int): l'age du tamagoshi.</li>
 * 		<li>maxEnergy (int) : l'énergie qu'il peut avoir au maximum.</li>
 * 		<li>energy (int) : son énergie actuelle.</li>
 * 		<li>name (String) : le nom du Tamagoshi.</li>
 * </ul>
 * 
 * @author Siauvaud Julien
 */
public class Tamagoshi {
	private int age, maxEnergy, energy;
	private String name;
	private static int lifeTime = 10;
	private Random rand = new Random();
	private boolean etat = true;
	
	
	/**
	 * Constructeur d'un Tamagoshi.<br><br>
	 * Initialise le Tamagoshi avec un nom placé en paramètre, un age = 0, une énergie comprise entre 3 et 7, ainsi qu'une énergie maximale comprise entre 5 et 9.
	 * @param chaine représente le nom du Tamagoshi
	 */
	public Tamagoshi(String chaine) {
		this.name = chaine;
		this.age = 0;
		this.energy = 3 + rand.nextInt(4);
		this.maxEnergy = 5 + rand.nextInt(4);
	}
	
	/**
	 * Cette méthode retourne l'état du tamagoshi. Elle permet d'afficher son nom et son énergie également.
	 * @return etatDeForme (booléen) : true si énergie > 5, si non false.
	 */
	public boolean parle(){
		String etatDeForme;
		
		if (this.energy > 5){
			etatDeForme = "heureux";
			System.out.println(this.name+" : "+etatDeForme);
			return true;
		}
		else {
			etatDeForme = "affamé";
			System.out.println(this.name+" : "+etatDeForme);
			return false;
		}
	}
	
	
	
	/**
	 * Cette méthode permet de voir si le tamagoshi a gagné 1 point d'énergie ou s'il avait déjà une énergie suffisante.
	 * La méthode retourne true s'il accepte de manger ou false dans le cas contraire.
	 * @return true si le tamagoshi a été nourri. ou false dans le cas contraire.
	 */
	public boolean mange(){
		if(this.maxEnergy>this.energy){
			this.energy += 1+rand.nextInt(2);
			if(this.energy>this.maxEnergy){
				this.energy = this.maxEnergy;
			}
			System.out.println(this.name+" : Merci de m'avoir donné de l'énergie.");
			return true;
		}
		else {
			System.out.println(this.name+" : Je n'ai pas faim.");
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
			System.out.println(this.name+" : je suis KO.");
			this.etat = false;
			return false;
		}
		else {
			return true;
		}
	}
	
	
	/**
	 * Méthode qui ajoute 1 à l'&ge du Tamagoshi.
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
	
	public int getLifeTime(){
		return this.lifeTime;
	}
	
	/**
	 * Méthode main qui lance le programme.
	 * @param args
	 */
	public static void main(String args[]){
		//Création et affichage de l'objet tamagoshi :
		//Tamagoshi t1 = new Tamagoshi("T1");
		//System.out.println(t1.toString());
		
		//Tamagoshi t2 = new Tamagoshi("T2");
		//System.out.println(t2.toString());
		int nom = 19;
		int denom = 20;
		int mult = 100;
		double calcul = ((double)nom/(double)denom)*(double)mult;
		
		System.out.println(calcul);
	}
	
}
