package finite_automata.Helpers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import finite_automata.FiniteAutomata;
import finite_automata.IFiniteAutomata;
import finite_automata.Transition;
import finite_automata.Exceptions.FailedToGetFiniteAutomataFromStringListException;

public class FiniteAutomataHelperTest
{
	private IFiniteAutomata testFiniteAutomata;

	@Test
	public void getFiniteAutomataFromStringList_ListIsNull_ThrowsIllegalArgumentException()
	{
		try
		{
			FiniteAutomataHelper.getFiniteAutomataFromStringList(null);

			Assert.fail();
		}
		catch (FailedToGetFiniteAutomataFromStringListException e)
		{
		}
	}

	@Test
	public void getFiniteAutomataFromStringList_ReturnsValidFiniteAutomata()
	{
		try
		{
			IFiniteAutomata finiteAutomata = FiniteAutomataHelper
					.getFiniteAutomataFromStringList(FiniteAutomataHelper
							.convertFiniteAutomataToList(this.testFiniteAutomata));
			
			Assert.assertEquals(this.testFiniteAutomata.getAlphabet(), finiteAutomata.getAlphabet());
		}
		catch (FailedToGetFiniteAutomataFromStringListException e)
		{
			Assert.fail();
		}
	}

	@Before
	public void setUp() throws Exception
	{
		this.testFiniteAutomata = new FiniteAutomata();

		this.testFiniteAutomata.setAlphabetCardinality(2);
		this.testFiniteAutomata.setStatesCardinality(3);
		this.testFiniteAutomata.setInitialState(0);
		this.testFiniteAutomata.addFiniteState(2);

		this.testFiniteAutomata.addTransition(new Transition(0, 'a'), 1);
		this.testFiniteAutomata.addTransition(new Transition(0, 'b'), 2);
		this.testFiniteAutomata.addTransition(new Transition(1, 'a'), 0);
		this.testFiniteAutomata.addTransition(new Transition(1, 'b'), 2);
	}
}
