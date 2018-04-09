package it.polito.tdp.spellchecker.model;

public class RichWord {
	
	private String parola;
	private boolean corretta;
	

	public RichWord(String parolaDaAnalizzare) {

		parola=parolaDaAnalizzare;
			
	}

	
	public RichWord(String parolaDaAnalizzare, boolean b) {

		parola=parolaDaAnalizzare;
		corretta=b;
		
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public boolean isCorretta() {
		return corretta;
	}

	public void setCorretta(boolean corretta) {
		this.corretta = corretta;
	}
	
	

}
