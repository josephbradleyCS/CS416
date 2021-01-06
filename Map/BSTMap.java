/**
 * hgfds.
 *
 * @author jkbra
 * @version 1.001
 * @param <K> fcwefcwer
 * @param <V> fcwerfrw
 */
@SuppressWarnings("unchecked")
public class BSTMap<K extends Comparable, V> implements Map<K, V> {

    BST<Entry<K, V>> bst = new BST<>();
    int size;

    @Override
    public void clear() {
        bst.clear();
    }

    @Override
    public boolean containsKey(K key) {
        return bst.contains(key);
    }

    @Override
    public V get(K key) {
        // return bst.get(key).value;
        if (bst.get(key) != null) {
            return bst.get(key).value;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (bst.add(new Entry<>(key, value))) {
            size++;
            return bst.get(key).value;
        }
        bst.get(key).value = value;
        return value;
    }

    @Override
    public V putIfAbsent(K key, V value) {
        if (bst.contains(key)) {
            return null;
        }
        return put(key, value);
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public V remove(K key) {
        V ret = bst.get(key).value;
        if (bst.remove(key)) {
            return ret;
        }
        return ret;
    }

    @Override
    public int size() {
        return bst.size();
    }

    /**
     * SDFGHJKLKJHGFDFGHJKL.
     *
     * @param <K> DFGHJKL
     * @param <V> DFGHJKL
     */
    public class Entry<K extends Comparable, V> implements Map.Entry, Comparable {

        K key;
        V value;

        /**
         * aSDFGHJKLKJHGFDSA.
         *
         * @param key   DFGHJKL
         * @param value DFGHJKL
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "(" + key + ", " + value + ")";
        }

        @Override
        @SuppressWarnings("unchecked")
        public int compareTo(Object o) {
            if (this.equals(o)) {
                return 0;
            }

            if (o instanceof Entry) {
                return key.compareTo(((Entry) o).getKey());
            }
            return key.compareTo(o);
        }

        /**
         * fffffffffffffffffff.
         *
         * @param o ffffffffff
         * @return f3  e f skcmkwesk  fsnjzks
         */
        @SuppressWarnings("unchecked")
        @Override
        public boolean equals(Object o) {
            if (o instanceof Entry) {
                return ((Entry) o).key.equals(key) && ((Entry) o).value.equals(value);
            }
            return o.equals(key);
        }
    }

    /**
     * 3rergjgtyrhe4weyrhjre4w.
     *
     * @return gewrb gdbfrwedferwf
     */
    public BST getTree() {
        return bst;
    }

}