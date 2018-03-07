package version3;

import java.awt.BorderLayout;

/** Controleur du Quizz */
public class Controleur {

	private Vue vue ;

	public Controleur(Vue vue) {

		this.vue = vue ;
	}

	/** methode qui nous fait commencer le quizz */
	public void commencer()
	{	
		this.vue.commencerQuizz();
	}
	
	/** methode qui nous fait continuer le quizz lorsque l'on trouve une bonne reponse*/
	public void continuer()
	{
		this.vue.continuerQuizz();
	}
	
	/** methode qui nous fait commencer le quizz lorsqu'il est termine */
	public void continuerFinDePartie()
	{
		this.vue.continuerFinDePartie();
	}
	
	/** methode qui nous fait quitter le quizz */
	public void quitter()
	{
		this.vue.quitter();
	}

	public void validerReponse() {

		this.vue.validerReponse();
		
	}

	public void afficherSolution() {

		this.vue.afficherSolution();
		
	}

	public void quitterPendantLaPartie() {

		this.vue.quitterPendantLaPartie();
	}

	public void commencerLeQuizz() {
		this.vue.commencerLeQuizz();
		
	}

}
