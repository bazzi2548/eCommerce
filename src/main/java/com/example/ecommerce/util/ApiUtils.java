//package com.example.ecommerce.util;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;
//import org.apache.commons.lang3.builder.ToStringBuilder;
//import org.apache.commons.lang3.builder.ToStringStyle;
//import org.springframework.http.HttpStatus;
//
//// 웹에서 보내는 API응담을 일관되게 만들어주는 클래스
//public class ApiUtils {
//
//    public static <T> ApiResult<T> success(T response) { // 성공적인 api응답 반환
//        return new ApiResult<>(true, response, null);
//    }
//
//    public static ApiResult<?> error(String message, HttpStatus status) { // api 호출 실패
//        return new ApiResult<>(false, null, new ApiError(message, status.value()));
//    }
//
//    @Getter
//    @Setter
//    @AllArgsConstructor
//    public static class ApiResult<T> { // 제네릭으로 응답을 어떤형태로든 받아올 수있음 -> 재사용성 높임
//        private final boolean success; // 성공 또는 실패
//        private final T response;
//        private final ApiError error;
//
//        @Override
//        public String toString() {
//            return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
//                    .append("success", success)
//                    .append("response", response)
//                    .append("error", error)
//                    .toString();
//        }
//    }
//
//    @Getter @Setter @AllArgsConstructor
//    public static class ApiError {
//        private final String message;
//        private final int status;
//
//        @Override
//        public String toString() {
//            return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
//                    .append("message", message)
//                    .append("status", status)
//                    .toString();
//        }
//    }
//}
