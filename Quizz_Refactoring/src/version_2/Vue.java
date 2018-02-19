package version_2;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;


public class Vue extends JPanel implements ActionListener 
{

	private JLabel question;

	private JLabel score ;
	private JLabel matiere;
	private JLabel moyenne;


	private JRadioButton reponse;

	private JRadioButton[] reponseAlea = new JRadioButton[4];

	private JButton valider,continuer,quitter,terminer;


	private int questionIdx,reponseIdx;
	private int choice ;
	private int[] aleaIdx ;
	private int moyenneInt, scoreInt = 0 ;
	public int cmp  ;

	private ArrayList<String> questionTab = new ArrayList <String>();
	private ArrayList<String> reponseTab = new ArrayList <String>();

	private GridBagConstraints c ;

	private Border loweredetched;
	private Border titleBorder ;

	private JLabel essai;

	private JButton solutionButton;

	private JPanel solution;

	private JPanel quizz;

	private JPanel menu;

	protected String essaiStr = "";

	protected int essaiInt = 0;

	protected String reponseStr = "";

	private int essaiTtl;

	private Controleur controleur ;

	private JPanel panelTampon;

	private JButton commencer ;
	
	public Vue()
	{

		controleur = new Controleur(this);


		setPreferredSize(new Dimension(1250,625));
		setOpaque(false);
		initQuesRep();
		commencer = new JButton("Commencer");
		commencer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				commencerQuizz() ;
			}

			});

		add(commencer,BorderLayout.CENTER);

	}

	public JPanel panSolution()
	{
		JPanel solution = new JPanel();
		solution.setOpaque(false);
		solution.setLayout(new GridBagLayout());
		solution.setPreferredSize(new Dimension(1150,120));
		essai = new JLabel(essaiStr);
		solutionButton = new JButton("Afficher la solution");
		solutionButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				essai.setText(reponseStr);
				scoreInt -= (3 - essaiInt) ;
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

		if(essaiTtl == 10 || scoreInt >= 20)
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
			JLabel congratulation = new JLabel("<html> Bien joue !<br> la reponse de cette question : "+questionTab.get(questionIdx)+"<br> est : "+reponseStr+"<br></html>");
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		quizz.setLayout(new GridBagLayout());
		c = new GridBagConstraints();

		/// STEP 1 : definition d'un index aleatoire ///
		questionIdx = (int)(Math.random()*questionTab.size());

		if(questionIdx == questionTab.size())
		{
			questionIdx -= (int)(Math.random()*questionTab.size());
		}

		reponseIdx = questionIdx ;

		/// STEP 2 : definition d'une question aleatoire ///

		question = new JLabel(questionTab.get(questionIdx));


		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		titleBorder = BorderFactory.createTitledBorder(
				loweredetched, (String) question.getText());
		quizz.setBorder(titleBorder);

		/// STEP 3 :recuperation des reponsses ///

		reponse = new JRadioButton(reponseTab.get(reponseIdx)) ;
		reponseStr = reponse.getText() ;
		reponse.addActionListener(this);
		reponseAlea[0] = reponse ;
		int[] idx = new int[4] ;
		idx[0]=reponseIdx;
		System.out.println(reponseIdx+" : "+idx[0]);
		/** insertion des differentes reponses fausse aleatoire */

		Random r = new Random();
		int indexAlea = 0 + r.nextInt(reponseTab.size() - 0) ;

		for(int j = 1 ; j<idx.length; j++)
		{
			int k = 1 ;
			System.out.println(j+" 1er");
			while(k<idx.length)
			{
				System.out.println("index k :"+k+" pour le random : "+indexAlea);
				if(indexAlea == reponseIdx)
				{
					indexAlea = 0 + r.nextInt(reponseTab.size() - 0) ;
					k = 0 ;
				}
				if(idx[k] == indexAlea)
				{
					indexAlea = 0 + r.nextInt(reponseTab.size() - 0) ;
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
			JRadioButton tmp = new JRadioButton(reponseTab.get(idx[i])) ;
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
		if(essaiTtl == 20)
		{
			panWin();
		}
	}
	private void initQuesRep() 
	{
		Listequestion listeDeQuestion = new Listequestion("ANGULAR/CM1");
		Listereponse listeDeReponse = new Listereponse("ANGULAR/CM1");

		questionTab = listeDeQuestion.recupererQuestionTab();
		reponseTab = listeDeReponse.recupererReponseTab();

	}

	private JPanel panMenu() {

		JPanel menu = new JPanel();
		menu.setOpaque(false);
		menu.setPreferredSize(new Dimension(1150,125));
		/// STEP 1 : definition d'un index aleatoire ///

		matiere = new JLabel("Révision des bases du langages AngularJS");
		moyenne = new JLabel(moyenneInt+"/20");
		score = new JLabel(scoreInt+"/20");


		if(scoreInt >= 20 || essaiTtl >= 20)
		{
			essaiTtl = 0 ;
			scoreInt = 0 ;
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
				System.exit(0);       
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
		menu = panMenu();
		add(menu,BorderLayout.NORTH);

		quizz = panQuizz();
		add(quizz,BorderLayout.CENTER);

		solution = panSolution() ;
		add(solution,BorderLayout.SOUTH);
		repaint();
		revalidate();
		
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

	protected void continuerFinDePartie()
	{
		remove(panelTampon);
		scoreInt = 0;
		score.setText(scoreInt+"/20");
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
	
	protected static void main(String[] args) {
		JFrame f1 = new JFrame("Quizz");
		Vue p1 = new Vue();
		f1.add(p1,BorderLayout.CENTER);
		f1.setSize(1250, 730);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
					choice = i ;
				}
			}
		}
	}

	public String getQuestion() {
		return question.getText();
	}

	public String getScore() {
		return score.getText();
	}

	public String getMatiere() {
		return matiere.getText();
	}

	public String getMoyenne() {
		return moyenne.getText();
	}

	public JRadioButton getReponse() {
		return reponse;
	}

	public String[] getReponseAlea() {

		String[] tmp = new String[reponseAlea.length];
		for (int i = 0; i < reponseAlea.length; i++) {
			tmp[i] = reponseAlea[0].getText();
		}
		return tmp;
	}

	public String getValider() {
		return valider.getText();
	}

	public String getContinuer() {
		return continuer.getText();
	}

	public String getQuitter() {
		return quitter.getText();
	}

	public String getTerminer() {
		return terminer.getText();
	}

	public int getQuestionIdx() {
		return questionIdx;
	}

	public int getReponseIdx() {
		return reponseIdx;
	}

	public int getChoice() {
		return choice;
	}

	public int[] getAleaIdx() {
		return aleaIdx;
	}

	public int getMoyenneInt() {
		return moyenneInt;
	}

	public int getScoreInt() {
		return scoreInt;
	}

	public int getCmp() {
		return cmp;
	}

	public ArrayList<String> getQuestionTab() {
		return questionTab;
	}

	public ArrayList<String> getReponseTab() {
		return reponseTab;
	}

	public String getC() {
		return c.toString();
	}

	public String getLoweredetched() {
		return loweredetched.toString();
	}

	public String getTitleBorder() {
		return titleBorder.toString();
	}

	public String getEssai() {
		return essai.getText();
	}

	public String getSolutionButton() {
		return solutionButton.getText();
	}

	public String getSolution() {
		return solution.toString();
	}

	public String getQuizz() {
		return quizz.toString();
	}

	public String getMenu() {
		return menu.toString();
	}

	public String getEssaiStr() {
		return essaiStr;
	}

	public int getEssaiInt() {
		return essaiInt;
	}

	public String getReponseStr() {
		return reponseStr;
	}

	public int getEssaiTtl() {
		return essaiTtl;
	}

	public void setQuestion(String question) {
		this.question.setText(question);
	}

	public void setScore(String score) {
		this.score.setText(score);
	}

	public void setMatiere(String matiere) {
		this.matiere.setText(matiere);
	}

	public void setMoyenne(String moyenne) {
		this.moyenne.setText(moyenne);
	}

	public void setReponse(String reponse) {
		this.reponse.setText(reponse);
	}

	public void setReponseAlea(String[] reponse) {

		for (int i = 0; i < reponseAlea.length; i++) {
			this.reponseAlea[i].setText(reponse[i]) ;
		}
	}

	public void setValider(String valider) {
		this.valider.setText(valider);
	}

	public void setContinuer(String continuer) {
		this.continuer.setText(continuer);
	}

	public void setQuitter(String quitter) {
		this.quitter.setText(quitter);
	}

	public void setTerminer(String terminer) {
		this.terminer.setText(terminer);
	}

	public void setQuestionIdx(int questionIdx) {
		this.questionIdx = questionIdx;
	}

	public void setReponseIdx(int reponseIdx) {
		this.reponseIdx = reponseIdx;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public void setAleaIdx(int[] aleaIdx) {
		this.aleaIdx = aleaIdx;
	}

	public void setMoyenneInt(int moyenneInt) {
		this.moyenneInt = moyenneInt;
	}

	public void setScoreInt(int scoreInt) {
		this.scoreInt = scoreInt;
	}

	public void setCmp(int cmp) {
		this.cmp = cmp;
	}

	public void setQuestionTab(ArrayList<String> questionTab) {
		this.questionTab = questionTab;
	}

	public void setReponseTab(ArrayList<String> reponseTab) {
		this.reponseTab = reponseTab;
	}

	public void setEssai(String essai) {
		this.essai.setText(essai);
	}

	public void setSolutionButton(String solutionButton) {
		this.solutionButton.setText(solutionButton);
	}

	public void setMenu(JPanel menu) {
		this.menu = menu;
	}

	public void setEssaiStr(String essaiStr) {
		this.essaiStr = essaiStr;
	}

	public void setEssaiInt(int essaiInt) {
		this.essaiInt = essaiInt;
	}

	public void setReponseStr(String reponseStr) {
		this.reponseStr = reponseStr;
	}

	public void setEssaiTtl(int essaiTtl) {
		this.essaiTtl = essaiTtl;
	}

	public void validerReponse() {
		if(reponseAlea[choice].getText() == reponse.getText())
		{
			scoreInt++;

			essaiInt = 0 ;
			essaiStr = "" ;
			score.setText(scoreInt+"/20");
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
			scoreInt -= 1 ;
			essaiInt += 1 ;
			if(essaiInt >= 3)
			{
				essaiStr = reponseStr;
			}
			else
			{
				essaiStr += "|" ;
			}
			essai.setText(essaiStr);
			score.setText(scoreInt+"/20");
			System.out.println(essaiInt);
		}

		essaiTtl += 1 ;
		
	}
}

