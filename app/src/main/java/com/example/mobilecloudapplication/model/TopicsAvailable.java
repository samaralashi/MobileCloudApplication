package com.example.mobilecloudapplication.model;

public class TopicsAvailable {
    String topicId;
    String topicName;
    private boolean isChecked;


    public TopicsAvailable(String topicId, String topicName, boolean isChecked) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.isChecked = isChecked;

    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

}
