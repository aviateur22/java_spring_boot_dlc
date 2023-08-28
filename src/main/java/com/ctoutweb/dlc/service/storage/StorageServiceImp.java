package com.ctoutweb.dlc.service.storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ctoutweb.dlc.entity.ProductEntity;
import com.ctoutweb.dlc.exception.custom.FileException;
import com.ctoutweb.dlc.model.product.SaveProductRequest;

@Service
public class StorageServiceImp implements StorageService {
	
	private final Path root = Paths.get("uploads");	

	@Override
	public ProductEntity saveFile(SaveProductRequest productRequest, int userId) {
		
		try {	
			
			Instant createdAt = Instant.now();
			MultipartFile file = productRequest.getFile();
			String fileExtension = this.getFileExtension(file);
			String fileName = this.generateUUIDName(file.getOriginalFilename()) + "." + fileExtension;
			Path filePath = this.root.resolve(fileName);			
			Files.copy(file.getInputStream(), filePath);
			
			return ProductEntity.builder()
					.withCreatedAt(Timestamp.from(createdAt))
					.withFileExtension(fileExtension)
					.withFileName(fileName)
					.withPath(this.getFilePath(fileName))
					.withProductOpenDate(productRequest.getProductOpenDate())
					.withProductEndDate(productRequest.getProductEndDate())
					.withUserId(userId)
					.build();
			
		} catch (Exception e) {
			
			if (e instanceof FileAlreadyExistsException) {				
		        throw new FileException("nom de fichier déja présent");
		    }			
			
			if(e instanceof FileException) {
				throw new FileException(e.getMessage());
			}
			
			if(e instanceof NoSuchFileException) {
				throw new FileException("impossible de sauvegarder le fichier");
			}
			e.printStackTrace();
		     throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void deleteFile(String fileName) {
		try {			
			Path filePath = this.root.resolve(fileName);			
			Files.delete(filePath);
			
		} catch (Exception e) {					
			
			if(e instanceof FileException) {
				throw new FileException(e.getMessage());
			} else if(e instanceof NoSuchFileException) {
				
			} else {
			
				e.printStackTrace();
			     throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	public String getImageInBase64Format(String fileName) {			
		try {
			Path filePath = this.root.resolve(fileName);	
			byte[] imageByte = Files.readAllBytes(filePath);			
			return Base64.getEncoder().encodeToString(imageByte);
			
		} catch(Exception ex) {
			if(ex instanceof FileException) {	
				return "no-file-data";				
				
			} else if(ex instanceof NoSuchFileException) {				
				return "no-file-data";
				
			} else if(ex instanceof IOException)  {							
				return "no-file-data";
				
			} else {				
				return "no-file-data";
			}
		}		
	}
	
	@SuppressWarnings("null")
	private String getFileExtension(MultipartFile file) throws IOException {
		InputStream fileStream = file.getInputStream();		
		byte[] magicBytes = new byte[3];		
		fileStream.read(magicBytes);
		fileStream.close();		
		
		switch(file.getContentType().toLowerCase()) {
			case "application/pdf": {
				if(!FileType.PDF.is(magicBytes)) throw new FileException("le fichier n'est pas un valide PDF");
				return "pdf";
				
			}			
			
			case "image/png": {
				if(!FileType.PNG.is(magicBytes)) throw new FileException("le fichier n'est pas un valid PNG");
				return "png";
			}			
			
			case "image/jpeg": {
				if(!FileType.JPEG.is(magicBytes)) throw new FileException("le fichier n'est pas un valid JPEG");
				return "jpg";
			}
						
			default: throw new FileException("le fichier n'est pas un valide");
		}

	}
	
	private String generateUUIDName(String fileOriginalName) {
		final long currentTimeMillis = System.currentTimeMillis();
		return String.valueOf(UUID.nameUUIDFromBytes((fileOriginalName + " " + currentTimeMillis).getBytes()));
	}
	
	private String getFilePath(String fileName) {
		try {
			Path path = root.resolve(fileName);
			Resource resource = new UrlResource(path.toUri());
			
			if(!resource.exists() || !resource.isReadable()) {
				return "unkow_file_url";
			}
			
			return resource.getURI().toString();
				
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "unkow_file_url";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "unkow_file_url";
		}
		
		
	}
}