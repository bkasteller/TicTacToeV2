package GrilleSimple;

public class Case {
	private Coordonnee coordonnee;
	private char contenu;
	
	public Case(Coordonnee coordonnee, char pion)
	{
		this.coordonnee = coordonnee;
		contenu = pion;
	}
	
	public Coordonnee getCoordonnee()
	{
		return coordonnee;
	}
	
	public char getContenu()
	{
		return contenu;
	}
	
	public void setContenu(char contenu)
	{
		this.contenu = contenu;
	}
}
