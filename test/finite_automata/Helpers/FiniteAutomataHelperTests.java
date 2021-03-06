package finite_automata.Helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import finite_automata.FiniteAutomata;
import finite_automata.IFiniteAutomata;
import finite_automata.Transition;
import finite_automata.Exceptions.FailedToGetFiniteAutomataFromStringListException;

public class FiniteAutomataHelperTests
{
	private IFiniteAutomata finiteAutomata;
	
	@Test(expected = IllegalArgumentException.class)
	public void convertFiniteAutomataToList_FinitAutomataIsNull_ThrowsIllegalArgumentException()
			throws Exception
	{
		FiniteAutomataHelper.convertFiniteAutomataToList(null);
	}

	@Test
	public void convertFiniteAutomataToList_ReturnsValidList() throws Exception
	{
		this.finiteAutomata = new FiniteAutomata();

		final int alphabetCardinality = 1;
		final int statesCardinality = 3;
		final int initialState = 0;
		final int finiteState = 2;

		final Transition transition = new Transition(initialState, 'a',
				finiteState);
		
		this.finiteAutomata.setAlphabetCardinality(alphabetCardinality);
		this.finiteAutomata.setStatesCardinality(statesCardinality);
		this.finiteAutomata.setInitialState(initialState);

		this.finiteAutomata.addFiniteState(finiteState);

		this.finiteAutomata.addTransition(transition);

		List<String> list = new ArrayList<String>()
				{
			{
				this.add(Integer.toString(alphabetCardinality));
				this.add(Integer.toString(statesCardinality));
				this.add(Integer.toString(initialState));
				this.add(String.format("1 %1$d", finiteState));
				this.add(String.format("%1$d %2$s %3$d",
						transition.getFromState(), transition.getCharacter(),
						transition.getToState()));
			}
				};

				Assert.assertEquals(list, FiniteAutomataHelper
				.convertFiniteAutomataToList(this.finiteAutomata));
	}

	@Test
	public void getAllAcceptedWords_AutomataIsNonDetermined_ReturnsValidListWithoutDuplicates()
			throws Exception
	{
		this.finiteAutomata = new FiniteAutomata();
		
		this.finiteAutomata.setAlphabetCardinality(2);
		this.finiteAutomata.setStatesCardinality(4);
		this.finiteAutomata.setInitialState(0);

		this.finiteAutomata.addFiniteState(3);
		
		this.finiteAutomata.addTransition(new Transition(0, 'a', 1));
		this.finiteAutomata.addTransition(new Transition(0, 'a', 2));
		this.finiteAutomata.addTransition(new Transition(1, 'b', 3));
		this.finiteAutomata.addTransition(new Transition(2, 'b', 3));

		Set<String> expectedWords = new HashSet<String>()
				{
			{
				this.add("ab");
			}
				};
		
		Assert.assertEquals(expectedWords,
				FiniteAutomataHelper.getAllAcceptedWords(this.finiteAutomata));
	}

	@Test
	public void getAllAcceptedWords_AutomataIsNotFinite_ReturnsEmptyList()
			throws Exception
	{
		this.finiteAutomata = new FiniteAutomata();
		
		this.finiteAutomata.setAlphabetCardinality(1);
		this.finiteAutomata.setStatesCardinality(3);
		this.finiteAutomata.setInitialState(0);

		this.finiteAutomata.addFiniteState(2);
		
		this.finiteAutomata.addTransition(new Transition(0, 'a', 1));

		Assert.assertEquals(0,
				FiniteAutomataHelper.getAllAcceptedWords(this.finiteAutomata)
						.size());
	}

	@Test
	public void getAllAcceptedWords_FinitAutomataIsNotSet_ReturnsEmptyList()
			throws Exception
	{
		this.finiteAutomata = new FiniteAutomata();

		Assert.assertEquals(0,
				FiniteAutomataHelper.getAllAcceptedWords(this.finiteAutomata)
						.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void getAllAcceptedWords_FiniteAutomataIsNull_ThrowsIllegalArgumentException()
	{
		FiniteAutomataHelper.getAllAcceptedWords(null);
	}

	@Test
	public void getAllAcceptedWords_ReturnsValidWordsList() throws Exception
	{
		Set<String> expectedWords = new HashSet<String>()
				{
			{
				this.add("abe");
				this.add("abcdbe");
			}
				};

				Assert.assertEquals(expectedWords,
				FiniteAutomataHelper.getAllAcceptedWords(this.finiteAutomata));
	}

	@Test(expected = FailedToGetFiniteAutomataFromStringListException.class)
	public void getFiniteAutomataFromStringList_InvalidListFormat_ThrowsFailedToGetFiniteAutomataFromStringListException()
			throws Exception
	{
		List<String> list = new ArrayList<String>()
				{
			{
				this.add("1");
			}
				};

				FiniteAutomataHelper.getFiniteAutomataFromStringList(list);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getFiniteAutomataFromStringList_ListIsNull_ThrowsIllegalArgumentException()
			throws Exception
	{
		FiniteAutomataHelper.getFiniteAutomataFromStringList(null);
	}

	@Test(expected = FailedToGetFiniteAutomataFromStringListException.class)
	public void getFiniteAutomataFromStringList_ListWithInvalidData_ThrowsFailedToGetFiniteAutomataFromStringListException()
			throws Exception
	{
		List<String> list = new ArrayList<String>()
				{
			{
				this.add("1"); // alphabet cardinality
				this.add("1"); // states cardinality
				this.add("0"); // initial state
				this.add(""); // finite states
				this.add("0 b 1"); // transition
			}
				};

				FiniteAutomataHelper.getFiniteAutomataFromStringList(list);
	}

	@Test
	public void getFiniteAutomataFromStringList_ReturnsValidList()
			throws Exception
	{
		this.finiteAutomata = new FiniteAutomata();

		final int alphabetCardinality = 1;
		final int statesCardinality = 3;
		final int initialState = 0;
		final int finiteState = 2;

		final Transition transition = new Transition(initialState, 'a',
				finiteState);
		
		this.finiteAutomata.setAlphabetCardinality(alphabetCardinality);
		this.finiteAutomata.setStatesCardinality(statesCardinality);
		this.finiteAutomata.setInitialState(initialState);

		this.finiteAutomata.addFiniteState(finiteState);

		this.finiteAutomata.addTransition(transition);

		IFiniteAutomata automata = FiniteAutomataHelper
				.getFiniteAutomataFromStringList(FiniteAutomataHelper
						.convertFiniteAutomataToList(this.finiteAutomata));
		
		Assert.assertEquals(this.finiteAutomata.getAlphabet(),
				automata.getAlphabet());

		Assert.assertEquals(this.finiteAutomata.getFiniteStates(),
				automata.getFiniteStates());

		Assert.assertEquals(this.finiteAutomata.getInitialState(),
				automata.getInitialState());

		Assert.assertEquals(this.finiteAutomata.getStatesCardinality(),
				automata.getStatesCardinality());

		Assert.assertEquals(this.finiteAutomata.getTransitionsMap(),
				automata.getTransitionsMap());
	}

	@Before
	public void setUp() throws Exception
	{
		this.finiteAutomata = new FiniteAutomata();
		
		this.finiteAutomata.setAlphabetCardinality(5);
		this.finiteAutomata.setStatesCardinality(5);
		this.finiteAutomata.setInitialState(0);

		this.finiteAutomata.addFiniteState(4);
		
		this.finiteAutomata.addTransition(new Transition(0, 'a', 1));
		this.finiteAutomata.addTransition(new Transition(1, 'b', 2));
		this.finiteAutomata.addTransition(new Transition(2, 'c', 3));
		this.finiteAutomata.addTransition(new Transition(3, 'd', 1));
		this.finiteAutomata.addTransition(new Transition(2, 'e', 4));
	}
}
