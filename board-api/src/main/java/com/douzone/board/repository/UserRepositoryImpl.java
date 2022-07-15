package com.douzone.board.repository;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class UserRepositoryImpl extends QuerydslRepositorySupport {
    public UserRepositoryImpl(Class<?> domainClass) {
        super();
    }
}
