package finite_automata.Exceptions;

public class StateIsAlreadyFinalException extends Exception
{
	public StateIsAlreadyFinalException()
	{

	}

	public StateIsAlreadyFinalException(String message)
	{
		super(message);
	}

	public StateIsAlreadyFinalException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public StateIsAlreadyFinalException(Throwable cause)
	{
		super(cause);
	}
}
