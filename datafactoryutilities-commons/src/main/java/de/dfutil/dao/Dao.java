package de.dfutil.dao;

import java.util.List;
import java.util.Optional;

//FIXME add RepositoryFactory type parameter and providing method, for e.g. redistemplate or jpa entitymanager
public interface Dao <T>{

        Optional<T> get(long id);

        List<T> getAll();

        void save(T t);

        void update(T t, String[] params);

        void delete(T t);



}
