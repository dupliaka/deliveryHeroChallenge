package entity;

import lombok.Builder;

import java.util.ArrayList;
@Builder
public class Task {
  String content;
  Integer project_id;
  Integer order;
  ArrayList<Integer> label_ids;
  Integer priority;
  String due_string;
  String due_date;
  String due_datetime;
  String due_lang;
}
