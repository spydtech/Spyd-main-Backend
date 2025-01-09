package spyd.example.SPYD.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import spyd.example.SPYD.enums.Applied_Position;

@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequest {
    private Long id;
    private String first_name;
    private String last_name;
    private String email_address;
    private String Phone_number;
    private String LinkedIn;
    private String cover_letter;
    private Applied_Position position_applied;
}
