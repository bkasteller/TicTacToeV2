package GrilleSimple;

import java.util.Scanner;

public class Main {
	public static void main(String args[]) 
	{
		final boolean DETAILLE = /* on affiche les detailles (true/false) ? */ true;
		final char PION1 = 'X',
		   	  	   PION2 = 'O';
		boolean kikiJoue = /* le joueur de type humanoïde commence (true/false) ? */ false;
		float tour = 1;
		Scanner sc = new Scanner(System.in);
		Grille grille = new Grille();
		BestBot iRobot = new BestBot(new Grille());
		grille.afficher();
		while(!grille.fin())
		{
			Coordonnee coordonnee;
			char pion;
			if(tour % 1 == 0 && DETAILLE)
			{
				System.out.println("Tour : " + (int)tour);
			}
			if(kikiJoue)
			{
				pion = PION1;
				System.out.println("Conseillé " + iRobot.reflechir(pion));
				System.out.print("A vous, entrez x y : ");
				coordonnee = new Coordonnee(sc.nextInt(), sc.nextInt());
			}
			else
			{
				pion = PION2;
				coordonnee = iRobot.reflechir(pion);
			}
			grille.jouer(coordonnee, pion);
			iRobot.add(coordonnee, pion);
			System.out.println(kikiJoue ? "Player1 a joué : " + coordonnee : "iRobot a joué : " + coordonnee);
			grille.afficher();
			kikiJoue = !kikiJoue;
			tour+=.5;
		}
		System.out.println("FIN DE PARTIE");
	}
}
