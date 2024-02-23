package com.persistent.efficiencyunleashed.service;

import com.persistent.efficiencyunleashed.model.ActivityData;
import com.persistent.efficiencyunleashed.model.UserData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserDataServiceImpl implements UserDataService {

    public static final String BOARD_ID = "BOARD_ID";
    public static final String SLACK_CHANNEL_ID = "SLACK_CHANNEL_ID";
    public static final String JIRA_USER_NAME = "JIRA_USER_NAME";
    private final MiroService miroService;
    private final SlackService slackService;
    private final M365Service m365Service;
    private final JiraService jiraService;

    public UserDataServiceImpl(MiroService miroService, SlackService slackService, M365Service m365Service, JiraService jiraService) {
        this.miroService = miroService;
        this.slackService = slackService;
        this.m365Service = m365Service;
        this.jiraService = jiraService;
    }

    @Override
    public List<ActivityData> getMasterActivity() {
        return getActivityData(false);
    }

    private ArrayList<ActivityData> getActivityData(boolean isTaskDataRequire) {
        ArrayList<ActivityData> activityDataList = new ArrayList<>();

        Map<Integer, Map<String, String>> map = getTaskConnectorMap();

        ActivityData activityData = getActivityData(1, "Activity: Go-No-Go Analysis Salesforce", "Open", map.get(1));
        activityData.setDate("22/02/2024");
        if (!isTaskDataRequire) {
            activityData.setTaskData(null);
        }
        activityDataList.add(activityData);

        activityData = getActivityData(2, "Activity: Estimate MS Defender Connector", "Close", map.get(2));
        activityData.setDate("22/02/2024");
        if (!isTaskDataRequire) {
            activityData.setTaskData(null);
        }
        activityDataList.add(activityData);

        activityData = getActivityData(3, "Activity: Random application crash after last deployment", "Open", map.get(3));
        activityData.setDate("22/02/2024");
        if (!isTaskDataRequire) {
            activityData.setTaskData(null);
        }
        activityDataList.add(activityData);

        activityData = getActivityData(4, "Activity: Planning of next deployment", "Open", map.get(4));
        activityData.setDate("22/02/2024");
        if (!isTaskDataRequire) {
            activityData.setTaskData(null);
        }
        activityDataList.add(activityData);
        return activityDataList;
    }

    @Override
    public UserData getUserActivity() {
        UserData userData = new UserData();
        ArrayList<ActivityData> activityDataList = getActivityData(true);
        userData.setData(activityDataList);

        UserData.User user = new UserData.User();
        user.setId("123456789");
        user.setName("John Ab");
        user.setEmail("john.Ab@example.com");
        user.setRole("Architect");
        user.setDepartment("Engineering");
        user.setLocation("New Delhi");
        userData.setUser(user);

        userData.setDate("2024-02-18");
        return userData;
    }

    private static Map<Integer, Map<String, String>> getTaskConnectorMap() {
        Map<Integer, Map<String, String>> map = new HashMap<>();
        Map<String, String> hm = new HashMap<>();
        hm.put(BOARD_ID, "uXjVNd0j2Qk=");
        hm.put(SLACK_CHANNEL_ID, "C06L43NS735");
        hm.put(JIRA_USER_NAME, "radhika.edlabadkar@gmail.com");
        map.put(1, hm);

        hm = new HashMap<>();
        hm.put(BOARD_ID, "uXjVNpG9d2Q=");
        hm.put(SLACK_CHANNEL_ID, "C06KPNC4HL7");
        hm.put(JIRA_USER_NAME, "radhika.edlabadkar@gmail.com");
        map.put(2, hm);

        hm = new HashMap<>();
        hm.put(BOARD_ID, "uXjVNpFHLDc=");
        hm.put(SLACK_CHANNEL_ID, "C06LBTA1F3N");
        hm.put(JIRA_USER_NAME, "radhika.edlabadkar@gmail.com");
        map.put(3, hm);

        hm = new HashMap<>();
        hm.put(BOARD_ID, "uXjVNpFeK-s=");
        hm.put(SLACK_CHANNEL_ID, "C06L6G5PG4V");
        hm.put(JIRA_USER_NAME, "radhika.edlabadkar@gmail.com");
        map.put(4, hm);

        return map;
    }

    private ActivityData getActivityData(int taskId, String task, String status, Map<String, String> hm) {
        ActivityData activityData = new ActivityData();
        activityData.setTaskNo(taskId);
        activityData.setTask(task);
        activityData.setStatus(status);

        ActivityData.TaskData taskData = new ActivityData.TaskData();

        //Miro
        ArrayList<ActivityData.Miro> miro = miroService.getMiroMessages(hm.get(BOARD_ID));
        log.info("miro responses: {}", miro);
        taskData.setMiro(miro);

        //Slack
        ArrayList<ActivityData.Slack> slackList = slackService.getSlackMessages(hm.get(SLACK_CHANNEL_ID));
        taskData.setSlack(slackList);

        //M365
        ArrayList<ActivityData.M365> m365ArrayList = m365Service.getM365Mails();
        taskData.setM365(m365ArrayList);

        //Jira
        ArrayList<ActivityData.Jira> jiraArrayList = jiraService.getJiraIssues(hm.get(JIRA_USER_NAME));
        activityData.setTaskData(taskData);
        log.info("activityData: {}", activityData);
        return activityData;
    }
}
