package dev.imsurabhi.readapi.services.impl;

import dev.imsurabhi.readapi.dtos.CassandraUserDto;
import dev.imsurabhi.readapi.dtos.CassandraUsersResponseDto;
import dev.imsurabhi.readapi.dtos.MongoUserDto;
import dev.imsurabhi.readapi.dtos.MongoUsersResponseDto;
import dev.imsurabhi.readapi.response.MatchDataResponse;
import dev.imsurabhi.readapi.services.FetchDataService;
import dev.imsurabhi.readapi.services.MatchDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MatchDataServiceImpl implements MatchDataService {

    private static final Logger logger = LoggerFactory.getLogger(MatchDataServiceImpl.class);

    @Autowired
    FetchDataService fetchDataService;

    @Override
    public MatchDataResponse matchDataAutomatedFlow() {
        // TODO: fetch data from Mongo.
        MongoUsersResponseDto mongoUsersResponseDto = fetchDataService.fetchAllUsersFromMongo();
        if(Objects.isNull(mongoUsersResponseDto)) {
            logger.error("Got null response from fetchDataService");
            // TODO: handle
        }

        // TODO: fetch data from Cassandra.
        CassandraUsersResponseDto cassandraUsersResponseDto = fetchDataService.fetchAllUsersFromCassandra();
        if(Objects.isNull(cassandraUsersResponseDto)) {
            logger.error("Got null response from fetchDataService");
            // TODO: handle
        }

        // TODO: compare data and return.
        Boolean isSimilar = compareDataFromMongoAndCassandra(mongoUsersResponseDto, cassandraUsersResponseDto);

        MatchDataResponse matchDataResponse = new MatchDataResponse();
        if (isSimilar) {
            matchDataResponse.setStatus(Boolean.TRUE.toString());
            matchDataResponse.setMessage("Data sets are matching.");
        } else {
            matchDataResponse.setStatus(Boolean.FALSE.toString());
            matchDataResponse.setMessage("Data sets are different !");
        }

        return matchDataResponse;
    }

    private Boolean compareDataFromMongoAndCassandra(MongoUsersResponseDto mongoUsersResponseDto,
                                                     CassandraUsersResponseDto cassandraUsersResponseDto) {
        if (mongoUsersResponseDto.getMongoUserList().size() != cassandraUsersResponseDto.getCassandraUserList().size())
            return Boolean.FALSE;
        int userCount = 0;
        for (MongoUserDto mongoUser : mongoUsersResponseDto.getMongoUserList()) {
            Boolean isPresentInCassandraUserList = Boolean.FALSE;
            for (CassandraUserDto cassandraUser : cassandraUsersResponseDto.getCassandraUserList()) {
                boolean nameEquality = cassandraUser.getName().equals(mongoUser.getName());
                boolean cityEquality = cassandraUser.getCity().equals(mongoUser.getCity());
                if (nameEquality && cityEquality) {
                    isPresentInCassandraUserList = Boolean.TRUE;
                    break;
                }
            }
            if (!isPresentInCassandraUserList) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

}
