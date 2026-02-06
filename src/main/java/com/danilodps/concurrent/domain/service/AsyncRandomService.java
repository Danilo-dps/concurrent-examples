package com.danilodps.concurrent.domain.service;

import java.util.concurrent.CompletableFuture;

public interface AsyncRandomService {
    CompletableFuture<Integer> getRandomNumberAsync();
    CompletableFuture<String> getRandomColorAsync();
}
