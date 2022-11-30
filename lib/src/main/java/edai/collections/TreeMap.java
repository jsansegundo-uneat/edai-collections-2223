package edai.collections;

public class TreeMap<TKey extends Comparable<TKey>, TValue> {
    private final BinaryTree<MapEntry<TKey, TValue>> tree = new BinaryTree<>();

    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public int size() {
        return tree.size();
    }

    @SuppressWarnings("unchecked")
    public Object[] values() {
        Object[] entries = tree.listData(); // MapEntry<TKey, TValue>[]
        Object[] output = new Object[entries.length];

        for (int i = 0; i < entries.length; ++i) {
            MapEntry<TKey, TValue> entry = (MapEntry<TKey, TValue>) entries[i];
            output[i] = entry.getValue();
        }

        return output;
    }

    @SuppressWarnings("unchecked")
    public Object[] keys() {
        Object[] entries = tree.listData(); // MapEntry<TKey, TValue>[]
        Object[] output = new Object[entries.length];

        for (int i = 0; i < entries.length; ++i) {
            MapEntry<TKey, TValue> entry = (MapEntry<TKey, TValue>) entries[i];
            output[i] = entry.getKey();
        }

        return output;
    }

    public void put(TKey key, TValue value) {
        MapEntry<TKey, TValue> newEntry = new MapEntry<>(key, value);
        tree.insert(newEntry);
    }

    public boolean contains(TKey key) {
        return tree.search(createEntryForSearch(key)) != null;
    }

    private MapEntry<TKey, TValue> createEntryForSearch(TKey key) {
        return new MapEntry<TKey, TValue>(key, null);
    }

    public TValue get(TKey key) {
        var treeNode = tree.search(createEntryForSearch(key));
        if (treeNode == null) {
            throw new KeyNotFoundException();
        }

        MapEntry<TKey, TValue> entry = treeNode.getData();
        return entry.getValue();
    }

    public boolean remove(TKey key) {
        if (!contains(key)) return false;

        tree.remove(createEntryForSearch(key));
        return true;
    }
}

class MapEntry<TKey extends Comparable<TKey>, TValue>
        implements Comparable<MapEntry<TKey, TValue>> {

    private final TKey key;
    private final TValue value;

    public MapEntry(TKey key, TValue value) {
        this.key = key;
        this.value = value;
    }

    public TKey getKey() {
        return key;
    }

    public TValue getValue() {
        return value;
    }

    @Override
    public int compareTo(MapEntry<TKey, TValue> other) {
        return this.key.compareTo(other.key);
    }
}