package GrilleSimple;

public class Case {
	private char contenu;
	
	public Case(char pion)
	{
		contenu = pion;
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
