package com.danilodps.concurrent.domain.service.impl;

import com.danilodps.concurrent.domain.model.enums.ColorsEnum;
import com.danilodps.concurrent.domain.service.RandomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RandomServiceImpl implements RandomService {

    @Override
    public Integer randomNumber() {
        delay();
        log.info("Obtendo um número aleatório {}", Thread.currentThread());
        return ThreadLocalRandom.current().nextInt(1, 5000) * 50;
    }

    @Override
    public String randomColor(){
        delay();
        log.info("Obtendo uma cor aleatória entre as 7 em uso {}", Thread.currentThread());
        int value = ThreadLocalRandom.current().nextInt(1, 7);
        return switch (value){
            case 1 -> ColorsEnum.RED.getColor();
            case 2 -> ColorsEnum.ORANGE.getColor();
            case 3 -> ColorsEnum.YELLOW.getColor();
            case 4 -> ColorsEnum.GREEN.getColor();
            case 5 -> ColorsEnum.BLUE.getColor();
            case 6 -> ColorsEnum.INDIGO.getColor();
            case 7 -> ColorsEnum.VIOLET.getColor();
            default -> "";
        };
    }

    private void delay() {
        try {
            int milli = ThreadLocalRandom.current().nextInt(200, 2500);
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
