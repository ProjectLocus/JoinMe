package edu.cnm.deepdive.joinme.view;

import edu.cnm.deepdive.joinme.model.entity.Person;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JoinMeBackEndService {

  @GET("vertices/people")
  Call<List<Person>> getAllVerticiesPeople(@Header("Authorization") String authorization);

  @PUT("person/{personId}")
  Call<Person> updatePerson(@Header("Authorization") String authorization,
      @Path("personId") int personId, @Body Person person);

  @POST("person")
  Call<Person> addPerson(@Header("Authorization") String authorization, @Body Person person);


}
