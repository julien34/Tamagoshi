package tamagoshis;

public class GrosMangeur extends Tamagoshi {

	public GrosMangeur(String chaine) {
		super(chaine);
	}

	@Override
	public boolean consommeEnergie() {
		this.energy--;
		return super.consommeEnergie();
	}

}