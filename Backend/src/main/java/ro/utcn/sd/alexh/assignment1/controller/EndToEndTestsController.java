package ro.utcn.sd.alexh.assignment1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.utcn.sd.alexh.assignment1.seed.Seed;

@Profile("e2e")
@RestController
@RequiredArgsConstructor
public class EndToEndTestsController {
    private final Seed seed;

    @RequestMapping("/test/reseed")
    public void reseed() {
        seed.clear();
        seed.run();
    }
}
