package com.github.sejoung.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author kim se joung
 *
 */
@Getter
@Setter
@ToString
public class ClickViewData {
    @JsonProperty("totalLag")
    private Integer totalLag;
    @JsonProperty("percentageCovered")
    private Integer percentageCovered;
    @JsonProperty("partitionOffsets")
    private List<Integer> partitionOffsets = null;
    @JsonProperty("partitionLatestOffsets")
    private List<Integer> partitionLatestOffsets = null;
    @JsonProperty("owners")
    private List<String> owners = null;
}
