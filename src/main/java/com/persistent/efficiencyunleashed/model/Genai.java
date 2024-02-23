package com.persistent.efficiencyunleashed.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Genai {
    public ActivityData.Slack slack;
    public ActivityData.Miro miro;
    public ActivityData.Jira jira;
    public ActivityData.M365 m365;
}
