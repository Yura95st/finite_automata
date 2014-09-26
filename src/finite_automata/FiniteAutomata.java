package finite_automata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import finite_automata.Exceptions.StateIsAlreadyFinalException;

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
	public void addTransition(Transition transition, int state)
	{
		// TODO Auto-generated method stub

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
	public void removeTransition(Transition transition, int state)
	{
		// TODO Auto-generated method stub

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
