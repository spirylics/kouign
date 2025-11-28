package fr.spirylics.kouign.application;

import fr.spirylics.kouign.domain.model.Model;
import fr.spirylics.kouign.domain.model.in.ModelService;
import java.util.SequencedSet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/{version}/models")
@RequiredArgsConstructor
public class ModelsController {

    @Getter
    final ModelService modelService;

    @GetMapping(version = "1.0")
    public SequencedSet<Model> list() {
        return getModelService().find();
    }
}
