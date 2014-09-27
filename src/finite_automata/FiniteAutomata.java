package finite_automata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import finite_automata.Exceptions.NonExistentTransitionException;
import finite_automata.Exceptions.StateIsAlreadyFinalException;
import finite_automata.Exceptions.TransitionAlreadyExistsException;

public class FiniteAutomata implements IFiniteAutomata
{
	public static final int DEFAULT_INITIAL_STATE = 0;

	public static final String FINITE_AUTOMATA_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

	private List<Character> alphabet;

	private List<Integer> finiteStates;

	private int initialState;

	private int statesCardinality;

	private Map<Transition, Integer> transitionsMap;

	public FiniteAutomata()
	{
		this.alphabet = new ArrayList<Character>();
		this.statesCardinality = 0;
		this.initialState = FiniteAutomata.DEFAULT_INITIAL_STATE;
		this.finiteStates = new ArrayList<Integer>();
		this.transitionsMap = new HashMap<Transition, Integer>();
	}

	@Override
	public void addFiniteState(int state) throws StateIsAlreadyFinalException
	{
		this.checkState(state);

		if (this.finiteStates.contains(state))
		{
			throw new StateIsAlreadyFinalException(String.format(
					"State: %1$d is already final.", state));
		}

		this.finiteStates.add(state);
	}

	@Override
	public void addTransition(Transition transition, int state) throws TransitionAlreadyExistsException
	{
		if (transition == null)
		{
			throw new IllegalArgumentException("Argument can't be null: transition.");
		}
		
		if (state < 0 || state >= this.statesCardinality)
		{
			throw new IllegalArgumentException(String.format("State value: %1$d is invalid.", state));
		}
		
		if (this.transitionsMap.containsKey(transition))
		{
			throw new TransitionAlreadyExistsException(String.format("Transition (%1$d;%2$s) already exists.", transition.getState(), transition.getCharacter()));
		}
		
		char character = transition.getCharacter();
		int fromState = transition.getState();
		
		if (!this.alphabet.contains(character) || fromState < 0 || fromState >= this.statesCardinality)
		{
			throw new IllegalArgumentException(String.format("Transition (%1$d;%2$s) is invalid.", transition.getState(), transition.getCharacter()));
		}
		
		this.transitionsMap.put(transition, state);
	}

	private void checkState(int state)
	{
		if (state < 0)
		{
			throw new IllegalArgumentException(
					"Argument must be not less than zero: state.");
		}

		if (state >= this.statesCardinality)
		{
			throw new IllegalArgumentException(String.format(
					"State: %1$d must be less than states cardinality: %2$d",
					state, this.statesCardinality));
		}
	}

	@Override
	public List<Character> getAlphabet()
	{
		return this.alphabet;
	}

	@Override
	public List<Integer> getFiniteStates()
	{
		return this.finiteStates;
	}

	@Override
	public int getInitialState()
	{
		return this.initialState;
	}

	@Override
	public int getStatesCardinality()
	{
		return this.statesCardinality;
	}

	@Override
	public Map<Transition, Integer> getTransitionsMap()
	{
		return this.transitionsMap;
	}

	@Override
	public void removeTransition(Transition transition) throws NonExistentTransitionException
	{
		if (transition == null)
		{
			throw new IllegalArgumentException("Argument can't be null: transition.");
		}
		
		if (!this.transitionsMap.containsKey(transition))
		{
			throw new NonExistentTransitionException(String.format("Transition (%1$d;%2$s) does not exist.", transition.getState(), transition.getCharacter()));
		}
		
		this.transitionsMap.remove(transition);
	}

	@Override
	public void setAlphabetCardinality(int cardinality)
	{
		if (cardinality < 0)
		{
			throw new IllegalArgumentException(
					"Argument must be not less than zero: cardinality.");
		}

		if (cardinality > FiniteAutomata.FINITE_AUTOMATA_ALPHABET.length())
		{
			throw new IllegalArgumentException(
					String.format(
							"Cardinality: %1$d must be less or equal to FINITE_AUTOMATA_ALPHABET size: %2$d",
							cardinality,
							FiniteAutomata.FINITE_AUTOMATA_ALPHABET.length()));
		}

		this.alphabet.clear();
		this.transitionsMap.clear();

		for (int i = 0; i < cardinality; i++)
		{
			this.alphabet
					.add(FiniteAutomata.FINITE_AUTOMATA_ALPHABET.charAt(i));
		}
	}

	@Override
	public void setInitialState(int state)
	{
		this.checkState(state);

		this.initialState = state;
	}

	@Override
	public void setStatesCardinality(int cardinality)
	{
		if (cardinality < 0)
		{
			throw new IllegalArgumentException(
					"Argument must be not less than zero: cardinality.");
		}

		this.initialState = FiniteAutomata.DEFAULT_INITIAL_STATE;

		this.finiteStates.clear();
		this.transitionsMap.clear();

		this.statesCardinality = cardinality;
	}
}
