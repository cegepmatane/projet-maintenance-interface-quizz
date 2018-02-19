package version_1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Listequestion {
	
	private ArrayList<String> questionTab;
	private int compteurQuestion = 0 ;
	public Listequestion(String categorie) {
		
		questionTab = new ArrayList<String>();
		try
    	{
    		BufferedReader fichierQues = new BufferedReader(new FileReader("Quizz/Fichiers/"+categorie+"/Questions.txt"));
    		String ligneQues ;

    		while ((ligneQues = fichierQues.readLine()) != null ) {

    			System.out.println(ligneQues);
    			questionTab.add(ligneQues);
    			compteurQuestion++;
    			
    			}
    			
    		fichierQues.close();
    	} 
    		catch (IOException e) {
    		e.printStackTrace() ;
    	}
	}
	
	public static void main(String[] args)
	{
		Listequestion question = new Listequestion("Angular/CM1");
	}

	public ArrayList<String> recupererQuestionTab() {
		return questionTab;
	}

	public int recupererCompteurQuestion() {
		return compteurQuestion;
	}

	public void definirQuestionTab(ArrayList<String> questionTab) {
		this.questionTab = questionTab;
	}

	public void definirCompteurQuestion(int compteurQuestion) {
		this.compteurQuestion = compteurQuestion;
	}
}
