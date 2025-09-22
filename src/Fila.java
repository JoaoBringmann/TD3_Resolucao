public class Fila<T> implements Estrutura<T> {
    private Node<T> front, rear;

    public void push(T data) {
        Node<T> node = new Node<>(data, null);
        if (rear != null) rear.next = node;
        rear = node;
        if (front == null) front = node;
    }

    public T pop() {
        if (front == null) return null;
        T data = front.data;
        front = front.next;
        if (front == null) rear = null;
        return data;
    }

    public boolean isEmpty() {
        return front == null;
    }
}
