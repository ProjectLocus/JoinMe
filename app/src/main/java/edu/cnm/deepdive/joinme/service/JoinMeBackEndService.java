package edu.cnm.deepdive.joinme.service;

import edu.cnm.deepdive.joinme.model.entity.Invitation;
import edu.cnm.deepdive.joinme.model.entity.Person;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JoinMeBackEndService {

  /**
   * Gets all People
   * @param authorization
   * @return
   */
  @GET("people")
  Call<List<Person>> getAllPeople(@Header("Authorization") String authorization);

  /**
   * Gets a certain person by their ID
   * @param authorization
   * @param personId
   * @return
   */
  @GET("people/{personId}")
  Call<Person> getPerson(@Header("Authorization") String authorization,
      @Path("personId") long personId);

  /**
   * Updates a certain person by their ID
   * @param authorization
   * @param personId
   * @param person
   * @return
   */
  @PUT("people/{personId}")
  Call<Person> updatePerson(@Header("Authorization") String authorization,
      @Path("personId") long personId, @Body Person person);

  /**
   * Creates a new person
   * @param authorization
   * @param person
   * @return
   */
  @POST("people")
  Call<Person> addPerson(@Header("Authorization") String authorization, @Body Person person);

  /**
   * Deletes a certain person
   * @param authorization
   * @return
   */
  @DELETE("people/{personId}")
  Call<Person> deletePerson(@Header("Authorization") String authorization,
      @Path("personId") long personId);

  /**
   * Gets all people near a person
   * @param authorization
   * @param person
   * @param personId
   * @return
   */
  @GET("people/{personId}/people")
  Call<List<Person>> getAllPeopleNearPerson(@Header("Authorization") String authorization,
      @Body Person person, @Path("personId") long personId);

  /**
   * Gets all invitations per user by their ID
   * @param Authorization
   * @param personId
   * @return
   */
  @GET("people/{personId}/invitation")
  Call<List<Invitation>> getAllInvitationsPerPerson(@Header("Authorization") String Authorization,
      @Path("personId") long personId);

  /**
   * Gets a specific invitation by its ID, that a certain person created
   * @param authorization
   * @param personId
   * @param person
   * @param invitationId
   * @param invitation
   * @return
   */
  @GET("people/{personId}/invitation/{invitationId}")
  Call<Invitation> getInvitationPerPerson(@Header("Authorization") String authorization,
      @Path("personId") long personId, @Body Person person, @Path("invitationId") long invitationId,
      @Body Invitation invitation);

  /**
   * Creates a new invitation by the person
   * @param authorization
   * @param personId
   * @param invitationId
   * @param invitation
   * @return
   */
  @POST("people/{personId}/invitation")
  Call<Invitation> addInvitation(@Header("Authorization") String authorization,
      @Path("personId") long personId, @Path("invitationId") long invitationId,
      @Body Invitation invitation);

  /**
   * Updates a specific invitation, that a person created
   * @param authorization
   * @param personId
   * @param invitationId
   * @return
   */
  @PUT("people/{personId}/invitation/{invitationId}")
  Call<Invitation> updateInvitation(@Header("Authorization") String authorization,
      @Path("personId") long personId, @Path("invitationId") long invitationId);

  /**
   * Deletes a specific invitation that a person created
   * @param authorization
   * @param personId
   * @param invitationId
   * @return
   */
  @DELETE("people/{personId}/invitation/{invitationId}")
  Call<Invitation> deleteInvitation(@Header("Authorization") String authorization,
      @Path("personId") long personId, @Path("invitationId") long invitationId);

  /**
   * Gets all invitations
   * @param authorization
   * @return
   */
  @GET("invitations")
  Call<List<Invitation>> getAllInvitations(@Header("Authorization") String authorization);

  /**
   * Gets a specific invitation
   * @param authorization
   * @param invitationId
   * @return
   */
  @GET("invitations/{invitationId}")
  Call<Invitation> getInvitation(@Header("Authorization") String authorization,
      @Path("invitationId") long invitationId);

  /**
   * Gets all people associated with an invitation
   * @param authorization
   * @param invitationId
   * @param invitation
   * @return
   */
  @GET("invitations/{invitationId}/people")
  Call<List<Invitation>> getPeoplePerInvitation(@Header("Authorization") String authorization,
      @Path("invitaitonId") long invitationId, @Body Invitation invitation);
}
