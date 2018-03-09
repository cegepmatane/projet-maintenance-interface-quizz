package version4;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame f1 = new JFrame("Quizz");
		f1.setSize(1250, 730);
		
		
		
		
		
		Quizz modele = new Quizz();
		Vue vue = new Vue(modele);
		Controleur controleur = new Controleur(vue);
		modele.definirControleur(controleur);
		f1.add(vue,BorderLayout.CENTER);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
