abstract class AbstractHashTable<T> {

    // ** конструктор **
    // постусловие: создана пустая хэш-таблица
    protected AbstractHashTable() {
    }

    // ** команды **
    // предусловие: в хэш-таблице имеется свободный слот для value
    // постусловие: в хэш-таблицу добавлено новое значение
    public abstract void put(T value);

    // предусловие: в хэш-таблице имеется значение value
    // постусловие: из хэш-таблицы удалено значение value
    public abstract void remove(T value);

    // ** запросы **
    public abstract boolean contains(T value); // содержится ли значение value в хэш-таблице

    public abstract int size(); // количество элементов в хэш-таблице

    // ** запросы статусов **
    public abstract int get_put_status();
    public abstract int get_remove_status();
}

public class HashTable<T> extends AbstractHashTable<T> {
    private int size = 0;
    private int capacity = 8;
    private LinkedList<T>[] slots;
    private int freeSlots = capacity;
    private final int SUB_CAPACITY = 8;
    private final int PUT_STATUS_NIL = 0; // команда put ещё не вызывалась
    private final int PUT_STATUS_OK = 1; // вставка элемента прошла успешно
    private final int PUT_STATUS_ERR = 2; // лимит коллизий исчерпан
    private final int REMOVE_STATUS_NIL = 0; // команда remove ещё не вызывалась
    private final int REMOVE_STATUS_OK = 1; // удаление элемента прошло успешно
    private final int REMOVE_STATUS_ERR = 2; // хэш-таблица пуста
    private int put_status;
    private int remove_status;

    public HashTable(){
        slots = initialize();
        put_status = PUT_STATUS_NIL;
        remove_status = REMOVE_STATUS_NIL;
    }

    @Override
    public void put(T value) {
        int index = index(value);
        if (slots[index] == null) {
            slots[index] = new LinkedList<>();
            freeSlots--;
        }
        boolean contains = slots[index].contains(value);
        if (contains) {
            put_status = PUT_STATUS_OK;
            return;
        }
        if (slots[index].size() == SUB_CAPACITY)
            put_status = PUT_STATUS_ERR;
        else {
            slots[index].add(value);
            put_status = PUT_STATUS_OK;
        }
        if ((float) freeSlots / capacity <= 0.3) {
            extend();
        }
    }

    @Override
    public void remove(T value) {
        int index = index(value);
        if (slots[index] == null) {
            remove_status = REMOVE_STATUS_ERR;
            return;
        }
        boolean result = slots[index].contains(value);
        if (result) {
            slots[index].remove(value);
            remove_status = REMOVE_STATUS_OK;
        } else
            remove_status = REMOVE_STATUS_ERR;
        if (slots[index].isEmpty())
            freeSlots++;
    }

    @Override
    public boolean contains(T value) {
        int index = index(value);
        if (slots[index] == null) {
            return false;
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int get_put_status() {
        return put_status;
    }

    @Override
    public int get_remove_status() {
        return remove_status;
    }


    private int index(T value) {
        int asNumber = value.toString().chars().sum();
        return asNumber % capacity;
    }

    private LinkedList<T>[] initialize() {
        return new LinkedList[capacity];
    }

    private void extend() {
        capacity *= 2;
        LinkedList<T>[] newSlots = initial();

        for (LinkedList<T> ts : slots) {
            if (ts == null)
                continue;
            for (T slot : ts) {
                int index = index(slot);
                if (newSlots[index] == null)
                    newSlots[index] = new LinkedList<>();
                newSlots[index].add(slot);
            }
        }

        slots = newSlots;
    }
}
