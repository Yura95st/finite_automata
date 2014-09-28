package finite_automata;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import finite_automata.Helpers.ExceptionHelper;
import finite_automata.Helpers.FiniteAutomataHelper;

public class Main
{
	private static IFiniteAutomata finiteAutomata;

	private static Set<Transition> visited = new HashSet<Transition>();

	private static void dfs(Transition transition, String word)
	{
		if (!Main.visited.contains(transition))
		{
			Main.visited.add(transition);
		}

		word += transition.getCharacter();

		if (Main.finiteAutomata.getFiniteStates().contains(
				transition.getState()))
		{
			System.out.println(word);
		}

		List<Transition> children = new ArrayList<Transition>();

		Map<Integer, List<Transition>> transitionsMap = Main.finiteAutomata
				.getTransitionsMap();

		List<Transition> stateTransitions = transitionsMap.get(transition
				.getState());

		for (Transition childTransition : stateTransitions)
		{
			if (!Main.visited.contains(childTransition))
			{
				children.add(childTransition);
			}
		}

		for (Transition childTransition : children)
		{
			Main.dfs(childTransition, word);
		}

		Main.visited.clear();
	}

	public static void main(String[] args)
	{
		try
		{
			Path path = Paths.get(args[0]);

			List<String> lines = Files.readAllLines(path,
					StandardCharsets.UTF_8);

			Main.finiteAutomata = FiniteAutomataHelper
					.getFiniteAutomataFromStringList(lines);

			for (String string : FiniteAutomataHelper
					.convertFiniteAutomataToList(Main.finiteAutomata))
			{
				System.out.println(string);
			}

			System.out.println("===");

			Transition initialTransition = new Transition(
					Main.finiteAutomata.getInitialState(), '*');

			Main.dfs(initialTransition, "");
		}
		catch (Exception exception)
		{
			System.out.println("Error occured:");
			System.out.println(ExceptionHelper
					.getFullExceptionMessage(exception));
		}
	}
}
