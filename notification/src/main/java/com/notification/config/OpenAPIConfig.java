package com.notification.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(title = "Notification API", version = "1.0"),
        servers = @Server(url = "/")
)
public class OpenAPIConfig {
}
