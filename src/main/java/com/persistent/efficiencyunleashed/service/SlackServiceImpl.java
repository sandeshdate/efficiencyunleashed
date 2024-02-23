package com.persistent.efficiencyunleashed.service;

import com.persistent.efficiencyunleashed.dao.UserDAO;
import com.persistent.efficiencyunleashed.model.ActivityData;
import com.persistent.efficiencyunleashed.model.SlackHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Service
public class SlackServiceImpl implements SlackService {
    private UserDAO userDAO;

    public SlackServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

//    @Autowired
//    private RestTemplate restTemplate;
//
//    private String accessToken = "xoxb-5906823108935-5934381760129-ZcXX52uVodlktFXtewSTPHJv";
//
//    public ResponseEntity<SlackHistory> getMessagesOfChannesl() {
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setBearerAuth(accessToken);
//            return restTemplate.exchange(RequestEntity.get(new URI("https://slack.com/api/conversations.history?channel=C05SNU85M71&limit=10")).headers(headers).build(), SlackHistory.class);
//        } catch (URISyntaxException exception) {
//            return  null;
//        }
//    }

    @Override
    public ArrayList<ActivityData.Slack> getSlackMessages(String channelId) {
        ArrayList<ActivityData.Slack> slackArrayList = new ArrayList<>();
        SlackHistory slackHistory = userDAO.callSlackMessages(channelId);

        if (!ObjectUtils.isEmpty(slackHistory.getMessages())) {
            slackHistory.getMessages().forEach(message -> {
                if (!ObjectUtils.isEmpty(message)) {
                    ActivityData.Slack slack = new ActivityData.Slack();
                    slack.setMessage(message.getText());
                    slack.setTimestamp(message.getTs());
                    slackArrayList.add(slack);
                }
            });
        }

        return slackArrayList;
    }
}
