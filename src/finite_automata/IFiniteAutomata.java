package finite_automata;

import java.util.List;
import java.util.Map;

import finite_automata.Exceptions.NonExistentTransitionException;
import finite_automata.Exceptions.StateIsAlreadyFinalException;
import finite_automata.Exceptions.TransitionAlreadyExistsException;

public interface IFiniteAutomata
{
	/**
	 * Adds finite state
	 *
	 * @param state
	 * @throws StateIsAlreadyFinalException
	 */
	void addFiniteState(int state) throws StateIsAlreadyFinalException;
	
	/**
	 * Adds transition
	 *
	 * @param transition
	 * @throws TransitionAlreadyExistsException
	 */
	void addTransition(Transition transition)
			throws TransitionAlreadyExistsException;
	
	/**
	 * Gets alphabet
	 *
	 * @return
	 */
	List<Character> getAlphabet();
	
	/**
	 * Gets the list of finite states
	 *
	 * @return
	 */
	List<Integer> getFiniteStates();
	
	/**
	 * Gets initial state
	 *
	 * @return
	 */
	int getInitialState();
	
	/**
	 * Gets cardinality of the states
	 *
	 * @return
	 */
	int getStatesCardinality();
	
	/**
	 * Gets map of the transitions
	 *
	 * @return
	 */
	Map<Integer, List<Transition>> getTransitionsMap();
	
	/**
	 * Removes transition
	 *
	 * @param transition
	 */
	void removeTransition(Transition transition)
			throws NonExistentTransitionException;
	
	/**
	 * Sets cardinality of the alphabet
	 *
	 * @param cardinality
	 */
	void setAlphabetCardinality(int cardinality);
	
	/**
	 * Sets initial state
	 *
	 * @param state
	 */
	void setInitialState(int state);
	
	/**
	 * Sets cardinality of the states
	 *
	 * @param cardinality
	 */
	void setStatesCardinality(int cardinality);
}
