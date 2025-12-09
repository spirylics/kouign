package fr.spirylics.kouign.application;

import fr.spirylics.kouign.domain.model.Model;
import fr.spirylics.kouign.domain.model.in.ModelService;
import java.time.Instant;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/{version}/models")
@RequiredArgsConstructor
public class ModelsController {

    @Getter
    final ModelService modelService;

    @GetMapping(version = "1")
    public List<Model> allV1()
    {
        return getModelService().find().toList();
    }

    @GetMapping(version = "2+")
    public List<Model> allV2()
    {
        return List.of(new Model("kerV2", "kerV2", Instant.now(), "kouign", "?"));
    }
}
