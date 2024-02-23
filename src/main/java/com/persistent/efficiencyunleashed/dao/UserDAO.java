package com.persistent.efficiencyunleashed.dao;

import com.persistent.efficiencyunleashed.model.EmailResponse;
import com.persistent.efficiencyunleashed.model.JiraSearchIssuesResponse;
import com.persistent.efficiencyunleashed.model.MiroGetBoardItemsResponse;
import com.persistent.efficiencyunleashed.model.SlackHistory;

public interface UserDAO {
    MiroGetBoardItemsResponse callMiroBoardItems(String boardId);

    SlackHistory callSlackMessages(String channelId);

    EmailResponse getM365Mails();

    JiraSearchIssuesResponse getAllJiraIssues(String userName);
}
