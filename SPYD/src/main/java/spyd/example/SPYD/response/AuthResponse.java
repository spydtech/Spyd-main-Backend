package spyd.example.SPYD.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AuthResponse {
    private String jwt;
    private boolean status;
    public AuthResponse() {
        // TODO Auto-generated constructor stub
    }

    public AuthResponse(String jwt, boolean status) {
        super();
        this.jwt = jwt;
        this.status = status;
    }

    public AuthResponse(Object o, boolean b, String s) {
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
