package nc.apps.propertyholder;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmartAdderPropertyHolder {
    private final String domain;
    private final String port;
    private final String serverContext;

    public SmartAdderPropertyHolder(String domain, String port, String serverContext) {
        this.domain = domain;
        this.port = port;
        this.serverContext = serverContext;
    }
}
