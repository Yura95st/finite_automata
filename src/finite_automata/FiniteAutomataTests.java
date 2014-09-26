package finite_automata;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import finite_automata.Exceptions.StateIsAlreadyFinalException;

public class FiniteAutomataTests
{
	private List<Character> alphabet;

	private IFiniteAutomata finiteAutomata;

	@Test
	public void addFiniteState_FiniteStateSuccessfullySet()
	{
		int state = 7;

		this.finiteAutomata.setStatesCardinality(state + 1);

		try
		{
			this.finiteAutomata.addFiniteState(state);
		}
		catch (StateIsAlreadyFinalException e)
		{
			Assert.fail();
		}

		Assert.assertEquals(true, this.finiteAutomata.getFiniteStates()
				.contains(state));
	}

	@Test(expected = IllegalArgumentException.class)
	public void addFiniteState_InvalidState_ThrowsIllegalArgumentException()
	{
		int statesCardinality = 7;

		this.finiteAutomata.setStatesCardinality(statesCardinality);

		try
		{
			this.finiteAutomata.addFiniteState(statesCardinality + 1);
		}
		catch (StateIsAlreadyFinalException e)
		{
			Assert.fail();
		}
	}

	@Test
	public void addFiniteState_StateIsAlreadyFinite_ThrowsStateIsAlreadyFinalException()
	{
		int state = 7;

		this.finiteAutomata.setStatesCardinality(state + 1);

		try
		{
			this.finiteAutomata.addFiniteState(state);
			this.finiteAutomata.addFiniteState(state);

			Assert.fail();
		}
		catch (StateIsAlreadyFinalException e)
		{

		}
	}

	@Test
	public void getAlphabet_AlphabetCardinalityIsNotSet_ReturnsEmptyList()
	{
		Assert.assertEquals(0, this.finiteAutomata.getAlphabet().size());
	}

	@Test
	public void getFiniteStates_FiniteStatesAreNotSet_ReturnsEmptyList()
	{
		Assert.assertEquals(0, this.finiteAutomata.getFiniteStates().size());
	}

	@Test
	public void getInitialState_InitialStateIsNotSet_ReturnsDefaultValue()
	{
		Assert.assertEquals(FiniteAutomata.DEFAULT_INITIAL_STATE,
				this.finiteAutomata.getInitialState());
	}

	@Test
	public void getStatesCardinality_StatesCardinalityIsNotSet_ReturnsZero()
	{
		Assert.assertEquals(0, this.finiteAutomata.getStatesCardinality());
	}

	@Test
	public void setAlphabetCardinality_CardinalityIsInvalid_ThrowsIllegalArgumentException()
	{
		try
		{
			this.finiteAutomata.setAlphabetCardinality(-1);

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
		this.finiteAutomata.setAlphabetCardinality(this.alphabet.size());

		Assert.assertEquals(this.alphabet, this.finiteAutomata.getAlphabet());

		Assert.assertEquals(0, this.finiteAutomata.getTransitionsMap().size());
	}

	@Test
	public void setInitialState_InvalidState_ThrowsIllegalArgumentException()
	{
		int statesCardinality = 7;

		this.finiteAutomata.setStatesCardinality(statesCardinality);

		try
		{
			this.finiteAutomata.setInitialState(-1);

			this.finiteAutomata.setInitialState(statesCardinality + 1);

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
		int setStatesCardinality = 2;

		this.finiteAutomata.setStatesCardinality(setStatesCardinality);

		Assert.assertEquals(setStatesCardinality,
				this.finiteAutomata.getStatesCardinality());

		Assert.assertEquals(FiniteAutomata.DEFAULT_INITIAL_STATE,
				this.finiteAutomata.getInitialState());
		Assert.assertEquals(0, this.finiteAutomata.getFiniteStates().size());
		Assert.assertEquals(0, this.finiteAutomata.getTransitionsMap().size());
	}

	@Before
	public void setUp()
	{
		this.alphabet = new ArrayList<Character>()
		{
			{
				this.add('a');
				this.add('b');
				this.add('c');
			}
		};

		this.finiteAutomata = new FiniteAutomata();
	}

	@Test
	public void testAddTransition()
	{
		Assert.fail("Not yet implemented");
	}

	@Test
	public void testRemoveTransition()
	{
		Assert.fail("Not yet implemented");
	}

}
