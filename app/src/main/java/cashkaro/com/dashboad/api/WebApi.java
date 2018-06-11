package cashkaro.com.dashboad.api;

import cashkaro.com.dashboad.model.ResultResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by yasar on 29/8/17.
 */

public interface WebApi {


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("school_list")
    Call<ResultResponse> getSchoolList(@Body String json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("get_visitor_data")
    Call<ResultResponse> getVisitorData(@Body String json);

}
