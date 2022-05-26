package com.demo.reactor;

import com.demo.reactor.model.Persona;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ReactorApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ReactorApplication.class);

    public void reactor() {
        //flujo de datos
        Mono.just(new Persona(1, "juan", 22))
                .doOnNext(p -> {
                    //logica adicional
                    log.info("[Reactor] persona" + p);
                })
                //suscripcion
                .subscribe(p -> log.info("[Reactor] persona" + p));
    }

    public void rxjava2() {
        //flujo de datos
        Observable.just(new Persona(1, "juan", 22))
                .doOnNext(p -> log.info("[Rxjava2] persona" + p))
                //suscripcion
                .subscribe(p -> log.info("[Rxjava2] persona" + p));

    }

    public void mono(){
        Mono.just(new Persona(1,"juan",22))
                .subscribe(p-> log.info(p.toString()));

    }

    public void flux(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"juan",20));
        personas.add(new Persona(2,"juan1",21));
        personas.add(new Persona(2,"juan2",22));
        Flux.fromIterable(personas).subscribe(p-> log.info(p.toString()));


    }

    public void monoFlux(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"juan",20));
        personas.add(new Persona(2,"juan1",21));
        personas.add(new Persona(2,"juan2",22));

        Flux<Persona> fx = Flux.fromIterable(personas);
        fx.collectList().subscribe(lista-> log.info(lista.toString()));

    }

    public static void main(String[] args) {
        SpringApplication.run(ReactorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        reactor();
//        rxjava2();
//        mono();
//        flux();
        monoFlux();

    }
}
