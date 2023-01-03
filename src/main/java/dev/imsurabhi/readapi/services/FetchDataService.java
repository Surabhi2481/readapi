package dev.imsurabhi.readapi.services;

import dev.imsurabhi.readapi.dtos.CassandraUsersResponseDto;
import dev.imsurabhi.readapi.dtos.MongoUsersResponseDto;

public interface FetchDataService {

    MongoUsersResponseDto fetchAllUsersFromMongo();

    CassandraUsersResponseDto fetchAllUsersFromCassandra();

}
