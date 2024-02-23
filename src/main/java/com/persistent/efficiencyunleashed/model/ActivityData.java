package com.persistent.efficiencyunleashed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityData {
    public int taskNo;
    public String task;
    public String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String date;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public TaskData taskData;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TaskData{
        public ArrayList<Slack> slack;
        public ArrayList<Miro> miro;
        public ArrayList<Jira> jira;
        public ArrayList<M365> m365;
        public Actions actions;
        public Genai genai;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Miro{
        public String timestamp;
        public String activity;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        public String summary;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Slack{
        public String timestamp;
        public String message;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        public String summary;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Jira{
        public String timestamp;
        public String issue_id;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        public String summary;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class M365{
        public String timestamp;
        public String event;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        public String summary;
    }

}
