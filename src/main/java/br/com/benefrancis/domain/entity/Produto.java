package br.com.benefrancis.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUTO")
    @SequenceGenerator(name = "SQ_PRODUTO", sequenceName = "SQ_PRODUTO", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_PRODUTO")
    private Long id;
    private String nome;
    private BigDecimal preco;

    //Gang dos Manys: Nunca andam só. Por este motivo eu tenho que colocar uma outra anaotação
    //Join....
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "SABOR",
            referencedColumnName = "ID_SABOR"
            , foreignKey = @ForeignKey(name = "FK_PRODUTO_SABOR")
    )
    private Sabor sabor;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST}) //TRAS TUDO DA TABELA
    @JoinTable(name = "TB_OPCIONAL_PRODUTO",
    joinColumns = {
            @JoinColumn(name = "PRODUTO", referencedColumnName = "ID_PRODUTO", foreignKey = @ForeignKey(name = "FK_PRODUTO_OPCIONAL")
            )
    },
    inverseJoinColumns = {
            @JoinColumn(name = "OPCIONAL", referencedColumnName = "ID_OPCIONAL", foreignKey = @ForeignKey(name = "FK_OPCIONAL_PRODUTO")) }
    )
    private Set<Opcional> opcionais = new LinkedHashSet<>();


}