package utils;

public class R {
    private Integer code;
    private String message;
    private Object data;

    public R(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /*
     * 静态方法返回正确状态
     * */
    public static R ok(Object data){
        return new R(Staus.Success.getCode(),Staus.Success.getMessage()).setData(data);
    }

    /*
     * 静态方法返回错误状态
     * */
    public static R err(Integer code,String message){
        return new R(code,message);
    }


    public Integer getCode() {
        return code;
    }

    public R setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public R setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public R setData(Object data) {
        this.data = data;
        return this;
    }


    @Override
    public String toString() {
        return "R{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
