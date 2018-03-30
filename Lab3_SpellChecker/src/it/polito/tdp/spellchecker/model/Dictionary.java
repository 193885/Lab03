package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Dictionary {
	
	private List<String> dizIng = new LinkedList<>();
	private List<String> dizIta = new LinkedList<>();
	private List <String> lingueDisponibili = new LinkedList<>();
	private List <RichWord> paroleControllate = new LinkedList<>();
		
	public void loadDictionary(String language){
		
		if(language.compareTo("inglese")==0){
			
			lingueDisponibili.add(language);
			
			try{
			
			BufferedReader br = new BufferedReader(new FileReader("rsc/English.txt"));
			
				String riga;
				while( (riga = br.readLine())!=null){
						
					StringTokenizer st = new StringTokenizer(riga,"\n");
					
					String parola=st.nextToken();
										
						dizIng.add(parola);		
				}
				
				br.close();
			}
				
			catch(IOException ioe){
				System.out.println("Erorre nella lettura del file");
			}	
		}
		
		else if(language.compareTo("italiano")==0){
			
			lingueDisponibili.add(language);
			
			try{
			
			BufferedReader br = new BufferedReader(new FileReader("rsc/Italian.txt"));
			
				String riga;
				while( (riga = br.readLine())!=null){
						
					StringTokenizer st = new StringTokenizer(riga,"\n");
					
					String parola=st.nextToken();
										
						dizIta.add(parola);		
				}
				
				br.close();
			}
				
			catch(IOException ioe){
				System.out.println("Erorre nella lettura del file");
			}				
		}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
						
		for(String lingua : lingueDisponibili) {
			
			if(lingua.compareTo("inglese")==0) {	
						    						
					for(String parolaDaAnalizzare : inputTextList) {
		
						if(dizIng.contains(parolaDaAnalizzare))
								
							paroleControllate.add(new RichWord(parolaDaAnalizzare,true ));
						
						else
								
							paroleControllate.add(new RichWord(parolaDaAnalizzare,false ));
					}
				
				return paroleControllate;	
			}
			
			else if(lingua.compareTo("italiano")==0) {
							
					for(String parola : inputTextList) {
						
						if(dizIta.contains(parola))
							
							paroleControllate.add(new RichWord(parola, true));
						
						else
							
							paroleControllate.add(new RichWord(parola, false));						
					}				
							
				return paroleControllate;		
			}
			
		}
				
		return null;		
	}	
}