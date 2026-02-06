package com.danilodps.concurrent.domain.service.impl;

import com.danilodps.concurrent.domain.service.AsyncRandomService;
import com.danilodps.concurrent.domain.service.RandomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncRandomServiceImpl implements AsyncRandomService {

    private final RandomService randomService;

    @Override
    @Async("taskExecutor")
    public CompletableFuture<Integer> getRandomNumberAsync() {
        delay();
        return CompletableFuture.completedFuture(randomService.randomNumber());
    }

    @Override
    @Async("taskExecutor")
    public CompletableFuture<String> getRandomColorAsync() {
        delay();
        return CompletableFuture.completedFuture(randomService.randomColor());
    }

    private void delay() {
        try {
            int milli = ThreadLocalRandom.current().nextInt(2000);
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
