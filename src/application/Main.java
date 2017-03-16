package application;

import java.util.Scanner;

class Session {
  public static void main(String[] args) {
		
	  DB db = new DB();
	  db.getKategori();
		
	  Scanner scanner = new Scanner(System.in);
	  System.out.println("Velkommen til din dagbok! \nKommandoer:\n'ny': Lag ny økt i dagboken\n"
	      + "'best': Se den meste du har løftet i en øvelse\n'stats': Se gjennomsnittsbelastningen din\n"
	      + "'slutt': Avslutter dagboken\n 'forrige': se en oversikt over forrige økt ");
		
	  while (scanner.hasNextLine()) {
	    String line = scanner.nextLine();
		    
	    if(line.equals("ny")) {
	      System.out.println("\nDato:");
	      String dato = scanner.nextLine();
	      System.out.println("Tidspunkt:");
	      String tid = scanner.nextLine();
	      System.out.println("Varighet:");
	      String varighet = scanner.nextLine();
	      System.out.println("Notat:");
	      String notat = scanner.nextLine();
	      int OktID = db.setOkt(dato, tid, varighet, notat);       

	      
	      System.out.println("\nAntall øvelser:");
	      String antall = scanner.nextLine();
				
	      for (int i = 0; i < Integer.parseInt(antall); i++) {
	    	  System.out.println("Øvelse " + (i+1)+":");
	    	  String ovelse = scanner.nextLine();
	    	  if (db.checkOvelse(ovelse)) {
	    	    System.out.println("Form (ranger med 1-10):");
	          int form = Integer.parseInt(scanner.nextLine());
	          System.out.println("Prestasjon (ranger med 1-10):");
	          int prestasjon = Integer.parseInt(scanner.nextLine());
	          System.out.println("Antall sett:");
            int sett = Integer.parseInt(scanner.nextLine());
            System.out.println("Antall repetisjoner:");
            int reps = Integer.parseInt(scanner.nextLine());
            System.out.println("Belastning i kilo:");
            int belastning = Integer.parseInt(scanner.nextLine());
            int OvelseID = db.getOvelseID(ovelse);
            db.setOktOvelse(OvelseID, OktID, form, prestasjon, sett, reps, belastning);
         	  }
	    	  
	    	  else {
	    	    System.out.println("\nFant ikke øvelsen, vennligst opprett øvelsen:");
            System.out.println("Beskriv øvelsen:");
            String beskrivelse = scanner.nextLine();
            db.setOvelse(ovelse, beskrivelse);
            System.out.println("Form (ranger med 1-10):");
            int form = Integer.parseInt(scanner.nextLine());
            System.out.println("Prestasjon (ranger med 1-10):");
            int prestasjon = Integer.parseInt(scanner.nextLine());
            System.out.println("Antall sett:");
            int sett = Integer.parseInt(scanner.nextLine());
            System.out.println("Antall repetisjoner:");
            int reps = Integer.parseInt(scanner.nextLine());
            System.out.println("Belastning i kilo:");
            int belastning = Integer.parseInt(scanner.nextLine());
            int OvelseID = db.getOvelseID(ovelse);
            db.setOktOvelse(OvelseID, OktID, form, prestasjon, sett, reps, belastning);
            
	    	  }
	    	  
	    	  
			  }
	      System.out.println("Gratulerer! Du fikk lagt inn økten din!");
	    	}
	    else if(line.equals("best")) {
	      System.out.println("Skriv øvelsen du vil se max på:");
	      String ovelse = scanner.nextLine();
        if (db.checkOvelse(ovelse)){
          double belastning = db.getBest(ovelse);
          System.out.println(ovelse + ": " + belastning + "kg");
        }                 
        else {
          System.out.println("Øvelsen finnes ikke!");
          
        }
	    }
	    
	    else if(line.equals("stats")) {
	      System.out.println("Gjennomsnittlig belastning på alle øvelser:\n");
	      double gjennomsnitt = db.getStats();
	      System.out.println(gjennomsnitt+"kg");
	    }
	    else if(line.equals("slutt")) {
	      System.exit(0);
	    }
	    
	    else if(line.equals("forrige")){
	      System.out.println("Oversikt over forrige økt:");
	      System.out.println(db.getLast());
	    }
	    
	    else {
	      System.out.println("Ugyldig kommando!\n");
	    }
	  }
	}
}