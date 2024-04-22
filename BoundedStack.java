import java.util.LinkedList;
import java.util.List;

public class BoundedStack<T> {
    private List<T> stack; // список, хранящий занчения
    private int status_pop; // статус команды pop()
    private int status_peek; // статус команды peek()
    private int status_push; // статус команды push()
    private int max_size = 32; // значение размера стэка по умолчанию

    public final int POP_NIL = 0; // push() ещё не вызывалась
    public final int POP_OK = 1; // последняя pop() отработала нормально
    public final int POP_ERR = 2; // стек пуст


    public final int PEEK_NIL = 0; // push() ещё не вызывалась
    public final int PEEK_OK = 1; // последняя peek() вернула корректное значение
    public final int PEEK_ERR = 2; // стек пуст

    public final int PUSH_NIL = 0; // push() ещё не вызывалась
    public final int PUSH_OK = 1; // последняя push() отработала нормально
    public final int PUSH_ERR = 2; // в стеке нет свободного места

    // * конструкторы *
    // постусловие: создан новый пустой стек
    public BoundedStack() {
        this.stack = new LinkedList<>();
    }

    public BoundedStack(int size){
        if (size > 0) {
            max_size = size;
        }
        new BoundedStack<T>();
        status_pop = POP_NIL;
        status_peek = PEEK_NIL;
        status_push = PUSH_NIL;
    }


    // * команды *
    // предусловие: в стеке менее максимального кол-ва элементов
    // постусловие: в стек добавлено новое значение
    public void push(T value){
        if (stack.size() < max_size) {
            stack.add(value);
            status_push = PUSH_OK;
        } else {
            status_push = PUSH_ERR;
        }
    }

    // предусловие: стек не пустой
    // постусловие: из стека удалён верхний элемент
    public void pop() {
        if(!stack.isEmpty()) {
            stack.remove(stack.size() - 1);
            status_pop = POP_OK;
        } else {
            status_pop = POP_ERR;
        }
    }

    // постусловие: из стека удалятся все значения
    public void clear() {
        stack.clear();
        status_pop = POP_NIL;
        status_peek = PEEK_NIL;
        status_push = PUSH_NIL;
    }


    // * запросы *
    // предусловие: стек не пустой
    public T peek() {
        T result;
        if (!stack.isEmpty()) {
            result = stack.get(stack.size() - 1);
            status_peek = PEEK_OK;
        } else {
            result = null;
            status_peek = PEEK_ERR;
        }
        return result;
    }

    public int size() {
        return stack.size();
    }
    public int max_size() {
        return this.max_size;
    }

    // дополнительные запросы:
    // возвращает значение POP_*
    public int get_pop_status(){ 
        return status_pop;
    }

    // возвращает значение PEEK_*
    public int get_peek_status() { 
        return status_peek;
    }

    // возвращает значение PUSH_*
    public int get_push_status() { 
        return status_push;
    }
}
