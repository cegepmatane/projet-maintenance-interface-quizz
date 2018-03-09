package version3;

import java.util.ArrayList;

/** Modele du Quizz */
public class Quizz {

	private int questionIdx,reponseIdx;
	private int choice ;
	private int[] aleaIdx ;
	private int moyenneInt, scoreInt = 0 ;
	public int cmp  ;

	private ArrayList<String> questionTab = new ArrayList <String>();
	private ArrayList<String> repondefinirab = new ArrayList <String>();

	
	protected String essaiStr = "";

	protected int essaiInt = 0;

	protected String reponseStr = "";

	private int essaiTtl;
	
	private String[] categorieListe = new String[]{"Angular"};
	 
	private String categoriePhrase ;
	
	private String[] sousCategorieListe = new String[]{"CM1"};
	
	private String sousCategoriePhrase ;
	
	private Controleur controleur ;

	public Quizz() {
		
	}

	public int recupererQuestionIdx() {
		return questionIdx;
	}

	public void definirQuestionIdx(int questionIdx) {
		this.questionIdx = questionIdx;
	}

	public int recupererReponseIdx() {
		return reponseIdx;
	}

	public void definirReponseIdx(int reponseIdx) {
		this.reponseIdx = reponseIdx;
	}

	public int recupererChoice() {
		return choice;
	}

	public void definirChoice(int choice) {
		this.choice = choice;
	}

	public int[] recupererAleaIdx() {
		return aleaIdx;
	}

	public void definirAleaIdx(int[] aleaIdx) {
		this.aleaIdx = aleaIdx;
	}

	public int recupererMoyenneInt() {
		return moyenneInt;
	}

	public void definirMoyenneInt(int moyenneInt) {
		this.moyenneInt = moyenneInt;
	}

	public int recupererScoreInt() {
		return scoreInt;
	}

	public void definirScoreInt(int scoreInt) {
		this.scoreInt = scoreInt;
	}

	public int recupererCmp() {
		return cmp;
	}

	public void definirCmp(int cmp) {
		this.cmp = cmp;
	}

	public ArrayList<String> recupererQuestionTab() {
		return questionTab;
	}

	public void definirQuestionTab(ArrayList<String> questionTab) {
		this.questionTab = questionTab;
	}

	public ArrayList<String> recupererReponseTab() {
		return repondefinirab;
	}

	public void definirReponseTab(ArrayList<String> repondefinirab) {
		this.repondefinirab = repondefinirab;
	}

	public String recupererEssaiStr() {
		return essaiStr;
	}

	public void definirEssaiStr(String essaiStr) {
		this.essaiStr = essaiStr;
	}

	public int recupererEssaiInt() {
		return essaiInt;
	}

	public void definirEssaiInt(int essaiInt) {
		this.essaiInt = essaiInt;
	}

	public String recupererReponseStr() {
		return reponseStr;
	}

	public void definirReponseStr(String reponseStr) {
		this.reponseStr = reponseStr;
	}

	public int recupererEssaiTtl() {
		return essaiTtl;
	}

	public void definirEssaiTtl(int essaiTtl) {
		this.essaiTtl = essaiTtl;
	}

	public String[] recupererCategorieListe() {
		
		
		return categorieListe;
	}

	public void definirCategorieListe(String[] categorieListe) {
		this.categorieListe = categorieListe;
	}

	public String recupererCategoriePhrase() {
		return categoriePhrase;
	}

	public void definirCategoriePhrase(String categoriePhrase) {
		this.categoriePhrase = categoriePhrase;
	}

	public Controleur recupererControleur() {
		return controleur;
	}

	public void definirControleur(Controleur controleur) {
		this.controleur = controleur;
	}

	public String[] recupererSousCategorieListe() {
		return sousCategorieListe;
	}

	public void definirSousCategorieListe(String[] sousCategorieListe) {
		this.sousCategorieListe = sousCategorieListe;
	}

	public String recupererSousCategoriePhrase() {
		return sousCategoriePhrase;
	}

	public void definirSousCategoriePhrase(String sousCategoriePhrase) {
		this.sousCategoriePhrase = sousCategoriePhrase;
	}

	public void initialiserQuestionEtReponse() {
		
		Listequestion listeDeQuestion = new Listequestion(recupererCategoriePhrase()+"/"+recupererSousCategoriePhrase());
		Listereponse listeDeReponse = new Listereponse(recupererCategoriePhrase()+"/"+recupererSousCategoriePhrase());

		definirQuestionTab(listeDeQuestion.recupererQuestionTab());
		definirReponseTab(listeDeReponse.recupererReponseTab());
		
	}

}
