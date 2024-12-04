package com.webmovie.daos;

import java.util.List;

abstract public interface DAOs <EntityType,KeyType>{
    abstract List<EntityType> findAll();
    abstract EntityType findById(KeyType id);
    abstract EntityType create(EntityType item);
    abstract EntityType update(EntityType item);
    abstract EntityType deleteById(KeyType id);
}
