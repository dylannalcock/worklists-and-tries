package datastructures.dictionaries;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.trie.TrieMap;
import cse332.types.BString;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * See cse332/interfaces/trie/TrieMap.java
 * and cse332/interfaces/misc/Dictionary.java
 * for method specifications.
 */
public class HashTrieMap<A extends Comparable<A>, K extends BString<A>, V> extends TrieMap<A, K, V> {
    public class HashTrieNode extends TrieNode<Map<A, HashTrieNode>, HashTrieNode> {
        public HashTrieNode() {
            this(null);
        }

        public HashTrieNode(V value) {
            this.pointers = new HashMap<A, HashTrieNode>();
            this.value = value;
        }

        @Override
        public Iterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> iterator() {
            return pointers.entrySet().iterator();
        }
    }

    public HashTrieMap(Class<K> KClass) {
        super(KClass);
        this.root = new HashTrieNode();
    }

    @Override
    public V insert(K key, V value) {
        if(key == null || value == null) {
            throw new IllegalArgumentException();
        }

        V returnVal;

        if(key.isEmpty()) {
            returnVal = this.root.value;
        } else {
            HashTrieNode currentRoot = (HashTrieNode) this.root;

            for(A a: key) {
                if(!currentRoot.pointers.containsKey(a)) {
                    currentRoot.pointers.put(a, new HashTrieNode());
                }
                currentRoot = currentRoot.pointers.get(a);
            }
            returnVal = currentRoot.value;
            currentRoot.value = value;
        }
        if(returnVal == null) {
            size++;
        }
        return returnVal;
    }

    @Override
    public V find(K key) {
        if(key == null) {
            throw new IllegalArgumentException();
        }
        HashTrieNode currentRoot = (HashTrieNode) this.root;
        for(A a: key) {

            currentRoot = currentRoot.pointers.get(a);
            if (currentRoot == null) {
                return null;
            }
        }
        return currentRoot.value;
    }

    @Override
    public boolean findPrefix(K key) {
        if(key == null) {
            throw new IllegalArgumentException();
        }

        HashTrieNode currentRoot = (HashTrieNode) this.root;
        for(A a: key) {
            currentRoot = currentRoot.pointers.get(a);
            if(currentRoot == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void delete(K key) {
        if(key == null) {
            throw new IllegalArgumentException();
        }

        HashTrieNode currentRoot = (HashTrieNode) this.root;
        HashTrieNode finalRoot = (HashTrieNode) this.root;
        A finalKey = null;

        if (key.iterator().hasNext()) {
            finalKey = key.iterator().next();
        }

        for (A a : key) {
            if (currentRoot == null) {
                return;
            } else if (currentRoot.value != null || currentRoot.pointers.size() > 1) {
                finalRoot = currentRoot;
                finalKey = a;
            }
            if (!currentRoot.pointers.isEmpty()) {
                currentRoot = currentRoot.pointers.get(a);
            } else {
                return;
            }
        }

        if (currentRoot != null && currentRoot.value != null) {
            if (!currentRoot.pointers.isEmpty()) {
                currentRoot.value = null;
            } else if (finalKey != null) {
                finalRoot.pointers.remove(finalKey);
            } else {
                this.root.value = null;
            }
            size--;
        }
    }

    @Override
    public void clear() {
        throw new NotYetImplementedException();
    }
}
