package com.persistent.efficiencyunleashed.service;

import com.persistent.efficiencyunleashed.dao.UserDAO;
import com.persistent.efficiencyunleashed.model.ActivityData;
import com.persistent.efficiencyunleashed.model.JiraSearchIssuesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;

@Service
@Slf4j
public class JiraServiceImpl implements JiraService{
    private final UserDAO userDAO;

    public JiraServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public ArrayList<ActivityData.Jira> getJiraIssues(String userName) {
        ArrayList<ActivityData.Jira> jiraArrayList = new ArrayList<>();
        JiraSearchIssuesResponse jiraSearchIssuesResponse = userDAO.getAllJiraIssues(userName);
        if(!ObjectUtils.isEmpty(jiraSearchIssuesResponse)
                && !ObjectUtils.isEmpty(jiraSearchIssuesResponse.getIssues())) {
            jiraSearchIssuesResponse.getIssues().forEach(jiraIssue -> {
                ActivityData.Jira jira = new ActivityData.Jira();
                jira.setIssue_id(jiraIssue.getId());
                jira.setSummary(jiraIssue.getFields().getSummary());
                jira.setTimestamp(jiraIssue.getFields().getCreated());
                jiraArrayList.add(jira);
            });
        }

        return jiraArrayList;
    }
}
