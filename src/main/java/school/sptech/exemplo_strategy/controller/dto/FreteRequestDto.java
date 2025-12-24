package school.sptech.exemplo_strategy.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import school.sptech.exemplo_strategy.entity.Frete;
import school.sptech.exemplo_strategy.entity.TipoFreteEnum;

public class FreteRequestDto {
    @NotNull
    @NotBlank
    private String tipo;

    @NotNull
    @DecimalMin(value = "10.1")
    private Double valorEncomenda;

    @NotNull
    @DecimalMin(value = "1.1")
    private Double pesoEmKg;

    public static Frete toEntity(FreteRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Frete frete = new Frete();

        try {
            frete.setTipo(TipoFreteEnum.valueOf(dto.getTipo().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de frete inválido: " + dto.getTipo());
        }


        frete.setValorEncomenda(dto.getValorEncomenda());
        frete.setPesoEmKg(dto.getPesoEmKg());


        double valorBase = 0.0;
        double taxaPeso = 0.0;
        double total = 0.0;

        if (frete.getTipo() == TipoFreteEnum.NORMAL){
            valorBase = 10.0;
            taxaPeso = 0.1;

            total = valorBase + (frete.getPesoEmKg()) * taxaPeso;

        }
        if (frete.getTipo() == TipoFreteEnum.EXPRESSO){
            valorBase = 30.0;
            taxaPeso = 0.50;

            total = valorBase + (frete.getPesoEmKg() * taxaPeso);

        }
        if (frete.getTipo() == TipoFreteEnum.TRANSPORTADORA){
            valorBase = 20.0;
            taxaPeso = 0.20;

            total = valorBase + (frete.getPesoEmKg()) * taxaPeso;

        }



        frete.setValorFrete(total);
        return frete;

    }

    public @NotNull @NotBlank String getTipo() {
        return tipo;
    }

    public void setTipo(@NotNull @NotBlank String tipo) {
        this.tipo = tipo;
    }

    public @NotNull @DecimalMin(value = "10.1") Double getValorEncomenda() {
        return valorEncomenda;
    }

    public void setValorEncomenda(@NotNull @DecimalMin(value = "10.1") Double valorEncomenda) {
        this.valorEncomenda = valorEncomenda;
    }

    public @NotNull @DecimalMin(value = "1.1") Double getPesoEmKg() {
        return pesoEmKg;
    }

    public void setPesoEmKg(@NotNull @DecimalMin(value = "1.1") Double pesoEmKg) {
        this.pesoEmKg = pesoEmKg;
    }
}
