package ar.edu.utn.frc.tup.lc.iv.controllers.construction;

import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionResponseDto;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.ConstructionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/constructions")
public class ConstructionController {
    private final ConstructionService constructionService;

    @PostMapping
    public ConstructionResponseDto createConstruction(ConstructionRequestDto constructionRequestDto) {
        return constructionService.registerConstruction(constructionRequestDto);
    }
}
