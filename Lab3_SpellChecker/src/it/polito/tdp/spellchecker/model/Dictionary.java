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
	private List <RichWord> paroleControllate = new LinkedList<>();
		
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
}