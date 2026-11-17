package dev.robertbian.ai;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/assistant")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AssistantResource {
    @Inject AssistantService assistant;

    @POST
    @Path("/chat")
    public ChatResponse chat(@Valid ChatRequest request) {
        return new ChatResponse(request.conversationId(),
                assistant.reply(request.conversationId(), request.message()));
    }

    public record ChatRequest(
            @NotBlank @Size(max = 80) String conversationId,
            @NotBlank @Size(max = 2000) String message) {}

    public record ChatResponse(String conversationId, String answer) {}
}
