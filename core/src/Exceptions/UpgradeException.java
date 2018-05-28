package Exceptions;

@SuppressWarnings("serial")
public class UpgradeException extends Exception
{
	public UpgradeException(String text)
	{
		super("Tower " + text + " at max level!");
	}
}
