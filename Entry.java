// TODO: Auto-generated Javadoc
// Do not modify this class.

/**
 * The Class Entry.
 *
 * @param <Key> the generic type
 * @param <Value> the generic type
 */
public class Entry<Key extends Comparable<Key>,Value> {

  /** The key. */
  private final Key key;
  
  /** The value. */
  private final Value value;

  /**
   * Instantiates a new entry.
   *
   * @param key the key
   * @param value the value
   */
  public Entry(Key key, Value value) {
    this.key = key;
    this.value = value;
  }

  /**
   * Gets the key.
   *
   * @return the key
   */
  public Key getKey() {
    return key;
  }

  /**
   * Gets the value.
   *
   * @return the value
   */
  public Value getValue() {
    return value;
  }
}
