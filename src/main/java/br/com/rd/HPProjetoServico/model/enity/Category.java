package br.com.rd.HPProjetoServico.model.enity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity(name = "CATEGORIA")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_categoria;
    //    @NotNull(message = "Brand field is empty")
    @Column(nullable = false)
    private String description_category;
    //    @NotNull(message = "Model field is empty")

}
