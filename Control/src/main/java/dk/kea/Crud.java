package dk.kea;

import java.util.List;

public interface Crud<T> {
    void addObject(T object);
    void deleteById(int id);
    List findById(int id);
    void updateObject (T object);
    List fetchAll();
}
