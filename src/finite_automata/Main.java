package finite_automata;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import finite_automata.Helpers.ExceptionHelper;
import finite_automata.Helpers.FiniteAutomataHelper;

public class Main
{
	public static void main(String[] args)
	{
		try
		{
			Path path = Paths.get(args[0]);

			List<String> lines = Files.readAllLines(path,
					StandardCharsets.UTF_8);

			IFiniteAutomata finiteAutomata = FiniteAutomataHelper
					.getFiniteAutomataFromStringList(lines);

			for (String string : FiniteAutomataHelper
					.convertFiniteAutomataToList(finiteAutomata))
			{
				System.out.println(string);
			}

			List<String> words = FiniteAutomataHelper
					.getAllAcceptedWords(finiteAutomata);

			System.out.println();
			System.out
					.println(String
							.format("List of the words, accepted by the automata (%1$d word(s) found):",
									words.size()));
			
			for (String word : words)
			{
				System.out.println(word);
			}
		}
		catch (Exception exception)
		{
			System.out.println("Error occured:");
			System.out.println(ExceptionHelper
					.getFullExceptionMessage(exception));
		}
	}
}
