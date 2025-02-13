package Models;

import lombok.Data;

@Data
public class DeleteUserResponse {
    private int code;
    private String message;
    private int statusCode;
    private String type;
    }
