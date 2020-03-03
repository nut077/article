package com.github.nut077.article.entity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.OffsetDateTime;

public class Listener<T extends Common> {

  @PrePersist
  private void prePersist(T e) {
    e.setCreateDate(OffsetDateTime.now());
  }

  @PreUpdate
  private void preUpdated(T e) {
    e.setUpdatedDate(OffsetDateTime.now());
  }
}
