package avalon.usuarios.model.request;

import avalon.usuarios.model.pojo.Pregunta;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InteraccionRequest {

	@NotNull
	private Long preguntaId;
	@NotNull
	private Long respuestaId;

}
