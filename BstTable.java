import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * The Class BstTable.
 * @param <Key> the generic type
 * @param <Value> the generic type
 */
public class BstTable<Key extends Comparable<Key>, Value> implements Table<Key, Value> {
	
	private final Bst<Key, Value> bst;

	/**
	 * Create an empty bst table.
	 */
	public BstTable() {
		bst = new Empty<Key, Value>();
	}

	/**
	 * Create a new bst table after inserting or deleting.
	 * @param bst1 the bst
	 */
	public BstTable(Bst<Key, Value> bst1) {
		bst = bst1;
	}


	 /**
	  * Does the bst table contains the key
	  * @param k the key
	  * @return true if the key exists and false if not
	  */
	@Override
	public boolean containsKey(Key k) {
		return bst.has(k);
	}
	
	/* 
	 * Return the value of a key, if it exists.
	 * @param k the key
	 * @return the value of a key it exists.
	 */
	@Override
	public Optional<Value> get(Key k) {
		return bst.find(k);
	}

	/* 
	 * Is the bst table empty?
	 * @return true if the bst table is empty and false if it is not.
	 */
	@Override
	public boolean isEmpty() {
		return bst.isEmpty();
	}

	/* 
	 * Table with added or replaced entry
	 * @param k the key
	 * @param v the value
	 * @return new bst table with added and replaced entry
	 */
	@Override
	public Table<Key, Value> put(Key k, Value v) {
		return new BstTable<Key, Value>(bst.put(k, v));
	}

	/* 
	 *  Removes the entry with given key, if present.
	 *  @param k the key
	 *  @returns the Optional of the new table with the removed entry
	 */
	@Override
	public Optional<Table<Key, Value>> remove(Key k) {
		if(containsKey(k)){
		Table<Key, Value> table = new BstTable<Key, Value>(bst.delete(k).get());
		return Optional.of(table);
		}
		else{
			return Optional.empty();
		}
	}

	/* 
	 * Number of entries in the table.
	 * @return the number of entries in bst table
	 */
	@Override
	public int size() {
		return bst.size();
	}

	/* 
	 * The collection of values in the table
	 * @return the collection of values
	 */
	@Override
	public Collection<Value> values() {
		Collection<Value> c = new ArrayList<Value>();
		if (!isEmpty()) {
			Fork<Key, Value> f = (Fork<Key, Value>) bst;
			Entry<Key, Value> e = new Entry<>(f.getKey().get(), f.getValue().get());
			@SuppressWarnings("unchecked")
			Entry<Key, Value>[] a = (Entry<Key, Value>[]) Array.newInstance(e.getClass(), size());
			bst.saveInOrder(a);
			for (int i = 0; i < a.length; i++) {
				c.add(a[i].getValue());
			}
		}
		return c;
	}

	 /** The collection of keys in the table
	 * @return the collection of keys
	 */
	@Override
	public Collection<Key> keys() {
		Collection<Key> c = new ArrayList<Key>();
		if (!isEmpty()) {
			Fork<Key, Value> f = (Fork<Key,Value>) bst;
			Entry<Key, Value> e = new Entry<>(f.getKey().get(), f.getValue().get());
			@SuppressWarnings("unchecked")
			Entry<Key, Value>[] a = (Entry<Key, Value>[]) Array.newInstance(e.getClass(), size());
			bst.saveInOrder(a);
			for (int i = 0; i < a.length; i++) {
				c.add(a[i].getKey());
			}
		}
		return c;
	}
}
