package com.happydog.model;

public class Log {
    private String userId;
    private String username;
    private String action;
    private String targetType;
    private String targetId;
    private String targetName;
    private String ipAddress;

    // 构造函数
    public Log(String userId, String username, String action, String targetType, String targetId, String targetName, String ipAddress) {
        this.userId = userId;
        this.username = username;
        this.action = action;
        this.targetType = targetType;
        this.targetId = targetId;
        this.targetName = targetName;
        this.ipAddress = ipAddress;
    }

    // Getter 和 Setter 方法
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getTargetType() { return targetType; }
    public void setTargetType(String targetType) { this.targetType = targetType; }

    public String getTargetId() { return targetId; }
    public void setTargetId(String targetId) { this.targetId = targetId; }

    public String getTargetName() { return targetName; }
    public void setTargetName(String targetName) { this.targetName = targetName; }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
}
