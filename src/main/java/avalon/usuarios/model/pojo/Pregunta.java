package avalon.usuarios.model.pojo;

import avalon.usuarios.model.auditing.AuditingData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "preguntas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Pregunta extends AuditingData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "contenido")
    private String contenido;

    @NotNull
    @Column(name = "orden")
    private Long orden;

    @NotNull
    @Column(name = "nivel")
    private Long nivel;

    @JsonIgnore
    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL)
    private List<Interaccion> preguntaInteraccionList;

    @JsonIgnore
    @OneToMany(mappedBy = "respuesta", cascade = CascadeType.ALL)
    private List<Interaccion> respuestaInteraccionList;

}
