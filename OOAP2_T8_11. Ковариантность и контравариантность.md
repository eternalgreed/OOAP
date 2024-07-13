## Пример ковариантности:

У нас будет базовый класс Animal и наследующий его Cat. Пример кода будет демонстрировать ковариантность:

``` Java
class Animal {
}

class Cat extends Animal {
}

// В java List<T> не ковариантны, так как List<Animal> не является подтипом List<Cat>, однако можно добиться этого используя ? extends

List<Dog> dogs = new ArrayList<>();
List<? extends Animal> animals = dogs;

```
## Пример контравариантности:
Будем использовать тот же родительский класс Animal и класс-наследник Cat.
``` Java
class Animal {
// ...
}

class Cat extends Animal {
// ...
}


//Добиваемся контравариантности с помощью конструкции ? super 

List<Animal> animals = new ArrayList<>();
List<? super Dog> dogs = animals;
```
