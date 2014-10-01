package finite_automata.Helpers;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import finite_automata.FiniteAutomata;
import finite_automata.IFiniteAutomata;
import finite_automata.Transition;
import finite_automata.Exceptions.FailedToGetFiniteAutomataFromStringListException;

public class FiniteAutomataHelper
{
	/**
	 * Converts finite automata to the list of strings
	 *
	 * @param finiteAutomata
	 * @return
	 */
	public static List<String> convertFiniteAutomataToList(
			final IFiniteAutomata finiteAutomata)
	{
		if (finiteAutomata == null)
		{
			throw new IllegalArgumentException();
		}

		List<String> list = new ArrayList<String>()
				{
			{
				this.add(Integer.toString(finiteAutomata.getAlphabet().size()));
				this.add(Integer.toString(finiteAutomata.getStatesCardinality()));
				this.add(Integer.toString(finiteAutomata.getInitialState()));
				this.add(FiniteAutomataHelper
						.convertFiniteStatesListToString(finiteAutomata
								.getFiniteStates()));
				this.add(FiniteAutomataHelper
						.convertTransitionsMapToString(finiteAutomata
								.getTransitionsMap()));
			}
				};

				return list;
	}

	private static String convertFiniteStatesListToString(
			List<Integer> finiteStates)
	{
		int finiteStatesCount = finiteStates.size();

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(finiteStatesCount);

		for (int i = 0; i < finiteStatesCount; i++)
		{
			stringBuilder.append(" ");
			stringBuilder.append(finiteStates.get(i));
		}

		String finiteStatesString = stringBuilder.toString();

		return finiteStatesString;
	}

	private static String convertTransitionsMapToString(
			Map<Integer, List<Transition>> transitionsMap)
	{
		StringBuilder stringBuilder = new StringBuilder();

		Iterator iterator = transitionsMap.entrySet().iterator();

		while (iterator.hasNext())
		{
			Map.Entry<Integer, List<Transition>> entry = (Map.Entry<Integer, List<Transition>>) iterator
					.next();

			for (Transition transition : entry.getValue())
			{
				stringBuilder.append(transition.getFromState());
				stringBuilder.append(" ");
				stringBuilder.append(transition.getCharacter());
				stringBuilder.append(" ");
				stringBuilder.append(transition.getToState());
				stringBuilder.append(System.getProperty("line.separator"));
			}
		}

		String transitionsMapString = stringBuilder.toString().trim();

		return transitionsMapString;
	}

	/**
	 * Gets the list of words, accepted by the automata
	 *
	 * @param finiteAutomata
	 * @return
	 */
	public static List<String> getAllAcceptedWords(
			final IFiniteAutomata finiteAutomata)
	{
		if (finiteAutomata == null)
		{
			throw new IllegalArgumentException(
					"Argument can't be null: finiteAutomata");
		}

		List<String> words = new ArrayList<String>();

		if (finiteAutomata.getTransitionsMap().size() > 0)
		{
			Map<Transition, Integer> visited = new HashMap<Transition, Integer>();
			
			Stack<Map.Entry<String, Transition>> stack = new Stack<Map.Entry<String, Transition>>();
			
			Map<String, Transition> poppedTransitions = new HashMap<String, Transition>();
			
			// Initial step
			Transition initialTransition = new Transition(
					finiteAutomata.getInitialState(), '*',
					finiteAutomata.getInitialState());
			
			stack.push(new AbstractMap.SimpleEntry<>("", initialTransition));
			
			while (!stack.isEmpty())
			{
				Map.Entry<String, Transition> entry = stack.peek();

				Integer color = visited.get(entry.getValue());

				if (color == null)
				{
					color = 0;
				}
				
				visited.put(entry.getValue(), color + 1);

				boolean popFromStack = true;
				
				List<Transition> children = finiteAutomata.getTransitionsMap()
						.get(entry.getValue().getToState());
				
				for (Transition childTransition : children)
				{
					Integer childColor = visited.get(childTransition);

					if (childColor == null || childColor < 2)
					{
						String key = entry.getKey()
								+ childTransition.getCharacter();
						
						// Prevent from visiting the same chain of transitions
						// twice.
						if (!poppedTransitions.containsKey(key))
						{
							stack.push(new AbstractMap.SimpleEntry<>(key,
									childTransition));
							
							popFromStack = false;
						}
					}
				}
				
				if (popFromStack)
				{
					Map.Entry<String, Transition> poppedEntry = stack.pop();
					
					poppedTransitions.put(poppedEntry.getKey(),
							poppedEntry.getValue());
					
					visited.remove(poppedEntry.getValue());
					
					if (finiteAutomata.getFiniteStates().contains(
							poppedEntry.getValue().getToState()))
					{
						String word = poppedEntry.getKey();
						
						words.add(word);
					}
				}
			}
		}

		return words;
	}

	/**
	 * Gets finite automata from the list of strings
	 *
	 * @param list
	 * @return
	 * @throws FailedToGetFiniteAutomataFromStringListException
	 */
	public static IFiniteAutomata getFiniteAutomataFromStringList(
			List<String> list)
			throws FailedToGetFiniteAutomataFromStringListException
	{
		if (list == null)
		{
			throw new IllegalArgumentException("Argument can't be null: list.");
		}
		
		int listSize = list.size();

		if (listSize < 5)
		{
			throw new FailedToGetFiniteAutomataFromStringListException(
					"Invalid list format: list must contain at least 5 items.");
		}

		IFiniteAutomata finiteAutomata = new FiniteAutomata();

		try
		{
			finiteAutomata
					.setAlphabetCardinality(Integer.parseInt(list.get(0)));

			finiteAutomata.setStatesCardinality(Integer.parseInt(list.get(1)));

			finiteAutomata.setInitialState(Integer.parseInt(list.get(2)));

			String[] finiteStatesLine = list.get(3).split(" ");

			// Ignore the first parameter - cardinality of the finiteStates set.
			if (finiteStatesLine.length <= 1)
			{
				throw new FailedToGetFiniteAutomataFromStringListException(
						"Finite automata must have at least one finite state.");
			}

			for (int i = 1, count = finiteStatesLine.length; i < count; i++)
			{
				finiteAutomata.addFiniteState(Integer
						.parseInt(finiteStatesLine[i]));
			}

			for (int i = 4; i < listSize; i++)
			{
				String[] transitionLine = list.get(i).split(" ");

				Transition transition = new Transition(
						Integer.parseInt(transitionLine[0]),
						transitionLine[1].charAt(0),
						Integer.parseInt(transitionLine[2]));

				finiteAutomata.addTransition(transition);
			}
		}
		catch (FailedToGetFiniteAutomataFromStringListException exception)
		{
			throw exception;
		}
		catch (Exception exception)
		{
			throw new FailedToGetFiniteAutomataFromStringListException(
					"Failed to get finite automata from list.", exception);
		}

		return finiteAutomata;
	}
}
