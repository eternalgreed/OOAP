public abstract class AbstractPowerSet<T> extends AbstractHashTable<T>{
    // ** конструктор **
    // постусловие: создано пустое множество с максимальным количеством элементов size
    protected AbstractPowerSet(int size){}

    // ** запросы **
    // возвращает пересечение текущего множества с множеством anotherSet
    public abstract AbstractPowerSet<T> intersection(AbstractPowerSet<T> anotherSet);

    // возвращает объединение текущего множества и множества anotherSet
    public abstract AbstractPowerSet<T> union(AbstractPowerSet<T> anotherSet);

    // возвращает разницу между текущим множеством и множеством anotherSet
    public abstract AbstractPowerSet<T> difference(AbstractPowerSet<T> anotherSet);

    // проверка, является ли anotherSet подмножеством текущего множества
    public abstract boolean isSubset(AbstractPowerSet<T> anotherSet);
}

class PowerSet<T> extends HashTable<T> {
    PowerSet(int size){
        super(size);
    }

    public PowerSet<T> intersection(PowerSet<T> anotherSet) {
        PowerSet<T> newSet = new PowerSet<>(this.capacity);
        for (T slot : this.slots) {
            if (slot != null && anotherSet.contains(slot))
                newSet.put(slot);
        }
        return newSet;
    }

    public PowerSet<T> union(PowerSet<T> anotherSet) {
        PowerSet<T> newSet = new PowerSet<>(this.capacity + anotherSet.capacity);
        for (T slot : this.slots) {
            if (slot != null && !anotherSet.contains(slot))
                newSet.put(slot);
        }
        for (T slot : anotherSet.slots) {
            if (slot != null && !this.contains(slot))
                newSet.put(slot);
        }
        return newSet;
    }

    public PowerSet<T> difference(PowerSet<T> anotherSet){
        PowerSet<T> newSet = new PowerSet<>(this.capacity);
        for (T slot : this.slots) {
            if (slot != null && !anotherSet.contains(slot))
                newSet.put(slot);
        }
        return newSet;
    }

    public boolean isSubset(PowerSet<T> anotherSet) {
        for (T slot : anotherSet.slots)
            if (slot != null && !this.contains(slot))
                return false;
        return true;
    }
}
