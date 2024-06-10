package avalon.usuarios.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateNotificacionRequest {

	@NotNull
	private String asunto;
	@NotNull
	private String mensaje;
	@NotNull
	private String usuarioAseguradorId;
	@NotNull
	private Long tipoNotificacionId;

}
