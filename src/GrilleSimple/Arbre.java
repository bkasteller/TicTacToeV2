package GrilleSimple;

import java.util.HashSet;
import java.util.Set;

public class Arbre {
	private int valeur;
	private Set<Arbre> enfants = new HashSet<>();
	
	Arbre(int valeur)
	{
		this.valeur = valeur;
	}
	
	public void add(Arbre enfant)
	{
		enfants.add(enfant);
	}
	
	public Arbre compteARebours(long timer, int nbChoix)
	{
		if (nbChoix < 0)
			return null;
		Arbre arbre = new Arbre(nbChoix);
		for (int i = 1; i <= nbChoix-1; i++)
			arbre.add(compteARebours(timer, nbChoix - 1));
		return arbre;
	}
	
	@Override
	public String toString()
	{
		String res = "(";
		res += this.valeur;
		for(Arbre enfant : enfants)
				res += ", " + enfant.toString();
		res += ")";
		return res;
	}
	
	public static void main(String[] args)
	{
		Arbre arbre = new Arbre (1), arbre2 = new Arbre(2);
		arbre.add(arbre2);
		arbre.add(new Arbre(3));
		arbre.add(new Arbre(4));
		arbre.add(new Arbre(5));
		System.out.println(arbre);
	}
}
