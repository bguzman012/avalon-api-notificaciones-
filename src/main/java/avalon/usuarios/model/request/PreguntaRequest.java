package avalon.usuarios.model.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PreguntaRequest {

	@NotNull
	private String contenido;
	@NotNull
	private Long nivel;
	private String respuesta;
	private Long padreId;

}
