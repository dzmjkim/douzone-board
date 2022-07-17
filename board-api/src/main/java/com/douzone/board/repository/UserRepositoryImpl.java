package com.douzone.board.repository;

import com.douzone.board.entity.QUser;
import com.douzone.board.entity.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class UserRepositoryImpl extends QuerydslRepositorySupport implements CustomUserRepository{
    public UserRepositoryImpl(Class<?> domainClass) {
        super(User.class);
    }


    @Override
    public String findRefreshTokenByUsername(String username) {

        QUser user = QUser.user;

        return from(user)
            .select(user.refreshToken)
            .where(user.username.eq(username))
            .fetchOne();
    }
}
