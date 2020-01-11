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
		System.out.println(kikiJoue ? "Vous êtes le 1er à jouer" : "Vous jouez en 2nd");
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
				System.out.print("A vous, entrez x y : ");
				coordonnee = new Coordonnee(sc.nextInt(), sc.nextInt());
			}
			else
			{
				pion = PION2;
				coordonnee = iRobot.reflechir(pion);
			}
			grille.jouer(coordonnee, pion);
			System.out.println(kikiJoue ? "Player1 a joué : " + coordonnee : "iRobot a joué : " + coordonnee + (DETAILLE ? " | ExecutionTime : " + iRobot.getTemps() + "ms" : ""));
			iRobot.add(coordonnee, pion);
			grille.afficher();
			iRobot.reflechir(pion);
			kikiJoue = !kikiJoue;
			tour-=-.5; // pour le style, car on aime être original.
		}
		grille.afficher();
		System.out.println("+-----------------------+");
		System.out.println(!kikiJoue ? "| VICTOIRE DU JOUEUR 1 |" : "| VICTOIRE DU JOUEUR 2 |");
		System.out.println("+-----------------------+");
	}
}
