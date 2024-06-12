
package avalon.usuarios.model.DTOs;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import avalon.usuarios.exception.IllegalArgumentExceptionCustom;
import jakarta.validation.constraints.NotNull;

public abstract class NotificationMessageDTO {

    @NotNull(message = "El título no puede estar vacío")
    private String title;

    @NotNull(message = "El mensaje no puede estar vacío")
    private String body;

    private String image;

    @JsonProperty("emitter_usr")
    private String emitterUser;

    private Map<String, String> data;

    public NotificationMessageDTO() {

    }

    public void validate() {
        if (emitterUser == null) {
            throw new IllegalArgumentExceptionCustom("Al menos uno de los emisores debe estar presente",
                    NotificationMessageDTO.class.getName(),
                    true);
        }
    }

    public abstract String getRecipientToken();

    public abstract String getRecipientTopic();

    @Override
    public String toString() {
        return title + " " + body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public String getEmitterUser() {
        return emitterUser;
    }

    public void setEmitterUser(String emitterUser) {
        this.emitterUser = emitterUser;
    }

}
