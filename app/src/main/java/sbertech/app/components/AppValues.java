package sbertech.app.components;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AppValues {
    @Value("${app.welcomeMessage}")
    private String welcomeMessage;

    @Value("${app.filePath}")
    private String filePath;
}
