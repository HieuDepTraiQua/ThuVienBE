//package com.quanly.thuvien.repository;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.security.SecureRandom;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.multipart.MultipartFile;
//
//public class FileRepository {
//
//    static class StringGenerator {
//
//        private static final int DEFAULT_LENGTH = 32;
//        private static final String CHARSET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz=+-_$";
//        private static final SecureRandom RANDOM = new SecureRandom();
//
//        private static String generateFileName() {
//            return generateFileName(DEFAULT_LENGTH);
//        }
//
//        private static String generateFileName(int length) {
//            StringBuilder stringBuilder = new StringBuilder(length);
//            for (int i = 0; i < length; i++) {
//                stringBuilder.append(CHARSET.charAt(RANDOM.nextInt(CHARSET.length())));
//            }
//            return stringBuilder.toString();
//        }
//    }
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(FileRepository.class);
//
//    private static final String UPLOAD_PATH = "uploads";
//
//    private String storePath;
//
//    public FileRepository(String path) {
//        storePath = Paths.get(UPLOAD_PATH, path).toString();
//        try {
//            if (new File(storePath).mkdirs() == true) {
//                LOGGER.info("Created upload folder.");
//            }
//            LOGGER.info("[{}] upload folder is ready.", storePath);
//        } catch (Exception e) {
//            LOGGER.error("Initialization error!", e);
//        }
//    }
//
//    public boolean delete(String filePath) throws Exception {
//        File file = new File(filePath);
//        if (file.exists()) {
//            file.delete();
//        }
//        return true;
//    }
//
//    // TODO: Get file query?
//
//    public String saveOrUpdate(byte[] fileContent, String file) throws Exception {
//        String fileName = null;
//        if (file == null || file.isEmpty()) {
//            String fileExtension = file.substring(file.lastIndexOf("."));
//            fileName = StringGenerator.generateFileName() + fileExtension;
//        }
//        fileName = file.replace(" ", "_");
//        Path filePath = Paths.get(storePath, fileName);
//
//        FileOutputStream stream = new FileOutputStream(filePath.toString());
//        stream.write(fileContent);
//        stream.close();
//        return filePath.toString().replace('\\', '/');
//    }
//
//    public String saveOrUpdate(MultipartFile file, String fileName) throws Exception {
////		String originName = file.getOriginalFilename();
//        return saveOrUpdate(file.getBytes(), fileName);
//    }
//}