package avalon.usuarios.model.DTOs;

import jakarta.validation.constraints.NotNull;

public class NotificationMessageTopicDTO extends NotificationMessageDTO {

    @NotNull(message = "Los topics no puede estar vacío")
    private String topic;

    public String getTopic() {
        return topic;
    }

    public NotificationMessageTopicDTO() {
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String getRecipientToken() {

        return null;
    }

    @Override
    public String getRecipientTopic() {
        return getTopic();
    }

}
