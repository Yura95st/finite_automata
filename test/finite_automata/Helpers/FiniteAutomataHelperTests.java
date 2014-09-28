package finite_automata.Helpers;

import org.junit.Before;

import finite_automata.FiniteAutomata;
import finite_automata.IFiniteAutomata;
import finite_automata.Transition;

public class FiniteAutomataHelperTests
{
	private IFiniteAutomata testFiniteAutomata;

	@Before
	public void setUp() throws Exception
	{
		this.testFiniteAutomata = new FiniteAutomata();

		this.testFiniteAutomata.setAlphabetCardinality(2);
		this.testFiniteAutomata.setStatesCardinality(3);
		this.testFiniteAutomata.setInitialState(0);
		this.testFiniteAutomata.addFiniteState(2);

		this.testFiniteAutomata.addTransition(1, new Transition(0, 'a'));
		this.testFiniteAutomata.addTransition(2, new Transition(0, 'b'));
		this.testFiniteAutomata.addTransition(0, new Transition(1, 'a'));
		this.testFiniteAutomata.addTransition(2, new Transition(1, 'b'));
	}
}
