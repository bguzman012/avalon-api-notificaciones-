package avalon.usuarios.model.pojo;

import avalon.usuarios.model.auditing.AuditingData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "notificaciones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Notificacion extends AuditingData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "asunto")
    private String asunto;

    @NotNull
    @Column(name = "mensaje")
    private String mensaje;

    @NotNull
    @Column(name = "usuario_aseguradora_id")
    private String usuarioAseguradorId;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "tipo_notificacion_id")
    private TipoNotificacion tipoNotificacion;

}

