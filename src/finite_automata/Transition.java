package finite_automata;

public class Transition
{
	private char character;

	private int state;

	public Transition(char character, int state)
	{
		this.character = character;
		this.state = state;
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

		if (this.state != other.state)
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
	 * Returns the state
	 *
	 * @return
	 */
	public int getState()
	{
		return this.state;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;

		result = prime * result + this.character;
		result = prime * result + this.state;

		return result;
	}
}
