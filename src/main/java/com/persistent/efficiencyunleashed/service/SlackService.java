package com.persistent.efficiencyunleashed.service;

import com.persistent.efficiencyunleashed.model.ActivityData;

import java.util.ArrayList;

public interface SlackService {
    ArrayList<ActivityData.Slack> getSlackMessages(String channelId);
}
