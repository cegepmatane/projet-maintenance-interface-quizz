

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


public class AngularQCM extends JPanel implements ActionListener 
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
    
    public AngularQCM()
    {
    	setPreferredSize(new Dimension(1250,625));
    	setOpaque(false);
    	initQuesRep();
    	JButton commancer = new JButton("Commencer");
    	commancer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				remove(commancer);
				menu = panMenu();
		        add(menu,BorderLayout.NORTH);
		        
		        quizz = panQuizz();
		        add(quizz,BorderLayout.CENTER);
		        
		        solution = panSolution() ;
		        add(solution,BorderLayout.SOUTH);
				repaint();
				revalidate();
			}});
    	
        add(commancer,BorderLayout.CENTER);
        
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
    	JPanel tmp = new JPanel();
    	tmp.setLayout(new GridBagLayout());
    	
    	if(essaiTtl == 10 || scoreInt >= 20)
    	{
    		JLabel congratulation = new JLabel("Fin de la partie !");
     	   	continuer = new JButton("Continuer");
     	   	quitter = new JButton("Quitter");
     	   	quitter.addActionListener(new ActionListener(){
     	   		public void actionPerformed(ActionEvent e)
     	   		{
     	   			remove(tmp);
     	   			remove(menu);
     	   			remove(quizz);
     	   			remove(solution);
     	   			removeAll();
    	 	   		repaint();
    	 	 	   	revalidate();
     	   			
     	   		}
     	   	}
     	   	);
        	c.insets = new Insets(10,50,0,50);
        	c.gridx = 0 ;
            c.gridy = 1 ;
            c.gridwidth = 1 ;
           
     	   	tmp.add(congratulation,c);
     	   	
     	   	c.insets = new Insets(10,50,0,50);
     	   	c.gridx = 1 ;
     	   	c.gridy = 1 ;
     	   	c.gridwidth = 1 ;
     	   	tmp.add(continuer,c);
     	   	
     	   c.insets = new Insets(10,50,0,50);
    	   	c.gridx = 2 ;
    	   	c.gridy = 1 ;
    	   	c.gridwidth = 1 ;
     	   	tmp.add(quitter, c);
     	   	
     	   	
     	   	continuer.addActionListener(new ActionListener(){
     	   		public void actionPerformed(ActionEvent e)
     	   		{
     	   			remove(tmp);
     	   			scoreInt = 0;
     	   			score.setText(scoreInt+"/20");
     	   			quizz = panQuizz() ;
     	   			solution = panSolution() ;
     	   			add(quizz,BorderLayout.CENTER);
     	   			add(solution,BorderLayout.SOUTH);
    	 	   		repaint();
    	 	 	   	revalidate();
     	   			
     	   		}
     	   	}
     	   	);
     	   	
     	   	repaint();
     	   	revalidate();
     	   
     	   	return(tmp);
    	}
    	else
    	{
    		JLabel congratulation = new JLabel("<html> Bien joue !<br> la reponse de cette question : "+questionTab.get(questionIdx)+"<br> est : "+reponseStr+"<br></html>");
     	   	continuer = new JButton("Continuer");
     	   	
        	c.insets = new Insets(10,50,0,50);
        	c.gridx = 0 ;
            c.gridy = 1 ;
            c.gridwidth = 1 ;
           
     	   	tmp.add(congratulation,c);
     	   	
     	   	c.insets = new Insets(10,50,0,50);
     	   	c.gridx = 1 ;
     	   	c.gridy = 1 ;
     	   	c.gridwidth = 1 ;
     	   	tmp.add(continuer,c);
     	   	
     	   	continuer.addActionListener(new ActionListener(){
     	   		public void actionPerformed(ActionEvent e)
     	   		{
     	   			remove(tmp);
     	   			quizz = panQuizz() ;
     	   			solution = panSolution() ;
     	   			add(quizz,BorderLayout.CENTER);
     	   			add(solution,BorderLayout.SOUTH);
    	 	   		repaint();
    	 	 	   	revalidate();
     	   			
     	   		}
     	   	}
     	   	);
     	   	
     	   	repaint();
     	   	revalidate();
     	   
     	   	return(tmp);
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
    	try
    	{
    		BufferedReader fichierRep = new BufferedReader(new FileReader("Quizz/Fichiers/Angular/CM1/Reponses.txt"));
    		BufferedReader fichierQues = new BufferedReader(new FileReader("Quizz/Fichiers/Angular/CM1/Questions.txt"));
    		String ligneQues ;
    		String ligneRep ;
    		cmp = 0 ;
    		while ((ligneQues = fichierQues.readLine()) != null && (ligneRep = fichierRep.readLine()) != null) {

    			System.out.println(ligneQues);
    			questionTab.add(ligneQues);
    			System.out.println(ligneRep);
    			reponseTab.add(ligneRep);
    			cmp++;
    			
    			}
    			
    		fichierQues.close();
    		fichierRep.close();
    	} 
    		catch (IOException e) {
    		e.printStackTrace() ;
    	}
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
    
   
	public static void main(String[] args) {
        JFrame f1 = new JFrame("Quizz");
        AngularQCM p1 = new AngularQCM();
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
}
