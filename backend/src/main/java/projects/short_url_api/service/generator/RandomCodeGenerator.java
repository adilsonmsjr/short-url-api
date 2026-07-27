package projects.short_url_api.service.generator;

import org.springframework.stereotype.Component;

@Component
public class RandomCodeGenerator implements CodeGenerator {

    @Override
    public String generate() {
        return ShortCodeGenerator.generate();
    }

}
