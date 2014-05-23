package com.luckypants.books;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/file")
public class DownloadFileService {
	@GET
	@Path("/download")
	@Produces("image/jpeg")
	public Response downloadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {

		String uploadedFileLocation = "/temp/" + fileDetail.getFileName();
		File file = new File(uploadedFileLocation);
		ResponseBuilder response = Response.ok((Object)file);
		response.header("Content-Disposition", "attachment;");
		return response.build();
		
	}
}