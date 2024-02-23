package com.persistent.efficiencyunleashed.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JiraSearchIssuesResponse {
    private List<JiraTaskData> issues;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JiraTaskData {
        private String id;
        private fieldsData fields;

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class fieldsData{
            private String summary;
            private String created;
            private String description;
        }

    }
}
