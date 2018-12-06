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
   *
   * @return
   */
  @GET("people")
  Call<List<Person>> getAllPeople();

  /**
   * Gets a certain person by their ID
   *
   * @param personId
   * @return
   */
  @GET("people/{personId}")
  Call<Person> getPerson(@Path("personId") long personId);

  /**
   * Updates a certain person by their ID
   *
   * @param personId
   * @param person
   * @return
   */
  @PUT("people/{personId}")
  Call<Person> updatePerson(@Path("personId") long personId, @Body Person person);

  /**
   * Creates a new person
   *
   * @param person
   * @return
   */
  @POST("people")
  Call<Person> addPerson(@Body Person person);

  /**
   * Deletes a certain person
   *
   * @return
   */
  @DELETE("people/{personId}")
  Call<Person> deletePerson(@Path("personId") long personId);

  /**
   * Gets all people near a person
   *
   * @param person
   * @param personId
   * @return
   */
  @GET("people/{personId}/people")
  Call<List<Person>> getAllPeopleNearPerson(@Body Person person, @Path("personId") long personId);

  /**
   * Gets all invitations per user by their ID
   *
   * @param personId
   * @return
   */
  @GET("people/{personId}/invitation")
  Call<List<Invitation>> getAllInvitationsPerPerson(@Path("personId") long personId);

  /**
   * Gets a specific invitation by its ID, that a certain person created
   *
   * @param personId
   * @param person
   * @param invitationId
   * @param invitation
   * @return
   */
  @GET("people/{personId}/invitation/{invitationId}")
  Call<Invitation> getInvitationPerPerson(@Path("personId") long personId, @Body Person person, @Path("invitationId") long invitationId,
      @Body Invitation invitation);

  /**
   * Creates a new invitation by the person
   *
   * @param personId
   * @param invitationId
   * @param invitation
   * @return
   */
  @POST("people/{personId}/invitation")
  Call<Invitation> addInvitation(@Path("personId") long personId, @Path("invitationId") long invitationId,
      @Body Invitation invitation);

  /**
   * Updates a specific invitation, that a person created
   *
   * @param personId
   * @param invitationId
   * @return
   */
  @PUT("people/{personId}/invitation/{invitationId}")
  Call<Invitation> updateInvitation(@Path("personId") long personId, @Path("invitationId") long invitationId);

  /**
   * Deletes a specific invitation that a person created
   *
   * @param personId
   * @param invitationId
   * @return
   */
  @DELETE("people/{personId}/invitation/{invitationId}")
  Call<Invitation> deleteInvitation(@Path("personId") long personId, @Path("invitationId") long invitationId);

  /**
   * Gets all invitations
   *
   * @return
   */
  @GET("invitations")
  Call<List<Invitation>> getAllInvitations();

  /**
   * Gets a specific invitation
   *
   * @param invitationId
   * @return
   */
  @GET("invitations/{invitationId}")
  Call<Invitation> getInvitation(@Path("invitationId") long invitationId);

  /**
   * Gets all people associated with an invitation
   *
   * @param invitationId
   * @param invitation
   * @return
   */
  @GET("invitations/{invitationId}/people")
  Call<List<Invitation>> getPeoplePerInvitation(@Path("invitaitonId") long invitationId, @Body Invitation invitation);
}
