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
	      System.out.println("\nDato:\n");
	      String dato = scanner.nextLine();
	      System.out.println("\nTidspunkt:\n");
	      String tid = scanner.nextLine();
				System.out.println("\nVarighet:\n");
				String varighet = scanner.nextLine();
				System.out.println("\nNotat:");
				String notat = scanner.nextLine();
				System.out.println("\nAntall øvelser:\n");
			  String antall = scanner.nextLine();
				
			  for (int i = 0; i < Integer.parseInt(antall); i++) {
			    System.out.println("\nØvelse:\n");
			    String ovelse = scanner.nextLine();
			    System.out.println(ovelse + "\n");
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