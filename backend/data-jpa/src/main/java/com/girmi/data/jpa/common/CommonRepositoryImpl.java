package com.girmi.data.jpa.common;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.Serializable;

public class CommonRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements CommonRepository<T, ID> {

    private EntityManager entityManager;

    public CommonRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public void insert(T vo) throws Exception {
        entityManager.persist(vo);
    }


}
