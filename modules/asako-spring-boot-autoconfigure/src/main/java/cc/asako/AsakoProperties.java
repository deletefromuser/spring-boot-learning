package cc.asako;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("asako")
public class AsakoProperties {
    private String greeting;
    private String name;
    private int id;

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
