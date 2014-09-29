package finite_automata.Helpers;

import java.util.AbstractMap;
import java.util.ArrayList;
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
		System.out.println("======");

		List<String> words = new ArrayList<String>();
		
		List<Transition> visited = new ArrayList<Transition>();

		Stack<Map.Entry<String, List<Transition>>> stack = new Stack<Map.Entry<String, List<Transition>>>();
		
		// Initial step
		List<Transition> initialTransitions = finiteAutomata
				.getTransitionsMap().get(finiteAutomata.getInitialState());
		
		stack.push(new AbstractMap.SimpleEntry<>("", initialTransitions));
		
		while (!stack.isEmpty())
		{
			Map.Entry<String, List<Transition>> entry = stack.peek();
			
			boolean popFromStack = true;
			
			for (Transition transition : entry.getValue())
			{
				visited.add(transition);

				List<Transition> children = new ArrayList<Transition>();
				
				List<Transition> stateTransitions = finiteAutomata
						.getTransitionsMap().get(transition.getToState());
				
				for (Transition childTransition : stateTransitions)
				{
					if (!visited.contains(childTransition))
					{
						children.add(childTransition);
					}
				}
				
				if (children.size() > 0)
				{
					String key = entry.getKey() + transition.getCharacter();
					
					stack.push(new AbstractMap.SimpleEntry<>(key, children));
					
					popFromStack = false;
				}
			}
			
			if (popFromStack)
			{
				stack.pop();

				for (Transition transition : entry.getValue())
				{
					if (finiteAutomata.getFiniteStates().contains(
							transition.getToState()))
					{
						String word = entry.getKey()
								+ transition.getCharacter();
						words.add(word);

						System.out.println(word);
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
