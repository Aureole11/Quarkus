package com.quark.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.specimpl.BuiltResponseEntityNotBacked;

@Path("/mobile")
public class MobileResource {

	List<String> mobileList = new ArrayList<>();
	
  /**
   * 
   * @return
   */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getMobileList(){
		return Response.ok(mobileList).build();
	}
	
	/**
	 * This method add a new mobile name in list when we hit /mobile url with POST request
	 * @param MobileName
	 */
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addNewMobile(String MobileName) {
		mobileList.add(MobileName);
		return Response.ok(MobileName).build();
//		return "Added mobile in the list";
	}
	
	@PUT
	@Path("/{oldmobilename}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateMobile(@PathParam("oldmobilename") String oldMobileName, @QueryParam("newmobilename") String newMobileName) {
		mobileList=mobileList.stream().map(mobile ->{
			if(mobile.equals(oldMobileName)) {
				return newMobileName;
			}else {
				return mobile;
			}
		}).collect(Collectors.toList());
		return Response.ok(mobileList).build();
	}
	
	@DELETE
	@Path("/{mobiletodelete}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteMobile(@PathParam("mobiletodelete") String mobileName) {
		boolean isRemoved = mobileList.remove(mobileName);
		if(isRemoved) {
//			return Response.noContent().build();
			return Response.ok(mobileList).build();
		}else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}
