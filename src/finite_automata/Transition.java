package finite_automata;

public class Transition
{
	private char character;

	private int fromState;

	private int toState;

	public Transition(int fromState, char character, int toState)
	{
		this.character = character;
		this.fromState = fromState;
		this.toState = toState;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}

		if (obj == null)
		{
			return false;
		}
		if (this.getClass() != obj.getClass())
		{
			return false;
		}

		Transition other = (Transition) obj;

		if (this.character != other.character)
		{
			return false;
		}

		if (this.fromState != other.fromState)
		{
			return false;
		}

		if (this.toState != other.toState)
		{
			return false;
		}

		return true;
	}

	/**
	 * Returns the character
	 *
	 * @return
	 */
	public char getCharacter()
	{
		return this.character;
	}

	/**
	 * Returns the fromState
	 *
	 * @return
	 */
	public int getFromState()
	{
		return this.fromState;
	}

	/**
	 * Returns the toState
	 *
	 * @return
	 */
	public int getToState()
	{
		return this.toState;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;

		int result = 1;

		result = prime * result + this.character;
		result = prime * result + this.fromState;
		result = prime * result + this.toState;

		return result;
	}
}
