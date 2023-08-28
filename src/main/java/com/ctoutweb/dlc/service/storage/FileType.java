package com.ctoutweb.dlc.service.storage;

import com.ctoutweb.dlc.exception.custom.FileException;

public enum FileType {
	JPEG(0xFF, 0xD8, 0xFF), 
	PDF(0x25, 0x50, 0x44),
	PNG(0x89,0x50,0x4E);
	
	private final int[] magicBytes;
	
	private FileType(int ...bytes) {
		this.magicBytes = bytes;		
	}
	
	// Checks if bytes match a specific magic bytes sequence
    public boolean is(byte[] bytes) {
    	
        if (bytes.length != magicBytes.length) {
        	throw new FileException("impossible de vérifier le fichier envoyé");
        }
        	
        for (int i=0; i<bytes.length; i++)
            if (Byte.toUnsignedInt(bytes[i]) != magicBytes[i])
                return false;
        return true;
    }
}
