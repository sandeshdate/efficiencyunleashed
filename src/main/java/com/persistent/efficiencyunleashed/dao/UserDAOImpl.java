package com.persistent.efficiencyunleashed.dao;

import com.persistent.efficiencyunleashed.model.EmailResponse;
import com.persistent.efficiencyunleashed.model.JiraSearchIssuesResponse;
import com.persistent.efficiencyunleashed.model.MiroGetBoardItemsResponse;
import com.persistent.efficiencyunleashed.model.SlackHistory;
import com.persistent.efficiencyunleashed.model.Token;
import com.persistent.efficiencyunleashed.repository.TokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Optional;

@Repository
@Slf4j
public class UserDAOImpl implements UserDAO {

    public static final String MIRO_ACCESS_TOKEN = "MIRO_ACCESS_TOKEN";
    public static final String MIRO_BOARD_ITEMS_URL = "https://api.miro.com/v2/boards/%s/items?type=sticky_note&limit=10";
    public static final String SLACK_ACCESS_TOKEN = "SLACK_ACCESS_TOKEN";
    public static final String SLACK_MESSAGE_URL = "https://slack.com/api/conversations.history?channel=%s&limit=10";
    public static final String M365_ACCESS_TOKEN = "M365_ACCESS_TOKEN";
    public static final String M365_MAILS_URL = "https://graph.microsoft.com/v1.0/me/messages";
    public static final String JIRA_ACCESS_TOKEN = "JIRA_ACCESS_TOKEN";
    private final RestTemplate restTemplate;
    private final TokenRepository tokenRepository;

    public UserDAOImpl(RestTemplate restTemplate, TokenRepository tokenRepository) {
        this.restTemplate = restTemplate;
        this.tokenRepository = tokenRepository;
    }

    private void setAuthToken(HttpHeaders headers, String tokenKey) {
        Optional<Token> token = tokenRepository.findByKey(tokenKey);
        log.info("token: {}", token.get().getValue());
        headers.setBearerAuth(token.get().getValue());
    }

    @Override
    public MiroGetBoardItemsResponse callMiroBoardItems(String boardId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        setAuthToken(headers, MIRO_ACCESS_TOKEN);
        URI uri = UriComponentsBuilder.fromHttpUrl(String.format(MIRO_BOARD_ITEMS_URL, boardId)).encode().build().toUri();

        try {
            HttpEntity<MiroGetBoardItemsResponse> miroGetBoardItemsResponse = restTemplate.exchange(
                    new RequestEntity<>(headers, HttpMethod.GET, uri), MiroGetBoardItemsResponse.class);
            log.info("miroGetBoardItemsResponse:: {} ", miroGetBoardItemsResponse);
            return miroGetBoardItemsResponse.getBody();
        } catch (Exception e) {
            log.error("Miro Exception: {} {}", e, e.getMessage());
        }
        return null;
    }

    @Override
    public SlackHistory callSlackMessages(String channelId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        setAuthToken(headers, SLACK_ACCESS_TOKEN);
        URI uri = UriComponentsBuilder.fromHttpUrl(String.format(SLACK_MESSAGE_URL, channelId)).encode().build().toUri();
        try {
            HttpEntity<SlackHistory> slackHistoryResponseEntity = restTemplate.exchange(
                    new RequestEntity<>(headers, HttpMethod.GET, uri), SlackHistory.class);
            log.info("slackHistoryResponseEntity:: {} ", slackHistoryResponseEntity);
            return slackHistoryResponseEntity.getBody();
        } catch (Exception e) {
            log.error("Slack Exception: {} {}", e, e.getMessage());
        }
        return null;
    }

    @Override
    public EmailResponse getM365Mails() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        setAuthToken(headers, M365_ACCESS_TOKEN);
        URI uri = UriComponentsBuilder.fromHttpUrl(M365_MAILS_URL).encode().build().toUri();
        try {
            HttpEntity<EmailResponse> emailResponseResponseEntity = restTemplate.exchange(
                    new RequestEntity<>(headers, HttpMethod.GET, uri), EmailResponse.class);
            log.info("emailResponseResponseEntity:: {} ", emailResponseResponseEntity);
            return emailResponseResponseEntity.getBody();
        } catch (Exception e) {
            log.error("M365 Exception: {} {}", e, e.getMessage());
        }
        return null;
    }

    @Override
    public JiraSearchIssuesResponse getAllJiraIssues(String userName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        setBasicAuthToken(headers, userName, JIRA_ACCESS_TOKEN);

        URI uri = UriComponentsBuilder.fromHttpUrl("https://radhika-edlabadkar.atlassian.net/rest/api/2/search").encode().build().toUri();
        try {
            HttpEntity<JiraSearchIssuesResponse> issuesResponseResponseEntity = restTemplate.exchange(
                    new RequestEntity<>(headers, HttpMethod.GET, uri), JiraSearchIssuesResponse.class);
            log.info("issuesResponseResponseEntity:: {} ", issuesResponseResponseEntity);
            return issuesResponseResponseEntity.getBody();
        } catch (Exception e) {
            log.error("Jira Exception: {} {}", e, e.getMessage());
        }
        return null;
    }

    private void setBasicAuthToken(HttpHeaders headers, String userName, String tokenKey) {
        Optional<Token> token = tokenRepository.findByKey(tokenKey);
        log.info("token: {}", token.get().getValue());
        headers.setBasicAuth(userName, token.get().getValue());
    }
}
