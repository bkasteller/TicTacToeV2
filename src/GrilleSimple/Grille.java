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
				grille.put(new Coordonnee(x, y), new Case(CASE_VIDE));
			}
		}
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
	
	public void jouer(Coordonnee coordonnee, char pion)
	{
		getCase(coordonnee).setContenu(pion);
	}
	
	public boolean fin()
	{
		return verifLigne(getTaille()-1)
			|| verifCol(getTaille()-1)
			|| verifDiago1()
			|| verifDiago2()
			|| full();
	}
	
	private boolean verifLigne(int y)
	{
		if(y < 0)
		{
			return false;
		}
		int compte1 = 0, 
			compte2 = 0;
		char pion = getCaseVide();
		for(int x = 0; x < getTaille(); x++)
		{
			char contenu = getCase(new Coordonnee(x, y)).getContenu();
			if(contenu != getCaseVide())
			{
				if(pion == getCaseVide())
				{
					pion = contenu;
				}
				if(pion == contenu && contenu != getCaseVide())
				{
					compte1++;
				}
				else
				{
					compte2++;
				}
			}
		}
		if(compte1 == getTaille() || compte2 == getTaille())
		{
			return true;
		}
		return verifLigne(y-1);
	}
	
	private boolean verifCol(int x)
	{
		if(x < 0)
		{
			return false;
		}
		int compte1 = 0, 
			compte2 = 0;
		char pion = getCaseVide();
		for(int y = 0; y < getTaille(); y++)
		{
			char contenu = getCase(new Coordonnee(x, y)).getContenu();
			if(contenu != getCaseVide())
			{
				if(pion == getCaseVide())
				{
					pion = contenu;
				}
				if(pion == contenu && contenu != getCaseVide())
				{
					compte1++;
				}
				else
				{
					compte2++;
				}
			}
		}
		if(compte1 == getTaille() || compte2 == getTaille())
		{
			return true;
		}
		return verifCol(x-1);
	}
	
	private boolean verifDiago1()
	{
		int compte1 = 0, 
			compte2 = 0;
		char pion = getCaseVide();
		for(int i = 0; i < getTaille(); i++)
		{
			char contenu = getCase(new Coordonnee(i, i)).getContenu();
			if(contenu != getCaseVide())
			{
				if(pion == getCaseVide())
				{
					pion = contenu;
				}
				if(pion == contenu && contenu != getCaseVide())
				{
					compte1++;
				}
				else
				{
					compte2++;
				}
			}
		}
		if(compte1 == getTaille() || compte2 == getTaille())
		{
			return true;
		}
		return false;
	}
	
	private boolean verifDiago2()
	{
		int compte1 = 0, 
			compte2 = 0;
		char pion = getCaseVide();
		for(int i = 0; i < getTaille(); i++)
		{
			char contenu = getCase(new Coordonnee(i, getTaille()-i-1)).getContenu();
			if(contenu != getCaseVide())
			{
				if(pion == getCaseVide())
				{
					pion = contenu;
				}
				if(pion == contenu && contenu != getCaseVide())
				{
					compte1++;
				}
				else
				{
					compte2++;
				}
			}
		}
		if(compte1 == getTaille() || compte2 == getTaille())
		{
			return true;
		}
		return false;
	}
	
	private boolean full()
	{
		for (int y = 0; y < getTaille(); y++)
		{
			for (int x = 0; x < getTaille(); x++)
			{
				if(getCase(new Coordonnee(x, y)).getContenu() != getCaseVide())
				{
					return true;
				}
			}
		}
		return true;
	}
}
