package kr.spring.boot.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {

    public static String uploadFile(String uploadPath, String originalName, byte[] fileData)throws Exception{
    	// 랜덤한 UUID 생성 (파일명 중복 방지)
        UUID uid = UUID.randomUUID();
        // 원본 파일명 앞에 UUID 추가
        String savedName = uid.toString() + "_" + originalName;
        // 연/월/일 폴더 경로 생성
        String savedPath = calcPath(uploadPath);
        // 저장할 파일 객체 생성
        File target = new File(uploadPath + savedPath, savedName);
        // 파일 데이터 복사 (파일 저장)
        FileCopyUtils.copy(fileData, target);
        // 저장된 파일의 경로 문자열 반환
        String uploadFileName = getFileName(savedPath, savedName);
        return uploadFileName;
    } // 파일 업로드

    private static String calcPath(String uploadPath) {
        Calendar cal = Calendar.getInstance();
        String yearPath = File.separator+cal.get(Calendar.YEAR);
        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) +1);
        String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
        makeDir(uploadPath, yearPath, monthPath, datePath);
        return datePath;
    } // 연/월/일 폴더 생성
    
    private static void makeDir(String uploadPath, String... paths) {
    	// 마지막 폴더가 존재하면 종료
        if(new File(paths[paths.length-1]).exists()) {
        	return;
        }
        // 전달 받은 모든 폴더를 경로를 순차적으로 생성
        for(String path : paths) {
            File dirPath = new File(uploadPath + path);
            // 폴더가 존재하지 않으면 생성
            if(!dirPath.exists()) {
            	dirPath.mkdir();
            }
        }
    } // 폴더 생성
    
    private static String getFileName(String path, String fileName)
            throws Exception{
        String iconName = path + File.separator + fileName;
        return iconName.replace(File.separatorChar, '/');
    } // 저장된 파일 경로 반환
    
    public static void deleteFile(String uploadPath, String fi_name) {
		fi_name = fi_name.replace('/', File.separatorChar);
		File file = new File(uploadPath + fi_name);
		//파일이 존재하면 파일을 삭제
		if(file.exists()) {
				file.delete();
		}
    } // 파일 삭제
}
