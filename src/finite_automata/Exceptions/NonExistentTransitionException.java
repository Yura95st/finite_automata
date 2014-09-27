package finite_automata.Exceptions;

public class NonExistentTransitionException extends Exception
{
	public NonExistentTransitionException()
	{

	}

	public NonExistentTransitionException(String message)
	{
		super(message);
	}

	public NonExistentTransitionException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public NonExistentTransitionException(Throwable cause)
	{
		super(cause);
	}
}
