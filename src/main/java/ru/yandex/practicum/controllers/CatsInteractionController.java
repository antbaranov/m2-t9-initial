package ru.yandex.practicum.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cats")
public class CatsInteractionController {
    private int happiness = 0;

    @GetMapping("/converse")
    public Map<String, String> converse() {
        happiness++;
        return Map.of("talk", "Мяу");
    }

    @GetMapping("/pet")
    public Map<String, String> pet(@RequestParam(required = false) final Integer count) {
        if (count == null) {
            throw new IncorrectCountException("Параметр count равен null.");
        }
        if (count <= 0) {
            throw new IncorrectCountException("Параметр count имеет отрицательное значение.");
        }

        happiness += count;
        return Map.of("talk", "Муррр. ".repeat(count));
    }

    @GetMapping("/happiness")
    public Map<String, Integer> happiness() {
        return Map.of("happiness", happiness);
    }

  /*  @ExceptionHandler
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

   /* @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    // отлавливаем исключение NullPointerException
    public Map<String, String> handleIncorrectCount(final RuntimeException e) {
        // возвращаем сообщение об ошибке
        return Map.of(
                "error", "Ошибка с параметром count.",
                "errorMessage", e.getMessage()
        );
    }
*/
    @ExceptionHandler
    public Map<String, String> handle(final IncorrectCountException e) {
        return Map.of(
                "error", "Ошибка с параметром count.",
                "errorMessage", e.getMessage()
        );
    }
}