package org.example;

import lombok.Getter;

import java.util.Random;

public class Treap<K extends Comparable> {

    Node<K> root;

    public void add(K key) {
        root = insert(root, key);
    }

    public void removeByDoorNumber(int doorNumber) {
        K key = findKth(doorNumber);
        root = deleteNode(root, key);
    }

    public K findKth(int k) {
        if (k < 1 || k > getSize(root)) {
            throw new IllegalArgumentException("k is out of bounds");
        }
        return findKth(root, k);
    }

    private K findKth(Treap.Node<K> node, int k) {
        if (node == null)
            return null;

        int leftSize = getSize(node.left);
        if (k == leftSize + 1) {
            return node.key;
        } else if (k <= leftSize) {
            return findKth(node.left, k);
        } else {
            return findKth(node.right, k - leftSize - 1);
        }
    }

    private int getSize(Treap.Node<K> node) {
        return node == null ? 0 : node.size;
    }

    private Node<K> deleteNode(Node<K> cur, K key) {
        if (cur == null)
            return cur;

        if (key.compareTo(cur.key) < 0)
            cur.left = deleteNode(cur.left, key);
        else if (key.compareTo(cur.key) > 0)
            cur.right = deleteNode(cur.right, key);

            // IF KEY IS AT ROOT

            // If left is NULL
        else if (cur.left == null) {
            Node<K> temp = cur.right;
            cur = temp;  // Make right child as root
        }
        // If Right is NULL
        else if (cur.right == null) {
            Node<K> temp = cur.left;
            cur = temp;  // Make left child as root
        }
        // If key is at root and both left and right are not NULL
        else if (cur.left.priority < cur.right.priority) {
            cur = leftRotation(cur);
            cur.left = deleteNode(cur.left, key);
        } else {
            cur = rightRotation(cur);
            cur.right = deleteNode(cur.right, key);
        }

        updateSize(cur);
        return cur;
    }


    private void updateSize(Treap.Node<K> node) {
        if (node != null) {
            node.size = 1 + getSize(node.left) + getSize(node.right);
        }
    }

    private Node<K> insert(Node<K> cur, K key) {
        if (cur == null) {
            return new Node<>(key);
        }
        if (key.compareTo(cur.key) > 0) {
            cur.right = insert(cur.right, key);
            if (cur.right.priority < cur.priority) {
                cur = leftRotation(cur);
            }

        } else {
            cur.left = insert(cur.left, key);
            if (cur.left.priority < cur.priority) {
                cur = rightRotation(cur);
            }

        }

        updateSize(cur);
        return cur;
    }

    /* T1, T2 and T3 are subtrees of the tree rooted with y
  (on left side) or x (on right side)
                y                               x
               / \     Right Rotation          /  \
              x   T3   – – – – – – – >        T1   y
             / \       < - - - - - - -            / \
            T1  T2     Left Rotation            T2  T3 */

    private Node<K> leftRotation(Node<K> x) {
        Node<K> y = x.right;
        Node<K> T2 = y.left;

        y.left = x;
        x.right = T2;

        return y;
    }

    private Node<K> rightRotation(Node<K> y) {
        Node<K> x = y.left;
        Node<K> T2 = x.right;

        x.right = y;
        y.left = T2;

        return x;
    }

    @Getter
    public static class Node<K extends Comparable> {
        static Random RND = new Random();
        K key;
        int priority;
        Node<K> left;
        Node<K> right;
        int size;

        public Node(K key) {
            this(key, RND.nextInt());
        }

        public Node(K key, int priority) {
            this(key, priority, null, null);
        }

        public Node(K key, int priority, Node<K> left, Node<K> right) {
            this.key = key;
            this.priority = priority;
            this.left = left;
            this.right = right;
            this.size = 1;
        }

        @Override
        public String toString() {
            return String.format("(%d,%d)", key, priority);
        }
    }
}
