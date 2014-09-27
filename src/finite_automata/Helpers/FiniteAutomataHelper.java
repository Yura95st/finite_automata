package finite_automata.Helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import finite_automata.FiniteAutomata;
import finite_automata.IFiniteAutomata;
import finite_automata.Transition;
import finite_automata.Exceptions.FailedToGetFiniteAutomataFromStringListException;

public class FiniteAutomataHelper
{
	/**
	 * Gets finite automata from the list of strings
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
						transitionLine[1].charAt(0));

				finiteAutomata.addTransition(transition,
						Integer.parseInt(transitionLine[2]));
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
	
	/**
	 * Converts finite automata to the list of strings
	 * @param finiteAutomata
	 * @return
	 */
	public static List<String> convertFiniteAutomataToList(final IFiniteAutomata finiteAutomata)
	{
		if (finiteAutomata == null)
		{
			throw new IllegalArgumentException();
		}
		
		List<String> list = new ArrayList<String>()
		{
			{
				add(Integer.toString(finiteAutomata.getAlphabet().size()));
				add(Integer.toString(finiteAutomata.getStatesCardinality()));
				add(Integer.toString(finiteAutomata.getInitialState()));
				add(FiniteAutomataHelper.convertFiniteStatesListToString(finiteAutomata.getFiniteStates()));
				add(FiniteAutomataHelper.convertTransitionsMapToString(finiteAutomata.getTransitionsMap()));
			}
		};
		
		return list;
	}
	
	private static String convertFiniteStatesListToString(List<Integer> finiteStates)
	{
		int finiteStatesCount = finiteStates.size();
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(Integer.toString(finiteStatesCount));
		
		for (int i=0; i< finiteStatesCount; i++)
		{
			stringBuilder.append(" ");
			stringBuilder.append(Integer.toString(finiteStates.get(i)));
		}
		
		String finiteStatesString = stringBuilder.toString();
		
		return finiteStatesString;
	}
	
	private static String convertTransitionsMapToString(Map<Transition, Integer> transitionsMap)
	{
		StringBuilder stringBuilder = new StringBuilder();
		
		Iterator iterator = transitionsMap.entrySet().iterator();
		
	    while (iterator.hasNext()) 
	    {
	        Map.Entry entry = (Map.Entry) iterator.next();

	        Transition transition = (Transition) entry.getKey();
	        
	        stringBuilder.append(Integer.toString(transition.getState()));
			stringBuilder.append(" ");
			stringBuilder.append(transition.getCharacter());
			stringBuilder.append(" ");
			stringBuilder.append(entry.getValue());
			
			stringBuilder.append(System.getProperty("line.separator"));
	    }
		
		String transitionsMapString = stringBuilder.toString().trim();
		
		return transitionsMapString;
	}
}
