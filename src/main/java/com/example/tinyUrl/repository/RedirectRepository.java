package com.example.tinyUrl.repository;

import com.example.tinyUrl.entity.Redirect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RedirectRepository extends JpaRepository<Redirect, Long> {
    Optional<Redirect> findByAlias(String alias);

    Boolean existsByAlias(String alias);

    Optional<Redirect> findByUrl(String url);

    Boolean existsByUrl(String url);
}
