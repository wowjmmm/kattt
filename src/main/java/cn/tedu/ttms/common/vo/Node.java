package cn.tedu.ttms.common.vo;
import java.io.Serializable;
/**此对象可以看成是树结构中的一个节点对象
 * 用于封装具体的节点信息*/
public class Node implements Serializable{
	private static final long serialVersionUID = -1253271724374377858L;
	private Integer id;
	private String name;
	private Integer parentId;
	
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
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
    
	
	
}
