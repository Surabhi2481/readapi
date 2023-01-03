package dev.imsurabhi.readapi.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.imsurabhi.readapi.dtos.CassandraUserDto;
import dev.imsurabhi.readapi.dtos.CassandraUsersResponseDto;
import dev.imsurabhi.readapi.dtos.MongoUserDto;
import dev.imsurabhi.readapi.dtos.MongoUsersResponseDto;
import dev.imsurabhi.readapi.exceptions.ReadApiException;
import dev.imsurabhi.readapi.services.FetchDataService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@PropertySource(value={"classpath:services.properties"})
public class FetchDataServiceImpl implements FetchDataService {

    private static final Logger logger = LoggerFactory.getLogger(FetchDataServiceImpl.class);

    @Value("${service.read.mongo.url}")
    private String serviceReadMongoUrl;

    @Value("${service.read.cassandra.url}")
    private String serviceReadCassandraUrl;

    @Override
    public MongoUsersResponseDto fetchAllUsersFromMongo() {
        MongoUsersResponseDto mongoUsersResponseDto = new MongoUsersResponseDto();
        List<MongoUserDto> mongoUserDtoList = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(serviceReadMongoUrl);
        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
            if(response.getStatusLine().getStatusCode() != HttpStatus.OK.value()) {
                logger.error("Got non HTTP 200 response while call mongo api to fetch al users. statusCode :: {}",
                        response.getStatusLine().getStatusCode());
                return null;
            }
            if(response.getStatusLine().getStatusCode() == 200) {
                logger.error("Got HTTP 200 OK response while call mongo api to fetch al users.");
                HttpEntity httpEntity = response.getEntity();
                if (httpEntity != null) {
                    String responseString = EntityUtils.toString(httpEntity);
                    if(!StringUtils.isEmpty(responseString)) {
                        ObjectMapper mapper = new ObjectMapper();
                        mongoUserDtoList = mapper.readValue(responseString, new TypeReference<List<MongoUserDto>>(){});
                        mongoUsersResponseDto.setMongoUserList(mongoUserDtoList);
                    }
                }

            }
        } catch (Exception e) {
            logger.error("Error occurred while calling mongo service to fetch user data. Exception :: {}", e);
            throw new ReadApiException("Error occurred while calling mongo service to fetch user data.");
        }
        return mongoUsersResponseDto;
    }

    @Override
    public CassandraUsersResponseDto fetchAllUsersFromCassandra() {
        CassandraUsersResponseDto cassandraUsersResponseDto = new CassandraUsersResponseDto();
        List<CassandraUserDto> cassandraUserDtoList = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(serviceReadCassandraUrl);
        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
            if(response.getStatusLine().getStatusCode() != HttpStatus.OK.value()) {
                logger.error("Got non HTTP 200 response while call cassandra api to fetch al users. statusCode :: {}",
                        response.getStatusLine().getStatusCode());
                return null;
            }
            if(response.getStatusLine().getStatusCode() == 200) {
                logger.error("Got HTTP 200 OK response while call cassandra api to fetch al users.");
                HttpEntity httpEntity = response.getEntity();
                if (httpEntity != null) {
                    String responseString = EntityUtils.toString(httpEntity);
                    if(!StringUtils.isEmpty(responseString)) {
                        ObjectMapper mapper = new ObjectMapper();
                        cassandraUserDtoList = mapper.readValue(responseString, new TypeReference<List<CassandraUserDto>>() {});
                        cassandraUsersResponseDto.setCassandraUserList(cassandraUserDtoList);
                    }
                }

            }
        } catch (Exception e) {
            logger.error("Error occurred while calling cassandraUsersResponseDto service to fetch user data. Exception :: {}", e);
            throw new ReadApiException("Error occurred while calling cassandraUsersResponseDto service to fetch user data.");
        }
        return cassandraUsersResponseDto;
    }
}
