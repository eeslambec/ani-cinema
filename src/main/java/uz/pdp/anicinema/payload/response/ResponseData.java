package uz.pdp.anicinema.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.ResponseEntity;
import uz.pdp.anicinema.utils.enums.Code;

import static uz.pdp.anicinema.utils.enums.Code.SUCCESS;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"code", "data", "errorMessage", "timestamp"})
public class ResponseData<T> {

    private Code code;

    private T data;

    private String errorMessage;

    private Long timestamp;

    public static <T> ResponseData<T> responseData(Code code, T t) {
        return new ResponseData<>(code, t, null, System.currentTimeMillis());
    }

    public static ResponseData<?> errorResponseData(Code code, String key) {
        return new ResponseData<>(code, null, key, System.currentTimeMillis());
    }

    public static <T> ResponseEntity<ResponseData<T>> ok(Code code, T t) {
        return ResponseEntity.ok(ResponseData.responseData(code, t));
    }

    public static <T> ResponseEntity<ResponseData<T>> success(T t) {
        return ResponseEntity.ok(ResponseData.responseData(SUCCESS, t));
    }
}
