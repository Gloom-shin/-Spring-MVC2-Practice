package hello.upload.file;

import hello.upload.domain.UploadFile;
import lombok.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if(!multipartFile.isEmpty()){
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return null;
        }
        String originalFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreName(originalFileName);
        // 파일 복사 저장
        multipartFile.transferTo(new File(storeFileName));
        return new UploadFile(originalFileName, storeFileName);

    }

    private String createStoreName(String originalFileName) {
        String ext = extractExt(originalFileName);
        String uuid = UUID.randomUUID().toString();
        return uuid+"."+ ext;
    }

    private String extractExt(String originalFileName) {
        int pos = originalFileName.indexOf(".");
        return originalFileName.substring(pos+1);
    }


}
