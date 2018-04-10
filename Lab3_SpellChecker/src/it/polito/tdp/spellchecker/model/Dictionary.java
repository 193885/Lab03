package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Dictionary {
	
	
	private String lingua;
	private List<String> diz;
			
	public Dictionary() {
	}
	
	public boolean loadDictionary(String language){
		
		if ( diz!= null && lingua.compareTo(language)==0) //dizionario già caricato
			
			return true;
		
		diz= new LinkedList<>();
		lingua=language;
					
				try{
				
				BufferedReader br = new BufferedReader(new FileReader("rsc/" + language + ".txt"));
				
					String riga;
					while( (riga = br.readLine())!=null){
							
						StringTokenizer st = new StringTokenizer(riga,"\n");
						
						String parola=st.nextToken();
											
						diz.add(parola);		
					}
				
				br.close();
				return true;
			}
				
			catch(IOException ioe){
				System.out.println("Erorre nella lettura del file");
				return false;
			}	
	}

	public List<RichWord> spellCheckText(List<String> inputTextList){
		
		List <RichWord> paroleControllate = new LinkedList<>();
												
			for(String parolaDaAnalizzare : inputTextList) {
				
				RichWord richWord = new RichWord(parolaDaAnalizzare);
		
						if(diz.contains(parolaDaAnalizzare)) 
							
							richWord.setCorretta(true);
							
						else {
							richWord.setCorretta(false);
								
							paroleControllate.add(new RichWord(parolaDaAnalizzare));
						}
			}
									
				return paroleControllate;	
	}
	
	public List <RichWord> spellCheckTextLinear(List<String> inputTextList){
		
		List <RichWord> paroleControllate = new LinkedList<>();
		
		boolean trovato=false;		
		
		for(String parolaDaAnalizzare : inputTextList) {
			
			RichWord richWord = new RichWord(parolaDaAnalizzare);
			
			for (String s : diz) {
				
				if ( s.compareTo(parolaDaAnalizzare)==0) {
					
					trovato = true;
					break;
					
				}
			}
				if(trovato) 
				
					richWord.setCorretta(true);
								
				else

					paroleControllate.add(new RichWord(parolaDaAnalizzare));

			}
			
		return paroleControllate;
		
	}
	
	public List <RichWord> spellCheckTextDichotomic(List<String> inputTextList){
		
		List <RichWord> paroleControllate = new LinkedList<>();
		
		for (String parolaDaAnalizzare : inputTextList) {
			
			RichWord richWord = new RichWord(parolaDaAnalizzare);
			
			if(ricercaDicotomica(parolaDaAnalizzare))
				
				richWord.setCorretta(true);
			
			else {
				
				richWord.setCorretta(false);
				paroleControllate.add(richWord);
				
			}
			
		}
		
		return paroleControllate;
	}
		
	private boolean ricercaDicotomica(String parola) {
		
		int inizio=0;
		int fine = diz.size();
		
		while (inizio!=fine) {
			
			int medio= (fine+inizio)/2;
			
			if(parola.compareTo(diz.get(medio))==0) 
				
				return true;
				
			else if ( parola.compareTo( diz.get(medio) ) > 0)
			
				inizio= medio+1;
			
			else
				
				fine= medio-1;
		}
		
		return false;			
	}
			
}