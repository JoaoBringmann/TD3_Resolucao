public class Pilha<T> implements Estrutura<T> {
    private Node<T> top;

    public void push(T data) {
        top = new Node<>(data, top);
    }

    public T pop() {
        if (top == null) return null;
        T data = top.data;
        top = top.next;
        return data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
