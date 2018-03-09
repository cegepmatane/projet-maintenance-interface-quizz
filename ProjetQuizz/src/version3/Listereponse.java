package version3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Listereponse {

	private ArrayList<String> reponseTab;
	private int compteurReponse = 0 ;
	public Listereponse(String categorie) {

		reponseTab = new ArrayList<String>();
		try
		{
			BufferedReader fichierQues = new BufferedReader(new FileReader("Quizz/Fichiers/"+categorie+"/Reponses.txt"));
			String ligneQues ;

			while ((ligneQues = fichierQues.readLine()) != null ) {

				System.out.println(ligneQues);
				reponseTab.add(ligneQues);
				compteurReponse++;

			}

			fichierQues.close();
		} 
		catch (IOException e) {
			e.printStackTrace() ;
		}
	}

	public static void main(String[] args)
	{
		Listereponse Reponse = new Listereponse("Angular/CM1");
	}

	public ArrayList<String> recupererReponseTab() {
		return reponseTab;
	}

	public int recupererCompteurReponse() {
		return compteurReponse;
	}

	public void definirReponseTab(ArrayList<String> repondefinirab) {
		reponseTab = repondefinirab;
	}

	public void definirCompteurReponse(int compteurReponse) {
		this.compteurReponse = compteurReponse;
	}
}