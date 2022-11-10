package ru.yandex.practicum.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/dogs")
public class DogsInteractionController {
    private int happiness = 0;

    @GetMapping("/converse")
    public Map<String, String> converse() {
        happiness += 2;
        return Map.of("talk", "Гав!");
    }

    @GetMapping("/pet")
    public Map<String, String> pet(@RequestParam(required = false) final Integer count) {
        happiness += count;
        return Map.of("action", "Вильнул хвостом. ".repeat(count));
    }

    @GetMapping("/happiness")
    public Map<String, Integer> happiness() {
        return Map.of("happiness", happiness);
    }

/*    @ExceptionHandler
// отлавливаем исключение IllegalArgumentException
    public Map<String, String> handleNegativeCount(final IllegalArgumentException e) {
        // возвращаем сообщение об ошибке
        return Map.of("error", "Передан отрицательный параметр count.");
    }

    @ExceptionHandler
    // отлавливаем исключение NullPointerException
    public Map<String, String> handleNullCount(final NullPointerException e) {
        // возвращаем сообщение об ошибке
        return Map.of("error", "Параметр count не указан.");
    }*/

    @ExceptionHandler
    public Map<String, String> nandleRuntimeException(RuntimeException e) {
        return Map.of("error", "Произошла ошибка!");
    }

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    // отлавливаем исключение NullPointerException
    public Map<String, String> handleIncorrectCount(final RuntimeException e) {
        // возвращаем сообщение об ошибке
        return Map.of(
                "error", "Ошибка с параметром count.",
                "errorMessage", e.getMessage()
        );
    }
}
