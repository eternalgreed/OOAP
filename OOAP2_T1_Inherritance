``` Java
public class Ooap2_T2 {

    public static void main(String[] args) {
        Computer computer = new GamingPC(new IntelProcessor());
        computer.work();
    }
}

abstract class Computer {
    abstract void work();
}

abstract class Processor {
    abstract void start();
}

//наследование
class IntelProcessor extends Processor{

    @Override
    void start() {
        System.out.println("Intel CPU running...");
    }
}

//наследование
class GamingPC extends Computer {

    //композиция
    Processor processor;

    public GamingPC(Processor processor) {
        this.processor = processor;
    }


    @Override
    void work() {
        // А вот тут происходит обращение к сущности в поле,
        // без огладки на то, какой конкретно тип оно имеет (полиморфизм).
        // В зависимости от конкретного типа поля, метод отработает согласно своей реализации
        processor.start();
    }
}

```
