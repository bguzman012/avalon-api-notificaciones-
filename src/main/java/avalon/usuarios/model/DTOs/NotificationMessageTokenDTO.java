package avalon.usuarios.model.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class NotificationMessageTokenDTO extends NotificationMessageDTO {

    @NotNull(message = "El Token no puede estar vacío")
    private String token;

    public NotificationMessageTokenDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String recipientToken) {
        this.token = recipientToken;
    }

    @Override
    public String getRecipientTopic() {
        return null;

    }

    @Override
    public String getRecipientToken() {
        return getToken();
    }

}
