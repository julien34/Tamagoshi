package tamagoshis;

public class GrosJoueur extends Tamagoshi{

	public GrosJoueur(String chaine) {
		super(chaine);
	}

	@Override
	public boolean consommeFun() {
		this.fun--;
		return super.consommeFun();
	}
	
	

}
