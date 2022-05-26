package com.demo.reactor;

import com.demo.reactor.model.Persona;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Mono;
import rx.Observable;

@SpringBootApplication
public class ReactorApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ReactorApplication.class);

    public void reactor() {
        //flujo de datos
        Mono.just(new Persona(1, "juan", 22))
                //suscripcion
                .subscribe(p -> log.info("[Reactor] persona" + p));
    }

    public void rxjava2() {
        //flujo de datos
        Observable.just(new Persona(1, "juan", 22))
                //suscripcion
                .subscribe(p -> log.info("[Rxjava2] persona" + p));

    }

    public static void main(String[] args) {
        SpringApplication.run(ReactorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        reactor();
        rxjava2();

    }
}
