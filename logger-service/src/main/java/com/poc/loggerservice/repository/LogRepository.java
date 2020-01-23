package com.poc.loggerservice.repository;

import com.poc.loggerservice.domain.Log;
import com.poc.loggerservice.domain.QLog;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

public interface LogRepository extends MongoRepository<Log, String>, QuerydslPredicateExecutor<Log>, QuerydslBinderCustomizer<QLog> {

    @Override
    default void customize(QuerydslBindings querydslBindings, QLog log) {
        querydslBindings.bind(log.id).first(StringExpression::eq);
        querydslBindings.bind(log.methodName).first(StringExpression::containsIgnoreCase);
        querydslBindings.bind(log.instanceId).first(StringExpression::containsIgnoreCase);
        querydslBindings.bind(String.class).first(
                (SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }
}
