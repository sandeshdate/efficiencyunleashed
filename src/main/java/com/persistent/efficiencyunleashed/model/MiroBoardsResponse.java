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
public class MiroBoardsResponse {
    private List<BoardsData> data;
    private int offset;
    private int limit;
    private int total;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardsData {
        private String id;
        private String type;
        private String name;
    }
}
