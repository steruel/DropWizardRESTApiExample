package com.example.helloworld;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class HelloWorldConfiguration extends Configuration {
    @NotEmpty
    private String templateH;

    @NotEmpty
    private String templateG;

    @NotEmpty
    private String defaultName = "Stranger";

    @JsonProperty
    public String getTemplateH() {
        return templateH;
    }

    @JsonProperty
    public void setTemplateH(String template) {
        this.templateH = template;
    }

    @JsonProperty
    public String getTemplateG() { return templateG;  }

    @JsonProperty
    public void setTemplateG(String template) {
        this.templateG = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }
}

