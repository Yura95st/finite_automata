package finite_automata;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import finite_automata.Exceptions.NonExistentTransitionException;
import finite_automata.Exceptions.StateIsAlreadyFinalException;
import finite_automata.Exceptions.TransitionAlreadyExistsException;

public class FiniteAutomataTests
{
	private IFiniteAutomata finiteAutomata;
	
	@Test
	public void addFiniteState_FiniteStateSuccessfullyAdded()
			throws StateIsAlreadyFinalException
	{
		int state = this.finiteAutomata.getStatesCardinality() - 2;
		
		this.finiteAutomata.addFiniteState(state);
		
		Assert.assertEquals(true, this.finiteAutomata.getFiniteStates()
				.contains(state));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addFiniteState_InvalidState_ThrowsIllegalArgumentException()
			throws StateIsAlreadyFinalException
	{
		int state = this.finiteAutomata.getStatesCardinality();
		
		this.finiteAutomata.addFiniteState(state);
	}
	
	@Test(expected = StateIsAlreadyFinalException.class)
	public void addFiniteState_StateIsAlreadyFinite_ThrowsStateIsAlreadyFinalException()
			throws StateIsAlreadyFinalException
	{
		int finiteState = this.finiteAutomata.getFiniteStates().get(0);
		
		this.finiteAutomata.addFiniteState(finiteState);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addTransition_InvalidTransition_ThrowsIllegalArgumentException()
			throws TransitionAlreadyExistsException
	{
		Transition transition = new Transition(
				this.finiteAutomata.getStatesCardinality(),
				FiniteAutomata.FINITE_AUTOMATA_ALPHABET
				.charAt(this.finiteAutomata.getAlphabet().size()), -1);
		
		this.finiteAutomata.addTransition(transition);
	}
	
	@Test(expected = TransitionAlreadyExistsException.class)
	public void addTransition_TransitionAlreadyExists_ThrowsTransitionAlreadyExistsException()
			throws TransitionAlreadyExistsException
	{
		int state = this.finiteAutomata.getInitialState();
		Transition transition = this.finiteAutomata.getTransitionsMap()
				.get(state).get(0);
		
		this.finiteAutomata.addTransition(transition);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addTransition_TransitionIsNull_ThrowsIllegalArgumentException()
			throws TransitionAlreadyExistsException
	{
		this.finiteAutomata.addTransition(null);
	}
	
	@Test
	public void addTransition_TransitionSuccessfullyAdded()
			throws TransitionAlreadyExistsException,
			StateIsAlreadyFinalException
	{
		Transition transition = new Transition(
				this.finiteAutomata.getInitialState(), this.finiteAutomata
						.getAlphabet().get(1), this.finiteAutomata
						.getFiniteStates().get(0));
		
		this.finiteAutomata.addTransition(transition);
		
		Assert.assertEquals(
				true,
				this.finiteAutomata.getTransitionsMap()
						.get(transition.getFromState()).contains(transition));
	}
	
	@Test
	public void getAlphabet_AlphabetCardinalityIsNotSet_ReturnsEmptyList()
	{
		this.finiteAutomata = new FiniteAutomata();
		
		Assert.assertEquals(0, this.finiteAutomata.getAlphabet().size());
	}
	
	@Test
	public void getFiniteStates_FiniteStatesAreNotSet_ReturnsEmptyList()
	{
		this.finiteAutomata = new FiniteAutomata();
		
		Assert.assertEquals(0, this.finiteAutomata.getFiniteStates().size());
	}
	
	@Test
	public void getInitialState_InitialStateIsNotSet_ReturnsDefaultValue()
	{
		this.finiteAutomata = new FiniteAutomata();
		
		Assert.assertEquals(FiniteAutomata.DEFAULT_INITIAL_STATE,
				this.finiteAutomata.getInitialState());
	}
	
	@Test
	public void getStatesCardinality_StatesCardinalityIsNotSet_ReturnsZero()
	{
		this.finiteAutomata = new FiniteAutomata();
		
		Assert.assertEquals(0, this.finiteAutomata.getStatesCardinality());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void removeTransition_InvalidTransition_ThrowsIllegalArgumentException()
			throws NonExistentTransitionException
	{
		Transition transition = new Transition(
				this.finiteAutomata.getStatesCardinality(),
				FiniteAutomata.FINITE_AUTOMATA_ALPHABET
				.charAt(this.finiteAutomata.getAlphabet().size()), -1);
		
		this.finiteAutomata.removeTransition(transition);
	}
	
	@Test(expected = NonExistentTransitionException.class)
	public void removeTransition_TransitionDoesNotExist_ThrowsNonExistentTransitionException()
			throws NonExistentTransitionException
	{
		Transition transition = new Transition(
				this.finiteAutomata.getInitialState(), this.finiteAutomata
				.getAlphabet().get(0),
				this.finiteAutomata.getInitialState());
		
		this.finiteAutomata.removeTransition(transition);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void removeTransition_TransitionIsNull_ThrowsIllegalArgumentException()
			throws NonExistentTransitionException
	{
		this.finiteAutomata.removeTransition(null);
	}

	@Test
	public void removeTransition_TransitionIsSuccessfullyRemoved()
			throws NonExistentTransitionException
	{
		Transition transition = this.finiteAutomata.getTransitionsMap()
				.get(this.finiteAutomata.getInitialState()).get(0);
		
		this.finiteAutomata.removeTransition(transition);
		
		Assert.assertEquals(
				false,
				this.finiteAutomata.getTransitionsMap()
						.get(transition.getFromState()).contains(transition));
	}
	
	@Test
	public void setAlphabetCardinality_CardinalityIsInvalid_ThrowsIllegalArgumentException()
	{
		try
		{
			this.finiteAutomata.setAlphabetCardinality(-1);
			
			Assert.fail();
		}
		catch (IllegalArgumentException exception)
		{
		}

		try
		{
			this.finiteAutomata
					.setAlphabetCardinality(FiniteAutomata.FINITE_AUTOMATA_ALPHABET
							.length() + 1);
			
			Assert.fail();
		}
		catch (IllegalArgumentException exception)
		{
		}
	}
	
	@Test
	public void setAlphabetCardinality_SetsAlphabetAndClearsTransitionsMap()
	{
		int cardinality = 7;
		
		List<Character> alphabet = new ArrayList<Character>();
		
		for (int i = 0; i < cardinality; i++)
		{
			alphabet.add(FiniteAutomata.FINITE_AUTOMATA_ALPHABET.charAt(i));
		}
		
		this.finiteAutomata.setAlphabetCardinality(cardinality);
		
		Assert.assertEquals(alphabet, this.finiteAutomata.getAlphabet());
		
		for (int i = 0, count = this.finiteAutomata.getStatesCardinality(); i < count; i++)
		{
			Assert.assertEquals(0,
					this.finiteAutomata.getTransitionsMap().get(i).size());
		}
	}
	
	@Test
	public void setInitialState_InvalidState_ThrowsIllegalArgumentException()
	{
		int initialState = this.finiteAutomata.getStatesCardinality();
		
		try
		{
			this.finiteAutomata.setInitialState(-1);
			Assert.fail();
		}
		catch (IllegalArgumentException exception)
		{
		}

		try
		{
			this.finiteAutomata.setInitialState(initialState);
			Assert.fail();
		}
		catch (IllegalArgumentException exception)
		{
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setStatesCardinality_CardinalityIsInvalid_ThrowsIllegalArgumentException()
	{
		this.finiteAutomata.setStatesCardinality(-1);
	}
	
	@Test
	public void setStatesCardinality_SetsStatesAndClearsTransitionsMapFiniteStatesAndInitialState()
	{
		int statesCardinality = 2;
		
		this.finiteAutomata.setStatesCardinality(statesCardinality);
		
		Assert.assertEquals(statesCardinality,
				this.finiteAutomata.getStatesCardinality());
		
		Assert.assertEquals(FiniteAutomata.DEFAULT_INITIAL_STATE,
				this.finiteAutomata.getInitialState());

		Assert.assertEquals(0, this.finiteAutomata.getFiniteStates().size());
		
		for (int i = 0, count = this.finiteAutomata.getStatesCardinality(); i < count; i++)
		{
			Assert.assertEquals(0,
					this.finiteAutomata.getTransitionsMap().get(i).size());
		}
	}
	
	@Before
	public void setUp() throws Exception
	{
		this.finiteAutomata = new FiniteAutomata();
		
		this.finiteAutomata.setAlphabetCardinality(2);
		this.finiteAutomata.setStatesCardinality(3);
		this.finiteAutomata.setInitialState(0);
		this.finiteAutomata.addFiniteState(2);
		
		this.finiteAutomata.addTransition(new Transition(0, 'a', 1));
		this.finiteAutomata.addTransition(new Transition(1, 'b', 2));
	}
}
