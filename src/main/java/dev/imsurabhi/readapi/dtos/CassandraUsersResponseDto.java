package dev.imsurabhi.readapi.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CassandraUsersResponseDto {

    private List<CassandraUserDto> cassandraUserList;

}
