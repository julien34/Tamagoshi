package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utilisateur {
	

	 /**
	  * Méthode qui permet de saisir un texte depuis le clavier, et retourne ce dernier.
	  * 
	  * @return l'entrée saisie au clavier en levant une xception dans le cas où il y en aurait une.
	 */
	public static String saisieClavier(){

		    /*il faut gérer les exceptions car l'entrée standard 
		    peut ne pas être disponible : le constructeur de la 
		    classe InputStreamReader peut renvoyer une exception.*/
		    try{ 
		      BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
		      return clavier.readLine();
		    }
		    catch(IOException e){
		      e.printStackTrace();
		      System.exit(0);
		      return null;
		    }
	 }
	
	
	// une méthode main juste pour tester 
	  public static void main(String[] args) {
	    String saisie = Utilisateur.saisieClavier();
	    System.out.println("la saisie est : "+saisie);
	  }
}
