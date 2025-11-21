package ua.repository;

import java.util.*;
import java.util.logging.Logger;

public class GenericRepository<T> {

    private static final Logger logger = Logger.getLogger(GenericRepository.class.getName());

    private final Map<String, T> storage = new HashMap<>();
    private final IdentityExtractor<T> extractor;

    public GenericRepository(IdentityExtractor<T> extractor) {
        this.extractor = extractor;
    }

    public void add(T obj) {
        String id = extractor.extract(obj);

        if (storage.containsKey(id)) {
            logger.warning("Дублікат! Об'єкт з identity '" + id + "' вже існує.");
            return;
        }

        storage.put(id, obj);
        logger.info("Додано об'єкт з identity: " + id);
    }

    public void remove(T obj) {
        String id = extractor.extract(obj);
        storage.remove(id);
        logger.info("Видалено об'єкт з identity: " + id);
    }

    public List<T> getAll() {
        return new ArrayList<>(storage.values());
    }

    public T findByIdentity(String id) {
        logger.info("Пошук об'єкта з identity: " + id);
        return storage.get(id);
    }
}
