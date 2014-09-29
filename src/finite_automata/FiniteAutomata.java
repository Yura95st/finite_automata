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

	private Map<Integer, List<Transition>> transitionsMap;

	public FiniteAutomata()
	{
		this.alphabet = new ArrayList<Character>();
		this.statesCardinality = 0;
		this.initialState = FiniteAutomata.DEFAULT_INITIAL_STATE;
		this.finiteStates = new ArrayList<Integer>();
		this.transitionsMap = new HashMap<Integer, List<Transition>>();
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
	public void addTransition(Transition transition)
			throws TransitionAlreadyExistsException
	{
		this.checkTransition(transition);
		
		List<Transition> stateTransitions = this.transitionsMap.get(transition.getFromState());

		if (stateTransitions.contains(transition))
		{
			throw new TransitionAlreadyExistsException(String.format(
					"Transition (%1$d; %2$s; %3$d) already exists.",
					transition.getFromState(), transition.getCharacter(), transition.getToState()));
		}

		stateTransitions.add(transition);
	}
	
	private void checkTransition(Transition transition)
	{
		if (transition == null)
		{
			throw new IllegalArgumentException(
					"Argument can't be null: transition.");
		}
		
		int fromState = transition.getFromState();
		char character = transition.getCharacter();
		int toState = transition.getToState();
		
		try
		{
			this.checkState(fromState);
			this.checkState(toState);
		}
		catch(IllegalArgumentException exception)
		{
			throw new IllegalArgumentException(String.format(
					"Transition (%1$d; %2$s; %3$d) is invalid.",
					fromState, character, toState));
		}

		if (!this.alphabet.contains(character))
		{
			throw new IllegalArgumentException(String.format(
					"Transition (%1$d; %2$s; %3$d) is invalid.",
					fromState, character, toState));
		}
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
	public Map<Integer, List<Transition>> getTransitionsMap()
	{
		return this.transitionsMap;
	}

	@Override
	public void removeTransition(Transition transition)
			throws NonExistentTransitionException
	{
		this.checkTransition(transition);
		
		List<Transition> stateTransitions = this.transitionsMap.get(transition.getFromState());

		int index = stateTransitions.indexOf(transition);

		if (index == -1)
		{
			throw new NonExistentTransitionException(String.format(
					"Transition (%1$d; %2$s %3$d) does not exist.",
					transition.getFromState(), transition.getCharacter(), transition.getToState()));
		}

		stateTransitions.remove(index);
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

		for (int i = 0; i < this.statesCardinality; i++)
		{
			this.transitionsMap.get(i).clear();
		}

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

		this.statesCardinality = cardinality;

		for (int i = 0; i < this.statesCardinality; i++)
		{
			this.transitionsMap.put(i, new ArrayList<Transition>());
		}
	}
}
