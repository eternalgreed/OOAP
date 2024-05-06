abstract class ParentList<T> {

    // ** конструктор **
    // постусловие: создан новый пустой список
    protected ParentList() {
    }

    // ** команды **
    // предусловие: список не пустой
    // постусловие: курсор находится на первом узле списка
    public abstract void head();

    // предусловие: список не пустой
    // постусловие: курсор находится на последнем узле списка
    public abstract void tail();

    // предусловие: у курсора есть элемент
    // постусловие: курсор перемещён на один узел вправо
    public abstract void right();

    // предусловие: список не пустой
    // постусловие: за текущим узлом добавлен новый узел с заданным значением
    public abstract void put_right(T value);

    // предусловие: список не пустой
    // постусловие: перед текущим узлом добавлен новый узел с заданным значением
    public abstract void put_left(T value);

    // предусловие: список не пустой
    /* постусловие: текущий узел удалён, курсор смещён к правому соседнему узлу, если он есть,
       если нет, то курсор смещён к левому соседнему узлу, если он есть */
    public abstract void remove();

    // постусловие: список очищен от всех узлов
    public abstract void clear();

    // постусловие: новый узел добавлен в хвост списка
    public abstract void add_tail(T value);

    // постусловие: из списка удалены все узлы с заданным значением
    public abstract void remove_all(T value);

    // предусловие: список не пустой
    // постусловие: значение текущего узла заменено на новое
    public abstract void replace(T value);

    // постусловие: курсор находится на узле с требуемым значением, если такой узел был найден
    public abstract void find(T value);

    // ** запросы **
    // предусловие: список не пустой
    public abstract T get();

    public abstract int size();

    public abstract boolean is_head();

    public abstract boolean is_tail();

    public abstract boolean is_value();

    // ** дополнительные запросы **
    public abstract int get_head_status(); // возвращает значение HEAD_*

    public abstract int get_tail_status(); // возвращает значение TAIL_*

    public abstract int get_right_status(); // возвращает значение RIGHT_*

    public abstract int get_put_right_status(); // возвращает значение PUT_RIGHT_*

    public abstract int get_put_left_status(); // возвращает значение PUT_LEFT_*

    public abstract int get_remove_status(); // возвращает значение REMOVE_*

    public abstract int get_replace_status(); // возвращает значение REPLACE_*

    public abstract int get_find_status(); // возвращает значение FIND_*

    public abstract int get_remove_all_status(); // возвращает значение REMOVE_ALL_*

    public abstract int get_get_status(); // возвращает значение GET_*
}

abstract class LinkedList<T> extends ParentList<T> {

    // ** конструктор **
    // постусловие: создан новый пустой список
    protected LinkedList() {
        super();
    }
}

abstract class TwoWayList<T> extends ParentList<T> {
    // ** статусы **
    private final int LEFT_NIL = 0; // left() ещё не вызывалась
    private final int LEFT_OK = 1; // последняя left() отработала нормально
    private final int LEFT_ERR = 2; // связанный список пустой

    // ** конструктор **
    // постусловие: создан новый пустой список
    protected TwoWayList() {
        super();
    }

    // ** команды **
    // предусловие: левее курсора есть элемент
    // постусловие: курсор перемещён на один узел влево
    abstract void left();

    // ** дополнительные запросы **
    abstract int get_left_status(); // возвращает значение LEFT_*
}

class ParentListImpl<T> extends ParentList<T> {
    protected Dummy<T> head = new Dummy<>(null);
    private Dummy<T> tail = new Dummy<>(null);
    protected Node<T> current = null;
    private int size = 0;
    private final int HEAD_NIL = 0; // head() ещё не вызывалась
    private final int HEAD_OK = 1; // последняя head() отработала нормально
    private final int HEAD_ERR = 2; // связанный список пустой

    private final int TAIL_NIL = 0; // tail() ещё не вызывалась
    private final int TAIL_OK = 1; // последняя tail() отработала нормально
    private final int TAIL_ERR = 2; // связанный список пустой

    private final int RIGHT_NIL = 0; // right() ещё не вызывалась
    private final int RIGHT_OK = 1; // последняя right() отработала нормально
    private final int RIGHT_ERR = 2; // связанный список пустой

    private final int PUT_RIGHT_NIL = 0; // put_right() ещё не вызывалась
    private final int PUT_RIGHT_OK = 1; // последняя put_right() отработала нормально
    private final int PUT_RIGHT_ERR = 2; // связанный список пустой

    private final int PUT_LEFT_NIL = 0; // put_left() ещё не вызывалась
    private final int PUT_LEFT_OK = 1; // последняя put_left() отработала нормально
    private final int PUT_LEFT_ERR = 2; // связанный список пустой

    private final int REMOVE_NIL = 0; // remove() ещё не вызывалась
    private final int REMOVE_OK = 1; // последняя remove() отработала нормально
    private final int REMOVE_ERR = 2; // связанный список пустой

    private final int REPLACE_NIL = 0; // replace() ещё не вызывалась
    private final int REPLACE_OK = 1; // последняя replace() отработала нормально
    private final int REPLACE_ERR = 2; // связанный список пустой

    private final int FIND_NIL = 0; // find() ещё не вызывалась
    private final int FIND_OK = 1; // последняя find() отработала нормально
    private final int FIND_ERR = 2; // связанный список пустой

    private final int REMOVE_ALL_NIL = 0; // remove_all() ещё не вызывалась
    private final int REMOVE_ALL_OK = 1; // последняя remove_all() отработала нормально
    private final int REMOVE_ALL_ERR = 2; // связанный список пустой

    private final int GET_NIL = 0; // get() ещё не вызывалась
    private final int GET_OK = 1; // последняя get() отработала нормально
    private final int GET_ERR = 2; // связанный список пустой
    private int head_status = HEAD_NIL;

    private int tail_status = TAIL_NIL;

    private int right_status = RIGHT_NIL;

    private int put_right_status = PUT_RIGHT_NIL;

    private int put_left_status = PUT_LEFT_NIL;

    private int remove_status = REMOVE_NIL;

    private int replace_status = REPLACE_NIL;

    private int find_status = FIND_NIL;

    private int remove_all_status = REMOVE_ALL_NIL;

    private int get_status = GET_NIL;


    public ParentListImpl() {
        super();
        this.head.setNextNode(this.tail);
        this.tail.setPreviousNode(this.head);
    }

    @Override
    public void head() {
        if (current == null){
            head_status = HEAD_ERR;
        } else {
            current = head.nextNode();
            head_status = HEAD_OK;
        }
    }

    @Override
    public void tail() {
        if (current == null){
            tail_status = TAIL_ERR;
        } else {
            current = tail.previousNode();
            tail_status = TAIL_OK;
        }
    }

    @Override
    public void right() {
        if (current == null) {
            right_status = RIGHT_ERR;
        } else {
            current = current == tail.previousNode() ? current : current.nextNode();
            right_status = RIGHT_OK;
        }
    }

    @Override
    public void put_right(T value) {
        if (current == null) {
            put_right_status = PUT_RIGHT_ERR;
        } else {
            Node<T> newNode = new Node<>(value);
            newNode.setNextNode(current.nextNode());
            newNode.setPreviousNode(current);
            current.nextNode().setPreviousNode(newNode);
            current.setNextNode(newNode);
            size++;
            put_right_status = PUT_RIGHT_OK;
        }
    }

    @Override
    public void put_left(T value) {
        if (current == null) {
            put_left_status = PUT_LEFT_ERR;
        } else {
            Node<T> newNode = new Node<>(value);
            newNode.setNextNode(current);
            newNode.setPreviousNode(current.previousNode());
            current.previousNode().setNextNode(newNode);
            current.setPreviousNode(newNode);
            size++;
            put_left_status = PUT_LEFT_OK;
        }
    }

    @Override
    public void remove() {
        if (current == null) {
            remove_status = REMOVE_ERR;
        } else {
            current.previousNode().setNextNode(current.nextNode());
            current.nextNode().setPreviousNode(current.previousNode());
            current = current.nextNode() == tail ?
                    current.previousNode() :
                    current.nextNode();
            size--;
            remove_status = REMOVE_OK;
        }
    }

    @Override
    public void clear() {
        size = 0;
        current = null;
        head.setNextNode(this.tail);
        tail.setPreviousNode(this.head);
    }

    @Override
    public void add_tail(T value) {
        if (current == null) {
            tail_status = TAIL_ERR;
        } else {
            Node<T> newNode = new Node<>(value);
            tail.previousNode().setNextNode(newNode);
            newNode.setPreviousNode(tail.previousNode());
            newNode.setNextNode(tail);
            tail.setPreviousNode(newNode);
            size++;
            tail_status = TAIL_OK;
        }
    }

    @Override
    public void remove_all(T value) {
        if (current == null) {
            remove_all_status = REMOVE_ALL_ERR;
            return;
        }
        Node<T> node = this.head;
        while (node.next != tail){
            node = node.next;
            if (node.value != value){
                node = node.next;
                continue;
            }
            if (node == current) {
                this.remove();
            } else {
                node.previous.next = node.next;
                node.next.previous = node.previous;
                node = node.next;
            }
        }
        remove_all_status = REMOVE_ALL_OK;
    }

    @Override
    public void replace(T value) {
        if (current == null) {
            replace_status = REPLACE_ERR;
        } else {
            current.setValue(value);
            replace_status = REPLACE_OK;
        }
    }

    @Override
    public void find(T value) {
        if (current == null) {
            find_status = FIND_ERR;
            return;
        }
        while (current.next != tail) {
            current = current.next;
            if (current.value == value){
                break;
            }
        }
        find_status = FIND_OK;
    }

    @Override
    public T get() {
        if (current == null) {
            get_status = GET_ERR;
            return null;
        } else {
            get_status = GET_OK;
            return current.value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean is_head() {
        return current == head.nextNode();
    }

    @Override
    public boolean is_tail() {
        return current == tail.previousNode();
    }

    @Override
    public boolean is_value() {
        return current != null;
    }

    @Override
    public int get_head_status() {
        return head_status;
    }

    @Override
    public int get_tail_status() {
        return tail_status;
    }

    @Override
    public int get_right_status() {
        return right_status;
    }

    @Override
    public int get_put_right_status() {
        return put_right_status;
    }

    @Override
    public int get_put_left_status() {
        return put_left_status;
    }

    @Override
    public int get_remove_status() {
        return remove_status;
    }

    @Override
    public int get_replace_status() {
        return replace_status;
    }

    @Override
    public int get_find_status() {
        return find_status;
    }

    @Override
    public int get_remove_all_status() {
        return remove_all_status;
    }

    @Override
    public int get_get_status() {
        return get_status;
    }

    class Node<E> {
        private E value;
        private Node<E> next;
        private Node<E> previous;

        public Node(E value){
            this.value = value;
        }
        public E value(){
            return value;
        }
        public void setValue(E value){
            this.value = value;
        }
        public Node<E> nextNode(){
            return next;
        }

        public Node<E> previousNode(){
            return previous;
        }
        public void setNextNode(Node<E> nextNode){
            this.next = nextNode;
        }

        public void setPreviousNode(Node<E> previousNode){
            this.next = previousNode;
        }
    }

    class Dummy<E> extends Node<E>{
        public Dummy(E value) {
            super(value);
        }
    }
}

class LinkedListImpl<T> extends ParentListImpl<T> {
    public LinkedListImpl(){
        super();
    }
}

class TwoWayListImpl<T> extends ParentListImpl<T> {
    private final int LEFT_NIL = 0;
    private final int LEFT_OK = 1;
    private final int LEFT_ERR = 2;
    private int left_status = LEFT_NIL;

    protected TwoWayListImpl() {
        super();
    }
    public void left() {
        if (current == null) {
            left_status = LEFT_ERR;
        } else {
            current = current == head.nextNode() ? current : current.previousNode();
            left_status = LEFT_OK;
        }
    }

    public int get_left_status() {
        return left_status;
    }
}
