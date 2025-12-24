package school.sptech.exemplo_strategy.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.exemplo_strategy.controller.dto.FreteRequestDto;
import school.sptech.exemplo_strategy.controller.dto.FreteResponseDto;
import school.sptech.exemplo_strategy.entity.Frete;
import school.sptech.exemplo_strategy.service.FreteService;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fretes")
public class FreteController {


    private final FreteService freteService;

    public FreteController(FreteService freteService) {
        this.freteService = freteService;
    }

    @PostMapping
    public ResponseEntity<FreteResponseDto> calcularFrete(
            @Valid @RequestBody FreteRequestDto request) {


        Frete frete = FreteRequestDto.toEntity(request);
        Frete freteCadastrado = freteService.registrarCotacao(frete);

        return ResponseEntity.status(201).body(FreteResponseDto.freteResponseDto(freteCadastrado));
    }

    @GetMapping
    public ResponseEntity<List<FreteResponseDto>> listarFretes() {

        List<Frete> fretes = freteService.listarCotacoes();

        if (fretes.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        List<FreteResponseDto> responseDtos = fretes.stream().map(frete -> new FreteResponseDto(
                frete.getId(),
                frete.getTipo().name(),
                frete.getValorFrete(),
                frete.getTipo().getDescricao()
        ))
        .toList();

        return ResponseEntity.status(200).body(responseDtos);
    }
}
