package finite_automata.Exceptions;

public class FailedToGetFiniteAutomataFromStringListException extends Exception
{
	public FailedToGetFiniteAutomataFromStringListException()
	{

	}

	public FailedToGetFiniteAutomataFromStringListException(String message)
	{
		super(message);
	}

	public FailedToGetFiniteAutomataFromStringListException(String message,
			Throwable cause)
	{
		super(message, cause);
	}

	public FailedToGetFiniteAutomataFromStringListException(Throwable cause)
	{
		super(cause);
	}
}
