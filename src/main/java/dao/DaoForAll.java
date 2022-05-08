package dao;

import java.util.List;
import java.util.Set;

public interface DaoForAll<T> {
    T findByID(Long id);

    void create(T t);

    void deleteById(int id);

    void update(int id, T t);

    List<T> findAll();

    Set<T> get(int offset, int perPage, String sort);
}
