``` Java
public class General implements Serializable, Cloneable {
    //глубокое копирование объекта
    public <T> void deepCopy(T other) {
        try {
            other = this.clone()
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //клонирование объекта
    public General clone() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (General) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //сравнение объектов
    protected boolean equals(General other) {
        return super.equals(other);
    }

    //сериализация
    public byte[] serialize() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);
        oos.flush();
        return baos.toByteArray();
    }

    //десериализация
    public General deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (General) ois.readObject();
    }

    //печать 
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                sb.append(field.getName()).append(": ").append(field.get(this)).append("\n");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();

    }

    //проверка типа
    protected boolean isTypeOf(Class<?> someClass) {
        return this.getClass().equals(someClass) || someClass.isInstance(this);
    }

    //получение реального типа объекта
    protected Class<?> getType() {
        return this.getClass();
    }
}

class Any extends General {

}
```
