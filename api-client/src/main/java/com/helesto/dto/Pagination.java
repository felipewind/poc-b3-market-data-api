package com.helesto.dto;

public class Pagination {
  private Boolean lastPageIndicator = null;

  private String nextPageURL = null;

  public Boolean getLastPageIndicator() {
    return lastPageIndicator;
  }

  public void setLastPageIndicator(Boolean lastPageIndicator) {
    this.lastPageIndicator = lastPageIndicator;
  }

  public String getNextPageURL() {
    return nextPageURL;
  }

  public void setNextPageURL(String nextPageURL) {
    this.nextPageURL = nextPageURL;
  }

}

