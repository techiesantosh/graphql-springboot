package com.boot.graphql.controller;

import com.boot.graphql.model.Author;
import com.boot.graphql.model.Book;
import com.boot.graphql.model.StockPrice;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

@Controller
public class BookController {
    @QueryMapping
    public Book bookById(@Argument String id) {
        return Book.getById(id);
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.getAuthorId());
    }

    @MutationMapping
    public Author addAuthor(@Argument String firstname, @Argument String lastname ){
        return  Author.addAuthor(firstname,lastname);
    }

    @SubscriptionMapping
    public Flux<StockPrice> stockPrice() {
        Random random = new Random();
        return Flux.interval(Duration.ofSeconds(1))
                .map(num -> new StockPrice("symbol", random.nextDouble(), LocalDateTime.now()));

//        return Mono.delay(Duration.ofMillis(50))
//                .flatMapMany(aLong -> Flux.just(new StockPrice(symbol, random.nextDouble(), LocalDateTime.now())));

//     return  Flux.fromStream(Stream.generate(()->
//             new StockPrice("y", random.nextDouble(), LocalDateTime.now())))
//             .delayElements(Duration.ofSeconds(1))
//             .take(10);
    }
}