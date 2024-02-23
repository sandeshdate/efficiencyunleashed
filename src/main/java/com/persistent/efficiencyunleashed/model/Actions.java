package com.persistent.efficiencyunleashed.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Actions {
    public Email email;
    public Meeting meeting;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Email{
        public Date timestamp;
        public String subject;
        public String content;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Meeting{
        public Date timestamp;
        @JsonProperty("with")
        public String mywith;
        public String agenda;
        public String location;
    }
}
