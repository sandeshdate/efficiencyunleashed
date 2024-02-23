package com.persistent.efficiencyunleashed.repository;

import com.persistent.efficiencyunleashed.model.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TokenRepository extends MongoRepository<Token, String> {
    Optional<Token> findByKey(String key);
}
