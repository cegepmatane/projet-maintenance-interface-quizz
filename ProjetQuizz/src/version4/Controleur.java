package version4;

import java.awt.BorderLayout;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/** Controleur du Quizz */
public class Controleur {

	private Vue vue ;

	public Controleur(Vue vue) {

		this.vue = vue ;
	}

	/** methode qui nous fait commencer le quizz 
	 * @throws IOException */
	public void commencer() throws IOException
	{	
		this.vue.commencerQuizz();
	}
	
	/** methode qui nous fait continuer le quizz lorsque l'on trouve une bonne reponse
	 * @throws IOException */
	public void continuer() throws IOException
	{
		this.vue.continuerQuizz();
		if(this.vue.getModele().recupererEssaiTtl() >= 1 ){
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Quizz/Notes/"+this.vue.getModele().recupererCategoriePhrase()+".txt",true)));
		   out.println(this.vue.getModele().recupererScoreInt());
		   out.close();
		}
	}
	
	/** methode qui nous fait commencer le quizz lorsqu'il est termine 
	 * @throws IOException */
	public void continuerFinDePartie() throws IOException
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

}
