package graphic;

import javax.swing.JFrame;

import tamagoshis.Tamagoshi;

public class TamaFrame extends JFrame{
	
	private Tamagoshi tama;
	private TamaJPanel tjp;
	
	/**
	 * Constructeur de la classe.
	 */
	public TamaFrame(Tamagoshi tam, int pos){
		this.tama = tam;
		this.initFrameTama(pos);
	}
	
	
	/**
	 * Méthode qui initialise une frame.
	 */
	private void initFrameTama(int pos){
		
		this.tjp = new TamaJPanel(this.tama);
		this.add(tjp);
		
		this.setTitle(this.tama.getName()+" ("+this.tama.getClass().getSimpleName()+")");
		this.setSize(400, 400);
		this.setLocation(pos*401, 0);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}


	/**
	 * Méthode qui permet de récupérer le panel d'une frame.
	 * @return le Panel actf.
	 */
	public TamaJPanel getTjp() {
		return this.tjp;
	}
	
	public void setBtn(int i, boolean b){
		this.tjp.setBtn(i, b);
	}


	public Tamagoshi getTama() {
		return tama;
	}
}
