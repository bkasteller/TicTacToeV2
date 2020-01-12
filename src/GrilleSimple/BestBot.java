package GrilleSimple;

public class BestBot {
	private Arbre arbre;
	private Grille grille;
	private char pion;
	private long temps;
	
	public BestBot(Grille grille)
	{
		this.grille = grille;
	}
	
	public void add(Coordonnee coordonnee, char pion)
	{
		grille.jouer(coordonnee, pion); // on met à jour la grille avec le coup joué.
	}
	
	private void setPion(char pion)
	{
		this.pion = pion;
	}
	
	private char getPion()
	{
		return pion;
	}
	
	private boolean adversePion(char contenu)
	{
		return contenu != grille.getCaseVide()
			&& contenu != getPion();
	}
	
	public Coordonnee reflechir(char pion)
	{
		final int PROFONDEUR = 1;
		int max_val = -1000;
		Coordonnee coordonnee = null;
		setPion(pion);
		for(int y = 0; y < grille.getTaille(); y++)
		{
			for(int x = 0; x < grille.getTaille(); x++)
			{
				if(grille.getCase(new Coordonnee(x, y)).getContenu() == grille.getCaseVide())
				{
					grille.jouer(new Coordonnee(x, y), getPion());
					int val = min(PROFONDEUR);
					System.out.println("(" + x + ";" + y + ") score = " + val);
					//grille.afficher();
					if(val >= max_val)
					{
						max_val = val;
						coordonnee = new Coordonnee(x, y);
					}
					annuler(new Coordonnee(x, y));
				}
			}
		}
		return coordonnee;
	}
	
	private int min(int profondeur)
	{
		//System.out.print("Min " + profondeur + " | ");
		if(profondeur == 0 || grille.fin())
		{
			return eval();
		}
		int min_val = 1000;
		for(int y = 0; y < grille.getTaille(); y++)
		{
			for(int x = 0; x < grille.getTaille(); x++)
			{
				if(grille.getCase(new Coordonnee(x, y)).getContenu() == grille.getCaseVide())
				{
					grille.jouer(new Coordonnee(x, y), '?');
					int val = max(profondeur-1);
					//System.out.println(" = " + val);
					//grille.afficher();
					if(val < min_val)
					{
						min_val = val;
					}
					annuler(new Coordonnee(x, y));
				}
			}
		}
		return min_val;
	}
	
	private int max(int profondeur)
	{
		//System.out.print("Max " + profondeur + " | ");
		if(profondeur == 0 || grille.fin())
		{
			return eval();
		}
		int max_val = -1000;
		for(int y = 0; y < grille.getTaille(); y++)
		{
			for(int x = 0; x < grille.getTaille(); x++)
			{
				if(grille.getCase(new Coordonnee(x, y)).getContenu() == grille.getCaseVide())
				{
					grille.jouer(new Coordonnee(x, y), getPion());
					int val = min(profondeur-1);
					//System.out.println(x + " " + y + " : " + val);
					//grille.afficher();
					if(val > max_val)
					{
						max_val = val;
					}
					annuler(new Coordonnee(x, y));
				}
			}
		}
		return max_val;
	}
	
	private int eval()
	{
		return verifLigne(grille.getTaille()-1)
			 + verifCol(grille.getTaille()-1)
			 + verifDiago1()
			 + verifDiago2();
	}
	
	private int verifLigne(int y)
	{
		if(y < 0)
		{
			return 0;
		}
		int j1 = 0, j2 = 0;
		for(int x = 0; x < grille.getTaille(); x++)
		{
			char contenu = grille.getCase(new Coordonnee(x, y)).getContenu();
			if(contenu == getPion())
			{
				j1++;
			}
			else if(contenu != grille.getCaseVide())
			{
				j2++;
			}
		}
		//System.out.print("L" + y + " : " + calcScore(j1, j2) + "(" + j1 + ";" + j2 + ") ");
		return verifLigne(y-1) + calcScore(j1, j2);
	}
	
	private int verifCol(int x)
	{
		if(x < 0)
		{
			return 0;
		}
		int j1 = 0, j2 = 0;
		for(int y = 0; y < grille.getTaille(); y++)
		{
			char contenu = grille.getCase(new Coordonnee(x, y)).getContenu();
			if(contenu == getPion())
			{
				j1++;
			}
			else if(contenu != grille.getCaseVide())
			{
				j2++;
			}
		}
		//System.out.print("Col" + x + " : " + calcScore(j1, j2) + "(" + j1 + ";" + j2 + ") ");
		return verifCol(x-1) + calcScore(j1, j2);
	}
	
	private int verifDiago1()
	{
		int j1 = 0, j2 = 0;
		for(int i = 0; i < grille.getTaille(); i++)
		{
			char contenu = grille.getCase(new Coordonnee(i, i)).getContenu();
			if(contenu == getPion())
			{
				j1++;
			}
			else if(contenu != grille.getCaseVide())
			{
				j2++;
			}
		}
		//System.out.print("Diago1 : " + calcScore(j1, j2) + "(" + j1 + ";" + j2 + ") ");
		return calcScore(j1, j2);
	}
	
	private int verifDiago2()
	{
		int j1 = 0, j2 = 0;
		for(int i = 0; i < grille.getTaille(); i++)
		{
			char contenu = grille.getCase(new Coordonnee(i, grille.getTaille() - i - 1)).getContenu();
			if(contenu == getPion())
			{
				j1++;
			}
			else if(contenu != grille.getCaseVide())
			{
				j2++;
			}
		}
		//System.out.print("Diago2 : " + calcScore(j1, j2) + "(" + j1 + ";" + j2 + ") ");
		return calcScore(j1, j2);
	}
	
	private int calcScore(int j1, int j2)
	{
		if(j1 == grille.getTaille())
			return 1000;
		if(j2 == grille.getTaille())
			return -1000;
		if(j1 == 0 || j2 == 0)
			return j1 - j2;
		return 0;
	}
	
	private void annuler(Coordonnee coordonnee)
	{
		grille.getCase(coordonnee).setContenu(grille.getCaseVide());
	}
	
	public long getTemps()
	{
		return temps;
	}
}
