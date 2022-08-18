package nc.apps.propertyholder;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmartAdderPropertyHolder {
    private final String domain;
    private final String port;

    public SmartAdderPropertyHolder(String domain, String port) {
        this.domain = domain;
        this.port = port;
    }
}
