package version4;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

/** Vue du Quizz */
public class Vue extends JPanel implements ActionListener 
{

	private JLabel question;

	private JLabel score ;
	private JLabel matiere;
	private JLabel moyenne;


	private JRadioButton reponse;

	private JRadioButton[] reponseAlea = new JRadioButton[4];

	private JButton valider,continuer,quitter,terminer;


	private GridBagConstraints c ;

	private Border loweredetched;
	private Border titleBorder ;

	private JLabel essai;

	private JButton solutionButton;

	private JPanel solution;

	private JPanel quizz;

	private JPanel menu;

	private Controleur controleur ;

	private JPanel panelTampon;

	private JButton commencer ;

	private JComboBox<String> categorieBox;

	private JLabel categorieLabel ;

	private JComboBox<String> sousCategorieBox;

	private JLabel sousCategorieLabel ;

	private Quizz modele ;

	private JLabel information;

	private JComboBox<String> combo;

	private JPanel score1;

	public Vue()
	{
		controleur = new Controleur(this);


		setPreferredSize(new Dimension(1250,625));
		setOpaque(false);

		categorieLabel = new JLabel("Catégorie :");
		categorieBox = new JComboBox<String>();
		categorieBox.addItem(modele.recupererCategorieListe()[0]);

		sousCategorieLabel = new JLabel("Cours : ");
		sousCategorieBox = new JComboBox<String>();
		sousCategorieBox.addItem(modele.recupererSousCategorieListe()[0]);

		commencer = new JButton("Commencer");
		commencer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				controleur.commencerLeQuizz() ;
			}

		});

		add(commencer,BorderLayout.CENTER);

		add(categorieLabel,BorderLayout.CENTER);
		add(categorieBox,BorderLayout.CENTER);

		add(sousCategorieLabel,BorderLayout.CENTER);
		add(sousCategorieBox,BorderLayout.CENTER);

	}

	public JPanel panSolution()
	{
		JPanel solution = new JPanel();
		solution.setOpaque(false);
		solution.setLayout(new GridBagLayout());
		solution.setPreferredSize(new Dimension(1150,120));
		essai = new JLabel(modele.recupererEssaiStr());
		solutionButton = new JButton("Afficher la solution");
		solutionButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				controleur.afficherSolution();
			}

		});

		c.insets = new Insets(10,50,10,50);
		c.gridx = 1 ;
		c.gridy = 1 ;
		c.gridwidth = 1 ;

		solution.add(essai,c);

		c.insets = new Insets(10,50,10,50);
		c.gridx = 1 ;
		c.gridy = 2 ;
		c.gridwidth = 1 ;

		solution.add(solutionButton,c);
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		titleBorder = BorderFactory.createTitledBorder(
				loweredetched, (String) " Essais et indice ");
		solution.setBorder(titleBorder);
		return solution;
	}

	public JPanel panWin()
	{
		panelTampon = new JPanel();
		panelTampon.setLayout(new GridBagLayout());

		if(modele.recupererEssaiTtl() == 10 || modele.recupererScoreInt() >= 20)
		{
			JLabel congratulation = new JLabel("Fin de la partie !");
			continuer = new JButton("Continuer");
			quitter = new JButton("Quitter");
			quitter.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{

					controleur.quitter();
				}
			}
					);
			c.insets = new Insets(10,50,0,50);
			c.gridx = 0 ;
			c.gridy = 1 ;
			c.gridwidth = 1 ;

			panelTampon.add(congratulation,c);

			c.insets = new Insets(10,50,0,50);
			c.gridx = 1 ;
			c.gridy = 1 ;
			c.gridwidth = 1 ;
			panelTampon.add(continuer,c);

			c.insets = new Insets(10,50,0,50);
			c.gridx = 2 ;
			c.gridy = 1 ;
			c.gridwidth = 1 ;
			panelTampon.add(quitter, c);


			continuer.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{

					controleur.continuerFinDePartie();

				}
			}
					);

			repaint();
			revalidate();

			return(panelTampon);
		}
		else
		{
			JLabel congratulation = new JLabel("<html> Bien joue !<br> la reponse de cette question : "+modele.recupererQuestionTab().get(modele.recupererQuestionIdx())+"<br> est : "+modele.recupererReponseStr()+"<br></html>");
			continuer = new JButton("Continuer");

			c.insets = new Insets(10,50,0,50);
			c.gridx = 0 ;
			c.gridy = 1 ;
			c.gridwidth = 1 ;

			panelTampon.add(congratulation,c);

			c.insets = new Insets(10,50,0,50);
			c.gridx = 1 ;
			c.gridy = 1 ;
			c.gridwidth = 1 ;
			panelTampon.add(continuer,c);

			continuer.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					controleur.continuer();

				}
			}
					);

			repaint();
			revalidate();

			return(panelTampon);
		}

	}

	private JPanel panQuizz() {


		JPanel quizz = new JPanel();
		quizz.setOpaque(false);
		try {
			quizz.setPreferredSize(new Dimension(1150,250));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		quizz.setLayout(new GridBagLayout());
		c = new GridBagConstraints();

		/// STEP 1 : definition d'un index aleatoire ///
		modele.definirQuestionIdx((int)(Math.random()*modele.recupererQuestionTab().size()));

		if(modele.recupererQuestionIdx() == modele.recupererQuestionTab().size())
		{
			modele.definirQuestionIdx(modele.recupererQuestionIdx()-(int)(Math.random()*modele.recupererQuestionTab().size()));
		}

		modele.definirReponseIdx(modele.recupererQuestionIdx()) ;

		/// STEP 2 : definition d'une question aleatoire ///

		question = new JLabel(modele.recupererQuestionTab().get(modele.recupererQuestionIdx()));


		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		titleBorder = BorderFactory.createTitledBorder(
				loweredetched, (String) question.getText());
		quizz.setBorder(titleBorder);

		/// STEP 3 :recuperation des reponsses ///

		reponse = new JRadioButton(modele.recupererReponseTab().get(modele.recupererReponseIdx())) ;
		modele.definirReponseStr(reponse.getText()) ;
		reponse.addActionListener(this);
		reponseAlea[0] = reponse ;
		int[] idx = new int[4] ;
		idx[0]= modele.recupererReponseIdx();
		System.out.println(modele.recupererReponseIdx()+" : "+idx[0]);
		/** insertion des differentes reponses fausse aleatoire */

		Random r = new Random();
		int indexAlea = 0 + r.nextInt(modele.recupererReponseTab().size() - 0) ;

		for(int j = 1 ; j<idx.length; j++)
		{
			int k = 1 ;
			System.out.println(j+" 1er");
			while(k<idx.length)
			{
				System.out.println("index k :"+k+" pour le random : "+indexAlea);
				if(indexAlea == modele.recupererReponseIdx())
				{
					indexAlea = 0 + r.nextInt(modele.recupererReponseTab().size() - 0) ;
					k = 0 ;
				}
				if(idx[k] == indexAlea)
				{
					indexAlea = 0 + r.nextInt(modele.recupererReponseTab().size() - 0) ;
					k = 0 ;
				}

				else
				{
					k++;
				}
			}
			idx[j]= indexAlea ;
		}

		for(int i = 0 ; i < 4 ; i++)
		{
			JRadioButton tmp = new JRadioButton(modele.recupererReponseTab().get(idx[i])) ;
			tmp.addActionListener(this);
			reponseAlea[i] = tmp ;
		}

		for(int i = 0 ; i < 4 ; i++)
		{

			System.out.println(idx[i]+" "+i);
		}

		/// STEP 4 : melange des reponses ///

		for (int i=0; i < reponseAlea.length; i++) {
			int randomPosition = r.nextInt(reponseAlea.length);
			JRadioButton tmp = reponseAlea[i];
			reponseAlea[i] = reponseAlea[randomPosition];
			reponseAlea[randomPosition] = tmp;
		}

		/// STEP 6 : definition de l evenement ///

		valider = new JButton("Valider");

		/// STEP 7 : definition du layout et mise en place des objets ///

		quizz.setLayout(new GridBagLayout());
		c = new GridBagConstraints();

		c.gridx = 0 ;
		c.gridy = 1 ;
		c.gridwidth = 1 ;
		c.insets = new Insets(10,10,0,0);
		quizz.add(reponseAlea[0], c);

		c.gridx = 0 ;
		c.gridy = 2 ;
		c.gridwidth = 1 ;
		c.insets = new Insets(10,10,0,0);
		quizz.add(reponseAlea[1], c);

		c.gridx = 0 ;
		c.gridy = 3 ;
		c.gridwidth = 1 ;
		c.insets = new Insets(10,10,0,0);
		quizz.add(reponseAlea[2], c);

		c.gridx = 0 ;
		c.gridy = 4 ;
		c.gridwidth = 1 ;
		c.insets = new Insets(10,10,0,0);
		quizz.add(reponseAlea[3], c);

		valider.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

				controleur.validerReponse();
			}

		});


		c.gridx = 0 ;
		c.gridy = 5 ;
		c.gridwidth = 1 ;

		quizz.add(valider, c);

		return (quizz);
	}

	private void ScoreFin()
	{
		if(modele.recupererEssaiTtl() == 20)
		{
			panWin();
		}
	}

	private JPanel panMenu() {

		JPanel menu = new JPanel();
		menu.setOpaque(false);
		menu.setPreferredSize(new Dimension(1150,125));
		/// STEP 1 : definition d'un index aleatoire ///

		matiere = new JLabel("Révision des bases du langages AngularJS");
		moyenne = new JLabel(modele.recupererMoyenneInt()+"/20");
		score = new JLabel(modele.recupererScoreInt()+"/20");


		if(modele.recupererScoreInt() >= 20 || modele.recupererEssaiTtl() >= 20)
		{
			modele.definirEssaiTtl(0) ;
			modele.definirScoreInt(0) ;
		}
		/// STEP 7 : definition du layout et mise en place des objets ///

		menu.setLayout(new GridBagLayout());
		c = new GridBagConstraints();

		c.gridx = 0 ;
		c.gridy = 1 ;
		c.gridwidth = 1 ;
		c.insets = new Insets(0,10,0,50);
		menu.add(matiere, c);
		c.gridx = 1 ;
		c.gridy = 2 ;
		c.insets = new Insets(0,0,15,50);

		menu.add(score, c);
		c.gridx = 2 ;
		c.gridy = 2 ;
		c.gridwidth = 1 ;
		c.insets = new Insets(0,0,15,50);
		menu.add(moyenne, c);

		quitter = new JButton("Quitter l'application");
		quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				controleur.quitterPendantLaPartie();      
			}

		});

		c.gridx = 0 ;
		c.gridy = 2 ;
		c.gridwidth = 1 ;
		c.insets = new Insets(15,10,35,50);
		menu.add(quitter, c);

		return (menu);
	}

	protected void commencerQuizz() {

		remove(commencer);
		remove(categorieBox);
		remove(sousCategorieBox);
		remove(categorieLabel);
		remove(sousCategorieLabel);

		modele.definirCategoriePhrase((String) categorieBox.getSelectedItem());
		modele.definirSousCategoriePhrase((String) sousCategorieBox.getSelectedItem());


		modele.initialiserQuestionEtReponse();

		menu = panMenu();
		add(menu,BorderLayout.NORTH);

		quizz = panQuizz();
		add(quizz,BorderLayout.CENTER);

		solution = panSolution() ;
		add(solution,BorderLayout.SOUTH);

		score1 = panScore();
		add(score1,BorderLayout.SOUTH);

		repaint();
		revalidate();

	}

	public JPanel panScore()
	{
		JPanel score1 = new JPanel();
		score1.setOpaque(false);
		score1.setLayout(new GridBagLayout());
		score1.setPreferredSize(new Dimension(1150,120));
		combo = new JComboBox<String>();

		information = new JLabel("Visualiser les scores par thémes :"); 
		information.setLocation(10,20);
		information.setBorder(new EmptyBorder(0,0,0,10));
		valider = new JButton("Valider");
		valider.setLocation(122, 120);
		combo.setPreferredSize(new Dimension(100, 20));
		combo.addItem("Angular");
		score1.add(information);
		score1.add(combo);
		score1.add(valider);

		valider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String matiere = (String) combo.getSelectedItem();
				try {
					BufferedReader fichierTheme1 = new BufferedReader(new FileReader(new File("Quizz/Notes/"+matiere+".txt")));
					String notePhrase = fichierTheme1.readLine();
					int note =  Integer.parseInt(notePhrase);
					System.out.println(note);
					score1.remove(valider);
					information.setText("Dernière note : "+note);
					score1.remove(combo);
					score1.repaint();
					score1.revalidate();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});


		titleBorder = BorderFactory.createTitledBorder(
				loweredetched, (String) " Score");
		score1.setBorder(titleBorder);
		return score1;
	}


	protected void continuerQuizz()
	{
		remove(panelTampon);
		quizz = panQuizz() ;
		solution = panSolution() ;
		add(quizz,BorderLayout.CENTER);
		add(solution,BorderLayout.SOUTH);
		repaint();
		revalidate();
	}

	protected void validerReponse() {
		if(reponseAlea[modele.recupererChoice()].getText() == reponse.getText())
		{
			modele.definirScoreInt(modele.recupererScoreInt()+1);

			modele.definirEssaiInt(0) ;
			modele.definirEssaiStr("");
			score.setText(modele.recupererScoreInt()+"/20");
			remove(quizz);
			remove(solution);
			add(panWin());
			repaint();
			revalidate();

		}
		else
		{
			for(int i = 0 ; i < 4 ; i++)
			{
				reponseAlea[i].setSelected(false);
			}
			modele.definirScoreInt(modele.recupererScoreInt()-1) ;
			modele.definirEssaiInt(modele.recupererEssaiInt()+1) ;
			if(modele.recupererEssaiInt() >= 3)
			{
				modele.definirEssaiStr(modele.recupererReponseStr());
			}
			else
			{
				modele.definirEssaiStr(modele.recupererEssaiStr()+"|");
			}
			essai.setText(modele.recupererEssaiStr());
			score.setText(modele.recupererEssaiInt()+"/20");
			System.out.println(modele.recupererEssaiInt());
		}

		modele.definirEssaiTtl(modele.recupererEssaiTtl()+1) ;

	}

	protected void afficherSolution() {
		essai.setText(modele.recupererReponseStr());
		modele.definirScoreInt(modele.recupererScoreInt() - (3 - modele.recupererEssaiInt()))  ;

	}

	protected void quitterPendantLaPartie() {
		System.exit(0); 

	}

	protected void commencerLeQuizz() {
		commencerQuizz();	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		{
			for(int i = 0 ; i < 4 ; i++)
			{
				if (e.getSource() != reponseAlea[i])
				{
					reponseAlea[i].setSelected(false);
				}
				else
				{
					reponseAlea[i].setSelected(true);
					modele.definirChoice(i) ;
				}
			}
		}
	}

	protected void continuerFinDePartie()
	{
		remove(panelTampon);
		modele.definirScoreInt(0);
		score.setText(modele.recupererScoreInt()+"/20");
		quizz = panQuizz() ;
		solution = panSolution() ;
		add(quizz,BorderLayout.CENTER);
		add(solution,BorderLayout.SOUTH);
		repaint();
		revalidate();
	}

	protected void quitter()
	{
		remove(panelTampon);
		remove(menu);
		remove(quizz);
		remove(solution);
		removeAll();
		repaint();
		revalidate();
	}

	public String recupererQuestion() {
		return question.getText();
	}

	public String recupererScore() {
		return score.getText();
	}

	public String recupererMatiere() {
		return matiere.getText();
	}

	public String recupererMoyenne() {
		return moyenne.getText();
	}

	public JRadioButton recupererReponse() {
		return reponse;
	}

	public String[] recupererReponseAlea() {

		String[] tmp = new String[reponseAlea.length];
		for (int i = 0; i < reponseAlea.length; i++) {
			tmp[i] = reponseAlea[0].getText();
		}
		return tmp;
	}

	public String recupererValider() {
		return valider.getText();
	}

	public String recupererContinuer() {
		return continuer.getText();
	}

	public String recupererQuitter() {
		return quitter.getText();
	}

	public String recupererTerminer() {
		return terminer.getText();
	}

	public String recupererC() {
		return c.toString();
	}

	public String recupererLoweredetched() {
		return loweredetched.toString();
	}

	public String recupererTitleBorder() {
		return titleBorder.toString();
	}

	public String recupererEssai() {
		return essai.getText();
	}

	public String recupererSolutionButton() {
		return solutionButton.getText();
	}

	public String recupererSolution() {
		return solution.toString();
	}

	public String recupererQuizz() {
		return quizz.toString();
	}

	public String recupererMenu() {
		return menu.toString();
	}

	public void definirQuestion(String question) {
		this.question.setText(question);
	}

	public void definirScore(String score) {
		this.score.setText(score);
	}

	public void definirMatiere(String matiere) {
		this.matiere.setText(matiere);
	}

	public void definirMoyenne(String moyenne) {
		this.moyenne.setText(moyenne);
	}

	public void definirReponse(String reponse) {
		this.reponse.setText(reponse);
	}

	public void definirReponseAlea(String[] reponse) {

		for (int i = 0; i < reponseAlea.length; i++) {
			this.reponseAlea[i].setText(reponse[i]) ;
		}
	}

	public void definirValider(String valider) {
		this.valider.setText(valider);
	}

	public void definirContinuer(String continuer) {
		this.continuer.setText(continuer);
	}

	public void definirQuitter(String quitter) {
		this.quitter.setText(quitter);
	}

	public void definirTerminer(String terminer) {
		this.terminer.setText(terminer);
	}

	public void definirEssai(String essai) {
		this.essai.setText(essai);
	}

	public void definirSolutionButton(String solutionButton) {
		this.solutionButton.setText(solutionButton);
	}

	public void definirMenu(JPanel menu) {
		this.menu = menu;
	}

	public Quizz recupererModele() {
		return modele;
	}

	public void definirModele(Quizz modele) {
		this.modele = modele;
	}


}

