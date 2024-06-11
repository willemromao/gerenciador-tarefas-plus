package database;

import entity.Entity;
import entity.Tarefa;
import exception.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class DatabaseTable<T extends Entity> implements DatabaseTableI<T> {
    private int novoId = 1;
    private final Map<Integer, T> data = new HashMap<>();

    @Override
    public void save(T entity) {
        if (entity.getId() == 0) {
            entity.setId(novoId++);
        }
        data.put(entity.getId(), entity);
    }

    @Override
    public T findById(int id) throws EntityNotFoundException {
        T entity = data.get(id);
        if (entity == null) {
            throw new EntityNotFoundException("Entity with ID " + id + " not found.");
        }
        return entity;
    }

    @Override
    public void update(int id, T entity) throws EntityNotFoundException {
        if (!data.containsKey(id)) {
            throw new EntityNotFoundException("Entity with ID " + id + " not found.");
        }
        entity.setId(id);
        data.put(id, entity);
    }

    @Override
    public void delete(int id) throws EntityNotFoundException {
        if (data.remove(id) == null) {
            throw new EntityNotFoundException("Entity with ID " + id + " not found.");
        }
    }

    @Override
    public List<T> findAll(Predicate<Tarefa> filter) {
        return List.of();
    }
}