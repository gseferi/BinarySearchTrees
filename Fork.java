import java.lang.reflect.Array;
import java.util.Optional;


/**
 * The Class Fork.
 *
 * @param <Key> the generic type
 * @param <Value> the generic type
 */
public class Fork <Key extends Comparable<Key>, Value> implements Bst <Key, Value> {
	 
 	/** The key. */
 	private final Key key;
	 
 	/** The value. */
 	private final Value value;
	 
 	/** The left. */
 	private final Bst<Key, Value>left;
	 
 	/** The right. */
 	private final Bst<Key, Value>right;
	
	  
	/**
	 * Creates a new fork.
	 *
	 * @param k the key
	 * @param v the value
	 * @param l the left sub-tree
	 * @param r the right sub-tree
	 */
	public Fork (Key k, Value v, Bst<Key, Value> l, Bst<Key, Value> r) {
		assert(l!=null);
		assert(r!=null);
		assert(l.smaller(k));
		assert(r.bigger(k));
		
		key=k;
		value=v;
		left=l;
		right=r;
	}

	/* 
	 * Is this tree empty?
	 * @return false
	 */
	@Override
	public boolean isEmpty() {
		return false;
	}

	/* 
	 * Do every node have its key smaller than k?
	 * @param k the key
	 * @return whether or not every node has its key smaller than k
	 */
	@Override
	public boolean smaller(Key k) {
		return (key.compareTo(k) < 0 && right.smaller(k));
	}

	/* 
	 * Do every node have its key bigger than k?
	 * @param k the key
	 * @return whether or not every node has its key bigger than k
	 */
	@Override
	public boolean bigger(Key k) {
		return key.compareTo(k) > 0 && left.bigger(k);
	}

	/*
	 * Does the key occur in this tree?
	 * @param k the key
	 * return whether or not the key occurs in the tree
	 */
	@Override
	public boolean has(Key k) {
		if (k.compareTo(key) == 0)
		       return true;
		    else
		      if (k.compareTo(key) < 0)
			return left.has(k);
		      else
			return right.has(k);
	}

	/* 
	 * Finds the value of the node with a given key k, if it exists.
	 * @param k the key
	 * @return Optional of value
	 */
	@Override
	public Optional<Value> find(Key k) {
		if(k.compareTo(key)==0)
			return Optional.of(value);
		else if(k.compareTo(key)<0)
			return left.find(k);
		else
			return right.find(k);	
	}

	/* 
	 * Returns of this tree with k,v inserted, if the key isn't already there, or with the value replaced, it is already there.
	 * @param k the key
	 * @param v the value
	 * @return Bst
	 */
	@Override
	public Bst<Key, Value> put(Key k, Value v) {
		if(k.compareTo(key)==0)
			return new Fork<Key, Value>(k, v, left, right);
		else if(k.compareTo(key)<0)
			return new Fork <Key, Value>(key, value, left.put(k, v), right);
		else
			return new Fork <Key, Value>(key, value, left, right.put(k, v));
		
	}
	
	/* 
	 *  Returns the entry with smallest key (=left-most node), if it exists.
	 *  @return Optional of Entry
	 */
	@Override
	public Optional<Entry<Key, Value>> smallest() {
		if (left.isEmpty()) 
			return Optional.of(new Entry<Key, Value> (key, value));
	    else 
	    	return left.smallest();   
		
	}
		  

	/* 
	 * Return new tree with smallest element deleted, if it exists.
	 * @return Optional of Bst
	 */
	@Override
	public Optional<Bst<Key, Value>> deleteSmallest() {
		if (left.isEmpty()) 
		      return Optional.of(right);
		else
		      return Optional.of(new Fork<Key, Value>(key, value, left.deleteSmallest().get(), right));
	}

	/* 
	 *  Returns the entry with largest key (=right-most node), if it exists.
	 *  @return Optional of Entry
	 */
	@Override
	public Optional<Entry<Key, Value>> largest() {
		if (right.isEmpty()) 
			return Optional.of(new Entry<Key, Value> (key, value));
	    else 
	    	return right.largest();     
	}

	/* 
	 * * Return new tree with largest element deleted, if it exists.
	 * @return Optional of Bst
	 */
	@Override
	public Optional<Bst<Key, Value>> deleteLargest() {
		if (right.isEmpty()) 
		      return Optional.of(left);
		else
		      return Optional.of(new Fork<Key, Value>(key, value, left, right.deleteLargest().get()));
		  }
	

	/* 
	 */
	@Override
	public Optional<Bst<Key, Value>> delete(Key k) {
		if(has(k)){
			if(k.compareTo(key)==0)
				if(right.isEmpty())
					return Optional.of(left);
				else if(left.isEmpty())
					return Optional.of(right);
				else
					return Optional.of(new Fork<Key, Value>(left.largest().get().getKey(),left.largest().get().getValue(), left.deleteLargest().get(), right));
			else
				if(k.compareTo(key)<0)
					return Optional.of(new Fork<Key, Value> (key, value, left.delete(k).get(),right));
				else
					return Optional.of(new Fork<Key, Value> (key, value, left, right.delete(k).get()));
		}
		else {
			return Optional.empty();
		}
		
	}

	/* 
	 */
	@Override
	public String fancyToString() {
		return "\n\n\n" + fancyToString(0) + "\n\n\n";
	}

	/* 
	 */
	@Override
	public String fancyToString(int d) {
		int step = 4;  // depth step
	    String l = left.fancyToString(d+step);
	    String r = right.fancyToString(d+step);
	    return r + spaces(d) + key + "\n" + l;
	}
	
	  /**
  	 * Spaces.
  	 *
  	 * @param n the n
  	 * @return the string
  	 */
  	private String spaces(int n) { // Helper method for the above:
		    String s = "";
		    for (int i = 0; i < n; i++)
			s = s + " ";
		    return s;
	  }
		    
	/* 
	 */
	@Override
	public int size() {
		return left.size()+1+right.size();
	}

	/* 
	 */
	@Override
	public int height() {
		return 1 + Math.max(left.height(), right.height());
	}

	/* 
	 */
	@Override
	public void printInOrder() {
		if(!isEmpty())
			left.printInOrder();
			System.out.println(key+","+value);
			right.printInOrder();
	}

	/* 
	 */
	@Override
	public void saveInOrder(Entry<Key, Value>[] a) {
		this.saveInOrder(a, 0);
		
	}

	/* 
	 */
	@Override
	public int saveInOrder(Entry<Key, Value>[] a, int i) {
		int b = i;
		if(!left.isEmpty()){
			b = left.saveInOrder(a, i);	
		}
		a[b]= new Entry<Key, Value>(key, value);
		if(!right.isEmpty()){
			return right.saveInOrder(a, b+1);
		}
		else
			return b+1;
		}
	
	/**
	 * Gets the key.
	 *
	 * @return the key
	 */

	/* 
	 */
	@Override
	public Bst<Key, Value> balanced() {
		Bst <Key, Value> tree = new Empty <Key, Value> ();
		 Entry<Key,Value> e = new Entry<>(key,value);
		 @SuppressWarnings("unchecked")
		 Entry<Key,Value>[] a = (Entry<Key,Value>[]) Array.newInstance(e.getClass(), size());
		 this.saveInOrder(a);
		 tree = tree.put(a[a.length/2].getKey(),a[a.length/2].getValue());
		 tree = t1(a, tree, 0, (a.length/2)-1);
		 tree = t1(a, tree, (a.length/2)+1, a.length-1);
		 return tree;
		 
	}
	
	/**
	 * T1.
	 *
	 * @param a the a
	 * @param tree the tree
	 * @param s the s
	 * @param e the e
	 * @return the bst
	 */
	private Bst<Key, Value> t1(Entry<Key, Value>[] a, Bst <Key, Value> tree , int s, int e) {
		if(s<=e) {
		tree = tree.put(a[(s+e)/2].getKey(),a[(s+e)/2].getValue());
		tree = t1(a, tree, s, ((s+e)/2)-1);
		tree = t1(a, tree, ((s+e)/2)+1, e);
		}
		return tree;
			
	}

	@Override
	public Optional<Key> getKey() {
		return Optional.of(key);
	}

	@Override
	public Optional<Value> getValue() {
		return Optional.of(value);
	}

	@Override
	public Optional<Bst<Key, Value>> getLeft() {
		return Optional.of(left);
	}

	@Override
	public Optional<Bst<Key, Value>> getRight() {
		return Optional.of(right);
	}

}
