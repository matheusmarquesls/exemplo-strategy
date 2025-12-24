package school.sptech.exemplo_strategy.controller.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import school.sptech.exemplo_strategy.entity.Frete;
import school.sptech.exemplo_strategy.entity.TipoFreteEnum;

public class FreteResponseDto {


    private Integer id;
    @NotBlank
    @NotNull
    private String tipo;
    private String descricao;
    private Double valorFrete;

    public FreteResponseDto(Integer id, String tipo, Double valorFrete, String descricao) {
        this.id = id;
        this.tipo = tipo;
        this.valorFrete = valorFrete;
        this.descricao = descricao;
    }

    public FreteResponseDto() {
    }

    public static FreteResponseDto freteResponseDto(Frete entity) {
        FreteResponseDto dto = new FreteResponseDto();
        dto.setTipo(entity.getTipo().name());
        dto.setId(entity.getId());
        dto.setDescricao(entity.getTipo().getDescricao());
        dto.setValorFrete(entity.getValorFrete());
        return dto;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank @NotNull String getTipo() {
        return tipo;
    }

    public void setTipo(@NotBlank @NotNull String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }
}
