package version1;
import java.io.*; 
import java.util.*; 
import java.util.regex.Pattern; 
//<<<<<<<<<<<<<<<<<<<<<<<< 
class Lecture{ 

	public Lecture(String source, String categorie)
	{
		String filePath = source; 
		String garder = "";
		try{ 
			BufferedReader buff = new BufferedReader(new FileReader(filePath)); 
			try { 
				String line; 
				// Lire le fichier ligne par ligne 
				// La boucle se termine quand la méthode affiche "null" 
				while ((line = buff.readLine()) != null) 
				{ 
					System.out.println(line); 
					// ajouter ces 3 lignes <<<<<<<<<<<<<<<<<<<<<<<<<<<<< 
					int index = line.indexOf(categorie); 
					// partie commune des lignes à garder
					if(index > 0) 
						// si on trouve "put rate" 
						garder+= line + "\n"; 
				} 
				// Ajouter les lignes qui suivent ... <<<<<<<<<<<<<<<<<<<<<<<<< //
				System.out.println("\n\ngarder = " + "\"" + garder + "\""); 
				Pattern pattern = Pattern.compile("\n"); 
				// import java.util.regex.Pattern; 
				String[] tabGarder = pattern.split(garder); 
				// pour mettre les lignes conservées 
				// dans le tableau tabGarder 
				System.out.println(""); 
				// Résultat: 
				for(int i = 0; i < tabGarder.length; i++) 
				{ 
					String lGardee = tabGarder[i]; 
					System.out.println("lGardee: " + "\"" + lGardee.substring(0, lGardee.length()-7) + "\""); 
				} 
				// ... Jusqu'ici <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< 
				buff.close(); 
				//Lecture fini donc on ferme le flux 
			} catch (IOException e){ 
				System.out.println(e.getMessage());
				System.exit(1); 
			} 
		} catch (IOException e) { 
			System.out.println(e.getMessage());
			System.exit(1); 
		}
	} 
	public static void main(String[] args)
	{
		Lecture lec1 = new Lecture("Quizz/Fichiers/Angular/CM1/Reponses.txt", "ANGULAR");
		System.out.println("\n\n\n\n");
	}

}