package dev.imsurabhi.readapi.services;

import dev.imsurabhi.readapi.response.MatchDataResponse;
import org.springframework.beans.factory.annotation.Autowired;

public interface MatchDataService {

    MatchDataResponse matchDataAutomatedFlow();

}
