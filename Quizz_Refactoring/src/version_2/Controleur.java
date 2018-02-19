package version_2;

import java.awt.BorderLayout;

public class Controleur {

	private Vue vue ;

	public Controleur(Vue vue) {

		this.vue = vue ;
	}

	/** methode qui nous fait continuer le quizz */
	public void commencer()
	{
		
		this.vue.commencerQuizz();
		
	}
	
	/** methode qui nous fait continuer le quizz */
	public void continuer()
	{
		
		this.vue.continuerQuizz();
		
	}

}
