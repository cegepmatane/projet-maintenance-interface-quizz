package version_2;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main {

	protected static void main(String[] args) {
		JFrame f1 = new JFrame("Quizz");
		f1.setSize(1250, 730);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		Vue vue = new Vue();
		Controleur controleur = new Controleur(vue);
		Quizz modele = new Quizz(controleur);
		f1.add(vue,BorderLayout.CENTER);
		
	}
}
