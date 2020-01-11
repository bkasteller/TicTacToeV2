package GrilleSimple;

import java.util.HashMap;

public class Grille {
	private final int TAILLE = 3;
	private final char CASE_VIDE = '.';
	private HashMap<Coordonnee, Case> grille = new HashMap<Coordonnee, Case>();
	
	public Grille()
	{
		remplir();
	}
	
	public int getTaille()
	{
		return TAILLE;
	}
	
	public char getCaseVide()
	{
		return CASE_VIDE;
	}
	
	public Case getCase(Coordonnee coordonnee)
	{
		return grille.get(coordonnee);
	}
	
	private void remplir()
	{
		for(int y = 0; y < getTaille(); y++)
		{
			for(int x = 0; x < getTaille(); x++)
			{
				Coordonnee coordonnee = new Coordonnee(x, y);
				grille.put(coordonnee, new Case(coordonnee, CASE_VIDE));
			}
		}
	}
	
	public Object clone() throws CloneNotSupportedException 
	{ 
		return super.clone();
	} 
	
	public void afficher()
	{
		System.out.print("  ");
		for (int i = 0; i < getTaille(); i++)
		{
			System.out.print(i + " ");
		}
		System.out.println();
		for (int y = 0; y < getTaille(); y++)
		{
			for (int x = 0; x < getTaille(); x++)
			{
				if(x == 0)
				{
					System.out.print(y + " ");
				}
				System.out.print(getCase(new Coordonnee(x, y)).getContenu() + " ");
			}
			System.out.println();
		}
	}
	
	public Grille jouer(Coordonnee coordonnee, char pion)
	{
		getCase(coordonnee).setContenu(pion);
		return this;
	}
	
	public boolean fin()
	{
		return verifLigne(getTaille()-1)
			|| verifCol(getTaille()-1)
			|| verifDiago1()
			|| verifDiago2();
	}
	
	private boolean verifLigne(int y)
	{
		if(y < 0)
		{
			return false;
		}
		int compte = 1;
		char pion = getCase(new Coordonnee(0, y)).getContenu();
		for(int x = 1; x < getTaille(); x++)
		{
			char contenu = getCase(new Coordonnee(x, y)).getContenu();
			if (pion == contenu && contenu != getCaseVide())
			{
				compte++;
			}
			pion = getCase(new Coordonnee(x, y)).getContenu();
		}
		if(compte == getTaille())
			return true;
		return verifLigne(y-1);
	}
	
	private boolean verifCol(int x)
	{
		if(x < 0)
		{
			return false;
		}
		int compte = 1;
		char pion = getCase(new Coordonnee(x, 0)).getContenu();
		for(int y = 1; y < getTaille(); y++)
		{
			char contenu = getCase(new Coordonnee(x, y)).getContenu();
			if (pion == contenu && contenu != getCaseVide())
			{
				compte++;
			}
			pion = getCase(new Coordonnee(x, y)).getContenu();
		}
		if(compte == getTaille())
			return true;
		return verifCol(x-1);
	}
	
	private boolean verifDiago1()
	{
		int compte = 1;
		char pion = getCase(new Coordonnee(0, 0)).getContenu();
		for(int i = 1; i < getTaille(); i++)
		{
			char contenu = getCase(new Coordonnee(i, i)).getContenu();
			if (pion == contenu && contenu != getCaseVide())
			{
				compte++;
			}
			pion = getCase(new Coordonnee(i, i)).getContenu();
		}
		if(compte == getTaille())
			return true;
		return false;
	}
	
	private boolean verifDiago2()
	{
		int compte = 1;
		char pion = getCase(new Coordonnee(0, getTaille()-1)).getContenu();
		for(int i = 1; i < getTaille(); i++)
		{
			char contenu = getCase(new Coordonnee(i,getTaille()-i-1)).getContenu();
			if (pion == contenu && contenu != getCaseVide())
			{
				compte++;
			}
			pion = getCase(new Coordonnee(i,getTaille()-i-1)).getContenu();
		}
		if(compte == getTaille())
			return true;
		return false;
	}
}
