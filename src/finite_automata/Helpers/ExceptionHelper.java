package finite_automata.Helpers;

public class ExceptionHelper
{
	/**
	 * Gets full exception message recursively
	 * @param exception
	 * @return
	 */
	public static String getFullExceptionMessage(Exception exception)
	{
		StringBuilder stringBuilder = new StringBuilder();
		
		while (exception != null)
		{
			stringBuilder.append(exception.getMessage());
			stringBuilder.append(System.getProperty("line.separator"));
			
			exception = (Exception) exception.getCause();
		}
		
		String message = stringBuilder.toString().trim();
		
		return message;
	}
}
