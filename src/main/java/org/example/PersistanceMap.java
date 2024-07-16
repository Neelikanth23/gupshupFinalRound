package org.example;


public class PersistanceMap<K,V> {

    private static final int  INITIAL_SIZE = 1<<4;
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    Entry[] hashTable;


    PersistanceMap(){
        hashTable= new Entry[INITIAL_SIZE];
    }

    PersistanceMap(int capacity) {
        int tableSize = tableSizeFor(capacity);
        hashTable= new Entry[tableSize];
    }

    final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }





    public void put(K key, V value) {

        int hashCode = getHashCode(key);
        Entry node = hashTable[hashCode];

        if(node == null) {

            Entry newNode = new Entry(key, value);
            hashTable[hashCode] = newNode;
        } else {
            Entry previousNode = node;
            while (node != null) {

                if (node.key == key) {
                    node.value = value;
                    return;
                }
                previousNode = node;
                node = node.next;
            }
            Entry newNode = new Entry(key,value);
            previousNode.next = newNode;
        }
    }



    public int getHashCode(K key){
        int h;
        int hCode = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        return hCode%hashTable.length;
    }


    public V get(K key) {

        int hashCode = getHashCode(key);
        Entry node = hashTable[hashCode];

        while(node != null) {
            if(node.key.equals(key)) {
                return (V)node.value;
            }
            node = node.next;
        }
        return null;
    }

    public static void main(String args[]) {
        PersistanceMap<Integer, String> map = new PersistanceMap<>(7);
        map.put(1, "hi");
        map.put(2, "my");
        map.put(3, "name");
        map.put(4, "is");
        map.put(5, "Shrayansh");
        map.put(6, "how");
        map.put(7, "are");
        map.put(8, "you");
        map.put(9, "friends");
        map.put(10, "?");

        String value = map.get(8);
        System.out.println(value);


    }
}

