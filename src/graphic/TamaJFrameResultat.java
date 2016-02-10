package graphic;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import tamagoshis.Tamagoshi;

public class TamaJFrameResultat extends JFrame {
	
	private ArrayList<Tamagoshi> tamasVivants;
	private ArrayList<Tamagoshi> tamasMorts;
	private int score;
	
	private JLabel lblTitre;
	private AudioClip clip = Applet.newAudioClip(getClass().getResource("/sounds/autres/applaudissements.wav"));;

	
	/**
	 * Constructeur de la fenetre TamaJframeResultat. Affiche le bilan de la partie.
	 * @param listeTamaVivants, la liste des tamagoshi ayant survécus.
	 * @param listeTamaMorts, la liste des tamagoshis étant morts.
	 * @param score, le score de la partie.
	 */
	public TamaJFrameResultat(ArrayList<Tamagoshi> listeTamaVivants, ArrayList<Tamagoshi> listeTamaMorts, int score){
		this.tamasVivants = listeTamaVivants;
		this.tamasMorts = listeTamaMorts;
		this.score = score;
		
		this.initFenetre();//Initialise la fenêtre
		this.initPanel();//Initialise le panel
		this.clip.loop();//On boucle sur le sons d'applaudissements
	}
	
	
	/**
	 * Méthode qui initialise la fenetre.
	 */
	private void initFenetre(){
		this.setTitle("Bilan de la partie");//On définit le titre de la fenetre
		this.setSize(600, 400);//On choisi la taille
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//Lors de la fermeture de la fenetre
		this.setLocationRelativeTo(null);//On centre la fenetre au milieu de l'écran
		this.setVisible(true);//On affiche la fenetre
	}
	
	
	/**
	 * Méthode qui initialise le panel avec les composants.
	 */
	private void initPanel(){
		this.getContentPane().setLayout(new BorderLayout());//On initialise le layout en Borderlayout
		this.lblTitre = new JLabel("Votre score est de "+this.score+"%");
		
		this.add(this.lblTitre, BorderLayout.NORTH);
	}
	
}
