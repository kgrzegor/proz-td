package Exceptions;

@SuppressWarnings("serial")
public class GoldException extends Exception
{
	public GoldException(int gold)
	{
		super("Not enough gold!\nYou need " + gold + "g more.");
	}
}