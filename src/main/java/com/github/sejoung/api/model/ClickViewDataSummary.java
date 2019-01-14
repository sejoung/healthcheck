package com.github.sejoung.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kim se joung
 *
 */
@Getter
@Setter
@ToString
public class ClickViewDataSummary {
    @JsonProperty("ClickViewData")
    private ClickViewData clickViewData;
}
