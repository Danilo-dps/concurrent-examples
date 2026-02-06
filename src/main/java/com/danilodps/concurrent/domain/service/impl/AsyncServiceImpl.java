package com.danilodps.concurrent.domain.service.impl;

import com.danilodps.concurrent.domain.model.RandomUpdate;
import com.danilodps.concurrent.domain.repository.UserEntityRepository;
import com.danilodps.concurrent.domain.service.AsyncRandomService;
import com.danilodps.concurrent.domain.service.AsyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncServiceImpl implements AsyncService {

    private final AsyncRandomService asyncRandomService;
    private final UserEntityRepository userEntityRepository;

    @Override
    @Async("taskExecutor")
    public void getAsyncValue(String userId) {
        RandomUpdate randomUpdate = RandomUpdate.builder().build();

        CompletableFuture<Integer> integerCompletableFuture = asyncRandomService.getRandomNumberAsync();
        CompletableFuture<String> stringCompletableFuture = asyncRandomService.getRandomColorAsync();

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

}
