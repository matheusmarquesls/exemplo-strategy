package school.sptech.exemplo_strategy.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import school.sptech.exemplo_strategy.controller.dto.FreteRequestDto;
import school.sptech.exemplo_strategy.entity.Frete;
import school.sptech.exemplo_strategy.exception.FreteInvalidoException;
import school.sptech.exemplo_strategy.repository.FreteRepository;

import java.util.List;

@Service
public class FreteService {

    private final FreteRepository freteRepository;

    public FreteService(FreteRepository freteRepository) {
        this.freteRepository = freteRepository;
    }


    public List<Frete> listarCotacoes() {
        return freteRepository.findAll();
    }

    public Frete registrarCotacao(Frete frete){

        if (frete.getTipo() == null){
            throw new FreteInvalidoException("frete invalido");
        }
        return freteRepository.save(frete);
    }
}
