package version_2;

import java.awt.BorderLayout;

public class Controleur {

	private Vue vue ;

	public Controleur(Vue vue) {

		this.vue = vue ;
	}


	public void continuer()
	{
		
		this.vue.continuerQuizz();
		
	}

}
