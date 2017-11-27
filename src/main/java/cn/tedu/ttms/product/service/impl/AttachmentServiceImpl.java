package cn.tedu.ttms.product.service.impl;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.product.dao.AttachmentDao;
import cn.tedu.ttms.product.entity.Attachment;
import cn.tedu.ttms.product.service.AttachmentService;
@Transactional(rollbackFor=ServiceException.class)
@Service
public class AttachmentServiceImpl implements AttachmentService{
	@Autowired
	private AttachmentDao attachmentDao;
	
	@Transactional(readOnly=true)
	@Override
	public Attachment findObjectByDigest(String digest) {
		return attachmentDao.findObjectByDigest(digest);
	}
	@Transactional(readOnly=true)
	@Override
	public List<Attachment> findObjects() {
		return attachmentDao.findObjects();
	}
	@Transactional(propagation=Propagation.REQUIRED,
			isolation=Isolation.REPEATABLE_READ,
			rollbackFor=ServiceException.class)
	@Override
	public void uploadObject(
		String title,
		MultipartFile mFile) {
		//1.判定参数有效性
		if(StringUtils.isEmpty(title))
		throw new ServiceException("标题不能为空");
		if(mFile==null)
		throw new ServiceException("请先选择文件");
		if(mFile.isEmpty())
		throw new ServiceException("文件内容不能为空");
		//2.判定文件是否已上传(数据库是否有对应记录)
		//2.1获取文件中的字节
		String digest=null;
		try{
		byte bytes[]=mFile.getBytes();
		//2.2创建文件摘要(相同文件内容,摘要是一样的)
		digest=DigestUtils.md5DigestAsHex(bytes);
		System.out.println("digest="+digest);
		}catch(Exception e){e.printStackTrace();
		throw new ServiceException("文件摘要创建失败");          
		}
		//2.3根据摘要查询记录判定是否已经上传
	
		Attachment attach=
		attachmentDao.findObjectByDigest(digest);
		if(attach!=null)
		throw new ServiceException("文件已上传");
		//3.文件不存在则存储文件信息到数据库
		Attachment a=new Attachment();
		a.setTitle(title);
		a.setFileName(mFile.getOriginalFilename());
		a.setFileDisgest(digest);
		System.out.println(mFile.getContentType());
		a.setContentType(mFile.getContentType());
		String path="m:/"+mFile.getOriginalFilename();
		a.setFilePath(path);
		attachmentDao.insertObject(a);
		//4.实现上传操作
		try{
		mFile.transferTo(new File(path));
		}catch(IOException e){
		e.printStackTrace();
		throw new ServiceException("上传失败");
		}
		
	}
}







