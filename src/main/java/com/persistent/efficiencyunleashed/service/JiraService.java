package com.persistent.efficiencyunleashed.service;

import com.persistent.efficiencyunleashed.model.ActivityData;

import java.util.ArrayList;

public interface JiraService {
    ArrayList<ActivityData.Jira> getJiraIssues(String s);
}
