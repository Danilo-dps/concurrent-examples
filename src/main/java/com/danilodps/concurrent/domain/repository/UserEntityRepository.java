package com.danilodps.concurrent.domain.repository;

import com.danilodps.concurrent.domain.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, String> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = """
                UPDATE USER_ENTITY
                SET RANDOM_COLOR = :randomColor,
                    RANDOM_NUMBER = :randomNumber,
                    UPDATED_AT = :updatedAt
                WHERE USER_ID = :userId
                """)
    int updateColorAndNumber(
            @Param("userId") String userId,
            @Param("randomColor") String randomColor,
            @Param("randomNumber") Integer randomNumber,
            @Param("updatedAt") LocalDateTime updatedAt
    );
}
