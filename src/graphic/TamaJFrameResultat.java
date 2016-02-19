package graphic;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tamagoshis.Tamagoshi;

public class TamaJFrameResultat extends JFrame {
	
	private ArrayList<Tamagoshi> tamasVivants;
	private ArrayList<Tamagoshi> tamasMorts;
	private int score;
	
	private JLabel lblTitre;
	private AudioClip applaudissements = Applet.newAudioClip(getClass().getResource("/sounds/autres/applaudissements.wav"));
	private AudioClip huements = Applet.newAudioClip(getClass().getResource("/sounds/autres/huements.wav"));

	private JLabel lblImg = new JLabel();
	
	
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
	}
	
	
	/**
	 * Méthode qui initialise la fenetre.
	 */
	private void initFenetre(){
		this.setTitle("Bilan de la partie");//On définit le titre de la fenetre
		this.setSize(600, 500);//On choisi la taille
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//Lors de la fermeture de la fenetre
		this.setLocationRelativeTo(null);//On centre la fenetre au milieu de l'écran
		this.setVisible(true);//On affiche la fenetre
	}
	
	
	/**
	 * Méthode qui initialise le panel avec les composants.
	 */
	private void initPanel(){
		
		//S'il y a un mort au moins alors game over, si non congratulations
		if(this.tamasMorts.size()>0){
			this.lblImg.setIcon(new ImageIcon(getClass().getResource("/images/game-over.png")));//Image de game over
			this.huements.play();//On lance le sons d'applaudissements
			
		}
		else{
			this.lblImg.setIcon(new ImageIcon(getClass().getResource("/images/congratulations.png")));//Image de congratulations
			this.applaudissements.play();//On lance le sons d'applaudissements
		}
		
		//calcul du nombre de tamagoshis
		int nbTama = this.tamasMorts.size()+this.tamasVivants.size();
		
		//Création d'un panel qui recevra tous les tamagoshis + le score
		JPanel panelTama = new JPanel(new GridLayout(nbTama+3, 1));
		
		//Alignement de l'image au centre du panel (north)
		this.lblImg.setHorizontalAlignment(JLabel.CENTER);
		this.add(lblImg, BorderLayout.NORTH);
		
		//Affichage du score 
		this.lblTitre = new JLabel("Votre score est de "+this.score+"%");
		this.lblTitre.setHorizontalAlignment(JLabel.CENTER);//Alignement du label au centre
		panelTama.add(this.lblTitre);
		
		//Affichage d'un "blanc" et du "bilan"
		panelTama.add(new JLabel(""));
		panelTama.add(new JLabel("BILAN : "));
		
		//Boucle pour afficher tous les tamagoshis viavnts
		for(Tamagoshi t : this.tamasVivants){
			panelTama.add(new JLabel(t.getName()+" qui était un "+t.getClass().getSimpleName()+" a survécu et vous remercie :)"));
		}
		
		//Boucle pour afficher tous les tamagoshis morts
		for (Tamagoshi t : this.tamasMorts){
			panelTama.add(new JLabel(t.getName()+" qui était un "+t.getClass().getSimpleName()+" n'est pas arrivé au bout et ne vous félicite pas :("));
		}
		
		//Ajout du panel au centre
		this.add(panelTama, BorderLayout.CENTER);
	}
	
}
