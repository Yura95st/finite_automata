package finite_automata.Helpers;

import java.util.ArrayList;
import java.util.List;

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
		List<String> expectedWords = new ArrayList<String>()
				{
			{
				this.add("fge");
				this.add("fgeh");
				this.add("fgbcde");
				this.add("fgbcdeh");
				this.add("ae");
				this.add("aeh");
				this.add("abcde");
				this.add("abcdeh");
			}
				};

				List<String> words = FiniteAutomataHelper
				.getAllAcceptedWords(this.finiteAutomata);

				Assert.assertEquals(expectedWords.size(), words.size());

				for (String word : words)
				{
					Assert.assertTrue(expectedWords.contains(word));
				}
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
		
		this.finiteAutomata.setAlphabetCardinality(8);
		this.finiteAutomata.setStatesCardinality(7);
		this.finiteAutomata.setInitialState(0);

		this.finiteAutomata.addFiniteState(4);
		this.finiteAutomata.addFiniteState(6);
		
		this.finiteAutomata.addTransition(new Transition(0, 'a', 1));
		this.finiteAutomata.addTransition(new Transition(0, 'f', 5));
		this.finiteAutomata.addTransition(new Transition(1, 'b', 2));
		this.finiteAutomata.addTransition(new Transition(1, 'e', 4));
		this.finiteAutomata.addTransition(new Transition(2, 'c', 3));
		this.finiteAutomata.addTransition(new Transition(3, 'd', 1));
		this.finiteAutomata.addTransition(new Transition(4, 'h', 6));
		this.finiteAutomata.addTransition(new Transition(5, 'g', 1));
	}
}
