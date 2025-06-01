package kr.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.spring.boot.dao.CommunityDAO;
import kr.spring.boot.model.vo.CommunityVO;
import kr.spring.boot.model.vo.FileVO;
import kr.spring.boot.model.vo.PostVO;
import kr.spring.boot.pagination.PageMaker;
import kr.spring.boot.pagination.PostCriteria;
import kr.spring.boot.utils.UploadFileUtils;

@Service
public class CommunityServiceImp implements CommunityService {

	@Autowired
	private CommunityDAO communityDao;
	
	@Value("${file.upload-dir}")
	String uploadPath;
	
	@Override
	public List<CommunityVO> getCommunityList() {
		return communityDao.selectCommnunityList();
	}

	@Override
	public List<PostVO> getPostList(PostCriteria cri) {
		if(cri == null) {
			return null;
		}
		return communityDao.selectPostList(cri);
	}
	
	@Override
	public String getCommunityName(int co_num) {
		return communityDao.selectCommunityName(co_num);
	}

	@Override
	public PageMaker getPageMaker(PostCriteria cri) {
		if(cri == null) {
			return null;
		}
		int totalCount = communityDao.selectPostCount(cri);
		return new PageMaker(3, cri, totalCount);
	}

	@Override
	public boolean addPost(PostVO post, MultipartFile[] fileList) {
		if(post == null) {
			return false;
		}
		if(post.getPo_title().trim().length() == 0) {
			return false;
		}
		if(post.getPo_content().trim().length() == 0) {
			return false;
		}
		boolean res;
		try {
			res = communityDao.insertPost(post);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if(!res) {
			return false;
		}
		if(fileList == null || fileList.length == 0) {
			return true;
		}
		for(MultipartFile file : fileList) {
			uploadFile(file, post.getPo_num());
		}
		return true;
	}

	private void uploadFile(MultipartFile file, int po_num) {
		if(file == null || file.getOriginalFilename().length() == 0) {
			return;
		}
		try {
			String fi_ori_name = file.getOriginalFilename();
			String fi_path = UploadFileUtils.uploadFile(uploadPath, fi_ori_name, file.getBytes());
			FileVO fileVo = new FileVO(fi_path, fi_ori_name, po_num, "post");
			communityDao.insertFile(fileVo);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
