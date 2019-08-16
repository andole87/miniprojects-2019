package com.woowacourse.zzinbros.file;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UploadFile {
    private static final Logger logger = LoggerFactory.getLogger(UploadFile.class);
    private static final String PATH = "/Users/jyk/desktop/tt";
    private MultipartFile file;

    public UploadFile() {
    }

    public UploadFile(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getOriginName() {
        return file.getOriginalFilename();
    }

    public String getFileExtention() {
        return FilenameUtils.getExtension(getOriginName());
    }

    public void save() {
        try {
            file.transferTo(new File(PATH + "/" + hashFile() + "." + getFileExtention()));
        } catch (IOException e) {
            logger.warn("IOException " + e.getMessage());
            throw new IllegalArgumentException();
        }
    }

    private String hashFile() {
        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            throw new IllegalArgumentException();
        }

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(fileName.getBytes("UTF-8"), 0, fileName.length());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            logger.warn("Could not find hash algorithm", e);
            return fileName;
        } catch (UnsupportedEncodingException e) {
            logger.warn("Could not encode text", e);
            return fileName;
        }
    }

}
