package application;

import java.util.Scanner;

class Session {
  public static void main(String[] args) {
		
	  DB db = new DB();
	  db.getKategori();
		
	  Scanner scanner = new Scanner(System.in);
	  System.out.println("Velokmmen til din dagbok! \nKommandoer:");
		
	  while (scanner.hasNextLine()) {
	    String line = scanner.nextLine();
		    
	    if(line.equals("ny")) {
	      System.out.println("\nDato:");
	      String dato = scanner.nextLine();
	      System.out.println("\nTidspunkt:");
	      String tid = scanner.nextLine();
	      System.out.println("\nVarighet:");
	      String varighet = scanner.nextLine();
	      System.out.println("\nNotat:");
	      String notat = scanner.nextLine();
	      System.out.println("\nAntall øvelser:");
	      String antall = scanner.nextLine();
				
	      for (int i = 0; i < Integer.parseInt(antall); i++) {
	    	  System.out.println("\nØvelse:" + (i+1)+":");
	    	  String ovelse = scanner.nextLine();
	    	  System.out.println(ovelse + "\n");
	    	  if (db.checkOvelse(ovelse)) {
	    	    System.out.println("\nForm (ranger med 1-10):");
	          String form = scanner.nextLine();
	          System.out.println("\nPrestasjon (ranger med 1-10):");
	          String prestasjon = scanner.nextLine();
	          System.out.println("\nAntall sett:");
            String sett = scanner.nextLine();
            System.out.println("\nAntall repetisjoner:");
            String reps = scanner.nextLine();
            System.out.println("\nBelastning i kilo:");
            String belastning = scanner.nextLine();
     //       db.setOktOvelse(form, prestasjon, sett, reps, belastning, OvelseID)
            
            
	    	  }
	    	  else {
	    	    System.out.println("Fant ikke øvelsen, vennligst opprett øvelsen:");
	    	    System.out.println("\nNavn på øvelse:");
            String form = scanner.nextLine();
            System.out.println("\nBeskriv øvelsen:");
            String beskrivelse = scanner.nextLine();
            System.out.println("\nPrestasjon (ranger med 1-10):");
            String prestasjon = scanner.nextLine();
            System.out.println("\nAntall sett:");
            String sett = scanner.nextLine();
            System.out.println("\nAntall repetisjoner:");
            String reps = scanner.nextLine();
            System.out.println("\nBelastning i kilo:");
            String belastning = scanner.nextLine();
   //         db.setOktOvelse(form, prestasjon, sett, reps, belastning, OvelseID)
            
	    	  }
	    	  
	    	  
			  }
	    	}
	    else if(line.equals("best")) {
	      System.out.println("Beste trening:\n");
	    }
	    else if(line.equals("stats")) {
	      System.out.println("Beste trening:\n");
	    }
	    else if(line.equals("slutt")) {
	      String file = scanner.nextLine();
	      System.out.println(file);
	    }
	    else {
	      System.out.println("Ugyldig kommando!\n");
	    }
	  }
	}
}