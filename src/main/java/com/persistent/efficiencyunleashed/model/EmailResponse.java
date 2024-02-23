package com.persistent.efficiencyunleashed.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Builder
@ToString
@Data
@NoArgsConstructor(force = true)
public class EmailResponse {
    private final List<EmailListResponse> value;

    @AllArgsConstructor
    @Builder
    @Data
    @ToString
    @NoArgsConstructor
    public static class EmailListResponse {
        private String id;
        private String receivedDateTime;
        private String subject;
        private String bodyPreview;
    }
}
