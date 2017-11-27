package cn.tedu.ttms.product.entity;
import java.io.Serializable;
import java.util.Date;
/**
 * 团目实体对象:一个项目可以有个团
 * 序列化专题扩展:
 * 1)transient 关键字修饰的属性默认是否可以被序列化?
 *   不可以,假如我们就是要对使用transient关键字的属性
 *   序列化,你如何实现?
 * 2)类中使用static修饰的属性能否被序列化?不可以
 *   假如希望这个属性被序列化如何实现.
 * 3)Externalizable与Serializable有什么关系?什么
 *   场景下会使用Externalizable对象? 
 * 4)..................
 * */
public class Team implements Serializable{
	private static final long serialVersionUID = 1L;
	private  Integer id;
	private  String name;
	private  Integer projectId;
	private  Integer valid;
	private  String note;
	private  Date createdTime;
	private  Date modifiedTime;
	private  String createdUser;
	private  String modifiedUser;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", projectId=" + projectId + ", valid=" + valid + ", note=" + note
				+ ", modifiedTime=" + createdTime + ", modifiedTime=" + modifiedTime + ", createdUser=" + createdUser
				+ ", modifiedUser=" + modifiedUser + "]";
	}
	
}




