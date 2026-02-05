package com.danilodps.concurrent.domain.service.impl;

import com.danilodps.concurrent.domain.model.RandomUpdate;
import com.danilodps.concurrent.domain.repository.UserEntityRepository;
import com.danilodps.concurrent.domain.service.AsyncService;
import com.danilodps.concurrent.domain.service.RandomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Slf4j
@Scope
@Service
@RequiredArgsConstructor
public class AsyncServiceImpl implements AsyncService {

    private final RandomService randomService;
    private final UserEntityRepository userEntityRepository;

    public CompletableFuture<Integer> getRandomNumberAsync() {
        delay();
        return CompletableFuture.completedFuture(randomService.randomNumber());
    }

    public CompletableFuture<String> getRandomColorAsync() {
        delay();
        return CompletableFuture.completedFuture(randomService.randomColor());
    }

    @Async("taskExecutor")
    public void getAsyncValue(String userId) {
        RandomUpdate randomUpdate = RandomUpdate.builder().build();

        CompletableFuture<Integer> integerCompletableFuture = getRandomNumberAsync();
        CompletableFuture<String> stringCompletableFuture = getRandomColorAsync();

        CompletableFuture.allOf(integerCompletableFuture, stringCompletableFuture)
                .thenRun(() -> {
                    try {
                        randomUpdate.setRandomNumber(integerCompletableFuture.get());
                        randomUpdate.setRandomColor(stringCompletableFuture.get());

                        userEntityRepository.updateColorAndNumber(
                                userId,
                                randomUpdate.getRandomColor(),
                                randomUpdate.getRandomNumber(),
                                LocalDateTime.now());
                    } catch (Exception e) {
                        log.error("Error in async operations", e);
                    }
                });
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
