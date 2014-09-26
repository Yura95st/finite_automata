package finite_automata;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FiniteAutomata implements IFiniteAutomata
{
	public static final Set<Character> FINITE_AUTOMATA_ALPHABET = new HashSet<Character>()
	{
		{
			this.add('a');
			this.add('b');
			this.add('c');
			this.add('d');
			this.add('e');
			this.add('f');
			this.add('g');
			this.add('h');
			this.add('i');
			this.add('j');
			this.add('k');
			this.add('l');
			this.add('m');
			this.add('n');
			this.add('o');
			this.add('p');
			this.add('q');
			this.add('r');
			this.add('s');
			this.add('t');
			this.add('u');
			this.add('v');
			this.add('w');
			this.add('x');
			this.add('y');
			this.add('z');
		}
	};

	@Override
	public void addFiniteState(int state)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void addTransition(Transition transition, int state)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public List<Character> getAlphabet()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getFiniteStates()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getInitialState()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStatesCardinality()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<Transition, Integer> getTransitionsMap()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeTransition(Transition transition, int state)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setAlphabetCardinality(int cardinality)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setInitialState(int i)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setStatesCardinality(int cardinality)
	{
		// TODO Auto-generated method stub

	}
}
