package com.example.video.constant;

/**
 * @author Japin
 */
public enum ResultCodeEnum implements ExceptionCode {
    /**
     * 一级宏观消息
     */
    SUCCESS(200, "操作成功"),
    ERROR("操作失败"),
    NOT_AUTH("无权操作"),
    INTERNAL_SERVER_ERROR(401, "服务器内部错误"),

    /**
     * 二级宏观消息
     */
    INSERT_ERROR("添加失败"),
    UPDATE_ERROR("修改失败"),
    DELETE_ERROR("删除失败"),
    QUERY_ERROR("查询为空"),


    /**
     * 三级宏观消息：账号
     */
    UNAUTHORIZED("未登录或登录信息过期"),
    TOKEN_TTL("token已失效,请重新登录"),
    ACCOUNT_EXISTS("账号已存在"),
    ACCOUNT_NOT_EXIST("账号不存在"),
    ACCOUNT_LOCK("账号已被锁定"),
    ACCOUNT_LOGOUT("账号退出成功"),
    PASSWORD_NOT_CORRECT("密码不正确"),
    PASSWORD_NOT_SECURITY("密码长度在 6 到 15 个字符"),
    ACCOUNT_NAME_EXISTS("账号名已存在"),
    ACCOUNT_PHONE_EXISTS("手机号码已存在"),
    ACCOUNT_EMAIL_EXISTS("邮箱已存在"),
    ROLE_LOCK("您的角色已被锁定"),
    USER_NAME_SENSITIVE("你的用户名包含敏感词"),
    EMAIL_CODE_NOT_EXISTS("邮箱验证码不存在或者已过期"),
    EMAIL_CODE_ERROR("邮箱验证码错误"),


    /**
     * 三级宏观消息：文件异常
     */
    FILE_SENSITIVE("文件名含有敏感词，拒绝上传"),
    FILE_STORAGE_STRATEGY_ERROR("该文件存储方式不存在"),
    FILE_DOWNLOAD_STRATEGY_ERROR("该文件下载方式不存在"),
    FILE_UPLOAD_ERROR("文件上传失败"),
    FILE_DOWNLOAD_ERROR("文件下载失败"),
    FILE_NOT_EXISTS("文件记录不存在"),
    FILE_DIR_MKDIR_ERROR("文件夹创建失败"),
    FILE_STATUS_ERROR("文件状态并未改变"),
    FILE_USER_LIMIT("普通用户限制上传至分布式存储的文件大小为100MB，您可以升级为vip或者存储到服务器"),
    FILE_VIPER_LIMIT("vip用户限制上传至分布式存储的文件大小为200MB，您选择存储到服务器"),

    /**
     * 三级宏观消息：文件收藏
     */
    FILE_COLLECT_ERROR("文件已收藏"),

    /**
     * 三级宏观消息：标签
     */
    LABEL_SENSITIVE("标签包含敏感词"),
    LABEL_EXISTS("标签已存在"),

    /**
     * 三级宏观消息：邮件/邮箱
     */
    MAIL_NOT_OPEN("邮箱开关未打开"),
    MAIL_ERROR("邮箱出现异常了"),
    MAIL_TEMPLATE_ERROR("邮箱模板出现异常了"),

    /**
     * 系统配置
     */
    SYSTEM_LOCK("系统功能已关闭"),

    /**
     * 三级宏观消息：广告、通知
     */
    CONTENT_SENSITIVE("文本包含敏感词"),
    ;

    private final String message;
    private Integer code = 400;

    ResultCodeEnum(String message) {
        this.message = message;
    }

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }
}
