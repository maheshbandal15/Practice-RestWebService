package com.mkyong.rest;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/users")
public class UserRestService {

	@GET
	public Response getUser() {
		return Response.status(200).entity("getUser is called").build();
	}

	@GET
	@Path("/vip")
	public Response getUserVIP() {
		return Response.status(200).entity("getUserVIP is called").build();
	}

	@GET
	@Path("{name}")
	public Response getUserByName(@PathParam("name") String name) {
		return Response.status(200).entity("getUserByName is called, name : " + name).build();
	}

	@GET
	@Path("{id : \\d+}")
	public Response getUserById(@PathParam("id") String id) {
		return Response.status(200).entity("getUserById is called, id : " + id).build();
	}

	@GET
	@Path("/username/{username : [a-zA-Z][a-zA-Z_0-9]}")
	public Response getUserByUserName(@PathParam("username") String username) {
		return Response.status(200).entity("getUserByUserName is called, username : " + username).build();
	}

	@GET
	@Path("/books/{isbn : \\d+}")
	public Response getUserBookByISBN(@PathParam("isbn") String isbn) {
		return Response.status(200).entity("getUserBookByISBN is called, isbn : " + isbn).build();
	}

	// ===============@PathParam – Multiple Parameters====================================

	/**
	 * you can use @PathParem to inject the value of URI parameter that defined
	 * in @Path expression, into Java method. ie. value of @Path shall match value
	 * of @PathParam to inject values
	 */
	@GET
	@Path("{year}/{month}/{day}")
	public Response getUserHistory(@PathParam("year") int year, @PathParam("month") int month,
			@PathParam("day") int day) {
		String date = year + "/" + month + "/" + day;
		return Response.status(200).entity("getUserHistory is called, year/month/day : " + date).build();
	}

	// ===============@QueryParam example========================================================

	/**
	 * @QueryParam will convert the query parameter “orderBy=age&orderBy=name” into
	 *             java.util.List automatically. URI Pattern :
	 *             “users/query?from=100&to=200&orderBy=age&orderBy=name”
	 */
	@GET
	@Path("/query")
	public Response getUsers(@QueryParam("from") int from, @QueryParam("to") int to,
			@QueryParam("orderBy") List<String> orderBy) {
		return Response.status(200)
				.entity("getUsers is called, from : " + from + ", to : " + to + ", orderBy" + orderBy.toString())
				.build();
	}

	/**
	 * Alternatively, you can get the query parameters grammatically, via “@Context
	 * UriInfo“. See equivalent version below :
	 */
	@GET
	@Path("/query2")
	public Response getUsers(@Context UriInfo info) {
		String from = info.getQueryParameters().getFirst("from");
		String to = info.getQueryParameters().getFirst("to");
		List<String> orderBy = info.getQueryParameters().get("orderBy");
		return Response.status(200)
				.entity("getUsers is called, from : " + from + ", to : " + to + ", orderBy" + orderBy.toString())
				.build();

	}

	/**
	 * @DefaultValue is good for optional parameter. users/query3
	 */
	@GET
	@Path("/query3")
	public Response getUsersDefault(@DefaultValue("1000") @QueryParam("from") int from,
			@DefaultValue("999") @QueryParam("to") int to,
			@DefaultValue("name") @QueryParam("orderBy") List<String> orderBy) {
		return Response.status(200)
				.entity("getUsers is called, from : " + from + ", to : " + to + ", orderBy" + orderBy.toString())
				.build();
	}

}