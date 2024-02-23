package com.persistent.efficiencyunleashed.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SlackHistory {
    private List<SlackMessage> messages;
}
