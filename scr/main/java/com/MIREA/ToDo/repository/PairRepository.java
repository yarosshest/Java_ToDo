package com.MIREA.ToDo.repository;

import com.MIREA.ToDo.entity.Pair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PairRepository extends JpaRepository<Pair, Long> {
    Optional<Pair> findAllByStudygr(String studygr);
}
