package com.helesto.dto;

import javax.json.bind.annotation.JsonbProperty;

public class QuantityOrdersRsp {

  @JsonbProperty("Value")
  private Integer value = null;

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

}

