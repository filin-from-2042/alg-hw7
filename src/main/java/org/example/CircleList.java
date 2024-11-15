package org.example;

public class CircleList<K>{

    private Node<K> first, last, cursor;

    public void add(K value) {
        if(first == null) {
            first = new Node<>(value, null, null);
            first.prev = first;
            first.next = first;
            last = first;
            return;
        }
        Node<K> newNode = new Node<>(value, first, last);
        last.next = newNode;
        last = newNode;
        first.prev = last;
    }

    public K remove(int interval){
        if(cursor == null) {
            cursor = new Node<>(null, first, null);
        }

        for (int step = 0; step < interval; step++){
            cursor = cursor.next;
        }

        cursor.prev.next = cursor.next;
        cursor.next.prev = cursor.prev;

        return cursor.value;
    }

    static class Node<K>{
        private final K value;
        private Node<K> next;
        private Node<K> prev;

        public Node(K value, Node<K> next, Node<K> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}
