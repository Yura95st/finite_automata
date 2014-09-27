package finite_automata.Exceptions;

public class TransitionAlreadyExistsException extends Exception
{
	public TransitionAlreadyExistsException()
	{

	}

	public TransitionAlreadyExistsException(String message)
	{
		super(message);
	}

	public TransitionAlreadyExistsException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public TransitionAlreadyExistsException(Throwable cause)
	{
		super(cause);
	}
}
