public class NodeTester {
   private class Node { private Object element; private Node next;
public Node(Object e) { element = e; }
public Node(Object e, Node n) {
element = e; next = n; } }

Node n = new Node(1); n.next = new Node(2); Node m = new Node(3, n); m.next = new Node(4, n); Node p = m.next; n = n.next; p.next = n; n = null; p = null;
}