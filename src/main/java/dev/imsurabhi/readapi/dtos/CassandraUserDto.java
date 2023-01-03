package dev.imsurabhi.readapi.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CassandraUserDto {

    private String name;
    private String city;
//    private String phoneNumber;

}
